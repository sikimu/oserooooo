package oserooooo;

import java.util.ArrayList;

/**
 * オセロの盤面を管理するクラス
 */
public class BoardMaster {

    private final Board boardData;

    public BoardMaster(Board boardData) {
        this.boardData = boardData;
    }

    public boolean isFinish() {
        return isPass(Stone.BLACK) && isPass(Stone.WHITE);
    }

    /**
     * パスかどうか(おける場所があるかどうか)
     * @param stone
     * @return
     */
    public boolean isPass(Stone stone) {
        for (int x = 0; x < boardData.getW(); x++) {
            for (int y = 0; y < boardData.getH(); y++) {
                StonePoint point = new StonePoint(x, y);
                if (canPut(point, stone)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 石を置けるかどうか(全方向)
     * @param point
     * @param stone
     * @return
     */
    public boolean canPut(StonePoint point, Stone stone) {
        // すでに石が置いてある場合は置けない
        if (boardData.get(point) != null) {
            return false;
        }

        return Direction.getAllDirections().stream().filter(direction -> canPut(point, stone, direction)).count() > 0;
    }

    /**
     * 石を置けるかどうか(一方向)
     * @param point
     * @param stone
     * @param direction
     * @return
     */
    public boolean canPut(StonePoint point, Stone stone, Direction direction) {
        // すでに石が置いてある場合は置けない
        if (boardData.get(point) != null) {
            return false;
        }

        StonePoint p = point.clone();
        p.move(direction);

        // 相手の石がない場合は置けない
        if(boardData.get(p) != stone.getOpponent()){
            return false;
        }

        // 相手の石が続いている間は続け、自分の石があれば置ける
        while(boardData.get(p) != null){
            if(boardData.get(p) == stone){
                return true;
            }
            p.move(direction);
        }

        return false;
    }    

    /**
     * 石を置く(ひっくり返す)
     * @param point
     * @param stone
     */
    public void put(StonePoint point, Stone stone) {

        for(Direction direction: Direction.getAllDirections()){

            if(!canPut(point, stone, direction)){
                continue;
            }

            StonePoint p = point.clone();            
            while(true){            
                if(boardData.get(p) == stone){
                    break;
                }
                boardData.put(p, stone);
                p.move(direction);                    
            }
        }
    }

    /**
     * 石の数を取得
     * @param stone
     * @return
     */
    public int countStone(Stone stone) {
        int count = 0;
        for (int x = 0; x < this.boardData.getW(); x++) {
            for (int y = 0; y < this.boardData.getH(); y++) {
                if (this.boardData.get(x, y) == stone) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 置ける場所を取得
     */
    public ArrayList<StonePoint> getPuttablePoints(Stone stone) {
        ArrayList<StonePoint> points = new ArrayList<StonePoint>();
        for (int x = 0; x < this.boardData.getW(); x++) {
            for (int y = 0; y < this.boardData.getH(); y++) {
                StonePoint point = new StonePoint(x, y);
                if (canPut(point, stone)) {
                    points.add(point);
                }
            }
        }
        return points;
    }

    /**
     * 盤面を表示する
     */
    public void print() {
        for (int y = 0; y < this.boardData.getH(); y++) {
            for (int x = 0; x < this.boardData.getW(); x++) {
                Stone stone = this.boardData.get(x, y);
                if (stone == null) {
                    System.out.print(" ");
                } else if (stone == Stone.BLACK) {
                    System.out.print("B");
                } else {
                    System.out.print("W");
                }
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    /** 
     * 盤面を文字列で取得する
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < this.boardData.getH(); y++) {
            for (int x = 0; x < this.boardData.getW(); x++) {
                Stone stone = this.boardData.get(x, y);
                if (stone == null) {
                    sb.append(" ");
                } else if (stone == Stone.BLACK) {
                    sb.append("B");
                } else {
                    sb.append("W");
                }
            }
        }
        return sb.toString();
    }    
}
