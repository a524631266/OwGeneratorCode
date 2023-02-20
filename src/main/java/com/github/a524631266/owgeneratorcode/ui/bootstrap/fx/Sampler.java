package com.github.a524631266.owgeneratorcode.ui.bootstrap.fx;

import com.intellij.openapi.project.Project;
import javafx.application.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Sampler extends Application{
//    Project project;
    public Sampler(Project project) {
//        this.project = project;
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        Button button = new Button("zll");
        BorderPane borderPane = new BorderPane(button);
        Scene scene = new Scene(borderPane, 300, 300);
        scene.getRoot().setStyle("-fx-font-family: 'serif'");
        primaryStage.setScene(scene);
        primaryStage.setTitle("my");
        primaryStage.show();
    }
}
