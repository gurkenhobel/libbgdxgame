package com.mygdx.game.components.renderer;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by nils on 08.04.17.
 *
 * this provides a common interface for all renderer implementations on the RenderComponent
 */
public abstract class Renderer {

    //returns the sampled sprite.
    //caution: the sprite is no complete graphical representation of the
    //entities state. some properies from other components might be added in the RenderSystem. this
    //is preferable since the logic can be shared between all Renderer implementations when its in
    //the RenderSystem. this style conforms better with the ecs patterrn as well
    public abstract Sprite sampleSprite();
}
