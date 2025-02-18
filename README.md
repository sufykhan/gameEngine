Step 1: Setting up the IntelliJ IDE -  using jdk21

I wasn't able to configure at first
This blog helped me out: https://www.jetbrains.com/help/idea/creating-and-running-your-first-java-application.html#run_app


Step 2: I watched the video and coded side by side 
Writing very bad code at first for simulation a game result for tic tac toe

PART 2:

Step 3: Do refactoring and restructuring of the code.

Issue faced = @e get an error while accessing: board1.cells[i][0];

Above issue ensures that we could not make changes to underlying data structures and have to make restriction on how we want others to make changes. ie creating getCell method to access cell at i,j;

