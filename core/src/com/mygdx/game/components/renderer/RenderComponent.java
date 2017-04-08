package com.mygdx.game.components.renderer;

import com.badlogic.ashley.core.Component;

/**
 * Created by nils on 08.04.17.
 */
public class RenderComponent implements Component {
    //holds all data specific to render the entity
    public Renderer renderer;
    public int z;
}
