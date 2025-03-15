package stateManager;

import boards.TicTacToeBoard;
import game.Board;
import game.Cell;
import game.Move;
import game.Player;

import java.util.Optional;

public class DefensivePlacement implements Placement{

    private static DefensivePlacement defensivePlacement;

    public synchronized static Placement get(){
            if(defensivePlacement!= null) return defensivePlacement;
            return new DefensivePlacement();
    }

    @Override
    public Optional<Cell> place(TicTacToeBoard board, Player player){

        return Optional.ofNullable(defense(player,board));
    }

    @Override
    public Placement next(){
       return OffensivePlacement.get();
    }

    private Cell defense(Player player, TicTacToeBoard board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getSymbol(i, j) == null) {
                    Move move = new Move(player.flip(), new Cell(i,j));
                    Board boardCopy = board.clone();
                    boardCopy.move(move);
                    if (ruleEngine.getState(boardCopy).isOver()) {
                        return new Cell(i,j);
                    }
                }
            }
        }
        return null;
    }

}
