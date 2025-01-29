package fi.interjektio.dev.idea.sway.lang.highlighting

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType
import fi.interjektio.dev.idea.sway.lang.lexer.SwayLexer
import fi.interjektio.dev.idea.sway.lang.psi.SwayTypes

class SwaySyntaxHighlighter : SyntaxHighlighterBase() {
    override fun getHighlightingLexer(): Lexer = SwayLexer()

    override fun getTokenHighlights(tokenType: IElementType?): Array<TextAttributesKey> = when (tokenType) {
        SwayTypes.KEYWORD -> arrayOf(KEYWORD)
        SwayTypes.IDENTIFIER -> arrayOf(IDENTIFIER)
        SwayTypes.STRING -> arrayOf(STRING)
        SwayTypes.NUMBER -> arrayOf(NUMBER)
        SwayTypes.COMMENT -> arrayOf(COMMENT)
        SwayTypes.OPERATOR -> arrayOf(OPERATOR)
        SwayTypes.TYPE -> arrayOf(TYPE)
        else -> emptyArray()
    }

    companion object {
        val KEYWORD = TextAttributesKey.createTextAttributesKey("SWAY_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD)
        val IDENTIFIER = TextAttributesKey.createTextAttributesKey("SWAY_IDENTIFIER", DefaultLanguageHighlighterColors.IDENTIFIER)
        val STRING = TextAttributesKey.createTextAttributesKey("SWAY_STRING", DefaultLanguageHighlighterColors.STRING)
        val NUMBER = TextAttributesKey.createTextAttributesKey("SWAY_NUMBER", DefaultLanguageHighlighterColors.NUMBER)
        val COMMENT = TextAttributesKey.createTextAttributesKey("SWAY_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT)
        val OPERATOR = TextAttributesKey.createTextAttributesKey("SWAY_OPERATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN)
        val TYPE = TextAttributesKey.createTextAttributesKey("SWAY_TYPE", DefaultLanguageHighlighterColors.CLASS_NAME)
    }
}