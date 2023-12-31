package oserooooo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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

    /** 不要になった負けた情報リスト */
    private LinkedHashSet<String> unneededLostInfoList;

    /** あきらめた状態 */
    private boolean giveUp;

    public Player(String name, Stone stone) {
        this.name = name;
        this.stone = stone;

        this.loseInfoList = new LinkedHashSet<>();
        // 負けた情報を読み込む
        ClassLoader classLoader = getClass().getClassLoader();
        String resourcePath = classLoader.getResource(stone.toString()).getPath();
        try (BufferedReader reader = new BufferedReader(new FileReader(resourcePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                loseInfoList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.unneededLostInfoList = new LinkedHashSet<>();

        giveUp = false;
    }

    /**
     * 置く場所を探す
     * @param board
     * @return
     */
    public StonePoint searchInput(BoardMaster board) {   
        
        ArrayList<StonePoint> puttablePoints = board.getPuttablePoints(stone);

        if (giveUp == false) {
            // 負ける場所を避ける
            for (StonePoint point : puttablePoints) {
                if (loseInfoList.contains(board.toString() + point.toString())) {
                    continue;
                }
                lastPutInfo = board.toString() + point.toString();
                return point;
            }

            // 不要な負け情報を保存する
            for (StonePoint point : puttablePoints) {
                if (loseInfoList.contains(board.toString() + point.toString())) {
                    unneededLostInfoList.add(board.toString() + point.toString());
                }
            }

            // 負ける場所しかない場合はあきらめる
            giveUp = true;
        }
        
        return puttablePoints.get(0);
    }

    /**
     * ゲームの終了処理
     */
    public void finishGame(BoardMaster boardMaster) {

        if(unneededLostInfoList.size() > 0 && boardMaster.isWin(stone)){
            assert false;
        }

        giveUp = false;

        // 不要となる負け記録を削除する
        for (String info : unneededLostInfoList) {
            loseInfoList.remove(info);
        }
        unneededLostInfoList.clear();

        // 負けていた場合リストに追加
        if (boardMaster.isWin(stone) == false) {
            loseInfoList.add(lastPutInfo);
        }
    }

    public void printLoseInfoList() {
        System.out.println("負けた情報リスト:" + stone.toString());
        for (String info : loseInfoList) {
            System.out.println(info);
        }
    }

    public void outputLoseInfoList() {
        ClassLoader classLoader = getClass().getClassLoader();
        String resourcePath = classLoader.getResource(stone.toString()).getPath();        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(resourcePath, false))) {
            for (String info : loseInfoList) {
                writer.write(info + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
