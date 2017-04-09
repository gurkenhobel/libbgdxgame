package com.mygdx.game.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.components.PositionComponent;
import com.mygdx.game.components.SizeComponent;
import com.mygdx.game.components.renderer.RenderComponent;

import java.util.Comparator;

/**
 * Created by nils on 08.04.17.
 */
public class RenderSystem extends SortedIteratingSystem {
    private static ComponentMapper<RenderComponent> _rm;
    private static ComponentMapper<PositionComponent> _pm;
    private static ComponentMapper<SizeComponent> _sm;




    private Viewport _viewport;
    private Camera _camera;
    private SpriteBatch _batch;

    public RenderSystem(SpriteBatch batch) {
        super(Family.all(RenderComponent.class, PositionComponent.class, SizeComponent.class).get(), new ZComparator());
        _batch = batch;
        _rm = ComponentMapper.getFor(RenderComponent.class);
        _pm = ComponentMapper.getFor(PositionComponent.class);
        _sm = ComponentMapper.getFor(SizeComponent.class);

        _camera = new OrthographicCamera();
        _viewport = new ScalingViewport(Scaling.fill, 800, 480, _camera);
        _viewport.apply();
        _camera.position.set(_camera.viewportWidth / 2, _camera.viewportHeight / 2, 0);

    }


    //doing all the stuff that must happen before actual rendering can start
    //TODO: move that into seperate system with higher priority
    @Override
    public void update(float deltaTime){
        _camera.update();

        Gdx.gl.glClearColor(0.42f, 0.43f, 0.45f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



        _batch.begin();
        generateClippingScissors();
        _batch.setProjectionMatrix(_camera.combined);
        super.update(deltaTime);
        _batch.end();
        ScissorStack.popScissors();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        RenderComponent renderer = _rm.get(entity);
        PositionComponent position = _pm.get(entity);
        SizeComponent size = _sm.get(entity);

        renderer.renderer.render(_batch, position.x, position.y, size.width, size.height);
    }

    public void resize(int width, int height) {
        _viewport.update(width, height, true);
        _camera.position.set(_camera.viewportWidth / 2, _camera.viewportHeight / 2, 0);
    }

    private void generateClippingScissors(){
        Rectangle scissors = new Rectangle();
        Rectangle clipBounds = new Rectangle(_viewport.getScreenX(),_viewport.getScreenY(),
                _viewport.getScreenWidth(),_viewport.getScreenHeight());
        ScissorStack.calculateScissors(_camera, _batch.getTransformMatrix(), clipBounds, scissors);
        ScissorStack.pushScissors(scissors);
    }

    private static class ZComparator implements Comparator<Entity> {
        @Override
        public int compare(Entity e1, Entity e2) {
            return (int)Math.signum(_rm.get(e1).z - _rm.get(e2).z);
        }
    }
}
