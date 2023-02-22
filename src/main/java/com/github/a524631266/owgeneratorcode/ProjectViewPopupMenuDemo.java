package com.github.a524631266.owgeneratorcode;

import com.github.a524631266.owgeneratorcode.action.ActionUtils;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;

public class ProjectViewPopupMenuDemo extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here
        Project project = e.getProject();
        ActionUtils.notify(project.getName());
        // 使用javax制作ui
//        new TestCaseConfigUI().showUI(project);
        // 使用javafx制作ui
//        Application.launch(Sampler.class, "aa");
    }
}
