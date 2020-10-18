package com.treasurechest.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.treasurechest.game.TreasureChest;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Treasure Chest";
		// config.fullscreen = true; <- Enable Later after exit button added
		config.resizable = true;
		config.forceExit = true;
		config.useGL30 = false;
		config.width = 1920;
		config.height = 1080;
		new LwjglApplication(new TreasureChest(), config);
	}
}
