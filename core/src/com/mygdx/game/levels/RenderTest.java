package com.mygdx.game.levels;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.components.PositionComponent;
import com.mygdx.game.components.SizeComponent;
import com.mygdx.game.components.renderer.RenderComponent;
import com.mygdx.game.components.renderer.SheetAnimationRenderer;
import com.mygdx.game.components.renderer.SpriteRenderer;
import com.mygdx.game.systems.Size;

/**
 * Created by nils on 08.04.17.
 */
public class RenderTest implements ILevel {
    @Override
    public void loadLevel(PooledEngine engine) {

        engine.addEntity(createAnim(engine));
        engine.addEntity(createSprite(engine));

    }

    /**
     * this is messy. damn, so much code for only one very basic entity
     * we need to inject this stuff. tilemaps with object layers might solve the issue
     * but if we rely on those to implement the game, it will be hard to create procedual content.
     * we would have to design all our levels by hand
     * */
    private Entity createAnim(PooledEngine engine){
        Entity anim = engine.createEntity();
        RenderComponent renderer  = engine.createComponent(RenderComponent.class);
        renderer.renderer = new SheetAnimationRenderer("spritesheet.png",4, 4, 25);
        renderer.z = 0;
        PositionComponent pos = engine.createComponent(PositionComponent.class);
        pos.pos = new Vector2(500, 90);
        SizeComponent size = engine.createComponent(SizeComponent.class);
        size.size = new Size(300, 300);

        anim.add(renderer);
        anim.add(pos);
        anim.add(size);

        return anim;
    }

    private Entity createSprite(PooledEngine engine){
        Entity sprite = engine.createEntity();
        RenderComponent renderer  = engine.createComponent(RenderComponent.class);
        renderer.renderer = new SpriteRenderer("spritesheet.png");
        renderer.z = 0;
        PositionComponent pos = engine.createComponent(PositionComponent.class);
        pos.pos = new Vector2(0, 90);
        SizeComponent size = engine.createComponent(SizeComponent.class);
        size.size = new Size(300, 300);

        sprite.add(renderer);
        sprite.add(pos);
        sprite.add(size);

        return sprite;
    }
}
