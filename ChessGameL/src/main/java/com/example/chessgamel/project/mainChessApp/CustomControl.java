package com.example.chessgamel.project.mainChessApp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Control;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
public class CustomControl extends Control {
	public CustomControl(){
		setSkin(new CustomControlSkin(this));
		
		statusBar = new StatusBar();
		cell = new Cell(statusBar);
		getChildren().addAll(statusBar, cell);
		
		setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				cell.selectPiece(event.getX(), event.getY() - (statusBarSize / 2));
			}
			
		});

		setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.SPACE)
					cell.resetGame();
			}
		});
		
		statusBar.getResetButton().setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				cell.resetGame();
			}
			
		});

	}

	public void resize(double width, double height){
		super.resize(width, height - statusBarSize);
		cell.setTranslateY(statusBarSize / 2);
		cell.resize(width, height - statusBarSize);
		statusBar.resize(width, statusBarSize);
		statusBar.setTranslateY(-(statusBarSize / 2));
	}
	private Cell cell;
	private StatusBar statusBar; 
	private int statusBarSize = 100;	
}
