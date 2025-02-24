package game;

public  interface Board {
    void move(Move move);
//    public abstract Board copy();
    String getSymbol(int i, int j); // Breaking LisKov Substitution Principle (Subclass like chess cannot have getSymbol)
}
