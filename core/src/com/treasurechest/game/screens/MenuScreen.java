package com.treasurechest.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.treasurechest.game.TreasureChestGame;
import com.treasurechest.game.ui.Button;

public class MenuScreen implements Screen {
    private final TreasureChestGame game;
    private final AssetManager assets;

    private Stage   stage;
    private Button  createButton;
    private Button  loadButton;
    private Button  exitButton;
    private Button  optionsButton;
    private Image    bg;
    private Viewport viewport;

    public MenuScreen(TreasureChestGame game, AssetManager assets) {
        this.game = game;
        this.assets = assets;
    }

    @Override
    public void show() {
        viewport       = new StretchViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        stage          = new Stage(viewport);
        bg             = new Image(assets.get("menuBg.png", Texture.class));
        createButton   = new Button(assets.get("createButton.png",Texture.class));
        loadButton     = new Button(assets.get("loadButton.png",Texture.class));
        optionsButton  = new Button(assets.get("optionsButton.png",Texture.class));
        exitButton     = new Button(assets.get("exitButton.png",Texture.class));

        initActorsPositions(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        initListeners();

        addToStage(bg,createButton,loadButton,optionsButton,exitButton);

        Gdx.input.setInputProcessor(stage);
    }

    private void initListeners() {
        createButton.addListener(new Button.Listener() {
            @Override
            public void onAnimationOff() {
                //load a screen here, an example is provided below
                //game.setScreen(new CanvasScreen());
            }
        });
        loadButton.addListener(new Button.Listener() {
            @Override
            public void onAnimationOff() {
                //load a screen here, an example is provided below
                //game.setScreen(new CanvasScreen());
            }
        });
        optionsButton.addListener(new Button.Listener() {
            @Override
            public void onAnimationOff() {
                //load a screen here, an example is provided below
                //game.setScreen(new CanvasScreen());
            }
        });
        exitButton.addListener(new Button.Listener() {
            @Override
            public void onAnimationOff() {
                Gdx.app.exit();
            }
        });
    }

    private void initActorsPositions(float width, float height) {
        Texture bgTexture = assets.get("menuBg.png", Texture.class);
        Vector2 size      = Scaling.fillY.apply(bgTexture.getWidth(),bgTexture.getHeight(),0,height);

        bg.setSize(size.x,size.y);
        bg.setPosition(width/2 - bg.getWidth()/2,0);

        float scale = bg.getHeight()/bgTexture.getHeight();

        createButton.setSize(createButton.defaultWidth*scale, createButton.defaultHeight*scale);
        loadButton.setSize(loadButton.defaultWidth*scale, loadButton.defaultHeight*scale);
        optionsButton.setSize(optionsButton.defaultWidth*scale, optionsButton.defaultHeight*scale);
        exitButton.setSize(exitButton.defaultWidth*scale, exitButton.defaultHeight*scale);

        createButton.setPosition(bg.getX()+1312*scale,bg.getY()+959*scale);
        loadButton.setPosition(bg.getX()+1312*scale,bg.getY()+715*scale);
        optionsButton.setPosition(bg.getX()+1312*scale,bg.getY()+475*scale);
        exitButton.setPosition(bg.getX()+0,bg.getY()+0);
    }

    public void addToStage(Actor... actors){
        for(Actor a: actors)
            stage.addActor(a);
    }

    @Override
    public void render(float delta) {
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height);
        stage.getCamera().update();
    }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
