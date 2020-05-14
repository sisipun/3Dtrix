package io.kadach.model.shape;

import io.kadach.model.CellMap;
import io.kadach.model.base.Shape;

public class Box extends Shape {
    public Box(int x, int y, int z, CellMap map) {
        super(new int[][]{
                {x, y - 1, z},
                {x + 1, y - 1, z},
                {x, y - 1, z + 1},
                {x + 1, y - 1, z + 1},
                {x, y, z},
                {x + 1, y, z},
                {x, y, z + 1},
                {x + 1, y, z + 1},
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
