package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.levels.RenderTest;
import com.mygdx.game.levels.ILevel;


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

        ILevel level = new RenderTest();

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
