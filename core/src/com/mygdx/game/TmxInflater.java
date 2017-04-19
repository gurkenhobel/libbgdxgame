package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by nils on 19.04.17.
 */
public class TmxInflater {
    private TiledMap _map;

    private MapLayer _mainLogicLayer;


    public TmxInflater(TiledMap map){
        _map = map;
        _mainLogicLayer = map.getLayers().get(1);
    }

    public OrthographicCamera getCamera(){
        //poll the camera from the logic layer
        RectangleMapObject cameraData = (RectangleMapObject) _mainLogicLayer.getObjects().get("camera");
        //poll the camera property collection
        MapProperties cameraProperties = cameraData.getProperties();
        //instatantiate the camera
        OrthographicCamera camera = new OrthographicCamera();
        //initialize the camera with the state from the injected properties
        //default position is the middle left of the screen
        float xPos = cameraProperties.get("X", 0f, float.class);
        float yPos = cameraProperties.get("Y", Gdx.graphics.getHeight()/2f, float.class);
        camera.position.set(new Vector3(xPos, yPos, 0));

        return  camera;
    }
}
