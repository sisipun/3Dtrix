package io.kadach.pool;

import io.kadach.model.shape.Line;

public class LinePool extends BasePool<Line> {

    public LinePool() {
        super(2, 2);
    }

    @Override
    protected Line newObject() {
        return new Line(0, 0, 0, null);
    }
}
