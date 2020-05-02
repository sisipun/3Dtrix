package io.kadach.model;

import java.util.List;

import io.kadach.model.base.Cell;
import io.kadach.model.base.CellContainer;

public class Shape extends CellContainer {

    private final CellMap map;

    public Shape(List<Cell> cells, CellMap map) {
        this.cells = cells;
        this.map = map;
    }

    public List<Cell> getCells() {
        return cells;
    }

    public boolean step() {
        boolean step = true;
        for (Cell cell : cells) {
            if (cell.getY() == 0 || map.isFixed(cell.getX(), (cell.getY() - 1), cell.getZ())) {
                step = false;
                break;
            }
        }

        if (step) {
            for (Cell cell : cells) {
                cell.step();
            }
        } else {
            map.fixShape(this);
        }

        return step;
    }

    public boolean moveLeft() {
        return changePosition(-1, 0);
    }

    public boolean moveUp() {
        return changePosition(0, -1);
    }

    public boolean moveRight() {
        return changePosition(1, 0);
    }

    public boolean moveDown() {
        return changePosition(0, 1);
    }

    private boolean changePosition(int dx, int dz) {
        for (Cell cell : cells) {
            if (!map.isFree(cell.getX() + dx, cell.getY(), cell.getZ() + dz)) {
                return false;
            }
        }

        for (Cell cell : cells) {
            cell.changePosition(cell.getX() + dx, cell.getY(), cell.getZ() + dz);
        }
        return true;
    }
}
