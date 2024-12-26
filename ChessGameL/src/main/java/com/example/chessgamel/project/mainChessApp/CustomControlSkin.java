package com.example.chessgamel.project.mainChessApp;

import javafx.scene.control.Skin;
import javafx.scene.control.SkinBase;
class CustomControlSkin extends SkinBase<CustomControl> implements Skin<CustomControl> {
	public CustomControlSkin(CustomControl cc) {
		super(cc);
	}
}