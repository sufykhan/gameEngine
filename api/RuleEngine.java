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
    Map<String, List<Rule<TicTacToeBoard>>> ruleMap = new HashMap<>();

    public RuleEngine(){
         String name = TicTacToeBoard.class.getName();
         ruleMap.put(name,new ArrayList<>());
         // Rules
         ruleMap.get(name).add(new Rule<> ((board)->outerTraversal(board::getSymbol)));
         ruleMap.get(name).add(new Rule<> ((board)->outerTraversal((i,j)-> board.getSymbol(j,i))));
         ruleMap.get(name).add(new Rule<> ((board)->traverse((i)-> board.getSymbol(i,i))));
         ruleMap.get(name).add(new Rule<> ((board)->traverse((i)-> board.getSymbol(i,2-i))));
         ruleMap.get(name).add(new Rule<> (this::countMoves));

    }

    public GameState getState(Board board){
        if(board instanceof TicTacToeBoard board1) {
            List<Rule<TicTacToeBoard>>rules = ruleMap.get(TicTacToeBoard.class.getName());
            for(Rule <TicTacToeBoard> rule:rules){
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

    public GameState countMoves (TicTacToeBoard board1){
        int count=0;
        GameState gameState = new GameState(false, "-");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board1.getSymbol(i,j)!=null){
                    count ++;
                }
            }
        }
        if(count == 9){
            gameState = new GameState(true,"-");
        }
        return gameState;
    }

    public GameState outerTraversal(BiFunction<Integer, Integer,String> next){
        GameState result = new GameState(false, "-");
        for (int i = 0; i < 3; i++) {
            int finalI = i;
            Function<Integer,String> nextValue = (j) -> next.apply(finalI,j);
            GameState traversal = traverse(nextValue);
            if(traversal.isOver()) {
                result = traversal;
                break;
            }
        }
        return result;
    }

    public GameState traverse(Function<Integer,String> next){
        GameState result = new GameState(false, "-");
        boolean possibleStreak = true;
        for (int i = 0; i < 3; i++) {
            if (next.apply(i)== null || !next.apply(0).equals(next.apply(i))) {
                possibleStreak = false;
                break;
            }
        }
        if (possibleStreak) {
            result =  new GameState(true, next.apply(0));
        }
        return result;
    }
}


class Rule <T extends  Board> {
    Function<T , GameState> condition;
    Rule(Function<T, GameState> condition){
        this.condition = condition;
    }
}
