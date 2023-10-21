package oserooooo;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoardMasterTest {

    @Test
    @DisplayName("ひっくり返せるかどうか")
    public void canPutTest() {
        Board boardData = new Board(8, 8);

        boardData.put(3,3,Stone.BLACK);
        boardData.put(4,3,Stone.BLACK);    
        boardData.put(5,3,Stone.BLACK);
        boardData.put(3,4,Stone.BLACK);
        boardData.put(4,4,Stone.WHITE); 
        boardData.put(5,4,Stone.BLACK);  
        boardData.put(3,5,Stone.BLACK);
        boardData.put(4,5,Stone.BLACK);    
        boardData.put(5,5,Stone.BLACK);              

        BoardMaster board = new BoardMaster(boardData);

        // 右
        assertTrue(board.canPut(new StonePoint(2, 4), Stone.WHITE, Direction.RIGHT));
        assertFalse(board.canPut(new StonePoint(2, 4), Stone.BLACK, Direction.RIGHT));
        // 左
        assertTrue(board.canPut(new StonePoint(6, 4), Stone.WHITE, Direction.LEFT));
        assertFalse(board.canPut(new StonePoint(6, 4), Stone.BLACK, Direction.LEFT));
        // 上
        assertTrue(board.canPut(new StonePoint(4, 6), Stone.WHITE, Direction.UP));
        assertFalse(board.canPut(new StonePoint(4, 6), Stone.BLACK, Direction.UP));
        // 下
        assertTrue(board.canPut(new StonePoint(4, 2), Stone.WHITE, Direction.DOWN));
        assertFalse(board.canPut(new StonePoint(4, 2), Stone.BLACK, Direction.DOWN));
        // 右上
        assertTrue(board.canPut(new StonePoint(2, 6), Stone.WHITE, Direction.RIGHT_UP));
        assertFalse(board.canPut(new StonePoint(2, 6), Stone.BLACK, Direction.RIGHT_UP));
        // 右下
        assertTrue(board.canPut(new StonePoint(2, 2), Stone.WHITE, Direction.RIGHT_DOWN));
        assertFalse(board.canPut(new StonePoint(2, 2), Stone.BLACK, Direction.RIGHT_DOWN));
        // 左上
        assertTrue(board.canPut(new StonePoint(6, 6), Stone.WHITE, Direction.LEFT_UP));
        assertFalse(board.canPut(new StonePoint(6, 6), Stone.BLACK, Direction.LEFT_UP));
        // 左下
        assertTrue(board.canPut(new StonePoint(6, 2), Stone.WHITE, Direction.LEFT_DOWN));
        assertFalse(board.canPut(new StonePoint(6, 2), Stone.BLACK, Direction.LEFT_DOWN));
        
    }
}
