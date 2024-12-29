/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package os2_mazerat_project;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;
import javafx.application.Platform;
import javafx.scene.paint.Color;


/**
 *
 * @author MICHAEL NABIL
 */
public class Rat implements Runnable{
    
    Maze maze;
    Random randomGenerator = new Random(1000);
    ReentrantLock lock = new ReentrantLock();
    volatile AtomicBoolean stop = new AtomicBoolean();
    
    public Rat(Maze maze){
        this.maze = maze;
        maze.setPosition(0, 0, 0);     //close the start position
        Platform.runLater(()->{
            MazeGui.setBlock(0, 0, Color.YELLOW);
        });
        stop.set(false);
    }
    
    @Override
    public void run(){
        //Get the coordinates that the current thread will start from
        String [] initialCoordinates = Thread.currentThread().getName().split(",");
        int x = Integer.parseInt(initialCoordinates[0]);
        int y = Integer.parseInt(initialCoordinates[1]);
        
        //Color initialization
        int upperBound = 256;
        int rgb[] = new int[3];
        for (int i=0; i<rgb.length;i++)
            rgb[i] = randomGenerator.nextInt(upperBound);
        Color currentThreadColor = Color.rgb(rgb[0], rgb[1], rgb[2]);
        
        //THE BUG WAS HERE
        if(x!=0 || y!=0){   // (x!=0 && y!=0)   '&&' caused the bug, as when row=0 and col=1, then the condition become false and its body isnt executed so the rectangle color isnt set.
            final int runLaterX = x;
            final int runLaterY = y;
            Platform.runLater(()->{
                MazeGui.setBlock(runLaterX, runLaterY, currentThreadColor);
            });
        }
        
        Thread t;
//        if(x == this.maze.getSize() - 1 && y == this.maze.getSize() - 1){     //we reached the end, so set the end with the end color
//            final int runLaterX = x;
//            final int runLaterY = y;
//            Platform.runLater(()->{
//                MazeGui.setBlock(runLaterX, runLaterY, Color.YELLOW);
//            });
//            
//            System.out.println("Thread: "+Thread.currentThread().getName()+" reached the end");
//            //KILL the remaining threads
//            this.stop.set(false);
//            return;
//        }
        System.out.println("thread: "+Thread.currentThread().getName());
        
        while(!stop.get()){     //while true
            boolean isAvailableDown = false;
            boolean isAvailableRight = false;
            
            lock.lock();
            try{
//                (x==3 && y==4){       //for testing that reaching x=3 and y=4 terminates the remaining thread, we cant test it in the normal case because the thread reaching the end, is always the last thread running
                if(x == this.maze.getSize() - 1 && y == this.maze.getSize() - 1){     //we reached the end, so set the end with the end color
                    final int runLaterX = x;
                    final int runLaterY = y;
                    Platform.runLater(()->{
                        MazeGui.setBlock(runLaterX, runLaterY, Color.YELLOW);
                    });

                    System.out.println("Thread: "+Thread.currentThread().getName()+" reached the end");
                    //KILL the remaining threads
                    this.stop.set(true);
                    return;
                }
                
                if(maze.getPosition(x, y+1) == 1)
                    isAvailableRight = true;

                if(maze.getPosition(x+1, y) == 1)
                    isAvailableDown = true;

                if(isAvailableRight && isAvailableDown){
                    t = new Thread(this, Integer.toString(x)+","+Integer.toString(y+1));    //the new thread move right
                    t.start();

                    x++;
                    this.maze.setPosition(0, x, y);
                    final int runLaterX = x;
                    final int runLaterY = y;

                    Platform.runLater(()->{
                        MazeGui.setBlock(runLaterX, runLaterY, currentThreadColor);
                    });
                }
                else if(isAvailableRight){
                    y++;
                    this.maze.setPosition(0, x, y);

                    final int runLaterX = x;
                    final int runLaterY = y;

                    Platform.runLater(()->{
                        MazeGui.setBlock(runLaterX, runLaterY, currentThreadColor);
                    });

                }
                else if(isAvailableDown){
                    x++;
                    this.maze.setPosition(0,x,y);

                    final int runLaterX = x;
                    final int runLaterY = y;

                    Platform.runLater(()->{
                        MazeGui.setBlock(runLaterX, runLaterY, currentThreadColor);
                    });

                }
                else
                    return;     //kill the current thread
            }finally{
                lock.unlock();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Rat.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
