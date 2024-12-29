/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */


package os2_mazerat_project;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.paint.Color;


/**
 *
 * @author MICHAEL NABIL
 */
public class Main {

    static int mazeSize;

    public static Object waiter = new Object();
    
    public static void main(String[] args) {
        
        Maze m;
        
        Thread guiThread = new Thread(() -> {
            MazeGui.main(args);
        });
        guiThread.start();
        
        
        synchronized(waiter){
            try {
                waiter.wait();  //wait
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            int board[][] = generateMaze(mazeSize);
//                    {{1,1,1,1,0,0,0,1,1,1},
//                    {1,1,1,1,0,1,0,0,0,1},
//                    {1,1,1,1,0,1,1,1,1,1},
//                    {1,1,1,1,1,0,0,0,0,1},
//                    {1,1,1,1,1,1,0,1,0,1},
//                    {0,0,1,1,1,1,1,1,1,1},
//                    {1,0,1,1,1,1,0,0,1,0},
//                    {0,1,1,1,0,0,1,0,0,1},
//                    {1,1,1,1,1,1,1,0,0,1},
//                    {0,0,1,1,1,1,1,1,1,1}};
            m = new Maze(board);
            MazeGui.setBoard(board);

            waiter.notify();
            try {
                waiter.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
 
        Rat myMazeRat = new Rat(m);
        Thread startingThread = new Thread(myMazeRat,"0,0");
        startingThread.start();
    }
    
    public static int[][] generateMaze(int boardSize){
        int maze[][] = new int[boardSize][boardSize];
        Random random = new Random();
        
        for(int i=0; i<boardSize; i++)
            maze[i] = new int[boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if(random.nextDouble() > 0.3)
                    maze[i][j] = 1;
                else
                    maze[i][j] = 0;
            }
        }
        maze[0][0] = 1;
        maze[boardSize - 1][boardSize - 1] = 1;
        return maze;
    }
    public static void setMazeSize(int size){
        mazeSize = size;
    }
}
