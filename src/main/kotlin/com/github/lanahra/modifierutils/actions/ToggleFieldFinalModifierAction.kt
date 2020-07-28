package com.github.lanahra.modifierutils.actions

import com.github.lanahra.modifierutils.MODIFIER_FINAL
import com.github.lanahra.modifierutils.PsiInSelectionElementVisitor
import com.intellij.openapi.editor.Caret
import com.intellij.psi.PsiField

class ToggleFieldFinalModifierAction : ToggleElementModifierAction<PsiField>() {
    override fun modifier(): String {
        return MODIFIER_FINAL
    }

    override fun visitor(caret: Caret): PsiInSelectionElementVisitor<PsiField> {
        return PsiInSelectionElementVisitor(caret.selectionStart, caret.selectionEnd)
    }
}