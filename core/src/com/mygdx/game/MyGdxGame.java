package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
    private GameEngine _game;
    private OrthographicCamera _camera;

    @Override
    public void create() {
        _camera = new OrthographicCamera();
        _game = new GameEngine(_camera);
        _game.loadLevel("test-map.tmx");
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.42f, 0.43f, 0.45f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        _game.update(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void dispose() {
        _game.dispose();
    }
}
