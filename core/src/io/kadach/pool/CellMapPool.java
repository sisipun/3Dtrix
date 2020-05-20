package io.kadach.pool;

import io.kadach.model.base.CellMap;

public class CellMapPool extends BasePool<CellMap> {

    public CellMapPool() {
        super(1, 1);
    }

    @Override
    protected CellMap newObject() {
        return new CellMap(0, 0);
    }
}
