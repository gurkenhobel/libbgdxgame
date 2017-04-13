package com.mygdx.game.components.renderer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by nils on 09.04.17.
 * TODO: move texture loading to dedicated component. this will improve performance and prevent memory leaks
 */
public class SpriteRenderer extends Renderer{

    private Sprite _sprite;

    public SpriteRenderer(Texture texture){
        _sprite = new Sprite(texture);
    }

    @Override
    public Sprite sampleSprite() {
        return _sprite;
    }

}
