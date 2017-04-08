package com.mygdx.game;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.levels.ILevel;

/**
 * Created by nils on 08.04.17.
 */
public class GameEngine {

    private SpriteBatch _batch;
    private PooledEngine _engine;

    public GameEngine(SpriteBatch batch){
        _batch = batch;
        _engine = new PooledEngine();
    }

    public void initialize(ILevel level){
        level.loadLevel(_engine);
    }

    public void render(){
        _engine.update(Gdx.graphics.getDeltaTime());
    }
}
