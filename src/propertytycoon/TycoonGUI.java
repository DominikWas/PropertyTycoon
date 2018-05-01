/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propertytycoon;

import java.io.File;
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
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

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
    private Tycoon currentTycoonGame;
    private Stage stage;
    private TextField playerTextField;
    private Button playMusic;
    private Button pauseMusic;

    public Stage getStage() 
    {
        return stage;
    }
    
    //First page
    @Override
    /**
     * Load the menu page
     * @param primaryStage menu details
     */
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
        errorText.setText("Error: Incorrect no. of players, please try again.");
        errorText.setFont(Font.font ("Verdana", 22));
        errorText.setVisible(false);
        
        loadMonopolyMan();
        
        Label playerLabel = new Label("Number of players:");
        
        playerTextField = new TextField("#");
        
        HBox hb = new HBox();
        hb.getChildren().addAll(playerLabel, playerTextField);
        hb.setSpacing(10);
        hb.setTranslateX(400);
        hb.setTranslateY(438);
                 
        //Create a new layout and add elements to it like button and text
        StackPane root = new StackPane();

        root.setStyle("-fx-background-color: BEIGE;");  
        root.getChildren().add(loadMonopolyMan());         
        root.getChildren().add(titleText);
        root.getChildren().add(errorText);
        root.getChildren().add(hb);
                
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
                                        
                    //load the game
                    Tycoon tycoon = new Tycoon(Integer.parseInt(playerTextField.getText()));
                    currentTycoonGame = tycoon;
                    
                    //take turn for first player
                    currentTycoonGame.playerTakeTurn(currentTycoonGame.getPlayerInGameByID(0));
                    
                    //load the menu
                    loadGameMenu(primaryStage);                    
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
                @Override 
                public void handle(MouseEvent e) 
                {
                    newButton.setEffect(shadow);
                }
            });

        //Add shadow - mouse cursor = on
        quitButton.addEventHandler(MouseEvent.MOUSE_ENTERED, 
            new EventHandler<MouseEvent>() 
            {
                @Override 
                public void handle(MouseEvent e) 
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
    
    /**
     * Load the game
     * @param primaryStage game details
     */
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
            // First Player
            root2.getChildren().add(loadBoot());            
            
            // Second Player
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
                // Third Player
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
                // Fourth Player
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
                // Fifth Player
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
                // Sixth Player
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
        
        //TOP MENU - GAME
        Label currentPlayerLabel = new Label("Player " + currentTycoonGame.getPlayerInGameByID(0).getPlayerID() + "'s turn");
        currentPlayerLabel.setTranslateX(425);
        currentPlayerLabel.setTranslateY(-375);
        currentPlayerLabel.setScaleX(3);
        currentPlayerLabel.setScaleY(3);
        root2.getChildren().add(currentPlayerLabel);
        
        Label currentPlayerPropertiesLabel = new Label(currentTycoonGame.getPlayerInGameByID(0).getProperties().toString());
        currentPlayerPropertiesLabel.setTranslateX(425);
        currentPlayerPropertiesLabel.setTranslateY(-300);
        currentPlayerPropertiesLabel.setScaleX(2);
        currentPlayerPropertiesLabel.setScaleY(2);
        root2.getChildren().add(currentPlayerPropertiesLabel);
        
        //BOTTOM MENU - GAME
        //for seperating (x, y, w, h)
        Rectangle menuSep = new Rectangle(0, 0, 300, 3);
        menuSep.setStroke(Color.BLACK);
        menuSep.setFill(null);
        menuSep.setStrokeWidth(6);
        menuSep.setTranslateX(425);
        menuSep.setTranslateY(0);        
        root2.getChildren().add(menuSep);        
        
        //button used to take turn (roll dice)
        Button rollButton = new Button("Roll Dice");
        rollButton.setTranslateX(430);
        rollButton.setTranslateY(75);
        rollButton.setScaleX(2);
        rollButton.setScaleY(2);

        //Roll button functionality
        rollButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                
            }
        });

        root2.getChildren().add(rollButton);

        //button used to purchase a property
        Button buyButton = new Button("Buy Property");
        buyButton.setTranslateX(430);
        buyButton.setTranslateY(150);
        buyButton.setScaleX(2);
        buyButton.setScaleY(2);
        root2.getChildren().add(buyButton);

        //button used to auction a property
        Button auctionButton = new Button("Auction Property");
        auctionButton.setTranslateX(430);
        auctionButton.setTranslateY(225);
        auctionButton.setScaleX(2);
        auctionButton.setScaleY(2);
        root2.getChildren().add(auctionButton);
        
        //music player - reference provided in loadMusic() method
        root2.getChildren().add(loadMusic(root2));
        
        //button for trade, sell, construcc-tion, mortgage and view other profiles
        //TO DO
                
        primaryStage.setScene(scene2);
        primaryStage.show();
        System.out.println("Game loaded!");
    }
    
    /**
     * Load the Business Man on the title screen
     * @return an image of the Business Man
     */
    public ImageView loadMonopolyMan()
    {
        try 
        {
            Image monopolyMan = new Image(new FileInputStream("monopolyMan.jpg"), 800, 800, true, true);
            ImageView monopolyManView = new ImageView(monopolyMan);
            monopolyManView.setTranslateX(0);
            monopolyManView.setTranslateY(-210);
            monopolyManView.setScaleX(0.3);
            monopolyManView.setScaleY(0.3);
            return monopolyManView;
        } 
        catch (FileNotFoundException ex) 
        {
            Logger.getLogger(TycoonGUI.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    /**
     * Load the Boot on the game screen
     * @return an image of the Boot
     */
    public ImageView loadBoot()
    {
        try 
        {
            Image boot = new Image(new FileInputStream("boot.png"), 800, 800, true, true);
            ImageView bootView = new ImageView(boot);
            bootView.setTranslateX(195);
            bootView.setTranslateY(390);
            bootView.setScaleX(0.05);
            bootView.setScaleY(0.05);
            return bootView;
        } 
        catch (FileNotFoundException ex) 
        {
            Logger.getLogger(TycoonGUI.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    
    /**
    *   Loads up the music for the game menu
    *   Music taken from: https://www.youtube.com/watch?v=gUqH6Weyr2M
    *   Spiderman 2 Game - Pizza Theme
    *   Accessed: 30/04/2018
    *   @param p menu layout to be input
    *   @return mjuzikView the media view returned to play the music
    */   
    public MediaView loadMusic(StackPane p)
    {
        String musicLoc = "PTtheme.mp3";
        
        Media mjuzik = new Media(new File(musicLoc).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(mjuzik);
        mediaPlayer.setAutoPlay(true);
        
        playMusic = new Button("Music On/Off");
        
        MediaView mjuzikView = new MediaView(mediaPlayer); 
        
        //on click
        playMusic.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent e) 
            {
                Status status = mediaPlayer.getStatus();

                if (status == Status.PAUSED || status == Status.READY || status == Status.STALLED || status == Status.STOPPED) 
                {
                    mediaPlayer.play();
                } 
                else 
                {
                    mediaPlayer.pause();
                }
            }
        });
        
        playMusic.setTranslateX(0);
        playMusic.setTranslateY(-425);
        playMusic.setScaleX(1);
        playMusic.setScaleY(1);    
        
        p.getChildren().add(playMusic);
        
        return mjuzikView;
    }
    
    public static void main(String[] args)
    {
        launch(args);
        
        //Card c = new Card();
        //Tycoon tycoon = new Tycoon(3);
        //c.drawPot(tycoon.getPlayerInGameByID(0));
    }    
}
