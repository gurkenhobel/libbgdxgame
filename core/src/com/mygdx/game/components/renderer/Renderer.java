package com.mygdx.game.components.renderer;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by nils on 08.04.17.
 */
public abstract class Renderer implements Disposable {
    public void render(SpriteBatch batch, float posX, float posY, float width, float height){

    }


    public abstract void dispose();


}
