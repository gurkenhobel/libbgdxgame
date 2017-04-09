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
    private Texture _texture;

    public SpriteRenderer(String texturePath){
        _texture = new Texture(texturePath);
        _sprite = new Sprite(_texture);
    }

    @Override
    public void render(SpriteBatch batch, float posX, float posY, float width, float height){
        _sprite.setPosition(posX, posY);
        _sprite.setSize(width, height);
        _sprite.draw(batch);
    }

    @Override
    public void dispose() {
        _texture.dispose();
    }
}
