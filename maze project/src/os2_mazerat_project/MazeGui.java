/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package os2_mazerat_project;

/**
 *
 * @author MICHAEL NABIL
 */
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.TextArea;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;

public class MazeGui extends Application {
    
    //Attributes owned by mazeScene
    private static Stage window;
    private static Scene mazeScene;
    public static GridPane grd;
    static ObjectProperty<Paint>[][] dynamicColor;
    
    //Attributes owned by inputScene
    private static Scene inputScene;
    private static Group root;
    private int size;
    private boolean errorMessageFlag = false;
    
    private static int board[][];
    public static Object innerWaiter = new Object();
    
    @Override
    public void start(Stage stage){
        inputSceneStarter(stage);
    }
       public static void main(String[] args) {
            launch(args);
    }
       
    public static void setBlock(int rowPosition, int colPosition, Paint p){
            Platform.runLater(()->{
                MazeGui.dynamicColor[rowPosition][colPosition].set(p);
            });
       }
    public static void setBoard(int board[][]){
           MazeGui.board = board;
       }
    public static void switchScenes(){
        //try to know if it stops the previous scene or no
        //        window.setScene(inputScene);
       }
    
    public  void mazeSceneStarter(Stage stage){
        grd = new GridPane();
        
        //_________________________________________________-
//        for (int i = 0; i < MazeGui.board.length; i++) {
//            ColumnConstraints colConstraints = new ColumnConstraints();
//            colConstraints.setPercentWidth(100.0 / MazeGui.board.length); // Equal distribution for 5 columns
//            grd.getColumnConstraints().add(colConstraints);
//
//            RowConstraints rowConstraints = new RowConstraints();
//            rowConstraints.setPercentHeight(100.0 / MazeGui.board.length); // Equal distribution for 5 rows
//            grd.getRowConstraints().add(rowConstraints);
//        }
        //________________________________________
        
        // the static method 'set board' is called before the start method, so the dimensions of the board is set
        dynamicColor = new SimpleObjectProperty[board.length][board.length];
        
        for(int i=0;i<MazeGui.board.length;i++){
            for(int j=0;j<MazeGui.board[0].length;j++){
                Rectangle cell = new Rectangle();
                cell.setWidth(40);//65
                cell.setHeight(40);//65
                cell.setStroke(Color.BLACK);
                cell.setStrokeWidth(2);
                
                if(MazeGui.board[i][j] == 1){
                    dynamicColor[i][j] = new SimpleObjectProperty<>(Color.WHITE);
                }
                else{
                    dynamicColor[i][j] = new SimpleObjectProperty<>(Color.BLACK);
                }
                cell.fillProperty().bind(dynamicColor[i][j]);
                //________________
//                cell.widthProperty().bind(grd.widthProperty().divide(5));
//                cell.heightProperty().bind(grd.heightProperty().divide(5));
                //________________
                MazeGui.grd.add(cell, j, i,1,1);
            }
        }
               
        mazeScene = new Scene(grd,1000,800);//border
        
        window = stage;
        window.setScene(mazeScene);
        window.show();
        window.setX(0);
        window.setY(0);
    }
    public  void inputSceneStarter(Stage stage){
        root = new Group();
        
        Text label = new Text();
        label.setText("Enter the size of the maze: ");
        label.setX(0);
        label.setY(30);
        label.setFont(Font.font("Verdana",30));
        root.getChildren().add(label);
        
        TextArea textArea = new TextArea();
        textArea.setWrapText(true);
        textArea.setLayoutX(0);
        textArea.setLayoutY(50);
        textArea.setPrefSize(250,30 );
        textArea.setFont(Font.font("Verdana",30));
        textArea.setPromptText("Enter the text here");
        root.getChildren().add(textArea);
        
        Button submit = new Button();
        submit.setText("Submit");
        submit.setLayoutX(200);
        submit.setLayoutY(190);
        submit.setPrefSize(200, 60);
        submit.setFont(new Font("Arial",25));
        submit.setOnAction(e -> submitButtonAction(textArea));
        root.getChildren().add(submit);
        
        inputScene = new Scene(root);
//        window = new Stage();
        window = stage;
        window.setScene(inputScene);
        window.show();
    }
    public void submitButtonAction(TextArea textArea){
        System.out.println(textArea.getText());
        if(errorMessageFlag)
            root.getChildren().remove(3);
            errorMessageFlag = false;
        try{
            size = Integer.parseInt(textArea.getText().trim());
            if(size<=0)
                throw new NumberFormatException();
            Main.setMazeSize(size);
            
            synchronized(Main.waiter){
                Main.waiter.notify();
                try {
                    Main.waiter.wait();
                } catch (InterruptedException ex) {}
                mazeSceneStarter(window);
                Main.waiter.notify();
            }
//            Thread.sleep(3000);
//            window.close();
            mazeSceneStarter(window);
        }
        catch(NumberFormatException nFormat){
            Text errorMessage = new Text();
            errorMessage.setText("Invalid Input !");
            errorMessage.setStroke(Color.RED);
            errorMessage.setStrokeWidth(2);
            errorMessage.setFont(Font.font("Verdana",30));
            errorMessage.setX(0);
            errorMessage.setY(180);
            root.getChildren().add(errorMessage);
            errorMessageFlag = true;
        }
//        catch (InterruptedException ex) {
//            Logger.getLogger(MazeGui.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
}
