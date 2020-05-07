package io.kadach.model;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.utils.Pool;

import io.kadach.model.base.Drawable;

import static io.kadach.util.GameConstant.CELL_SIZE;
import static io.kadach.util.GameConstant.FIXED_CELL;
import static io.kadach.util.GameConstant.NONE_CELL;

public class CellMap implements Drawable, Pool.Poolable {

    private byte[][][] map;
    private int height;
    private int width;
    private int highestCell;
    private boolean overflow;

    public CellMap(int height, int width) {
        this.height = height;
        this.width = width;
        this.highestCell = 0;
        this.map = new byte[height][width][width];
        for (int y = 0; y < height; y++) {
            resetLine(y);
        }
    }

    public int removeFullSquares() {
        int fullSquareCount = 0;
        int lastFullIndex = 0;
        for (int y = 0; y <= highestCell; y++) {
            if (isFullSquare(y)) {
                fullSquareCount++;
                lastFullIndex = y;
            } else if (fullSquareCount != 0) {
                byte[][] swap = map[y];
                map[y] = map[lastFullIndex - fullSquareCount + 1];
                map[lastFullIndex - fullSquareCount + 1] = swap;
                lastFullIndex = y;
            }
        }

        for (int i = 0; i < fullSquareCount; i++) {
            resetLine(lastFullIndex - i);
        }

        return fullSquareCount;
    }

    public void addCells(int[][] cells) {
        for (int[] cell : cells) {
            map[cell[1]][cell[0]][cell[2]] = FIXED_CELL;
            if (cell[1] > highestCell) {
                highestCell = cell[1];
            }
        }
        if (highestCell >= height - 1) {
            overflow = true;
        }
    }

    public boolean isFullSquare(int y) {
        for (int x = 0; x < width; x++) {
            for (int z = 0; z < width; z++) {
                if (map[y][x][z] == NONE_CELL) {
                    return false;
                }
            }
        }
        return true;
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

    public boolean isOverflow() {
        return overflow;
    }

    private void resetLine(int y) {
        for (int x = 0; x < width; x++) {
            for (int z = 0; z < width; z++) {
                map[y][x][z] = NONE_CELL;
            }
        }
    }

    @Override
    public void draw(ModelBatch modelBatch, Environment environment, Model model) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                for (int z = 0; z < width; z++) {
                    if (map[y][x][z] == FIXED_CELL) {
                        modelBatch.render(new ModelInstance(model, x * CELL_SIZE, y * CELL_SIZE, z * CELL_SIZE), environment);
                    };
                }
            }
        }
    }

    @Override
    public void reset() {
        this.overflow = false;
        this.highestCell = 0;
        for (int y = 0; y < height; y++) {
            resetLine(y);
        }
    }
}
