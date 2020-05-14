package io.kadach.model.shape;

import io.kadach.model.CellMap;
import io.kadach.model.base.Shape;

public class Line extends Shape {
    public Line(int x, int y, int z, CellMap map) {
        super(new int[][]{
                {x, y - 3, z},
                {x, y - 2, z},
                {x, y - 1, z},
                {x, y, z}
        }, map);
    }

    @Override
    public boolean rotateLeft() {
        return false;
    }

    @Override
    public boolean rotateRight() {
        return false;
    }
}
