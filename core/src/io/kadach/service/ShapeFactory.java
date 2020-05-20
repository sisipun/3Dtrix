package io.kadach.service;

import com.badlogic.gdx.utils.Pools;

import io.kadach.model.base.CellMap;
import io.kadach.model.base.Shape;
import io.kadach.model.shape.Box;
import io.kadach.model.shape.Crown;
import io.kadach.model.shape.Horse;
import io.kadach.model.shape.Line;
import io.kadach.model.shape.Snake;
import io.kadach.pool.BoxPool;
import io.kadach.pool.CrownPool;
import io.kadach.pool.HorsePool;
import io.kadach.pool.LinePool;
import io.kadach.pool.SnakePool;

import static io.kadach.util.GameConstant.BOX_SHAPE;
import static io.kadach.util.GameConstant.CROWN_SHAPE;
import static io.kadach.util.GameConstant.HORSE_SHAPE;
import static io.kadach.util.GameConstant.LINE_SHAPE;
import static io.kadach.util.GameConstant.SNAKE_SHAPE;

public class ShapeFactory {

    public static void init() {
        Pools.set(Line.class, new LinePool());
        Pools.set(Snake.class, new SnakePool());
        Pools.set(Box.class, new BoxPool());
        Pools.set(Horse.class, new HorsePool());
        Pools.set(Crown.class, new CrownPool());
    }

    public static void free(Shape shape) {
        Pools.free(shape);
    }

    public static Shape generateShape(byte type, int x, int y, int z, CellMap map) {
        switch (type) {
            case LINE_SHAPE:
                return generateLine(x, y, z, map);
            case SNAKE_SHAPE:
                return generateSnake(x, y, z, map);
            case BOX_SHAPE:
                return generateBox(x, y, z, map);
            case HORSE_SHAPE:
                return generateHorse(x, y, z, map);
            case CROWN_SHAPE:
                return generateCrown(x, y, z, map);
        }

        return null;
    }

    private static Shape generateLine(int x, int y, int z, CellMap map) {
        return Pools.obtain(Line.class).init(x, y, z, map);
    }

    private static Shape generateSnake(int x, int y, int z, CellMap map) {
        return Pools.obtain(Snake.class).init(x, y, z, map);
    }

    private static Shape generateBox(int x, int y, int z, CellMap map) {
        return Pools.obtain(Box.class).init(x, y, z, map);
    }

    private static Shape generateHorse(int x, int y, int z, CellMap map) {
        return Pools.obtain(Horse.class).init(x, y, z, map);
    }

    private static Shape generateCrown(int x, int y, int z, CellMap map) {
        return Pools.obtain(Crown.class).init(x, y, z, map);
    }

    private ShapeFactory() {
    }
}
