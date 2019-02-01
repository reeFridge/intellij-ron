package org.ronrs.ron.lang

import com.intellij.lang.Language
import com.intellij.openapi.fileTypes.LanguageFileType

class RONLanguage : Language("RON", "text/ron") {
    override fun getAssociatedFileType(): LanguageFileType? = RONFileType.INSTANCE

    companion object {
        val INSTANCE = RONLanguage()
    }
}