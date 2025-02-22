package api;

import boards.TicTacToeBoard;
import game.*;

public class AIEngine {

    public Move getBasicMove(Player player, TicTacToeBoard board){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getSymbol(i,j)==null){
                    return new Move(player,new Cell(i,j));
                }
            }
        }
        return null;
    }

    public Move getSmartMove(Player player, TicTacToeBoard board){
        RuleEngine ruleEngine = new RuleEngine();
        // Victorious Move = We found a move where Computer Wins
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getSymbol(i,j)==null){
                    Move move = new Move(player, new Cell(i,j));
                    Board boardCopy = board.copy();
                    boardCopy.move(move);
                    if(ruleEngine.getState(boardCopy).isOver()){
                        return move;
                    }
                }
            }
        }

        // Defensive Move = We found a move where Player Wins and we replace it with computer move
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getSymbol(i,j)==null){
                    Move move = new Move(player.flip(), new Cell(i,j));

                    Board boardCopy = board.copy();
                    boardCopy.move(move);
                    if(ruleEngine.getState(boardCopy).isOver()){
                        return  new Move(player, new Cell(i,j));
                    }
                }
            }
        }
        return getBasicMove(player, board);
    }

    public Move suggestMove(Player player, Board board){
        if(board instanceof TicTacToeBoard board1){
            Move suggestedMove = null;
            if(hasLessMove(board1,2)){
                suggestedMove = getBasicMove(player,board1);
            } else {
                suggestedMove = getSmartMove(player,board1);
            }
            if(suggestedMove != null) return suggestedMove;
            throw new IllegalStateException();
        } else{
            throw new IllegalArgumentException();
        }
    }

    public boolean hasLessMove(TicTacToeBoard board, int threshHold) {
            int count = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board.getSymbol(i, j) != null) {
                        count++;
                    }
                }
            }
            return count<threshHold;
    }
}
