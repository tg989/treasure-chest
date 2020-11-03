package com.treasurechest.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class CanvasScreen implements Screen {
    private Game treasureChest;
    private Stage canvas;
    private Canvas drawableCanvas;
    private OrthographicCamera camera;
    //private SpriteBatch batch;

    public CanvasScreen(Game treasureChest) {
        canvas = new Stage(new ScreenViewport());
        this.treasureChest = treasureChest;
    }

    @Override
    public void show() {
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(true);

        drawableCanvas = new Canvas(canvas.getViewport().getScreenX(), canvas.getViewport().getScreenY(), 1440, 900);
        drawableCanvas.setTouchable((Touchable.enabled));
        /*
        drawableCanvas.addListener(new ActorGestureListener() {
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                drawableCanvas.drawAt((int) x, (int) y);
            }
        });

         */


        drawableCanvas.addListener(new InputListener() {
            boolean dragging;
            public boolean touchDown(InputEvent event, float screenX, float screenY, int pointer, int button) {
                System.out.println("Touched");
                dragging = true;
                return true;
            }

            public void touchDragged(InputEvent event, float screenX, float screenY, int pointer) {
                System.out.println("Dragged");
                drawableCanvas.drawAt((int) screenX, (int) screenY);
            }

            public void touchUp(InputEvent event, float screenX, float screenY, int pointer, int button) {
                dragging = false;
                System.out.println("Stop touch");
            }

        });

        //batch = new SpriteBatch();

        canvas.addActor(drawableCanvas);
        Gdx.input.setInputProcessor(canvas);
    }

    @Override
    public void render(float delta) {
        /*
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Texture texture = new Texture(drawableCanvas.getPixmap());
        texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        texture.bind();

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(texture, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        canvas.act(Gdx.graphics.getDeltaTime());
        canvas.draw();
        texture.dispose();

         */


        canvas.act();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        canvas.draw();

    }

    @Override
    public void resize(int width, int height) {
        canvas.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        canvas.dispose();
    }
}
