package com.mygdx.game.components.renderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * Created by nils on 08.04.17.
 */
public class SheetAnimationRenderer extends Renderer{

    private final int _frameCols, _frameRows;
    private float _frameInterval;
    private TextureRegion[] _frames;
    private Animation<TextureRegion> _animation;
    private Texture _sheet;
    private long _startTimestamp;



    /**
     * @param sheetPath path to the image of the spritesheet. the texture needs to be instantiated
     *                  here or else dispose might create bad references
     * @param cols      column count of the spritesheet
     * @param rows      row count of the spritesheet
     * @param fps       speed of the animation
     */
    public SheetAnimationRenderer(String sheetPath, int cols, int rows, int fps) {
        _frameCols = cols;
        _frameRows = rows;
        _frameInterval = 1.0f / fps;
        _sheet = new Texture(Gdx.files.internal(sheetPath));

        _startTimestamp = -1;

        _frames = getFrames(_sheet, rows, cols);
        _animation = new Animation<TextureRegion>(_frameInterval, _frames);
    }


    @Override
    public Sprite sampleSprite() {
        if(_startTimestamp == -1){
            _startTimestamp = TimeUtils.millis();
        }
        float sampleTime = (float)(TimeUtils.millis() - _startTimestamp) / 1000;
        TextureRegion frame = _animation.getKeyFrame(sampleTime, true);
        return new Sprite(frame);
    }


    public void setFps(int fps) {
        _frameInterval = 1.0f / fps;
        _animation = new Animation<TextureRegion>(_frameInterval, _frames);
    }

    public int getFps() {
        return Math.round(1.0f / _frameInterval);
    }


    /**
     * Splits the spritesheet into frames.
     * frames must be arranged from top left to bottom right
     */
    private TextureRegion[] getFrames(Texture sheet, int rows, int cols) {
        TextureRegion[][] tmp = TextureRegion.split(sheet,
                sheet.getWidth() / cols, sheet.getHeight() / rows);
        TextureRegion[] frames = new TextureRegion[rows * cols];
        //transform framegrid to frame sequence
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                frames[index++] = tmp[i][j];
            }
        }
        return frames;
    }

    @Override
    public void dispose() {
        _sheet.dispose();
    }
}
