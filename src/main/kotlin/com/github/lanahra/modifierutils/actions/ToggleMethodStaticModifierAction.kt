package com.github.lanahra.modifierutils.actions

import com.github.lanahra.modifierutils.MODIFIER_STATIC
import com.github.lanahra.modifierutils.PsiInSelectionElementVisitor
import com.intellij.openapi.editor.Caret
import com.intellij.psi.PsiMethod

class ToggleMethodStaticModifierAction : ToggleElementModifierAction<PsiMethod>() {
    override fun modifier(): String {
        return MODIFIER_STATIC
    }

    override fun visitor(caret: Caret): PsiInSelectionElementVisitor<PsiMethod> {
        return PsiInSelectionElementVisitor(caret.selectionStart, caret.selectionEnd)
    }
}
