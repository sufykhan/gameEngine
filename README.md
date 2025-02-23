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


Step 5: I got into the problem of testing the code again and again using manual testing. There-fore decided to write the unit test cases to validate my changes.

While doing Step 5, I learned these things:
1. Unit Test cases are important for quick validation of logic, in fact I found 2 bugs while doing unit testing
2. Unit Test should be readable and extensible so that we don't need to rewrite our tests again

Step 6: Open CLose Principle : Our system should be open to extension but close to modification

What we are doing in this: We want to have optimised suggestMove function but 
instead of changing in the Main file, we are extending the suggestMove function to incorporate smart and simple move

While doing Step 6, I learned these things:

1. We should not write such code which forces us to make changes in the callers or main functions which are already tested.
2. While modifying anything , we create a wrapper over already tested code instead of modifying it.
3. Smartly think of handling concurrent request without causing deadlock (Ex here is if we wanted to undo the suggested move , then high possibility is that parallel request can create deadlocks)


Step 7: Making tests extensible, we now don't rely on AiEngine to suggest move, as our main focus is to test the moves and rules.

Step 8: Using Prototype Design Pattern: It is used when we wanted to deep or shallow clone the object. It is beneficial when new Object creation is expensive

Step 9: We used Lambda functions to adhere to the DRY principle.
