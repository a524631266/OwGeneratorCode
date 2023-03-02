package com.github.a524631266.owgeneratorcode.window;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import org.jetbrains.annotations.NotNull;
import javax.swing.*;
import java.awt.*;


/**
 * @author : Liangliang.Zhang4
 * @version : 1.0
 * @date : 2023/2/23
 */
public class ToolWindowJavaFxView implements ToolWindowFactory {
    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        JComponent component = toolWindow.getComponent();

        final JFXPanel fxPanel = new JFXPanel();

        fxPanel.setVisible(true);
        fxPanel.setSize(500, 300);
        fxPanel.setBackground(Color.CYAN);
        component.getParent().add(fxPanel);

        Platform.runLater(() -> {
            Button button = new Button("点击时间");
            Scene scene = new Scene(button);
            fxPanel.setScene(scene);
        });

    }
}
