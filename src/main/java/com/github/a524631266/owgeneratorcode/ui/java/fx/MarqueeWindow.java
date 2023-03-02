package com.github.a524631266.owgeneratorcode.ui.java.fx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

/**
 * @author : Liangliang.Zhang4
 * @version : 1.0
 * @date : 2023/2/27
 */
public class MarqueeWindow extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane borderPane = new BorderPane();
        HBox hBox = initHeaderPane();
        borderPane.setTop(hBox);

        StackPane marqueStage = new StackPane();

        borderPane.setCenter(marqueStage);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @NotNull
    private HBox initHeaderPane() {
        HBox hBox = new HBox();
        hBox.setPrefHeight(100);
        TextArea textArea = new TextArea();

        Button button = new Button();
        hBox.getChildren().addAll(textArea, button);
        return hBox;
    }

    public static void main(String[] args) {
        launch();
    }


}
