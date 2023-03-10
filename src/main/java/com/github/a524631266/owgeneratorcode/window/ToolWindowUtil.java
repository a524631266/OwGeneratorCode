package com.github.a524631266.owgeneratorcode.window;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.a524631266.owgeneratorcode.ui.java.fx.MarqueeWindow;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import javafx.application.Application;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author : Liangliang.Zhang4
 * @version : 1.0
 * @date : 2023/2/23
 */
public class ToolWindowUtil {

    private JTextArea inputArea;
    private JTextArea outputArea;
    private JButton jsonParseButton;
    private JButton fxButton;
    private JComboBox timeUnitSelect;
    private JButton timestempButton;
    private JPanel panel;


    public ToolWindowUtil(Project project, ToolWindow toolWindow) {

        jsonParseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = inputArea.getText();
                outputArea.setText(JSONObject.toJSONString(JSONObject.parseObject(text), SerializerFeature.PrettyFormat));
            }
        });

        timestempButton.addActionListener(e -> {
            Object selectedItem = timeUnitSelect.getSelectedItem();
            System.out.println(selectedItem);
        });

        fxButton.addActionListener(action -> {
            Application.launch(MarqueeWindow.class);
        });
    }

    public JPanel getPanel() {
        return panel;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
