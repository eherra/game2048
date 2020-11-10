## Game 2048

Game 2048 where you need to move pieces on board and try to sum same value numbers with each other. The game is lost when there're no moves left (values are not summing up, down, left right) or won whe one of the piece's value is 2048.

The game will start with two random centered value's of '2's, and if summing has happened after a move (up, down, left or right), the board will be updated with random coordinated square value of '2' or with 10% chance square vale of '4'.

To start from terminal:
```console
mvn compile exec:java -Dexec.mainClass=game2048.ui.Ui
```

To run tests from terminal:
```console
mvn test
```

To get Jacoco report, run from terminal:
```console
mvn test jacoco:report
```

