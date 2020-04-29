package io.kadach;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;

import java.util.LinkedList;
import java.util.List;

public class Game extends ApplicationAdapter {

	private static final float CELL_SIZE = 2f;

    private PerspectiveCamera cam;
    private ModelBatch modelBatch;
    private Model model;
    private List<ModelInstance> instances;
    private Environment environment;
    private byte[][][] map = {
            {
                    {1, 0, 0},
                    {0, 1, 0},
                    {0, 0, 1}
            },
            {
                    {1, 0, 0},
                    {0, 0, 0},
                    {0, 0, 0}
            },
            {
                    {1, 0, 0},
                    {0, 0, 0},
                    {0, 0, 0}
            },
			{
					{1, 0, 0},
					{0, 0, 0},
					{0, 0, 0}
			}
    };

    @Override
    public void create() {
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
        modelBatch = new ModelBatch();

        cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(10f, 10f, 10f);
        cam.lookAt(0, 0, 0);
        cam.near = 1f;
        cam.far = 300f;
        cam.update();

        ModelBuilder modelBuilder = new ModelBuilder();
        model = modelBuilder.createBox(CELL_SIZE, CELL_SIZE, CELL_SIZE,
                new Material(ColorAttribute.createDiffuse(Color.GREEN)),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
		instances = new LinkedList<>();
        for (int x = 0; x < map.length; x++) {
        	for (int y = 0; y < map[0].length; y++) {
        		for (int z = 0; z < map[0][0].length; z++) {
        			if (map[x][y][z] == 1) {
						instances.add(new ModelInstance(model,  CELL_SIZE * x, CELL_SIZE * y, CELL_SIZE * z));
					}
				}
			}
		}
    }

    @Override
    public void render() {
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        modelBatch.begin(cam);
        for (ModelInstance instance : instances) {
			modelBatch.render(instance, environment);
		}
        modelBatch.end();
    }

    @Override
    public void dispose() {
        modelBatch.dispose();
        model.dispose();
    }
}
