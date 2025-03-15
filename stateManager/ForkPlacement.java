package stateManager;

import boards.TicTacToeBoard;
import game.*;

import java.util.Optional;

public class ForkPlacement implements Placement{

    private static ForkPlacement forkPlacement;

    public synchronized static ForkPlacement get(){
        if(forkPlacement!= null) return forkPlacement;
        return new ForkPlacement();
    }

    @Override
    public Optional<Cell> place(TicTacToeBoard board, Player player){

        return Optional.ofNullable(fork(player,board));
    }

    @Override
    public Placement next(){
        return CenterPlacement.get();
    }

    private Cell fork(Player player, TicTacToeBoard board) {
        GameInfo gameInfo = ruleEngine.getInfo(board);
        Cell forkCell = null;
        if(gameInfo.hasAFork()){
            System.out.println("GAME HAS A FORK AT -> "+ gameInfo.getForkCell().getRow() + ","+gameInfo.getForkCell().getCol()+" \n");
            forkCell = gameInfo.getForkCell();
            return forkCell;
        }
        return forkCell;
    }

}
