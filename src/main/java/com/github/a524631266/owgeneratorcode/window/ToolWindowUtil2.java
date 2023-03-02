package com.github.a524631266.owgeneratorcode.window;

import cn.hutool.core.date.DateUtil;
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
 * @date : 2023/3/2
 */
public class ToolWindowUtil2 {
    private JTextArea inputArea;
    private JTextArea outputArea;
    private JButton jsonParseButton;
    private JButton timestempButton;
    private JComboBox timeSelect;
    private JPanel jPanel;

    public ToolWindowUtil2(Project project, ToolWindow toolWindow) {

        jsonParseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = inputArea.getText();
                outputArea.setText(JSONObject.toJSONString(JSONObject.parseObject(text), SerializerFeature.PrettyFormat));
            }
        });

        timestempButton.addActionListener(e -> {
            String selectedItem = (String) timeSelect.getSelectedItem();
            if ("秒".equals(selectedItem)) {
                String text = inputArea.getText();
                outputArea.setText(DateUtil.date(Long.valueOf(text) * 1000).toString("yyyy-MM-dd HH:mm:ss"));
            } else if ("毫秒".equals(selectedItem)) {
                String text = inputArea.getText();
                outputArea.setText(DateUtil.date(Long.valueOf(text)).toString("yyyy-MM-dd HH:mm:ss"));
            }
        });

    }

    public JPanel getPanel() {
        return jPanel;
    }
}
