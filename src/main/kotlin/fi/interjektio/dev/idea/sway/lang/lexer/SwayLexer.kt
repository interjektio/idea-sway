package fi.interjektio.dev.idea.sway.lang.lexer

import com.intellij.lexer.LexerBase
import com.intellij.psi.tree.IElementType
import fi.interjektio.dev.idea.sway.lang.psi.SwayTypes
import com.intellij.psi.TokenType

class SwayLexer : LexerBase() {
    private var buffer: CharSequence = ""
    private var startOffset: Int = 0
    private var endOffset: Int = 0
    private var state: Int = 0
    private var currentToken: IElementType? = null

    private val keywords = setOf(
        "fn", "let", "mut", "struct", "enum", "impl", "trait", "pub",
        "mod", "const", "return", "true", "false"
    )

    override fun start(buffer: CharSequence, startOffset: Int, endOffset: Int, initialState: Int) {
        this.buffer = buffer
        this.startOffset = startOffset
        this.endOffset = startOffset
        this.state = initialState
        advance()
    }

    override fun getState(): Int = state

    override fun getTokenType(): IElementType? = currentToken

    override fun getTokenStart(): Int = startOffset

    override fun getTokenEnd(): Int = endOffset

    override fun advance() {
        if (endOffset >= buffer.length) {
            currentToken = null
            return
        }

        startOffset = endOffset
        val char = buffer[startOffset]

        when {
            char.isWhitespace() -> {
                while (endOffset < buffer.length && buffer[endOffset].isWhitespace()) {
                    endOffset++
                }
                currentToken = TokenType.WHITE_SPACE
            }

            char == '/' && endOffset + 1 < buffer.length && buffer[endOffset + 1] == '/' -> {
                while (endOffset < buffer.length && buffer[endOffset] != '\n') {
                    endOffset++
                }
                currentToken = SwayTypes.COMMENT
            }

            char == '"' -> {
                endOffset++
                while (endOffset < buffer.length && buffer[endOffset] != '"') {
                    endOffset++
                }
                if (endOffset < buffer.length) {
                    endOffset++
                }
                currentToken = SwayTypes.STRING
            }

            char.isDigit() -> {
                while (endOffset < buffer.length && buffer[endOffset].isDigit()) {
                    endOffset++
                }
                currentToken = SwayTypes.NUMBER
            }

            char.isLetter() || char == '_' -> {
                while (endOffset < buffer.length && (buffer[endOffset].isLetterOrDigit() || buffer[endOffset] == '_')) {
                    endOffset++
                }
                val word = buffer.subSequence(startOffset, endOffset).toString()
                currentToken = if (word in keywords) SwayTypes.KEYWORD else SwayTypes.IDENTIFIER
            }

            char in "+-*/=<>!&|%" -> {
                endOffset++
                currentToken = SwayTypes.OPERATOR
            }

            else -> {
                endOffset++
                currentToken = TokenType.BAD_CHARACTER
            }
        }
    }

    override fun getBufferSequence(): CharSequence = buffer

    override fun getBufferEnd(): Int = buffer.length
}