package api;

import Strategy.MoveStrategy;
import Strategy.MoveStrategyFactory;
import boards.TicTacToeBoard;
import game.*;

public class AIEngine {

    public Move suggestMove(Player player, Board board){
        if(board instanceof TicTacToeBoard board1){
            MoveStrategy moveStrategy = MoveStrategyFactory.createStrategy(board1);
            Cell suggestedMove = moveStrategy.move(player,board1);
            if(suggestedMove != null) return new Move(player,suggestedMove);
            throw new IllegalStateException();
        } else{
            throw new IllegalArgumentException();
        }
    }

}
