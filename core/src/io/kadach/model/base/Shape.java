package io.kadach.model.base;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.utils.Pool;

import static io.kadach.util.GameConstant.CELL_SIZE;

public abstract class Shape implements Rotatable, Drawable, Pool.Poolable {

    protected CellMap map;
    protected int[][] cells;

    public Shape(int[][] cells, CellMap map) {
        this.cells = cells;
        this.map = map;
    }

    protected Shape init(int[][] cells, CellMap map) {
        this.cells = cells;
        this.map = map;
        return this;
    }

    public void clone(Shape shape) {
        this.cells = new int[shape.cells.length][shape.cells[0].length];
        for (int i = 0; i < shape.cells.length; i++) {
            for (int j = 0; j < shape.cells[0].length; j++) {
                this.cells[i][j] = shape.cells[i][j];
            }
        }
        this.map = shape.map;
    }

    public boolean step() {
        boolean step = true;
        for (int[] cell : cells) {
            if (cell[1] == 0 || map.isFixed(cell[0], (cell[1] - 1), cell[2])) {
                step = false;
                break;
            }
        }

        if (step) {
            for (int[] cell : cells) {
                cell[1]--;
            }
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
        for (int[] cell : cells) {
            if (!map.isFree(cell[0] + dx, cell[1], cell[2] + dz)) {
                return false;
            }
        }

        for (int[] cell : cells) {
            cell[0] += dx;
            cell[2] += dz;
        }

        return true;
    }

    @Override
    public void draw(ModelBatch modelBatch, Environment environment, Model model) {
        for (int[] cell : cells) {
            modelBatch.render(new ModelInstance(model, cell[0] * CELL_SIZE, cell[1] * CELL_SIZE, cell[2] * CELL_SIZE), environment);
        }
    }

    @Override
    public void reset() {
        map = null;
    }

    @Override
    public boolean rotateLeft() {
        return false;
    }

    @Override
    public boolean rotateRight() {
        return false;
    }

    public int[][] getCells() {
        return cells;
    }
}
