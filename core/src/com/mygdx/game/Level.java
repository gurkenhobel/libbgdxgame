package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by nils on 18.04.17.
 */
public class Level implements Disposable {
    private TiledMap _map;
    private OrthogonalTiledMapRenderer _renderer;
    private OrthographicCamera _camera;

    public Level(String mapPath, OrthographicCamera camera) {
        _map = new TmxMapLoader().load(mapPath);    //TODO: integrate RessourceManger
        _renderer = new OrthogonalTiledMapRenderer(_map, 1);
        _camera = camera;
    }

    public void render(float deltaTime){
        if(_renderer == null)
            throw new IllegalStateException("something went terribly wrong");
        _renderer.setView(_camera);
        _renderer.render();
    }

    @Override
    public void dispose() {
        _renderer.dispose();
        _map.dispose();

    }
}
