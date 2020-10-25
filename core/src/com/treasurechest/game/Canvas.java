package com.treasurechest.game;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.utils.Disposable;

public class Canvas extends ModelInstance implements Disposable {

    public Canvas(Model model) {
        super(model);
    }

    @Override
    public void dispose() {

    }
}
