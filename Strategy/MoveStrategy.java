package Strategy;

import boards.TicTacToeBoard;
import game.Board;
import game.Cell;
import game.CellBoard;
import game.Player;

public abstract class MoveStrategy {

    public abstract Cell move(Player player, TicTacToeBoard board );

}