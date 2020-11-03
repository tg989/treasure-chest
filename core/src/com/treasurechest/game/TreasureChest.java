package com.treasurechest.game;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.utils.Array;

public class TreasureChest extends Game /* implements GestureDetector.GestureListener */{
	private Game treasureChest;
	ModelBatch modelBatch;
	PerspectiveCamera camera;
	CameraInputController cameraController;
	AssetManager assets;
	Array<ModelInstance> instances = new Array<ModelInstance>();
	//Box chest;
	Environment environment;
	// AnimationController controller;
	boolean loading;
	private CanvasScreen canvasScreen;

	@Override
	public void create () {

		this.setScreen(new CanvasScreen(treasureChest));
		/* MOVE
		modelBatch = new ModelBatch();
		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.2f, 0.2f, 0.2f, 1f));
		environment.add(new DirectionalLight().set(0.8f, 0.8f,0.8f, -1f, -0.8f, -0.2f));

		camera = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.position.set(0f, 3f, 0f);
		camera.rotate(90f, 0f, 1f, 0f);
		camera.lookAt(0,0,0);
		camera.near = 1f;
		camera.far = 300f;
		camera.update();

		cameraController = new CameraInputController(camera);
		Gdx.input.setInputProcessor(cameraController);

		Gdx.input.setInputProcessor(new GestureDetector(this));

		assets = new AssetManager();
		assets.load("Chest.g3db", Model.class);
		//assets.load("core/assets/Brush.g3db", Model.class);

		// controller = new AnimationController(null);

		loading = true;

		*/
	}

	/*
	private void doneLoading() {
		Model box = assets.get("Chest.g3db", Model.class);
		chest = new Box(box);
		chest.transform.scale(0.2f, 0.2f, 0.2f);

		//controller = new AnimationController(boxInstance);
		//controller.setAnimation("openBox");
		//chest.openBox();

		instances.add(chest);

		/*
		Model brush = assets.get("/core/assets/Brush.g3db", Model.class);
		ModelInstance brushInstance = new ModelInstance(brush);
		brushInstance.transform.scale(0.2f, 0.2f, 0.2f);

		instances.add(brushInstance);

		 */
	/*

		loading = false;
	}
	*/

	@Override
	public void render () {
		super.render();

		/* MOVE
		if (loading && assets.update()) {
			doneLoading();
		}
		cameraController.update();

		/*
		if (controller.current != null) {
			controller.update(Gdx.graphics.getDeltaTime());
		}

		 */
		/* MOVE
		if (chest != null) {
			chest.update(Gdx.graphics.getDeltaTime());
		}

		Gdx.gl.glViewport(0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		modelBatch.begin(camera);
		modelBatch.render(instances, environment);
		modelBatch.end();
		*/

	}

	@Override
	public void dispose () {
		// modelBatch.dispose();
	//	chest.dispose();
		instances.clear();
		assets.dispose();
	}
	/*
	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		System.out.println(x + " " + y + " " + button);
		/*
		Vector3 position = new Vector3();

		Ray ray = camera.getPickRay(x, y);
		System.out.println(ray);
		float dis = chest.intersects(chest.transform, ray);
		if(dis >= 0f) {
			System.out.println("True");
		} else {
			System.out.println("False");
		}

		 */

		//System.out.println(chest.contains(ray));

		/*
		if (chest.contains(x, y)) {
			System.out.println("Touch detected");
			chest.openBox();
		}

		 */
		/*
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		return false;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
		return false;
	}

	@Override
	public void pinchStop() {

	}

		 */
}
