package Strategy;

import boards.TicTacToeBoard;
import game.Cell;
import game.CellBoard;
import game.Player;
import stateManager.DefensivePlacement;
import stateManager.Placement;

import java.util.Optional;

public class OptimalMoveStrategy extends MoveStrategy {
    @Override
    public Cell move(Player player, TicTacToeBoard board) {
        Placement placement = DefensivePlacement.get();
        while (placement.next()!=null){
            Optional<Cell> place = placement.place(board,player);
            if(place.isPresent()){
                return place.get();
            }
            placement = placement.next();
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getSymbol(i,j)==null){
                    return new Cell(i,j);
                }
            }
        }
        return null;
    }
}