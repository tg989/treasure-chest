package com.treasurechest.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.treasurechest.game.TreasureChestGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		//Graphics.DisplayMode displayMode = LwjglApplicationConfiguration.getDesktopDisplayMode();
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Treasure Chest";
		//config.setFromDisplayMode(displayMode);

		config.width = 1440/2;
		config.height = 900/2;

		config.resizable = true;
		config.forceExit = true;

		config.useGL30 = false;
		new LwjglApplication(new TreasureChestGame(), config);
	}
}
