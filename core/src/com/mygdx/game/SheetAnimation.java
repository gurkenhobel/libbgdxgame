package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.TimeUtils;


/**
 * Created by nils on 06.04.17.
 * <p>
 * This class implements basic spritesheet animation
 * <p>
 * TODO: move into library. this seems reusable
 * 
 */
public class SheetAnimation implements Disposable {
    private final int _frameCols, _frameRows;
    private float _frameInterval;
    private TextureRegion[] _frames;
    private Animation<TextureRegion> _animation;
    private Texture _sheet;
    private long _startTimestamp;

    private Position _position;

    /**
     * @param sheetPath path to the image of the spritesheet. the texture needs to be instantiated
     *                  here or else dispose might create bad references
     * @param cols      column count of the spritesheet
     * @param rows      row count of the spritesheet
     * @param fps       speed of the animation
     */
    public SheetAnimation(String sheetPath, int cols, int rows, int fps, int posX, int posY) {
        _frameCols = cols;
        _frameRows = rows;
        _frameInterval = 1.0f / fps;
        _sheet = new Texture(Gdx.files.internal(sheetPath));
        _position = new Position(posX, posY);

        _startTimestamp = -1;

        _frames = getFrames(_sheet, rows, cols);
        _animation = new Animation<TextureRegion>(_frameInterval, _frames);
    }


    /**
     * Splits the spritesheet into frames.
     * frames must be arranged from top left to bottom right
     */
    private TextureRegion[] getFrames(Texture sheet, int rows, int cols) {
        TextureRegion[][] tmp = TextureRegion.split(sheet,
                sheet.getWidth() / cols, sheet.getHeight() / rows);
        TextureRegion[] frames = new TextureRegion[rows * cols];
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                frames[index++] = tmp[i][j];
            }
        }
        return frames;
    }

    public void setFps(int fps) {
        _frameInterval = 1.0f / fps;
        _animation = new Animation<TextureRegion>(_frameInterval, _frames);
    }

    public int getFps() {
        return Math.round(1.0f / _frameInterval);
    }

    public Position getPosition() {
        return _position;
    }

    public void setPosition(int x, int y) {
        _position.X = x;
        _position.Y = y;
    }

    /**
     * samples the animation at @param sampleTime and draws it onto the spritebatch
     *
     */
    public void sampleAndDraw(SpriteBatch batch) {
        if(_startTimestamp == -1){
            _startTimestamp = TimeUtils.millis();
        }
        float sampleTime = (float)(TimeUtils.millis() - _startTimestamp) / 1000;
        TextureRegion frame = _animation.getKeyFrame(sampleTime, true);
        batch.draw(frame, _position.X, _position.Y);
    }

    /**
     * frees resources of the animation.
     */
    @Override
    public void dispose() {
        _animation = null;
        _frames = null;
        _sheet.dispose();
    }

    private class Position {
        public int X;
        public int Y;

        public Position(int x, int y) {
            X = x;
            Y = y;
        }
    }
}
