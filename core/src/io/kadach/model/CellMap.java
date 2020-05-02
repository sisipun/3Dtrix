package io.kadach.model;

import io.kadach.model.base.Cell;
import io.kadach.model.base.CellContainer;

import static io.kadach.util.GameConstant.FIXED_CELL;
import static io.kadach.util.GameConstant.NONE_CELL;

public class CellMap extends CellContainer {

    private byte[][][] map;
    private int height;
    private int width;

    public CellMap(int height, int width) {
        this.height = height;
        this.width = width;
        this.map = new byte[height][width][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                for (int z = 0; z < width; z++) {
                    map[y][x][z] = NONE_CELL;
                }
            }
        }
    }

    public void fixShape(Shape shape) {
        for (Cell cell : shape.getCells()) {
            map[cell.getY()][cell.getX()][cell.getZ()] = FIXED_CELL;
            cells.add(cell);
        }
    }

    public boolean isFixed(int x, int y, int z) {
        return x >= 0 && x < width
                && y >= 0 && y < height
                && z >= 0 && z < width
                && map[y][x][z] == FIXED_CELL;
    }

    public boolean isFree(int x, int y, int z) {
        return x >= 0 && x < width
                && y >= 0 && y < height
                && z >= 0 && z < width
                && map[y][x][z] == NONE_CELL;
    }
}
