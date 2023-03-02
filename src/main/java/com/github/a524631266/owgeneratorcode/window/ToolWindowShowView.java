package com.github.a524631266.owgeneratorcode.window;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowAnchor;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.ui.content.ContentManager;
import com.intellij.ui.content.impl.ContentImpl;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * @author : Liangliang.Zhang4
 * @version : 1.0
 * @date : 2023/2/23
 */
public class ToolWindowShowView implements ToolWindowFactory {
    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        ToolWindowAnchor anchor = toolWindow.getAnchor();
        Icon icon = toolWindow.getIcon();
        String id = toolWindow.getId();
        System.out.println("id:" + id);
        ToolWindowUtil2 toolWindowUtil = new ToolWindowUtil2(project, toolWindow);
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(toolWindowUtil.getPanel(), "", false);
        toolWindow.getContentManager().addContent(content);

    }
}
