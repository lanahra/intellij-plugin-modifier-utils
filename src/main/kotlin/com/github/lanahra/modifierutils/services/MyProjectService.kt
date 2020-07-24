package com.github.lanahra.modifierutils.services

import com.intellij.openapi.project.Project
import com.github.lanahra.modifierutils.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
