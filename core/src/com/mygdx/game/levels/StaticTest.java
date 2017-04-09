package com.mygdx.game.levels;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.mygdx.game.components.PositionComponent;
import com.mygdx.game.components.SizeComponent;
import com.mygdx.game.components.renderer.RenderComponent;
import com.mygdx.game.components.renderer.SheetAnimationRenderer;
import com.mygdx.game.components.renderer.SpriteRenderer;

/**
 * Created by nils on 09.04.17.
 */
public class StaticTest implements ILevel {
    @Override
    public void loadLevel(PooledEngine engine) {
        engine.addEntity(createSprite(engine));
    }

    private Entity createSprite(PooledEngine engine){
        Entity sprite = engine.createEntity();
        RenderComponent renderer  = engine.createComponent(RenderComponent.class);
        renderer.renderer = new SpriteRenderer("spritesheet.png");
        renderer.z = 0;
        PositionComponent pos = engine.createComponent(PositionComponent.class);
        pos.x = 250;
        pos.y = 90;
        SizeComponent size = engine.createComponent(SizeComponent.class);
        size.width = 300;
        size.height = 300;

        sprite.add(renderer);
        sprite.add(pos);
        sprite.add(size);

        return sprite;
    }
}
