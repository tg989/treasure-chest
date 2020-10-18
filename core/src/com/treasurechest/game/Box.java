package com.treasurechest.game;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

public class Box extends ModelInstance implements Disposable {
    Array<Tray> trays = new Array<Tray>();

    public Box(Model model) {
        super(model);
    }

    @Override
    public void dispose() {
        trays.clear();
    }
}
