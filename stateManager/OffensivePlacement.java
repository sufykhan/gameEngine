package stateManager;

import boards.TicTacToeBoard;
import game.Board;
import game.Cell;
import game.Move;
import game.Player;

import java.util.Optional;

public class OffensivePlacement implements Placement{
    private static OffensivePlacement offensivePlacement;

    public synchronized static Placement get(){
        if(offensivePlacement!= null) return offensivePlacement;
        return new OffensivePlacement();
    }
    @Override
    public Optional<Cell> place(TicTacToeBoard board, Player player){
        return Optional.ofNullable(offense(player,board));
    }

    @Override
    public Placement next(){
        return ForkPlacement.get() ;
    }

    private Cell offense(Player player, TicTacToeBoard board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getSymbol(i,j)==null){
                    Move move = new Move(player.flip(), new Cell(i,j));

                    Board boardCopy = board.clone();
                    boardCopy.move(move);
                    if(ruleEngine.getState(boardCopy).isOver()){
                        return  new Cell(i,j);
                    }
                }
            }
        }
        return null;
    }

}
