package io.kadach.pool;

import io.kadach.model.shape.Snake;

public class SnakePool extends BasePool<Snake> {

    public SnakePool() {
        super(2, 2);
    }

    @Override
    protected Snake newObject() {
        return new Snake(0, 0, 0, null);
    }
}
