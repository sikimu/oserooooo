package oserooooo;

/**
 * オセロの石の位置を管理するクラス
 */
public class StonePoint {
    private int x;
    private int y;

    public StonePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * 複製する
     */
    public StonePoint clone() {
        return new StonePoint(this.x, this.y);
    }

    /**
     * 移動する
     */
    public void move(Direction direction) {
        this.x += direction.dx;
        this.y += direction.dy;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    /**
     * 文字列に変換する
     */
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
}
