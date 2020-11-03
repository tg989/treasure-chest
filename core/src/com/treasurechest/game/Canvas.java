package com.treasurechest.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Canvas extends Actor implements Disposable {
    private Pixmap canvasPixmap;
    private Pixmap drawingPixmap;
    private Color backgroundColor = Color.WHITE;
    private Color brushDefault = Color.BLACK;
    private float x, y, width, height;

    public Canvas(int x, int y) {
        width = x;
        height = y;
        canvasPixmap = new Pixmap(Gdx.files.internal("core/assets/canvasTexture.jpg"));
        //drawingPixmap = new Pixmap(256, 256, Pixmap.Format.RGBA8888);
        //size = 256;

        canvasPixmap.setColor(backgroundColor);
        canvasPixmap.fill();
    }

    public void drawAt(int x, int y) {
        //drawingPixmap = new Pixmap(Gdx.files.internal("core/assets/circle.png"));
        System.out.println("x: " + x / 2 + ", y: " + -y );
        //canvasPixmap.drawCircle( (int) ((x / 2), (int) ((-y + height/ 2)), 10);

        canvasPixmap.drawCircle(x, (int) (-y + height), 5);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.setColor(Color.WHITE);
        Texture texture = new Texture(canvasPixmap);
        batch.draw(texture, x, y, width, height);
    }

    public void normDraw(int x, int y) {
        drawAt((int) ((x * this.x) / this.x), (int) ((this.y - y) * this.y / this.y));
    }


    public Canvas(float x, float y, float width, float height) {
        canvasPixmap = new Pixmap(Gdx.files.internal("core/assets/canvasTexture.jpg"));
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        setBounds(x, y, width, height);
    }


    /*


    public void draw(float x, float y) {
        System.out.println(x + " " + y);

        Pixmap brushTexture = new Pixmap(Gdx.files.internal("core/assets/circle.png"));
        texture = new Texture(Gdx.files.internal("core/assets/canvasTexture.jpg"));
        texture.draw(brushTexture, (int) x, (int) y);

    }

     */

    public Pixmap getPixmap() {
        return canvasPixmap;
    }

    @Override
    public void dispose() {
        canvasPixmap.dispose();
        //drawingPixmap.dispose();
    }

}
