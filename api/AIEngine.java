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
    abstract static class State {
        protected Player player;
        protected TicTacToeBoard board;
        protected int threshold;

        public State(Player player, TicTacToeBoard board, int threshold) {
            this.player = player;
            this.board = board;
            this.threshold = threshold;
        }

        abstract Cell placeCell();  // Determine the move
        abstract State next();  // Determine the next state
    }

    // Opening state: Moves based on Basic Strategy
    class Opening extends State {
        public Opening(Player player, TicTacToeBoard board, int threshold) {
            super(player, board, threshold);
        }

        @Override
        Cell placeCell() {
            return getBasicMove(player, board);
        }

        @Override
        State next() {
            int moveCount = countMoves(board);
            if (moveCount < threshold) {
                return this;  // Remain in Opening
            } else if (moveCount < threshold + 1) {
                return new MidGame(player, board, threshold);
            } else {
                return new EndGame(player, board, threshold);
            }
        }
    }

    // MidGame state: Moves based on Smart Strategy
    class MidGame extends State {
        public MidGame(Player player, TicTacToeBoard board, int threshold) {
            super(player, board, threshold);
        }

        @Override
        Cell placeCell() {
            return getSmartMove(player, board);
        }

        @Override
        State next() {
            int moveCount = countMoves(board);
            if (moveCount < threshold) {
                return new Opening(player, board, threshold);
            } else if (moveCount < threshold + 1) {
                return this;  // Remain in MidGame
            } else {
                return new EndGame(player, board, threshold);
            }
        }
    }

    // EndGame state: Moves based on Optimal Strategy
    class EndGame extends State {
        public EndGame(Player player, TicTacToeBoard board, int threshold) {
            super(player, board, threshold);
        }

        @Override
        Cell placeCell() {
            return getOptimalMove(player, board);
        }

        @Override
        State next() {
            return this;  // Stay in EndGame
        }
    }

    public Move suggestMove(Player player, Board board){
        if(board instanceof TicTacToeBoard board1){
            Cell suggestedMove = null;
            int threshold = 2;
            State state;

            // Determine initial state based on countMoves()
            int moveCount = countMoves(board1);
            if (moveCount < threshold) {
                state = new Opening(player, board1, threshold);
            } else if (moveCount < threshold + 1) {
                state = new MidGame(player, board1, threshold);
            } else {
                state = new EndGame(player, board1, threshold);
            }

            // Get the suggested move based on the current state
            suggestedMove = state.placeCell();

//            if(countMoves(board1) < threshold){
//                suggestedMove = getBasicMove(player,board1);
//            } else if(countMoves(board1)< threshold + 1) {
//                suggestedMove = getSmartMove(player,board1);
//            } else{
//                suggestedMove = getOptimalMove(player, board1);
//            }

            //NOTE: By using State Design pattern also, we violates OCP, because we don't have clear state transition
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
