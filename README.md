# Multithreaded Maze solver

A Maze of blocks is given where source block is the upper left most block and destination block is lower rightmost block.
A rat starts from source and must reach the destination.

The rat can move only in two directions: forward and down. In the maze matrix, '0' means the block is a dead end and '1' means the
block can be used in the path from source to destination.

While current thread moving in the maze if it finds two possible directions it will continue in one and 
create new thread for the second possible direction while protecting the critical section to prevent race condition.

The user input: N (the maze size : N*N).
The output: the path is shown in real time using javafx gui.
