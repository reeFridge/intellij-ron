package org.ronrs.ron.file

import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.IStubFileElementType
import org.ronrs.ron.lang.RONFileStub
import org.ronrs.ron.lang.RONLanguage

class RONFileElementType : IStubFileElementType<RONFileStub>(RONLanguage.INSTANCE) {
    companion object {
        var INSTANCE: IFileElementType = RONFileElementType()
    }
}