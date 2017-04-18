package com.mygdx.game;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by nils on 18.04.17.
 */
public class GameEngine implements Disposable{
    private Level _currentLevel;
    private OrthogonalTiledMapRenderer _renderer;
    private PooledEngine _engine;
    private OrthographicCamera _camera;

    public GameEngine(OrthographicCamera camera){
        _engine = new PooledEngine();
        _camera = camera;
    }

    public void loadLevel(String levelName){
        if(_currentLevel != null)
            _currentLevel.dispose();

    }

    public void update(float deltaTime){
        _engine.update(deltaTime);
    }

    @Override
    public void dispose() {
        _engine.clearPools();
        _renderer.dispose();
        _currentLevel.dispose();
    }
}
