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
}
