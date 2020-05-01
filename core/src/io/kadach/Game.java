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
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;

import java.util.Random;

import io.kadach.model.CellMap;
import io.kadach.model.Shape;
import io.kadach.service.ShapeFactory;

import static io.kadach.util.GameConstant.CELL_HEIGHT;
import static io.kadach.util.GameConstant.CELL_SIZE;
import static io.kadach.util.GameConstant.CELL_WIDTH;
import static io.kadach.util.GameConstant.SHAPE_TYPES_COUNT;

public class Game extends ApplicationAdapter {

    private PerspectiveCamera camera;
    private Environment environment;
    private ModelBatch modelBatch;
    private Model cellModel;
    private Shape currentShape;
    private CellMap cellMap;
    private byte[] initialPoint;
    private Random random;

    @Override
    public void create() {
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
        modelBatch = new ModelBatch();

        camera = new PerspectiveCamera(70, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(10f, 10f, 10f);
        camera.lookAt(0, 0, 0);
        camera.near = 1f;
        camera.far = 300f;
        camera.update();

        random = new Random();
        initialPoint = new byte[]{4, 12, 4};

        ModelBuilder modelBuilder = new ModelBuilder();
        cellModel = modelBuilder.createBox(
                CELL_SIZE,
                CELL_SIZE,
                CELL_SIZE,
                new Material(ColorAttribute.createDiffuse(Color.GREEN)),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal
        );
        cellMap = new CellMap(CELL_HEIGHT, CELL_WIDTH);
        currentShape = ShapeFactory.generateShape((byte) random.nextInt(SHAPE_TYPES_COUNT), initialPoint, cellModel, cellMap);
    }

    @Override
    public void render() {
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        modelBatch.begin(camera);
        cellMap.draw(modelBatch, environment);
        currentShape.draw(modelBatch, environment);
        modelBatch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            step();
        }
    }

    @Override
    public void dispose() {
        modelBatch.dispose();
        cellModel.dispose();
    }

    public void step() {
        if (!currentShape.step()) {
            currentShape = ShapeFactory.generateShape((byte) random.nextInt(SHAPE_TYPES_COUNT), initialPoint, cellModel, cellMap);
        };
    }
}
