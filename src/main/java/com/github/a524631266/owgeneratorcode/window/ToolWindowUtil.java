package com.github.a524631266.owgeneratorcode.window;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;

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
    private JButton button1;
    private JPanel panel;


    public ToolWindowUtil(Project project, ToolWindow toolWindow) {

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = inputArea.getText();
                outputArea.setText(JSONObject.toJSONString(JSONObject.parseObject(text), SerializerFeature.PrettyFormat));
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }
}
