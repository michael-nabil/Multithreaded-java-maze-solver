/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package os2_mazerat_project;

/**
 *
 * @author MICHAEL NABIL
 */
public class Maze {
    private int board[][];
    
    public Maze(int baord[][]){
        this.board = baord;
    }
    
    public boolean setPosition(int value, int rowPosition, int colPosition){
        if(rowPosition>=board.length || colPosition>=board.length)
            return false;
        else{
            board[rowPosition][colPosition] = value;
            return true;
        }
    }
    public int getPosition(int rowPosition, int colPosition){
        if(rowPosition>=board.length || colPosition>=board.length)
            return -1;
        else
            return board[rowPosition][colPosition];    
    }
    public int getSize(){
        return this.board.length;
    }
}
