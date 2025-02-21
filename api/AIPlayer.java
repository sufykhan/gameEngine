package api;

import boards.TicTacToeBoard;
import game.*;

public class AIPlayer {

    public Move suggestMove(Player player, Board board){
        if(board instanceof TicTacToeBoard board1){
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board1.getCell(i,j)==null){
                        return new Move(player,new Cell(i,j));
                    }
                }
            }
            throw new IllegalStateException();
        } else{
            throw new IllegalArgumentException();
        }
    }

}
