package io.kadach.model.shape;

import io.kadach.model.base.CellMap;
import io.kadach.model.base.Shape;

public class Snake extends Shape {
    public Snake(int x, int y, int z, CellMap map) {
        super(new int[][]{
                {x, y - 2, z},
                {x, y - 1, z},
                {x - 1, y - 1, z},
                {x - 1, y, z}
        }, map);
    }

    public Snake init(int x, int y, int z, CellMap map) {
        super.init(new int[][]{
                {x, y - 2, z},
                {x, y - 1, z},
                {x - 1, y - 1, z},
                {x - 1, y, z}
        }, map);
        return this;
    }

    @Override
    public boolean rotateLeft() {
        int dx = cells[2][0] - cells[1][0];
        int dz = cells[2][2] - cells[1][2];

        if (map.isFree(cells[2][0] - dz - dx, cells[2][1], cells[2][2] - dz + dx) &&
                map.isFree(cells[3][0] - dz - dx, cells[3][1], cells[3][2] - dz + dx)) {
            cells[2][0] -= dx + dz;
            cells[2][2] -= dz - dx;
            cells[3][0] -= dx + dz;
            cells[3][2] -= dz - dx;
            return true;
        }

        return false;
    }

    @Override
    public boolean rotateRight() {
        int dx = cells[2][0] - cells[1][0];
        int dz = cells[2][2] - cells[1][2];

        if (map.isFree(cells[2][0] + dz - dx, cells[2][1], cells[2][2] - dz + dx) &&
                map.isFree(cells[3][0] + dz - dx, cells[3][1], cells[3][2] - dz + dx)) {
            cells[2][0] += dz - dx;
            cells[2][2] -= dz + dx;
            cells[3][0] += dz - dx;
            cells[3][2] -= dz + dx;
            return true;
        }

        return false;
    }
}
