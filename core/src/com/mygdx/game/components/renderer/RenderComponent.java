package com.mygdx.game.components.renderer;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by nils on 08.04.17.
 */
public class RenderComponent implements Component, Pool.Poolable {
    //holds all data specific to render the entity
    public Renderer renderer;
    public int z;

    @Override
    public void reset() {
        //TODO: recycle Renderers
        renderer = null;
        z = 0;
    }
}
