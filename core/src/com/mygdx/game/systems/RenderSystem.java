package com.mygdx.game.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.components.PositionComponent;
import com.mygdx.game.components.SizeComponent;
import com.mygdx.game.components.renderer.RenderComponent;
import com.mygdx.game.components.renderer.SheetAnimationRenderer;

import java.util.Comparator;

/**
 * Created by nils on 08.04.17.
 */
public class RenderSystem extends SortedIteratingSystem {
    private static ComponentMapper<RenderComponent> _rm;
    private static ComponentMapper<PositionComponent> _pm;
    private static ComponentMapper<SizeComponent> _sm;

    private SpriteBatch _batch;

    public RenderSystem(SpriteBatch batch) {
        super(Family.all(RenderComponent.class, PositionComponent.class, SizeComponent.class).get(), new ZComparator());
        _batch = batch;
        _rm = ComponentMapper.getFor(RenderComponent.class);
        _pm = ComponentMapper.getFor(PositionComponent.class);
        _sm = ComponentMapper.getFor(SizeComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        RenderComponent renderer = _rm.get(entity);
        PositionComponent position = _pm.get(entity);
        SizeComponent size = _sm.get(entity);

        renderer.renderer.render(_batch, position.x, position.y, size.width, size.hight);
    }

    private static class ZComparator implements Comparator<Entity> {
        @Override
        public int compare(Entity e1, Entity e2) {
            return (int)Math.signum(_rm.get(e1).z - _rm.get(e2).z);
        }
    }
}
