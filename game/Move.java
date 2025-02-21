package game;

public class Move {
    private Cell cell;

    private Player player;

    public Move(Player player, Cell cell) {
        this.cell = cell;
        this.player = player;
    }

    public Cell getCell(){
       return cell;
    }

    public Player getPlayer(){
        return player;
    }
}

