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
 *
 * this renderer can render sheet animations
 * (infos about sheet anim)
 * https://gamedevelopment.tutsplus.com/tutorials/an-introduction-to-spritesheet-animation--gamedev-13099
 */
public class SheetAnimationRenderer extends Renderer{

    private final int _frameCols, _frameRows;
    private float _frameInterval;
    private TextureRegion[] _frames;
    private Animation<TextureRegion> _animation;
    private Texture _sheet;
    private long _startTimestamp;



    /**
     * @param texture   needs to be loaded with ResourceManager. this is the spritesheet which
     *                  contains the animation
     * @param cols      column count of the spritesheet
     * @param rows      row count of the spritesheet
     * @param fps       speed of the animation
     */
    public SheetAnimationRenderer(Texture texture, int cols, int rows, int fps) {
        _frameCols = cols;
        _frameRows = rows;
        _frameInterval = 1.0f / fps;
        _sheet = texture;

        _startTimestamp = -1;

        _frames = getFrames(_sheet, rows, cols);
        _animation = new Animation<TextureRegion>(_frameInterval, _frames);
    }


    //controlling the entity state from a component is dirty. we need some means to control the
    //sampletime from the systems. i think we should introduce a AnimationComponent
    //TODO: create Component which contains time state
    //this might conflict with physics which need a time state as well. we might have to create a
    //component which both systems can consume
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
}
