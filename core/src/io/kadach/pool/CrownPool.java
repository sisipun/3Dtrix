package io.kadach.pool;

import io.kadach.model.shape.Crown;

public class CrownPool extends BasePool<Crown> {

    public CrownPool() {
        super(2, 2);
    }

    @Override
    protected Crown newObject() {
        return new Crown(0, 0, 0, null);
    }
}
