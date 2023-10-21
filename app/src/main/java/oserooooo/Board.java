package oserooooo;

/**
 * 盤面のデータを管理するクラス
 * 配列の都合上、xとyを逆にしているので認識しやすくするための意味合いが強い
 */
public class Board{
    private final Stone[][] boardData;

    public Board(int w, int h) {
        boardData = new Stone[h][w];
    }

    public Stone get(StonePoint point) {
        return get(point.getX(), point.getY());
    }

    public Stone get(int x, int y) {
        // 範囲外の場合はnullを返す
        if (y < 0 || y >= this.boardData.length || 
            x < 0 || x >= this.boardData[y].length) {
            return null;
        }
        return this.boardData[y][x];
    }

    public void put(StonePoint point, Stone stone) {
        this.boardData[point.getY()][point.getX()] = stone;
    }

    public void put(int x, int y, Stone stone) {
        this.boardData[y][x] = stone;
    }

    public int getW() {
        return boardData[0].length;
    }

    public int getH() {
        return boardData.length;
    }
}
