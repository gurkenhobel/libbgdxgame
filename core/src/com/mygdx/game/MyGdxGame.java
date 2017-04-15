package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MyGdxGame extends ApplicationAdapter {
    private OrthographicCamera _camera;

    private TiledMap _map;
    private OrthogonalTiledMapRenderer _mapRenderer;
    private OrthoCamController _camController;

    @Override
    public void create() {
        try {
            float w = Gdx.graphics.getWidth();
            float h = Gdx.graphics.getHeight();


            _camera = new OrthographicCamera();
            _camera.setToOrtho(false,(w/h) * 6, 6 );
            _camera.update();

            _camController = new OrthoCamController(_camera);
            Gdx.input.setInputProcessor(_camController);

            _map = new TmxMapLoader().load("test-map.tmx");
            _mapRenderer = new OrthogonalTiledMapRenderer(_map, 1/64f);
        }
        catch(Exception ex){
            Gdx.app.log("init", ex.getMessage(), ex);
        }
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.55f, 0.55f, 0.55f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        _camera.update();


        _mapRenderer.setView((OrthographicCamera) _camera);
        _mapRenderer.render();
    }

    @Override
    public void dispose() {
        _mapRenderer.dispose();
        _map.dispose();
    }
}
