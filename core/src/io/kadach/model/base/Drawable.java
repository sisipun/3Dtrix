package io.kadach.model.base;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;

public interface Drawable {

    void draw(ModelBatch modelBatch, Environment environment, Model model);

}
