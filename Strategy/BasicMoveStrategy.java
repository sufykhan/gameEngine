package Strategy;

import boards.TicTacToeBoard;
import game.Cell;
import game.CellBoard;
import game.Player;

public class BasicMoveStrategy extends MoveStrategy{

    @Override
    public Cell move(Player player, TicTacToeBoard board) {
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