package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
    SpriteBatch _batch;
    SheetAnimation _anim;

    @Override
    public void create() {
        _batch = new SpriteBatch();
        _anim = new SheetAnimation("spritesheet.png", 4, 4, 25, 50, 50);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.42f, 0.43f, 0.45f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        _batch.begin();
        _anim.sampleAndDraw(_batch);
        _batch.end();
    }

    @Override
    public void dispose() {
        _batch.dispose();
        _anim.dispose();
    }
}
