package com.mygdx.game;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.levels.AnimationTest;
import com.mygdx.game.levels.ILevel;


/**
 * TODO: move camera into engine
 */
public class MyGdxGame extends ApplicationAdapter {

    private SpriteBatch _batch;
    private Viewport _viewport;
    private Camera _camera;
    private GameEngine _engine;

    @Override
    public void create() {
        _batch = new SpriteBatch();
        _camera = new OrthographicCamera();
        _viewport = new ScalingViewport(Scaling.fill, 800, 480, _camera);
        _viewport.apply();
        _camera.position.set(_camera.viewportWidth / 2, _camera.viewportHeight / 2, 0);
        _engine = new GameEngine(_batch);

        ILevel level = new AnimationTest();

        _engine.initialize(level);

    }

    @Override
    public void render() {
        _camera.update();

        Gdx.gl.glClearColor(0.42f, 0.43f, 0.45f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        _batch.setProjectionMatrix(_camera.combined);

        _batch.begin();
        _engine.render();
        _batch.end();
    }

    @Override
    public void resize(int width, int height) {
        _viewport.update(width, height, true);
        _camera.position.set(_camera.viewportWidth / 2, _camera.viewportHeight / 2, 0);
    }

    @Override
    public void dispose() {
        _batch.dispose();
    }
}
