package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

/**
 * Created by nils on 12.04.17.
 */
public class ResourceManager {
    private static ResourceManager ourInstance = new ResourceManager();
    public static ResourceManager getInstance() {
        return ourInstance;
    }


    private HashMap<String, Texture> _textures;


    private ResourceManager() {
        _textures = new HashMap<String, Texture>();
    }

    public Texture loadTexture(String texturePath){
        Texture result = null;
        if(_textures.containsKey(texturePath)){
            result = _textures.get(texturePath);
        }
        else {
            result = new Texture(texturePath);
            _textures.put(texturePath, result);
        }
        return result;
    }

    public void clear(){
        for(Texture texture : _textures.values()){
            texture.dispose();
        }
        _textures.clear();
    }
}
