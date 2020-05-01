package io.kadach.service;

import com.badlogic.gdx.graphics.g3d.Model;

import java.util.Arrays;

import io.kadach.model.CellMap;
import io.kadach.model.Shape;
import io.kadach.model.base.Cell;

import static io.kadach.util.GameConstant.CELL_SIZE;
import static io.kadach.util.GameConstant.LINE_SHAPE;
import static io.kadach.util.GameConstant.SNAKE_LEFT_SHAPE;
import static io.kadach.util.GameConstant.SNAKE_RIGHT_SHAPE;

public class ShapeFactory {

    public static Shape generateShape(byte type, byte[] initialPoint, Model cellModel, CellMap map) {
        assert initialPoint != null;
        assert initialPoint.length == 3;
        switch (type) {
            case LINE_SHAPE:
                return generateLine(initialPoint, cellModel, map);
            case SNAKE_LEFT_SHAPE:
                return generateSnakeLeft(initialPoint, cellModel, map);
            case SNAKE_RIGHT_SHAPE:
                return generateSnakeRight(initialPoint, cellModel, map);
        }

        return null;
    }

    private static Shape generateLine(byte[] initialPoint, Model cellModel, CellMap map) {
        return new Shape(Arrays.asList(
                new Cell(initialPoint[0], initialPoint[1] - 3, initialPoint[2], CELL_SIZE, cellModel),
                new Cell(initialPoint[0], initialPoint[1] - 2, initialPoint[2], CELL_SIZE, cellModel),
                new Cell(initialPoint[0], initialPoint[1] - 1, initialPoint[2], CELL_SIZE, cellModel),
                new Cell(initialPoint[0], initialPoint[1], initialPoint[2], CELL_SIZE, cellModel)
        ), map);
    }

    private static Shape generateSnakeLeft(byte[] initialPoint, Model cellModel, CellMap map) {
        return new Shape(Arrays.asList(
                new Cell(initialPoint[0], initialPoint[1] - 2, initialPoint[2], CELL_SIZE, cellModel),
                new Cell(initialPoint[0], initialPoint[1] - 1, initialPoint[2], CELL_SIZE, cellModel),
                new Cell(initialPoint[0] - 1, initialPoint[1] - 1, initialPoint[2], CELL_SIZE, cellModel),
                new Cell(initialPoint[0] - 1, initialPoint[1], initialPoint[2], CELL_SIZE, cellModel)
        ), map);
    }

    private static Shape generateSnakeRight(byte[] initialPoint, Model cellModel, CellMap map) {
        return new Shape(Arrays.asList(
                new Cell(initialPoint[0], initialPoint[1] - 2, initialPoint[2], CELL_SIZE, cellModel),
                new Cell(initialPoint[0], initialPoint[1] - 1, initialPoint[2], CELL_SIZE, cellModel),
                new Cell(initialPoint[0] + 1, initialPoint[1] - 1, initialPoint[2], CELL_SIZE, cellModel),
                new Cell(initialPoint[0] + 1, initialPoint[1], initialPoint[2], CELL_SIZE, cellModel)
        ), map);
    }

    private ShapeFactory() {
    }
}
