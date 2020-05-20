package io.kadach.pool;

import io.kadach.model.shape.Horse;

public class HorsePool extends BasePool<Horse> {

    public HorsePool() {
        super(2, 2);
    }

    @Override
    protected Horse newObject() {
        return new Horse(0, 0, 0, null);
    }
}
