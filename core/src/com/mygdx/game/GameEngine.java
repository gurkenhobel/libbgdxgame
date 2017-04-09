package com.mygdx.game;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.levels.ILevel;
import com.mygdx.game.systems.RenderSystem;

/**
 * Created by nils on 08.04.17.
 */
public class GameEngine {

    private PooledEngine _engine;

    private RenderSystem _renderSystem;

    public GameEngine(SpriteBatch batch){
        _engine = new PooledEngine();
        _renderSystem = new RenderSystem(batch);
        _renderSystem.setProcessing(false);

        _engine.addSystem(_renderSystem);
    }

    public void initialize(ILevel level){
        _renderSystem.setProcessing(false);
        level.loadLevel(_engine);
        _renderSystem.setProcessing(true);

    }

    public void update(){
        _engine.update(Gdx.graphics.getDeltaTime());
    }

    public void resize(int width, int height){
        _renderSystem.resize(width, height);
    }
}
