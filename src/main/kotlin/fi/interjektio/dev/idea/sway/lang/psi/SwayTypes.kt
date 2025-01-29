package fi.interjektio.dev.idea.sway.lang.psi

import com.intellij.psi.tree.IElementType
import fi.interjektio.dev.idea.sway.lang.SwayLanguage

class SwayTokenType(debugName: String) : IElementType(debugName, SwayLanguage)

object SwayTypes {
    @JvmField val KEYWORD = SwayTokenType("KEYWORD")
    @JvmField val IDENTIFIER = SwayTokenType("IDENTIFIER")
    @JvmField val NUMBER = SwayTokenType("NUMBER")
    @JvmField val STRING = SwayTokenType("STRING")
    @JvmField val COMMENT = SwayTokenType("COMMENT")
    @JvmField val OPERATOR = SwayTokenType("OPERATOR")
    @JvmField val TYPE = SwayTokenType("TYPE")
}