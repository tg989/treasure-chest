package com.treasurechest.game.desktop;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.treasurechest.game.TreasureChest;

public class DesktopLauncher {
	public static void main (String[] arg) {
		//Graphics.DisplayMode displayMode = LwjglApplicationConfiguration.getDesktopDisplayMode();
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Treasure Chest";
		//config.setFromDisplayMode(displayMode);

		config.width = 1440;
		config.height = 900;

		config.resizable = true;
		config.forceExit = true;

		config.useGL30 = false;
		new LwjglApplication(new TreasureChest(), config);
	}
}
