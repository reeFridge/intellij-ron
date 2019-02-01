package org.ronrs.ron.lang

import com.intellij.openapi.fileTypes.LanguageFileType
import org.ronrs.ron.ide.icons.RONIcons
import javax.swing.Icon

class RONFileType : LanguageFileType(RONLanguage.INSTANCE) {
    override fun getName(): String = "RON"

    override fun getDescription(): String = "RON file"

    override fun getDefaultExtension(): String = "ron"

    override fun getIcon(): Icon? = RONIcons.FILE

    companion object {
        val INSTANCE = RONFileType()
    }
}