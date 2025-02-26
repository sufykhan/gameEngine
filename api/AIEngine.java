package api;

import boards.TicTacToeBoard;
import game.*;
import stateManager.DefensivePlacement;
import stateManager.OffensivePlacement;
import stateManager.Placement;

import java.util.Optional;

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

                    Board boardCopy = board.dummyMove(move);
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
                    Board boardCopy = board.dummyMove(move);
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
        Placement placement = DefensivePlacement.get();
        while (placement.next()!=null){
            Optional<Cell> place = placement.place(board,player);
            if(place.isPresent()){
                return place.get();
            }
            placement = placement.next();
        }
        return getBasicMove(player, board);
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
