package com.github.a524631266.owgeneratorcode.window;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.a524631266.owgeneratorcode.utils.VideoToGIf;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author : Liangliang.Zhang4
 * @version : 1.0
 * @date : 2023/3/2
 */
public class ToolWindowUtil2 {
    private JTextArea outputArea;
    private JButton jsonParseButton;
    private JButton timestempButton;
    private JComboBox timeSelect;
    private JPanel jPanel;
    private JTabbedPane tabbedPane1;
    private JTextField timeInput;
    private JTextArea timeOutPut;
    private JButton inputFileSelect;
    private JTextField inputFilePath;
    private JTextField outputFilePath;
    private JButton outputFIleSelect;
    private JTextField jsonInputArea;
    private JPanel picPanel;
    private JLabel picture;
    private JButton transferGif;
    private JSlider frameRateSlider;
    private JSlider scaleSlider;
    private JTextField scaleText;
    private JTextField frameRateText;

    public ToolWindowUtil2(Project project, ToolWindow toolWindow) {

        jsonParseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = jsonInputArea.getText();
                outputArea.setText(JSONObject.toJSONString(JSONObject.parseObject(text), SerializerFeature.PrettyFormat));
            }
        });

        timestempButton.addActionListener(e -> {
            String selectedItem = (String) timeSelect.getSelectedItem();
            if ("秒".equals(selectedItem)) {
                String text = timeInput.getText();
                timeOutPut.setText(DateUtil.date(Long.valueOf(text) * 1000).toString("yyyy-MM-dd HH:mm:ss"));
            } else if ("毫秒".equals(selectedItem)) {
                String text = timeInput.getText();
                timeOutPut.setText(DateUtil.date(Long.valueOf(text)).toString("yyyy-MM-dd HH:mm:ss"));
            }
        });


        inputFileSelect.addActionListener( e -> {
//            Platform.runLater(new Runnable() {
//                @Override
//                public void run() {
//                    //javaFX operations should go here
//
//                    Stage stage = new Stage();
//                    stage.show();
//                    FileChooser fileChooser = new FileChooser();
//
//                    File file1 = fileChooser.showOpenDialog(stage);
//
//                    System.out.println(file1.getName());
//                    inputFilePath.setText(file1.getAbsolutePath());
//                }
//            });

            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(null);
            File file = chooser.getSelectedFile();
            System.out.println(file.getName());
            inputFilePath.setText(file.getAbsolutePath());
        });

        outputFIleSelect.addActionListener( e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.showOpenDialog(null);
            File file = chooser.getSelectedFile();
            System.out.println(file.getName());
            outputFilePath.setText(file.getAbsolutePath());
        });

        transferGif.addActionListener( e -> {
            transferGif.setEnabled(false);
            String vedioFilePath = inputFilePath.getText();
            String opFilePath = outputFilePath.getText();
            String gifFileName = VideoToGIf.getNewFileName(vedioFilePath);
            String gifPath = opFilePath + File.separator + gifFileName;
            Double scale = BigDecimal.valueOf(scaleSlider.getValue() / 50.0).setScale(1, RoundingMode.HALF_UP).doubleValue();
            Integer frameRate = frameRateSlider.getValue();
            VideoToGIf.transform(new VideoToGIf.VideoParam(vedioFilePath, gifPath, scale, frameRate));
            ImageIcon img = new ImageIcon(gifPath);
            img.setImage(img.getImage().getScaledInstance(picture.getWidth(),picture.getHeight(), Image.SCALE_DEFAULT));
            picture.setIcon(img);
            transferGif.setEnabled(true);
        });

        updateFrameRateText();
        updateScaleText();
        frameRateSlider.addChangeListener(e -> {
            updateFrameRateText();
        });
        scaleSlider.addChangeListener(e -> {
            updateScaleText();
        });
    }

    private void updateScaleText() {
        int scale = scaleSlider.getValue();
        scaleText.setText(BigDecimal.valueOf(scale / 50.0).setScale(1, RoundingMode.HALF_UP).toPlainString());
    }

    private void updateFrameRateText() {
        int frameRate = frameRateSlider.getValue();
        frameRateText.setText(frameRate + "");
    }

    public JPanel getPanel() {
        return jPanel;
    }

}
