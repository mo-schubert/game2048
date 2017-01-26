# Game: 2048

2048 is a sliding block puzzle game for a single player. The original version was developed by Gabriele Circulli and is available under: https://gabrielecirulli.github.io/2048/

## The game

The objective of the game is to reach the number 2048 by sliding the numbered tiles on the game grid. The game is played on a 4x4 grid. The player can slide the tiles in one of the four directions (up, down, left, right). The tiles slide as far as possible until they are stopped by another tile or the edge of the grid. If two tiles with the same number hit each other while moving, they will merge into a tile with the total value of two tiles collided.

After each turn a new tile appears randomly on the grid. This tile has either the value 2 or 4.

## The implementation

The game is implemented in Java 8. The project is set up as a Maven project.