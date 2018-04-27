/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propertytycoon;

import java.util.Random;

/**
 *
 * @author dw294
 */
public class Player 
{
    private int playerID; //player's ID number, assume id 0 is first and id 1 
                          //2nd and so on...
    private int tokenID; //identification of the token (e.g.: boot)
    private int cash; //player's current money
    private int currentPos; //position on board (position ID)
    private String currentStringPos;
    private boolean isTurn; //is it the player's turn?
    private boolean isInJail; //is the player in jail?
    private boolean isOut; //means they can't pay/afford
    private Property[] properties; //the properties that are in the player's 
                              //posession
    private int jailCards; //get-out-of jail card free number (GOOJF)
    private Board board;
    private Tycoon currentTycoonGame;
    private TycoonGUI currentGUI;
    
    public Player(int playerID, int tokenID)
    {
        this.playerID = playerID;
        this.tokenID = tokenID;
        cash = 1500; //default for game
        currentPos = 0;
        board = new Board();
        currentStringPos = board.getPositionName(currentPos);
        isTurn = false;
        isInJail = false;
        isOut = false;
        properties = new Property[30]; //0-29, 30 possible properties the player can own
        //Properties includes Stations and Utilisties
        jailCards = 0; // no. of GOOJF per player
        currentGUI = new TycoonGUI();
    }

    public int getPlayerID() 
    {
        return playerID;
    }

    public void setPlayerID(int playerID) 
    {
        this.playerID = playerID;
    }

    public int getTokenID() 
    {
        return tokenID;
    }

    public void setTokenID(int tokenID) 
    {
        this.tokenID = tokenID;
    }

    public int getCash() 
    {
        return cash;
    }

    public void setCash(int cash) 
    {
        this.cash = cash;
    }

    public int getCurrentPos() 
    {
        return currentPos;
    }

    public void setCurrentPos(int currentPos) 
    {
        this.currentPos = currentPos;
    }
    
    public boolean isPlayersTurn() 
    {
        return isTurn;
    }

    public void setPlayersTurn(boolean playersTurn) 
    {
        this.isTurn = playersTurn;
    }

    public boolean isIsInJail() 
    {
        return isInJail;
    }

    public void setIsInJail(boolean isInJail) 
    {
        this.isInJail = isInJail;
    }

    public Property[] getProperties() 
    {
        return properties;
    }
    
    public Property getPropertyByID(int i)
    {
        return properties[i]; 
    }

    public void setProperties(Property[] properties) 
    {
        this.properties = properties;
    }

    public int getJailCards() 
    {
        return jailCards;
    }

    public void setJailCards(int jailCards) 
    {
        this.jailCards = jailCards;
    }

    public String getCurrentStringPos() 
    {
        return currentStringPos;
    }

    public void setCurrentStringPos(String currentStringPos) 
    {
        this.currentStringPos = currentStringPos;
    }

    public TycoonGUI getCurrentGUI() 
    {
        return currentGUI;
    }

    public void setCurrentGUI(TycoonGUI currentGUI) 
    {
        this.currentGUI = currentGUI;
    }
    
    public Dice takeTurn() //rolls the dice if it's player's turn
    {
        if (isPlayersTurn())
        {
            Dice dice = new Dice(); //new pair of dice
            dice.rollDice(); //value assign

            System.out.println("Die #1 rolled:" + " " + dice.getFirstVal() + "\n"
            + "Die #2 rolled:" + " " + dice.getSecVal() + "\n");
            
            setCurrentPos(getCurrentPos() + dice.getFirstVal() + dice.getSecVal());
            update();
            landing(); // What effects does the tile have?
            
            if (dice.getFirstVal() == dice.getSecVal()) //if double = another turn
            {
                Dice dice2 = new Dice(); //new pair of dice
                dice.rollDice(); //value assign

                System.out.println("Die #1 rolled:" + " " + dice2.getFirstVal() + "\n"
                + "Die #2 rolled:" + " " + dice2.getSecVal() + "\n");
                
                setCurrentPos(getCurrentPos() + dice.getFirstVal() + dice.getSecVal());                
                update();
                landing();
                
                if (dice2.getFirstVal() == dice2.getSecVal()) //double again = jail
                {
                    setCurrentPos(30);
                    update();
                    setIsInJail(true);
                }
            }
            return dice; //returns the dice
        }
        else
        {
            System.out.println("Sorry, it's not your turn!");
            return null;
        }
    }
    
    public void update()
    {
        currentStringPos = board.getPositionName(currentPos);
        
        if (cash < 0)
        {
            System.out.println("Sell or give up");
        }
    }
    
    public void landing()
    {
        boolean buy = false;
        boolean auction = false;
        
        // If the player lands on Pot Luck
        if (getCurrentPos() == 2 || getCurrentPos() == 17 || getCurrentPos() == 33)
        {
            currentTycoonGame.getOKPL();
        }

        // If the player lands on Opportunity Knocks
        if (getCurrentPos() == 7 || getCurrentPos() == 22 || getCurrentPos() == 36)
        {
            currentTycoonGame.getOKPL();
        }
        
        // If the player lands on Income Tax
        if (getCurrentPos() == 4)
        {
            cash = cash - 200;
        }
        
        // If the player lands on Super Tax
        if (getCurrentPos() == 38)
        {
            cash = cash - 100;
        }
   
        if (getCurrentPos() == 5)
        {
            for (int i = 0; i < currentTycoonGame.getPropertiesInGame().size(); i++)
            {
                if (currentTycoonGame.getPropertyInGame(i).getName().equals("Brighton Station") && currentTycoonGame.getPropertyInGame(i).isBuyable())
                {
                    if (getCash() >= currentTycoonGame.getPropertyInGame(i).getCost())
                    {
                        System.out.println("Would you like to buy Brighton Station for £200?");
                        
                        currentGUI.loadTurnMenu(currentGUI.getStage());

                        if (buy)
                        {
                            buyProperty(getPropertyByID(getCurrentPos()));
                        }
                        else if (auction)
                        {
                            //do something n end turn
                        }
                    }
                }
            }
        }
    }
    
/**
 * Represents a player purchasing a property in the game
 * @param p property to be bought
 */    
    public void buyProperty(Property p)
    {
        //Purchase the property        
        setCash(getCash() - getPropertyByID(getCurrentPos()).getCost());
        
        //add property to list
        for (int i = 0; i < properties.length; i++)
        {
            if (properties[i] == null)
            {
                properties[i] = p;
            }
        }
        managing();
    }
    
    public void managing()
    {
        //Selling
        //Trading
        //Mortgaging
        //Houses
        
        //Table Flip
    }
}
