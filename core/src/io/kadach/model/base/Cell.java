package io.kadach.model.base;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;

public class Cell extends ModelInstance {

    private int x;
    private int y;
    private int z;
    private float size;

    public Cell(int x, int y, int z, float size, Model model) {
        super(model, x * size, y * size, z * size);
        this.x = x;
        this.y = y;
        this.z = z;
        this.size = size;
    }

    public void step() {
        changePosition(this.x, this.y - 1, this.z);
    }

    public void draw(ModelBatch modelBatch, Environment environment) {
        modelBatch.render(this, environment);
    }

    public void changePosition(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.transform.setToTranslation(this.x * size, this.y * size, this.z * size);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }
}
