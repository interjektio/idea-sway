package fi.interjektio.dev.idea.sway.lang

import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon
import com.intellij.openapi.util.IconLoader

object SwayFileType : LanguageFileType(SwayLanguage) {

    override fun getName(): String = "Sway"

    override fun getDescription(): String = "Sway smart contract file"

    override fun getDefaultExtension(): String = "sw"

    override fun getIcon(): Icon = IconLoader.getIcon("/icons/fuel.svg", SwayFileType::class.java)
}