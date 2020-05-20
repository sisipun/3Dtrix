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
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Timer;

import java.util.Random;

import io.kadach.model.CellMap;
import io.kadach.model.base.Shape;
import io.kadach.service.ShapeFactory;

import static io.kadach.util.GameConstant.CELL_MAP_HEIGHT;
import static io.kadach.util.GameConstant.CELL_MAP_WIDTH;
import static io.kadach.util.GameConstant.CELL_SIZE;
import static io.kadach.util.GameConstant.SHAPE_TYPES_COUNT;

public class Game extends ApplicationAdapter {

    private PerspectiveCamera camera;
    private Environment environment;
    private ModelBatch modelBatch;
    private Model cellModel;
    private Model mapModel;
    private Model shadowModel;
    private Shape currentShape;
    private Shape shapeShadow;
    private CellMap cellMap;
    private int initialX;
    private int initialY;
    private int initialZ;
    private Vector3 cameraAttentionPoint;
    private Random random;
    private int score;

    @Override
    public void create() {
        random = new Random();
        initialX = 3;
        initialY = 12;
        initialZ = 3;
        cameraAttentionPoint = new Vector3(3, 0, 3);
        score = 0;

        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
        modelBatch = new ModelBatch();

        camera = new PerspectiveCamera(90, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(15f, 15f, 15f);
        camera.lookAt(cameraAttentionPoint);
        camera.near = 1f;
        camera.far = 300f;
        camera.update();

        ModelBuilder modelBuilder = new ModelBuilder();
        cellModel = modelBuilder.createBox(
                CELL_SIZE,
                CELL_SIZE,
                CELL_SIZE,
                new Material(ColorAttribute.createDiffuse(Color.GREEN)),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal
        );
        mapModel = modelBuilder.createBox(
                CELL_SIZE,
                CELL_SIZE,
                CELL_SIZE,
                new Material(ColorAttribute.createDiffuse(Color.RED)),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal
        );
        shadowModel = modelBuilder.createBox(
                CELL_SIZE,
                CELL_SIZE,
                CELL_SIZE,
                new Material(ColorAttribute.createDiffuse(Color.YELLOW)),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal
        );
        cellMap = new CellMap(CELL_MAP_HEIGHT, CELL_MAP_WIDTH);
        generateShape();
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                step();
                Gdx.app.log("Score:", String.valueOf(score));
            }
        }, .5f, .5f);
    }

    @Override
    public void render() {
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        modelBatch.begin(camera);
        shapeShadow.draw(modelBatch, environment, shadowModel);
        cellMap.draw(modelBatch, environment, mapModel);
        currentShape.draw(modelBatch, environment, cellModel);
        modelBatch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            currentShape.moveLeft();
            updateShape();
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            currentShape.moveUp();
            updateShape();
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            currentShape.moveRight();
            updateShape();
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            currentShape.moveDown();
            updateShape();
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
            camera.rotateAround(cameraAttentionPoint, new Vector3(0, 1, 0), 10);
            camera.update();
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
            camera.rotateAround(cameraAttentionPoint, new Vector3(0, 1, 0), -10);
            camera.update();
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            while (step()) ;
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.C)) {
            currentShape.rotateRight();
            updateShape();
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.Z)) {
            currentShape.rotateLeft();
            updateShape();
        }
    }

    @Override
    public void dispose() {
        modelBatch.dispose();
        cellModel.dispose();
    }

    private boolean step() {
        if (!currentShape.step()) {
            cellMap.addCells(currentShape);
            score += cellMap.removeFullSquares();
            if (cellMap.isOverflow()) {
                cellMap.reset();
                score = 0;
            }
            generateShape();
            return false;
        }
        return true;
    }

    private void generateShape() {
        byte type = (byte) random.nextInt(SHAPE_TYPES_COUNT);
        currentShape = ShapeFactory.generateShape(type, initialX, initialY, initialZ, cellMap);
        shapeShadow = ShapeFactory.generateShape(type, initialX, initialY, initialZ, cellMap);
        while (shapeShadow.step()) ;
    }

    private void updateShape() {
        shapeShadow.clone(currentShape);
        while (shapeShadow.step()) ;
    }
}
