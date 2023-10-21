package oserooooo;

import java.util.ArrayList;

/**
 * 探索方向を表すクラス
 */
public class Direction {

    public static final Direction UP = new Direction(0, -1);
    public static final Direction DOWN = new Direction(0, 1);
    public static final Direction LEFT = new Direction(-1, 0);
    public static final Direction RIGHT = new Direction(1, 0);
    public static final Direction RIGHT_UP = new Direction(1, -1);
    public static final Direction RIGHT_DOWN = new Direction(1, 1);
    public static final Direction LEFT_UP = new Direction(-1, -1);
    public static final Direction LEFT_DOWN = new Direction(-1, 1);


    public final int dx;
    public final int dy;

    private Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * 全方向を取得する
     */

    public static ArrayList<Direction> getAllDirections() {
        ArrayList<Direction> directions = new ArrayList<>();
        directions.add(UP);
        directions.add(DOWN);
        directions.add(LEFT);
        directions.add(RIGHT);
        directions.add(RIGHT_UP);
        directions.add(RIGHT_DOWN);
        directions.add(LEFT_UP);
        directions.add(LEFT_DOWN);
        return directions;
    }
} 