package com.treasurechest.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.utils.Disposable;

public class Brush extends Item implements Disposable {
    boolean draw = false;
    Color currentColor = Color.BLACK;

    public Brush(Model model) {
        super(model);
    }


}
