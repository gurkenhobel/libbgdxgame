package com.mygdx.game.components.renderer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by nils on 09.04.17.
 * renders a simple static sprite
 */
public class SpriteRenderer extends Renderer{

    private Sprite _sprite;

    //creates a sprite which covers the whole texture
    public SpriteRenderer(Texture texture){
        _sprite = new Sprite(texture);
    }

    //TODO: add constructor for creating sprites which only partialy cover the texture

    @Override
    public Sprite sampleSprite() {
        return _sprite;
    }

}
