package com.treasurechest.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.treasurechest.game.screens.LoadingScreen;

public class TreasureChestGame extends Game {

	public AssetManager assets;

	@Override
	public void create() {
		initAssets();
		setScreen(new LoadingScreen(this,assets));
	}


	 //list the assets we will use most frequently here, these assets will only be disposed at exit,
	 //LoadingScreen will load them.
	 //
	private void initAssets() {
		assets = new AssetManager();
		assets.load("menuBg.png", Texture.class);
		assets.load("createButton.png",Texture.class);
		assets.load("loadButton.png",Texture.class);
		assets.load("optionsButton.png",Texture.class);
		assets.load("exitButton.png",Texture.class);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if(getScreen() != null)
			getScreen().render(Gdx.graphics.getDeltaTime());
	}

	@Override
	public void dispose() {
		if(getScreen() != null)
			getScreen().dispose();
		assets.dispose();
	}
}
