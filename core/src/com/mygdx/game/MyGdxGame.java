package com.mygdx.game;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.levels.AnimationTest;
import com.mygdx.game.levels.ILevel;

public class MyGdxGame extends ApplicationAdapter {

    private SpriteBatch _batch;
    private Viewport _viewport;
    private Camera _camera;
    private GameEngine _engine;

    @Override
    public void create() {
        _batch = new SpriteBatch();
        _camera = new OrthographicCamera(800, 480);
        _camera.position.set(_camera.viewportWidth / 2f, _camera.viewportHeight / 2f, 0);
        _camera.update();
        _viewport = new FitViewport(800, 480, _camera);

        _engine = new GameEngine(_batch);

        ILevel level = new AnimationTest();

        _engine.initialize(level);

    }

    @Override
    public void render() {
        _camera.update();
        _batch.setProjectionMatrix(_camera.combined);

        Gdx.gl.glClearColor(0.42f, 0.43f, 0.45f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //there might be problems when batches become too big.
        // we'll need to move this into the engine to avoid that
        _batch.begin();
        _engine.render();
        _batch.end();
    }

    @Override
    public void dispose() {
        _batch.dispose();
    }
}
