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
    public boolean rotateLeft() {
        int dx = cells[3][0] - cells[2][0];
        int dz = cells[3][2] - cells[2][2];

        if (map.isFree(cells[3][0] - dz - dx, cells[3][1], cells[3][2] - dz + dx)) {
            cells[3][0] -= dx + dz;
            cells[3][2] -= dz - dx;
            return true;
        }

        return false;
    }

    @Override
    public boolean rotateRight() {
        int dx = cells[3][0] - cells[2][0];
        int dz = cells[3][2] - cells[2][2];

        if (map.isFree(cells[3][0] + dz - dx, cells[3][1], cells[3][2] - dz + dx)) {
            cells[3][0] += dz - dx;
            cells[3][2] -= dz + dx;
            return true;
        }

        return false;
    }
}
