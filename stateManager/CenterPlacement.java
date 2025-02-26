package stateManager;

import boards.TicTacToeBoard;
import game.Board;
import game.Cell;
import game.Move;
import game.Player;

import java.util.Optional;

public class CenterPlacement implements Placement{

    private static CenterPlacement centerPlacement;

    public synchronized static CenterPlacement get(){
        if(centerPlacement!= null) return centerPlacement;
        return new CenterPlacement();
    }

    @Override
    public Optional<Cell> place(TicTacToeBoard board, Player player){
        Cell center = null;
        if (board.getSymbol(1, 1) == null) {
            center= new Cell (1,1);
        }
        return Optional.ofNullable(center);

    }

    @Override
    public Placement next(){
        return CornerPlacement.get();
    }


}
