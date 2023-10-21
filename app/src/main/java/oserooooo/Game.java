package oserooooo;

/*
 * オセロの進行を管理するクラス
 */
public class Game {
    
    private BoardMaster board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    
    public Game() {
        this.player1 = new Player("先攻", Stone.BLACK);
        this.player2 = new Player("後攻", Stone.WHITE);
    }
    
    public void start() {

        init();

        gameing();

        end();
    }

    private void init() {
        Board boardData = new Board(8, 8);
        boardData.put(3,3,Stone.BLACK);
        boardData.put(4,4,Stone.BLACK);
        boardData.put(3,4,Stone.WHITE);
        boardData.put(4,3,Stone.WHITE);        
        
        this.board = new BoardMaster(boardData);

        this.currentPlayer = this.player1;

        //System.out.println("ゲームを開始します");
        //System.out.println("先攻のターンです");
    }

    /**
     * ゲーム中
     */
    private void gameing(){
        while (true) {
            if (this.board.isFinish()) {
                //System.out.println("両者とも置ける場所がなくなりました");
                break;
            }
            if (this.board.isPass(this.currentPlayer.stone)) {
                //System.out.println("置ける場所がないためパスします");
                this.currentPlayer = this.currentPlayer == this.player1 ? this.player2 : this.player1;
                //System.out.println(this.currentPlayer.name + "のターンです");
                continue;
            }
            StonePoint point = this.currentPlayer.searchInput(board);
            this.board.put(point, this.currentPlayer.stone);
            //System.out.println(point.getX() + ", " + point.getY() + "に置きました");
            // 盤面を表示する
            //this.board.print();

            this.currentPlayer = this.currentPlayer == this.player1 ? this.player2 : this.player1;
            //System.out.println(this.currentPlayer.name + "のターンです");
        }
    } 

    private void end(){

        int player1StoneCount = this.board.countStone(this.player1.stone);
        int player2StoneCount = this.board.countStone(this.player2.stone);

        // 負けた方は盤面を記録する
        if (player1StoneCount > player2StoneCount) {
            this.player2.recordLose();
        } else {
            this.player1.recordLose();
        }

        //System.out.println("先攻の石の数: " + player1StoneCount);        
        //System.out.println("後攻の石の数: " + player2StoneCount);        
    }

    /**
     * 負けリストの表示
     */
    public void printLoseInfoList() {
        this.player1.printLoseInfoList();
        this.player2.printLoseInfoList();
    }
}
