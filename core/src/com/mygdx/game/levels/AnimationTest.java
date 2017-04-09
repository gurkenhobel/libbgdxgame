package com.mygdx.game.levels;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.mygdx.game.components.PositionComponent;
import com.mygdx.game.components.SizeComponent;
import com.mygdx.game.components.renderer.RenderComponent;
import com.mygdx.game.components.renderer.SheetAnimationRenderer;

/**
 * Created by nils on 08.04.17.
 */
public class AnimationTest implements ILevel {
    @Override
    public void loadLevel(PooledEngine engine) {

        engine.addEntity(createAnim(engine));

    }

    private Entity createAnim(PooledEngine engine){
        Entity anim = engine.createEntity();
        RenderComponent renderer  = engine.createComponent(RenderComponent.class);
        renderer.renderer = new SheetAnimationRenderer("spritesheet.png",4, 4, 25);
        renderer.z = 0;
        PositionComponent pos = engine.createComponent(PositionComponent.class);
        pos.x = 250;
        pos.y = 90;
        SizeComponent size = engine.createComponent(SizeComponent.class);
        size.width = 300;
        size.height = 300;

        anim.add(renderer);
        anim.add(pos);
        anim.add(size);

        return anim;
    }
}
