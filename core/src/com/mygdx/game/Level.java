package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by nils on 18.04.17.
 */
/**
 * instances of this class will represent a single level in the game. this class is the entry point
 * for game logic. initialisation will be simplified by injecting the level state with an tmx file.
 * this file will contain all level specific data this class needs to run the game logic. it should
 * hold as little level specific logic as possible, for specialized levels create subtypes
 * */
public class Level implements Disposable {
    private TiledMap _map;
    private OrthogonalTiledMapRenderer _renderer;
    private OrthographicCamera _camera;

    public Level(String mapPath) {
        _map = new TmxMapLoader().load(mapPath);    //TODO: integrate RessourceManger
        _renderer = new OrthogonalTiledMapRenderer(_map, 1);
        //TODO: load camera from tmx
        TmxInflater inflater = new TmxInflater(_map);
        _camera = inflater.getCamera();
    }



    public void render(float deltaTime){
        if(_renderer == null)
            throw new IllegalStateException("something went terribly wrong. renderer is missing.");
        _renderer.setView(_camera);
        _renderer.render();
    }

    /**
     * update the game map. here the gamelogic is implemented. state, triggers and other
     * level-specific stuff we will come up with will be injected through the tmx
     * */
    public void update(float deltaTime){
        //TODO
    }

    public void processInput(/*some kind of input manager*/){
        //TODO
    }

    //TODO: audio

    @Override
    public void dispose() {
        _renderer.dispose();
        _map.dispose();

    }
}
