package com.mygdx.game.components.renderer;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by nils on 08.04.17.
 */
public class RenderComponent implements Component, Pool.Poolable {
    //holds all data specific to render the entity
    public Renderer renderer;
    //z index of the entity. defines its layering behavior during render
    public int z;

    //resets the component to a reusable state
    @Override
    public void reset() {
        //TODO: recycle Renderers
        renderer = null;
        z = 0;
    }
}
