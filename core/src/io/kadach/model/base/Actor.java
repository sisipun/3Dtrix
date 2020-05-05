package io.kadach.model.base;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.utils.Pool;

public abstract class Actor implements Pool.Poolable {

    public abstract void draw(ModelBatch modelBatch, Environment environment, Model model);

}
