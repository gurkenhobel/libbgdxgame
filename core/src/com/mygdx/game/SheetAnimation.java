package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by nils on 06.04.17.
 *
 * This class implements basic spritesheet animation
 *
 * TODO: actually test. no idea if any of this works
 */
public class SheetAnimation implements Disposable {
    private final int _frameCols, _frameRows;
    private float _frameInterval;
    private TextureRegion[] _frames;
    private Animation<TextureRegion> _animation;
    private Texture _sheet;

    /**
     * @param sheetPath path to the image of the spritesheet. the texture needs to be instantiated
     *                  here or else dispose might create bad references
     * @param cols column count of the spritesheet
     * @param rows row count of the spritesheet
     * @param fps speed of the animation
     * */
    public SheetAnimation(String sheetPath, int cols, int rows, int fps){
        _frameCols = cols;
        _frameRows = rows;
        _frameInterval = 1.0f/fps;
        _sheet = new Texture(Gdx.files.internal(sheetPath));

        _frames = getFrames(_sheet, rows, cols);
        _animation = new Animation<TextureRegion>(_frameInterval, _frames);
    }

    /**
     * Splits the spritesheet into frames
     * */
    private TextureRegion[] getFrames(Texture sheet, int rows, int cols){
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

    public void setFps(int fps){
        _frameInterval = 1.0f/fps;
        _animation = new Animation<TextureRegion>(_frameInterval, _frames);
    }

    public int getFps(){
        return Math.round(1.0f/ _frameInterval);
    }

    /**
     * samples the animation at @param sampleTime and draws it onto the spritebatch
     * @param posX x position of animation in viewport
     * @param posY y position of animation in viewport
     *
     * TODO: include sampletime in state and recalculate it every drawcall
     * */
    public void sampleAndDraw(SpriteBatch batch, float sampleTime, int posX, int posY){
        TextureRegion frame = _animation.getKeyFrame(sampleTime, true);
        batch.draw(frame, posX, posY);
    }

    /**
     * frees resources of the animation.
     * */
    @Override
    public void dispose() {
        _animation = null;
        _frames = null;
        _sheet.dispose();
    }
}
