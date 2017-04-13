package com.mygdx.game.components;

import com.badlogic.ashley.core.Component;
import com.mygdx.game.systems.Size;

/**
 * Created by nils on 08.04.17.
 * holds the size state of its entity.
 * TODO: add anchor to define position and perform translations
 */
public class SizeComponent implements Component {
    public Size size;
}
