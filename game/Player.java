package game;

public class Player {
    private final String playerSymbol;
    private User user;
    public Player(String playerSymbol){
        this.playerSymbol=playerSymbol;
        this.user = new User();
    }
    public String symbol(){
        return playerSymbol;
    }
    public Player flip(){
        return new Player(playerSymbol.equals("X") ? "O":"X");
    }
    public User getUser() {
        return user;
    }
}
