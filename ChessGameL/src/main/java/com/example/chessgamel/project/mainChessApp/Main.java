package com.example.chessgamel.project.mainChessApp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
public class Main extends Application {
	@Override
	public void init() {
		root = new StackPane();
		cControl = new CustomControl();
		root.getChildren().add(cControl);
	}
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Team Yareds Chess game");
		primaryStage.setScene(new Scene(root, 600, 600));
		primaryStage.setMinWidth(400);
		primaryStage.setMinHeight(400);
		primaryStage.show();
	}
	@Override
	public void stop() {
	}
	public static void main(String[] args) {
			launch(args);
	}
	private StackPane root;
	private CustomControl cControl;
}