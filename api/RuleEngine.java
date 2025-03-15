package api;

import boards.TicTacToeBoard;
import game.Board;
import game.GameState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class RuleEngine {
    Map<String, RuleSet<? extends Board>> ruleMap = new HashMap<>();

    public RuleEngine(){
         ruleMap.put(TicTacToeBoard.class.getName(),TicTacToeBoard.getRules());
    }

    public GameState getState(Board board){
        if(board instanceof TicTacToeBoard board1) {
            RuleSet<TicTacToeBoard> rules = (RuleSet<TicTacToeBoard>) ruleMap.get(TicTacToeBoard.class.getName());
            for(Rule <TicTacToeBoard> rule: rules){
                GameState gameState = rule.condition.apply(board1);
                if(gameState.isOver()){
                    return gameState;
                }
            }
            return new GameState(false,"-");
        } else {
            return new GameState(true, "-");
        }
    }
}


