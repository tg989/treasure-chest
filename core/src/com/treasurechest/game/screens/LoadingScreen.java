package com.treasurechest.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.treasurechest.game.TreasureChestGame;
import com.treasurechest.game.ui.ProgressBar;

public class LoadingScreen implements Screen {
    private final TreasureChestGame game;
    private final AssetManager      assets;

    private       Stage       stage;
    private       ProgressBar progressBar;
    private       Texture     bg;
    private       Texture     fill;

    public LoadingScreen(TreasureChestGame game, AssetManager assets) {
        this.assets = assets;
        this.game = game;
    }

    @Override
    public void show() {
        stage = new Stage();
        bg = new Texture("progressbar_bg.png");
        fill = new Texture("progressbar_fill.png");

        initProgressBar();

        stage.addActor(progressBar);
    }

    private void initProgressBar() {
        progressBar = new ProgressBar(bg,fill);
        progressBar.setSmoothPercent(true);

        progressBar.setSize(Gdx.graphics.getHeight()/2,Gdx.graphics.getHeight()*0.03f);
        progressBar.setPosition(Gdx.graphics.getWidth()/2 - progressBar.getWidth()/2, Gdx.graphics.getHeight()*0.05f);
    }

    @Override
    public void render(float delta) {
        stage.act();
        assets.update();

        progressBar.setPercent(assets.getProgress());

        stage.draw();

        if(progressBar.getSmoothPercent() >= 1){
            game.setScreen(new MenuScreen(game,assets));
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        stage.dispose();
        bg.dispose();
        fill.dispose();
    }
}
