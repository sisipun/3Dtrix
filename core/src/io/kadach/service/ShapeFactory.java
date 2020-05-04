package io.kadach.service;

import io.kadach.model.CellMap;
import io.kadach.model.Shape;

import static io.kadach.util.GameConstant.LINE_SHAPE;
import static io.kadach.util.GameConstant.SNAKE_LEFT_SHAPE;
import static io.kadach.util.GameConstant.SNAKE_RIGHT_SHAPE;

public class ShapeFactory {

    public static Shape generateShape(byte type, byte[] initialPoint, CellMap map) {
        assert initialPoint != null;
        assert initialPoint.length == 3;
        switch (type) {
            case LINE_SHAPE:
                return generateLine(initialPoint, map);
            case SNAKE_LEFT_SHAPE:
                return generateSnakeLeft(initialPoint, map);
            case SNAKE_RIGHT_SHAPE:
                return generateSnakeRight(initialPoint, map);
        }

        return null;
    }

    private static Shape generateLine(byte[] initialPoint, CellMap map) {
        return new Shape(new int[][]{
                {initialPoint[0], initialPoint[1] - 3, initialPoint[2]},
                {initialPoint[0], initialPoint[1] - 2, initialPoint[2]},
                {initialPoint[0], initialPoint[1] - 1, initialPoint[2]},
                {initialPoint[0], initialPoint[1], initialPoint[2]}
        }, map);
    }

    private static Shape generateSnakeLeft(byte[] initialPoint, CellMap map) {
        return new Shape(new int[][]{
                {initialPoint[0], initialPoint[1] - 2, initialPoint[2]},
                {initialPoint[0], initialPoint[1] - 1, initialPoint[2]},
                {initialPoint[0] - 1, initialPoint[1] - 1, initialPoint[2]},
                {initialPoint[0] - 1, initialPoint[1], initialPoint[2]}
        }, map);
    }

    private static Shape generateSnakeRight(byte[] initialPoint, CellMap map) {
        return new Shape(new int[][]{
                {initialPoint[0], initialPoint[1] - 2, initialPoint[2]},
                {initialPoint[0], initialPoint[1] - 1, initialPoint[2]},
                {initialPoint[0] + 1, initialPoint[1] - 1, initialPoint[2]},
                {initialPoint[0] + 1, initialPoint[1], initialPoint[2]}
        }, map);
    }

    private ShapeFactory() {
    }
}
