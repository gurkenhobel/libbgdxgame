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
import com.mygdx.game.levels.StaticTest;


/**
 * TODO: move camera into engine
 */
public class MyGdxGame extends ApplicationAdapter {

    private SpriteBatch _batch;
    private GameEngine _engine;

    @Override
    public void create() {
        _batch = new SpriteBatch();

        _engine = new GameEngine(_batch);

        ILevel level = new StaticTest();

        _engine.initialize(level);

    }

    @Override
    public void render() {

        _engine.update();
    }

    @Override
    public void resize(int width, int height) {
        _engine.resize(width, height);
    }

    @Override
    public void dispose() {
        _batch.dispose();
    }
}
