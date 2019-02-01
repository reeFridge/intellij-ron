package org.ronrs.ron.lang.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import org.ronrs.ron.lang.RONFileType
import org.ronrs.ron.lang.RONLanguage

class RONFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, RONLanguage.INSTANCE) {
    override fun getFileType(): FileType = RONFileType.INSTANCE

    override fun toString(): String = "RONFile:${this.name}"
}