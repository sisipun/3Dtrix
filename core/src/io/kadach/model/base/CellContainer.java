package io.kadach.model.base;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;

import java.util.LinkedList;
import java.util.List;

public abstract class CellContainer {

    protected List<Cell> cells;

    public CellContainer() {
        this.cells = new LinkedList<>();
    }

    public void draw(ModelBatch modelBatch, Environment environment) {
        for (Cell cell : cells) {
            cell.draw(modelBatch, environment);
        }
    }
}
