import Service.EmailService;
import Service.SMSService;
import api.*;
import boards.History;
import boards.TicTacToeBoard;
import commands.builder.EmailCommandBuilder;
import commands.builder.SMSCommandBuilder;
import game.Board;
import game.Cell;
import game.Move;
import game.Player;

import java.util.Scanner;

import static java.util.concurrent.TimeUnit.DAYS;

public class Main {

    public static void main(String[] args) {
        System.out.println("GAME START");
        GameEngine gameEngine = new GameEngine();
        AIEngine aiEngine = new AIEngine();
        RuleEngine ruleEngine = new RuleEngine();
        Board board = gameEngine.start("TicTacToe");
        Scanner scanner = new Scanner(System.in);
        Player computer = new Player("O");
        Player opponent = new Player("X");
        if(opponent.getUser().activeAfter(1,DAYS)){
            EmailService emailService = new EmailService();
            emailService.execute(new EmailCommandBuilder().user(opponent.getUser()).message("Glad, you are back").build());
        }
        while(!ruleEngine.getState(board).isOver()){
            System.out.println("Make your Move");
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            Move opponentMove = new Move(opponent,new Cell(row,col));
            gameEngine.move(board,opponentMove);
            System.out.println(board);
            if(!ruleEngine.getState(board).isOver()){
                Move computerMove = aiEngine.suggestMove(computer,board);
                gameEngine.move(board,computerMove);
                System.out.println(board);
            }
        }
        if(board instanceof TicTacToeBoard board1){
            History boardHistory = board1.getHistory();
            boardHistory.printHistory();
        }
        // Problem with below approach is that it is not extensible, if link support, image support is needed then we had to change everywhere
        if(ruleEngine.getState(board).getWinner().equals(opponent.symbol())){
            EmailService emailService = new EmailService();
            emailService.execute(new EmailCommandBuilder().user(opponent.getUser()).message("Congratulations, you won the match").build());

            SMSService smsService = new SMSService();
            smsService.execute(new SMSCommandBuilder().user(opponent.getUser()).message("Congratulations, you won the match").build());
        }
        System.out.println("Game winner is " + ruleEngine.getState(board).getWinner());
    }

}
