/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propertytycoon;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author dw294
 */

//How to Create a JavaFX GUI Application in NetBeans IDE
//ProgrammingKnowledge - Youtube

public class TycoonGUI extends Application
{
    private boolean startMenu = true;
    private boolean gameMenu = false;
    private boolean turnMenu = false;
    private Tycoon currentTycoonGame;
    private Stage stage;
    private TextField playerTextField;

    public Stage getStage() 
    {
        return stage;
    }
    
    @Override
    public void start(Stage primaryStage)
    {   
        stage = primaryStage;
        
        //title of program on the bar
        primaryStage.setTitle("Property Tycoon");
        
        //CREATE AND EDIT ELEMENTS (e.g.: title, button etc.)
        Button newButton = new Button("New Game");
        newButton.setTranslateX(150);
        
        Button quitButton = new Button("Quit");
        quitButton.setTranslateX(250);
        
        Text titleText = new Text();
        titleText.setText("Property Tycoon");
        titleText.setFont(Font.font ("Verdana", 48));
        
        Text errorText = new Text();
        errorText.setText("Error: Wrong number of players! Get memed son");
        errorText.setFont(Font.font ("Verdana", 48));
        errorText.setVisible(false);
        
        Label playerLabel = new Label("Number of players:");
        //playerLabel.setX(100);
        //playerLabel.setY(100);
        
        playerTextField = new TextField("#");
        //playerTextField.setTranslateX(200);
        //playerTextField.setTranslateY(100);
        
        HBox hb = new HBox();
        hb.getChildren().addAll(playerLabel, playerTextField);
        hb.setSpacing(10);
        hb.setTranslateX(400);
        hb.setTranslateY(438);
                 
        //Create a new layout and add elements to it like button and text
        StackPane root = new StackPane();
        
        //Background colour
        root.setStyle("-fx-background-color: BEIGE;");  
        root.getChildren().add(titleText);
        root.getChildren().add(errorText);
        root.getChildren().add(hb);
        
        //Note: Make sure to initialise button after, make it on top
        
        //New button - starting the game
        newButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                if (Integer.parseInt(playerTextField.getText()) > 6 || Integer.parseInt(playerTextField.getText()) < 2)
                {
                    errorText.setVisible(true);                    
                    System.out.println("Error: Please input in a valid amount of players (2-6).");
                }                
                else
                {
                    System.out.println("Start game");
                    startMenu = false;
                    gameMenu = true;
                    root.setVisible(false);
                    //load menu
                    loadGameMenu(primaryStage);
                    
                    //load the game
                    Tycoon tycoon = new Tycoon(Integer.parseInt(playerTextField.getText()));
                    currentTycoonGame = tycoon;
                    currentTycoonGame.getPlayerInGameByID(0).takeTurn();
                }
            }
        });
        
        //Make sure to initialise button after, make it on top
        quitButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                System.out.println("Quitting Property Tycoon...");
                System.exit(0);
            }
        });

        DropShadow shadow = new DropShadow();
        
        //Add shadow - mouse cursor = on
        newButton.addEventHandler(MouseEvent.MOUSE_ENTERED, 
            new EventHandler<MouseEvent>() 
            {
                @Override public void handle(MouseEvent e) 
                {
                    newButton.setEffect(shadow);
                }
            });

        //Add shadow - mouse cursor = on
        quitButton.addEventHandler(MouseEvent.MOUSE_ENTERED, 
            new EventHandler<MouseEvent>() 
            {
                @Override public void handle(MouseEvent e) 
                {
                    quitButton.setEffect(shadow);
                }
            });
        
        //Remove shadow - mouse cursor = off
        newButton.addEventHandler(MouseEvent.MOUSE_EXITED, 
            new EventHandler<MouseEvent>() 
            {
                @Override public void handle(MouseEvent e) 
                {
                    newButton.setEffect(null);
                }
            });        

        //Remove shadow - mouse cursor = off
        quitButton.addEventHandler(MouseEvent.MOUSE_EXITED, 
            new EventHandler<MouseEvent>() 
            {
                @Override public void handle(MouseEvent e) 
                {
                    quitButton.setEffect(null);
                }
            });        
        
        root.getChildren().add(newButton);
        root.getChildren().add(quitButton);        
        
        //Set position of elements attached to stackpane
        root.setAlignment(titleText, Pos.TOP_CENTER);
        root.setAlignment(errorText, Pos.BOTTOM_CENTER);
        root.setAlignment(newButton, Pos.CENTER);
        root.setAlignment(quitButton, Pos.CENTER);
        //root.setAlignment(playerLabel, Pos.CENTER_LEFT);
        //root.setAlignment(playerTextField, Pos.CENTER_RIGHT);
        root.setAlignment(hb, Pos.CENTER);
        
        //Make a new scene for the Property Tycoon
        //pass in the layout, in this case stackpane
        Scene scene = new Scene(root, 1200, 900);
        
        //set it
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void loadGameMenu(Stage primaryStage)
    {
        gameMenu = true;
        startMenu = false;
        
        //new layout
        StackPane root2 = new StackPane();        
        root2.setStyle("-fx-background-color: BEIGE;");
        Scene scene2 = new Scene(root2, 1200, 900);
        try 
        {
            //drawing the jpg board
            Image tycoonBoard = new Image(new FileInputStream("\\\\smbhome.uscs.susx.ac.uk\\dw294\\Pictures\\Property Tycoon Board draft.jpg"), 800, 800, true, true);
            ImageView tycoonView = new ImageView(tycoonBoard);
            tycoonView.setTranslateX(-150);
            tycoonView.setTranslateY(0);
            root2.getChildren().add(tycoonView);
        } 
        catch (FileNotFoundException ex) 
        {
            //jpg board not loaded
            Logger.getLogger(TycoonGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //HOW MANY PLAYERS MUST BE RENDERED
        if (Integer.parseInt(playerTextField.getText()) > 1)
        {
            // FIRST PLAYER IS MA DAWG
            try 
            {
                Image tycoonDog = new Image(new FileInputStream("\\\\smbhome.uscs.susx.ac.uk\\dw294\\Pictures\\Software Eng\\dog.jpeg"), 25, 25, true, true);
                ImageView tycoonViewDog = new ImageView(tycoonDog);
                tycoonViewDog.setTranslateX(195);
                tycoonViewDog.setTranslateY(345);
                root2.getChildren().add(tycoonViewDog);
            } 
            catch (FileNotFoundException ex) 
            {
                Logger.getLogger(TycoonGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            // SECOND PLAYER IS THE BOAT
            try 
            {
                Image tycoonBoat = new Image(new FileInputStream("\\\\smbhome.uscs.susx.ac.uk\\dw294\\Pictures\\Software Eng\\boat.jpg"), 35, 35, true, true);
                ImageView tycoonViewBoat = new ImageView(tycoonBoat);
                tycoonViewBoat.setTranslateX(230);
                tycoonViewBoat.setTranslateY(340);
                root2.getChildren().add(tycoonViewBoat);
            } 
            catch (FileNotFoundException ex) 
            {
                Logger.getLogger(TycoonGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (Integer.parseInt(playerTextField.getText()) > 2)
            {
                // THIRD PLAYER IS THE SHOE
                try
                {
                    Image tycoonShoe = new Image(new FileInputStream("\\\\smbhome.uscs.susx.ac.uk\\dw294\\Pictures\\Software Eng\\shoe.jpg"), 35, 35, true, true);
                    ImageView tycoonViewShoe = new ImageView(tycoonShoe);
                    tycoonViewShoe.setTranslateX(195);
                    tycoonViewShoe.setTranslateY(370);
                    root2.getChildren().add(tycoonViewShoe);
                }
                catch (FileNotFoundException ex)
                {
                    Logger.getLogger(TycoonGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (Integer.parseInt(playerTextField.getText()) > 3)
            {
                // FOURTH PLAYER WILL HAVE TWO NUMBER 9S A NUMBER 9 LARGE A NUMBER 6 WITH EXTRA DIP A NUMBER 7 TWO NUMBER 45S ONE WITH CHEESE AND A LARGE SODA
                try
                {
                    Image tycoonSmoke = new Image(new FileInputStream("\\\\smbhome.uscs.susx.ac.uk\\dw294\\Pictures\\Software Eng\\Smoke.jpg"), 25, 25, true, true);
                    ImageView tycoonViewSmoke = new ImageView(tycoonSmoke);
                    tycoonViewSmoke.setTranslateX(230);
                    tycoonViewSmoke.setTranslateY(370);
                    root2.getChildren().add(tycoonViewSmoke);
                }
                catch (FileNotFoundException ex)
                {
                    Logger.getLogger(TycoonGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (Integer.parseInt(playerTextField.getText()) > 4)
            {
                // FIFTH PLAYER IS DOM
                try
                {
                    Image tycoonSenpai = new Image(new FileInputStream("\\\\smbhome.uscs.susx.ac.uk\\dw294\\Pictures\\Software Eng\\senpai.jpg"), 25, 25, true, true);
                    ImageView tycoonViewSenpai = new ImageView(tycoonSenpai);
                    tycoonViewSenpai.setTranslateX(195);
                    tycoonViewSenpai.setTranslateY(390);
                    root2.getChildren().add(tycoonViewSenpai);
                }
                catch (FileNotFoundException ex)
                {
                    Logger.getLogger(TycoonGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (Integer.parseInt(playerTextField.getText()) > 5)
            {
                // SIXTH PLAYER CHEEKI BREEKI
                try
                {
                    Image tycoonSlav = new Image(new FileInputStream("\\\\smbhome.uscs.susx.ac.uk\\dw294\\Pictures\\Software Eng\\slav.jpg"), 25, 25, true, true);
                    ImageView tycoonViewSlav = new ImageView(tycoonSlav);
                    tycoonViewSlav.setTranslateX(230);
                    tycoonViewSlav.setTranslateY(390);
                    root2.getChildren().add(tycoonViewSlav);
                }
                catch (FileNotFoundException ex)
                {
                    Logger.getLogger(TycoonGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        //button for trade, sell, construcc-tion, mortgage and view other profiles
        //System.out.println("My name is Jeff");
        //TO DO LUL
                
        primaryStage.setScene(scene2);
        primaryStage.show();
        System.out.println("Game loaded!");
    }
    
    public void loadTurnMenu(Stage primaryStage)
    {
        turnMenu = true;
        Button buyButton = new Button("Buy");
        buyButton.setTranslateX(150);
        Button auctionButton = new Button("Auction");
        auctionButton.setTranslateX(300);
        
    }
    
    public static void main(String[] args)
    {
        launch(args);
        
        //Card c = new Card();
        //Tycoon tycoon = new Tycoon(3);
        //c.drawPot(tycoon.getPlayerInGameByID(0));
    }
}
