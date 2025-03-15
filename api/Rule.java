package api;

import game.Board;
import game.CellBoard;
import game.GameState;

import java.util.function.Function;

public class Rule {
    Function<CellBoard, GameState> condition;
    public Rule(Function<CellBoard, GameState> condition){
        this.condition = condition;
    }
}
