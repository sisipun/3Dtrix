package io.kadach;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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

    private static final float CELL_SIZE = 1f;
    private static final byte FIXED_CELL = 2;
    private static final byte MOVING_CELL = 1;
    private static final byte NONE_CELL = 0;

    private PerspectiveCamera cam;
    private ModelBatch modelBatch;
    private Model movingCellModel;
    private Model fixedCellModel;
    private List<ModelInstance> instances;
    private Environment environment;
    private byte[][] currentShapeCords = {{2, 4, 1}, {0, 5, 1}, {1, 5, 1}, {2, 5, 1}};
    private byte[][][] map = {
            {
                    {0, 0, 0},
                    {0, 0, 0},
                    {0, 0, 0}
            },
            {
                    {0, 0, 0},
                    {0, 0, 0},
                    {0, 0, 0}
            },
            {
                    {0, 0, 0},
                    {0, 0, 0},
                    {0, 0, 0}
            },
            {
                    {0, 0, 0},
                    {0, 0, 0},
                    {0, 0, 0}
            },
            {
                    {0, 0, 0},
                    {0, 0, 0},
                    {0, 1, 0}
            },
            {
                    {0, 1, 0},
                    {0, 1, 0},
                    {0, 1, 0}
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
        movingCellModel = modelBuilder.createBox(CELL_SIZE, CELL_SIZE, CELL_SIZE,
                new Material(ColorAttribute.createDiffuse(Color.GREEN)),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
        fixedCellModel = modelBuilder.createBox(CELL_SIZE, CELL_SIZE, CELL_SIZE,
                new Material(ColorAttribute.createDiffuse(Color.RED)),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
        instances = new LinkedList<>();
        visualizeMap();
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

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            step();
        }
    }

    @Override
    public void dispose() {
        modelBatch.dispose();
        movingCellModel.dispose();
    }

    public void step() {
        boolean moveShape = true;
        for (int i = 0; i < currentShapeCords.length; i++) {
            if (currentShapeCords[i][1] == 0 || map[currentShapeCords[i][1] - 1][currentShapeCords[i][0]][currentShapeCords[i][2]] == 2) {
                moveShape = false;
                break;
            }
        }
        if (moveShape) {
            for (int i = 0; i < currentShapeCords.length; i++) {
                map[currentShapeCords[i][1] - 1][currentShapeCords[i][0]][currentShapeCords[i][2]] = 1;
                map[currentShapeCords[i][1]][currentShapeCords[i][0]][currentShapeCords[i][2]] = 0;
                currentShapeCords[i][1]--;
            }
        } else {
            for (byte[] currentShapeCord : currentShapeCords) {
                map[currentShapeCord[1]][currentShapeCord[0]][currentShapeCord[2]] = 2;
            }
        }
        visualizeMap();
    }

    public void visualizeMap() {
        instances.clear();
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[0].length; x++) {
                for (int z = 0; z < map[0][0].length; z++) {
                    if (map[y][x][z] == MOVING_CELL) {
                        instances.add(new ModelInstance(movingCellModel, CELL_SIZE * x, CELL_SIZE * y, CELL_SIZE * z));
                    } else if (map[y][x][z] == FIXED_CELL) {
                        instances.add(new ModelInstance(fixedCellModel, CELL_SIZE * x, CELL_SIZE * y, CELL_SIZE * z));
                    }
                }
            }
        }
    }
}
