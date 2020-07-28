package com.github.lanahra.modifierutils.actions

import com.github.lanahra.modifierutils.MODIFIER_STATIC
import com.github.lanahra.modifierutils.PsiInSelectionElementVisitor
import com.intellij.openapi.editor.Caret
import com.intellij.psi.PsiField

class ToggleFieldStaticModifierAction : ToggleElementModifierAction<PsiField>() {
    override fun modifier(): String {
        return MODIFIER_STATIC
    }

    override fun visitor(caret: Caret): PsiInSelectionElementVisitor<PsiField> {
        return PsiInSelectionElementVisitor(caret.selectionStart, caret.selectionEnd)
    }
}
