package com.treasurechest.game.ui;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

public class Button extends Image {

    public Array<Listener> listeners = new Array<>();
    public final float defaultWidth;
    public final float defaultHeight;

    public Button(Texture texture) {
        super(texture);
        defaultWidth = texture.getWidth();
        defaultHeight = texture.getHeight();

        addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                setOrigin(Align.center);
                addAction(Actions.scaleTo(1.2f,1.2f,0.1f));
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                addAction(Actions.sequence(
                        Actions.scaleTo(1f,1f,0.1f),
                        Actions.run(new Runnable() {
                            @Override
                            public void run() {
                                for(Listener listener: listeners)
                                    listener.onAnimationOff();
                            }
                        })
                ));
            }
        });
    }

    public void addListener(Listener listener){
        listeners.add(listener);
    }

    public boolean removeListener(Listener listener) {
        return listeners.removeValue(listener,false);
    }

    public interface Listener{
        void onAnimationOff();
    }
}
