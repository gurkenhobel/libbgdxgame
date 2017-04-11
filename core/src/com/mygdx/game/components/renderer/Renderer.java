package com.mygdx.game.components.renderer;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by nils on 08.04.17.
 */
public abstract class Renderer implements Disposable {

    public abstract Sprite sampleSprite();



    public abstract void dispose();


}
