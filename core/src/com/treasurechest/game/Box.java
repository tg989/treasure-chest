package com.treasurechest.game;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.utils.AnimationController;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

public class Box extends ModelInstance implements Disposable {
    private Array<Tray> trays = new Array<Tray>();
    private AnimationController controller = new AnimationController(this);
    private BoundingBox bounds = new BoundingBox();
    private Vector3 position = new Vector3();
    private Vector3 centre = new Vector3();
    private  Vector3 dimensions = new Vector3();

    public Box(Model model) {
        super(model);
        calculateBoundingBox(bounds);
        bounds.getCenter(centre);
        bounds.getDimensions(dimensions);
    }

    public void openBox() {
        controller.setAnimation("openBox");
    }

    public AnimationController getController() {
        return controller;
    }

    public void update(float delta) {
        controller.update(delta);
    }

    public boolean isVisible(Matrix4 transform, Camera camera) {
        return camera.frustum.boundsInFrustum(transform.getTranslation(position).add(centre), dimensions);
    }

    public float intersects(Matrix4 transform, Ray ray) {
        transform.getTranslation(position).add(centre);
        if (Intersector.intersectRayBoundsFast(ray, position, dimensions)) {
            float length = ray.direction.dot(position.x - ray.origin.x, position.y - ray.origin.y, position.z - ray.origin.z);
            return position.dst2(ray.origin.x + ray.direction.x * length, ray.origin.y + ray.direction.y * length, ray.origin.z + ray.direction.z * length);
        }
        return -1f;
    }
    /*

    public boolean contains(Ray ray) {
        calculateBoundingBox(bounds);
        Vector3 position = new Vector3();
        transform.getTranslation(position);
        position.add(centre);
        return Intersector.intersectRayBounds(ray, bounds, null);
    }

     */

    @Override
    public void dispose() {
        trays.clear();
    }
}
