package game;

public class Player {
    private final String playerSymbol;
    public Player(String playerSymbol){
        this.playerSymbol=playerSymbol;
    }
    public String symbol(){
        return playerSymbol;
    }
}
