package stateManager;

import boards.TicTacToeBoard;
import game.Board;
import game.Cell;
import game.Move;
import game.Player;

import java.util.Optional;

public class CornerPlacement implements Placement{

    private static CornerPlacement cornerPlacement;

    public synchronized static CornerPlacement get(){
        if(cornerPlacement!= null) return cornerPlacement;
        return new CornerPlacement();
    }

    @Override
    public Optional<Cell> place(TicTacToeBoard board, Player player){
        int corners[][] =  new int[][]{{0,0},{0,2},{2,0},{2,2}};
        Cell cell = null;
        for(int i=0;i<4;i++){
            if(board.getSymbol(corners[i][0],corners[i][1])==null) cell= new Cell(corners[i][0],corners[i][1]);
        }
        return Optional.ofNullable(cell);
    }

    @Override
    public Placement next(){
        return null;
    }


}
