package oserooooo;

import java.util.ArrayList;
import java.util.LinkedHashSet;


/**
 * オセロのプレイヤーを管理するクラス
 */
public class Player {
    
    public final String name;
    public final Stone stone;

    /** 最後に置いた情報文字列 */
    private String lastPutInfo;

    /** 負けた情報リスト */
    private LinkedHashSet<String> loseInfoList;

    public Player(String name, Stone stone) {
        this.name = name;
        this.stone = stone;

        this.loseInfoList = new LinkedHashSet<>();
    }

    /**
     * 置く場所を探す
     * @param board
     * @return
     */
    public StonePoint searchInput(BoardMaster board) {   
        
        ArrayList<StonePoint> puttablePoints = board.getPuttablePoints(stone);

        // 負ける場所を避ける
        for (StonePoint point : puttablePoints) {
            if (loseInfoList.contains(board.toString() + point.toString())) {
                continue;
            }
            lastPutInfo = board.toString() + point.toString();
            return point;
        }
        
        // 負ける場所しかない場合は、リストに追加しておく
        loseInfoList.add(lastPutInfo);
        
        return puttablePoints.get(0);
    }

    /**
     * 負けを記録する
     */
    public void recordLose() {
        loseInfoList.add(lastPutInfo);
    }

    public void printLoseInfoList() {
        System.out.println("負けた情報リスト:" + stone.toString());
        for (String info : loseInfoList) {
            System.out.println(info);
        }
    }
}
