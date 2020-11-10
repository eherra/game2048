## Game 2048

Game 2048 where you need to move squares on board and try to sum same value numbers with each other. The game is lost when there're no moves left (values are not summing up, down, left or right) or won when one of the square's value is 2048.

The game will start with two random centered squares valueted of '2's, and if summing has happened after a move (up, down, left or right), the board will be updated with random coordinated square value of '2' or with 10% chance square vale of '4'.

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

