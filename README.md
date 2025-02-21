Step 1: Setting up the IntelliJ IDE -  using jdk21

I wasn't able to configure at first
This blog helped me out: https://www.jetbrains.com/help/idea/creating-and-running-your-first-java-application.html#run_app


Step 2: I watched the video and coded side by side 
Writing very bad code at first for simulation a game result for tic tac toe

PART 2:

Step 3: Do refactoring and restructuring of the code.

Issue faced = @e get an error while accessing: board1.cells[i][0];

Above issue ensures that we could not make changes to underlying data structures and have to make restriction on how we want others to make changes. ie creating getCell method to access cell at i,j;

Step 4: We need to adhere to the Single Responsibility principle now: (changed Move method and converted Board to abstract class with abstract move method)

While doing Step 4, I learned these two things:
1. When to use extend and when to use implements in the Java ?
2. What are abstract methods and classes and when to use them ? 

Outcome:
Game Engine => Responsibility => Any changes in the board, we come here (Like different moves or different board configuration)
Rule Engine => Responsibility => Make sure that rules are followed in the game
AI  Player => Responsibility => Suggestion Moves

