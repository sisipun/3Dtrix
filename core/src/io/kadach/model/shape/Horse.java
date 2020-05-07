package io.kadach.model.shape;

import io.kadach.model.CellMap;
import io.kadach.model.base.Shape;

public class Horse extends Shape {
    public Horse(int x, int y, int z, CellMap map) {
        super(new int[][]{
                {x, y - 2, z},
                {x, y - 1, z},
                {x, y, z},
                {x + 1, y, z}
        }, map);
    }

    @Override
    public boolean rotateX() {
        return false;
    }

    @Override
    public boolean rotateY() {
        return false;
    }

    @Override
    public boolean rotateZ() {
        return false;
    }
}
