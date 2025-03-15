package Strategy;

import boards.TicTacToeBoard;
import game.Board;

public class MoveStrategyFactory {


    public static MoveStrategy createStrategy(Board board){
        if(board instanceof TicTacToeBoard board1){
            int threshold = 2;
            if(countMoves(board1) < threshold){
                return new BasicMoveStrategy();
            } else if(countMoves(board1)< threshold + 1) {
                return new SmartMoveStrategy();
            } else{
                return new OptimalMoveStrategy();
            }
        } else{
            throw new IllegalArgumentException();
        }
    }

    public static int countMoves(TicTacToeBoard board) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getSymbol(i, j) != null) {
                    count++;
                }
            }
        }
        return count;
    }

}
