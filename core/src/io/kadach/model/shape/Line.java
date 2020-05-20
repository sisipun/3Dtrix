package io.kadach.model.shape;

import io.kadach.model.base.CellMap;
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

    public Line init(int x, int y, int z, CellMap map) {
        super.init(new int[][]{
                {x, y - 3, z},
                {x, y - 2, z},
                {x, y - 1, z},
                {x, y, z}
        }, map);
        return this;
    }
}
