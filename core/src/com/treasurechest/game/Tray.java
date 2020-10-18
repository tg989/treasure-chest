package com.treasurechest.game;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

public class Tray extends ModelInstance implements Disposable {
    Array<Item> items = new Array<Item>();

    public Tray(Model model) {
        super(model);
    }

    @Override
    public void dispose() {

    }
}
