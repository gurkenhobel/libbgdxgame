package com.mygdx.game;

import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by nils on 18.04.17.
 */
public class GameEngine implements Disposable{
    private Level _currentLevel;
    private OrthogonalTiledMapRenderer _renderer;

    public GameEngine(){
    }

    public void loadLevel(String levelName){
        if(_currentLevel != null)
            _currentLevel.dispose();

    }

    public void update(float deltaTime){
        _currentLevel.update(deltaTime);
        _currentLevel.processInput();
        _currentLevel.render(deltaTime);
    }

    @Override
    public void dispose() {
        _renderer.dispose();
        _currentLevel.dispose();
    }
}
