package io.kadach.service;

import io.kadach.model.CellMap;
import io.kadach.model.Shape;

import static io.kadach.util.GameConstant.BOX_SHAPE;
import static io.kadach.util.GameConstant.CROWN_SHAPE;
import static io.kadach.util.GameConstant.HORSE_SHAPE;
import static io.kadach.util.GameConstant.LINE_SHAPE;
import static io.kadach.util.GameConstant.SNAKE_SHAPE;

public class ShapeFactory {

    public static Shape generateShape(byte type, byte[] initialPoint, CellMap map) {
        assert initialPoint != null;
        assert initialPoint.length == 3;
        switch (type) {
            case LINE_SHAPE:
                return generateLine(initialPoint, map);
            case SNAKE_SHAPE:
                return generateSnake(initialPoint, map);
            case BOX_SHAPE:
                return generateBox(initialPoint, map);
            case HORSE_SHAPE:
                return generateHorse(initialPoint, map);
            case CROWN_SHAPE:
                return generateCrown(initialPoint, map);
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

    private static Shape generateSnake(byte[] initialPoint, CellMap map) {
        return new Shape(new int[][]{
                {initialPoint[0], initialPoint[1] - 2, initialPoint[2]},
                {initialPoint[0], initialPoint[1] - 1, initialPoint[2]},
                {initialPoint[0] - 1, initialPoint[1] - 1, initialPoint[2]},
                {initialPoint[0] - 1, initialPoint[1], initialPoint[2]}
        }, map);
    }

    private static Shape generateBox(byte[] initialPoint, CellMap map) {
        return new Shape(new int[][]{
                {initialPoint[0], initialPoint[1] - 1, initialPoint[2]},
                {initialPoint[0] + 1, initialPoint[1] - 1, initialPoint[2]},
                {initialPoint[0], initialPoint[1] - 1, initialPoint[2] + 1},
                {initialPoint[0] + 1, initialPoint[1] - 1, initialPoint[2] + 1},
                {initialPoint[0], initialPoint[1], initialPoint[2]},
                {initialPoint[0] + 1, initialPoint[1], initialPoint[2]},
                {initialPoint[0], initialPoint[1], initialPoint[2] + 1},
                {initialPoint[0] + 1, initialPoint[1], initialPoint[2] + 1},
        }, map);
    }

    private static Shape generateHorse(byte[] initialPoint, CellMap map) {
        return new Shape(new int[][]{
                {initialPoint[0], initialPoint[1] - 2, initialPoint[2]},
                {initialPoint[0], initialPoint[1] - 1, initialPoint[2]},
                {initialPoint[0], initialPoint[1], initialPoint[2]},
                {initialPoint[0] + 1, initialPoint[1], initialPoint[2]}
        }, map);
    }

    private static Shape generateCrown(byte[] initialPoint, CellMap map) {
        return new Shape(new int[][]{
                {initialPoint[0], initialPoint[1] - 2, initialPoint[2]},
                {initialPoint[0], initialPoint[1] - 1, initialPoint[2]},
                {initialPoint[0] + 1, initialPoint[1] - 1, initialPoint[2]},
                {initialPoint[0], initialPoint[1], initialPoint[2]}
        }, map);
    }

    private ShapeFactory() {
    }
}
