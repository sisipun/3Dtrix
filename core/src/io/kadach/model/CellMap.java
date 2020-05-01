package io.kadach.model;

import io.kadach.model.base.Cell;
import io.kadach.model.base.CellContainer;

import static io.kadach.util.GameConstant.FIXED_CELL;
import static io.kadach.util.GameConstant.NONE_CELL;

public class CellMap extends CellContainer {

    private byte[][][] map;

    public CellMap(int height, int width) {
        this.map = new byte[height][width][width];
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[0].length; x++) {
                for (int z = 0; z < map[0][0].length; z++) {
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
        return map[y][x][z] == FIXED_CELL;
    }
}
