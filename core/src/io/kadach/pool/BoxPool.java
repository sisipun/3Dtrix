package io.kadach.pool;

import io.kadach.model.shape.Box;

public class BoxPool extends BasePool<Box> {

    public BoxPool() {
        super(2, 2);
    }

    @Override
    protected Box newObject() {
        return new Box(0, 0, 0, null);
    }
}
