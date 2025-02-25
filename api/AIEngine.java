package api;

import boards.TicTacToeBoard;
import game.*;

public class AIEngine {
    private RuleEngine ruleEngine;

    public Cell getBasicMove(Player player, TicTacToeBoard board){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getSymbol(i,j)==null){
                    return new Cell(i,j);
                }
            }
        }
        return null;
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

    public Cell getSmartMove(Player player, TicTacToeBoard board){
        // Victorious Move = We found a move where Computer Wins
        Cell defense = defense(player,board);
        if(defense!=null) return defense;

        Cell offense = offense(player,board);
        if(offense!=null) return offense;

        return getBasicMove(player, board);
    }

    public Cell getOptimalMove(Player player, TicTacToeBoard board){

        // 1. Victorious Move = We found a winning move.
        Cell best = defense(player,board);
        if(best!=null) return best;
        // 2. Defensive Move = We found a move where Player wins and we block the move.
        best = offense(player,board);
        if(best!=null) return best;
        // 3. Fork Move = If I have a fork, then play it.
        // 4. AntiFork Move = If opponent have a fork, then block it.

        GameInfo gameInfo = ruleEngine.getInfo(board);
        if(gameInfo.hasAFork()){
            best = gameInfo.getForkCell();
            return best;
        }
        // 5. If the center is available, play it.
        if(board.getSymbol(1,1)==null) return new Cell(1,1);
        // 6. If the corner is available, play it.
        int corners[][] =  new int[][]{{0,0},{0,2},{2,0},{2,2}};
        for(int i=0;i<4;i++){
            if(board.getSymbol(corners[i][0],corners[i][1])==null) return new Cell(corners[i][0],corners[i][1]);
        }

        return getBasicMove(player, board);

        //return new Cell(0,0);
    }

    public Move suggestMove(Player player, Board board){
        if(board instanceof TicTacToeBoard board1){
            Cell suggestedMove = null;
            int threshold = 2;
            if(countMoves(board1) < threshold){
                suggestedMove = getBasicMove(player,board1);
            } else if(countMoves(board1)< threshold + 1) {
                suggestedMove = getSmartMove(player,board1);
            } else{
                suggestedMove = getOptimalMove(player, board1);
            }
            if(suggestedMove != null) return new Move(player,suggestedMove);
            throw new IllegalStateException();
        } else{
            throw new IllegalArgumentException();
        }
    }

    public int countMoves(TicTacToeBoard board) {
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
