/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propertytycoon;

/**
 *
 * @author Group 22
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
        //Properties includes Stations and Utilities
        jailCards = 0; // no. of GOOJF per player
        currentGUI = new TycoonGUI();
    }

    /**
     * Obtain a player's ID
     * @return playerID return player identification number
     */
    public int getPlayerID() 
    {
        return playerID;
    }

    /**
     * Initialise a player's ID
     * @param playerID the number that will be a player's ID
     */
    public void setPlayerID(int playerID) 
    {
        this.playerID = playerID;
    }

    /**
     * Obtain a player's token
     * @return tokenID return token identification number
     */
    public int getTokenID() 
    {
        return tokenID;
    }

    /**
     * Initialise a player's token
     * @param tokenID the token assigned to a player
     */
    public void setTokenID(int tokenID) 
    {
        this.tokenID = tokenID;
    }

    /**
     * Retrieve the amount of money a player has
     * @return cash return a number describing how much money a player has
     */
    public int getCash() 
    {
        return cash;
    }

    /**
     * Change the amount of money a player has
     * @param cash current amount of money a player has
     */
    public void setCash(int cash) 
    {
        this.cash = cash;
    }

    /**
     * Retrieve the current position of a player
     * @return currentPos return a player's position
     */
    public int getCurrentPos() 
    {
        return currentPos;
    }

    /**
     * Change the position of a player
     * @param currentPos a player's current position
     */
    public void setCurrentPos(int currentPos) 
    {
        this.currentPos = currentPos;
    }
    
    /**
     * Initialise a variable to be used to determine when it's a player's turn
     * @return isTurn return true or false depending on whether or not it's a player's turn
     */
    public boolean isPlayersTurn() 
    {
        return isTurn;
    }

    /**
     * Decide if it's a player's turn or not
     * @param playersTurn boolean saying if it's the player's turn or not
     */
    public void setPlayersTurn(boolean playersTurn) 
    {
        this.isTurn = playersTurn;
    }

    /**
     * Initialise a variable to be used to determine when a player is in jail
     * @return isInJail return true or false depending on whether or not a player is in jail
     */
    public boolean isIsInJail() 
    {
        return isInJail;
    }

    /**
     * Decide if a player is in jail
     * @param isInJail boolean saying if a player is in jail
     */
    public void setIsInJail(boolean isInJail) 
    {
        this.isInJail = isInJail;
    }

    /**
     * Retrieve a list of a player's owned properties
     * @return properties return a player's properties
     */
    public Property[] getProperties() 
    {
        return properties;
    }
    
    /**
     * Retrieve a particular property
     * @param i ID of a particular property
     * @return properties[i] return the particular property required
     */
    public Property getPropertyByID(int i)
    {
        return properties[i]; 
    }

    /**
     * Modify a player's list of properties
     * @param properties updated list of a player's properties
     */
    public void setProperties(Property[] properties) 
    {
        this.properties = properties;
    }

    /**
     * Retrieve how many Get Out Of Jail Free cards a player has
     * @return jailCards return the number of GOOJF cards a player has
     */
    public int getJailCards() 
    {
        return jailCards;
    }

    /**
     * Change how many GOOJF cards a player has
     * @param jailCards updated number of GOOJF cards a player has
     */
    public void setJailCards(int jailCards) 
    {
        this.jailCards = jailCards;
    }

    /**
     * Retrieve the name of the tile a player is on
     * @return currentStringPos return name of the tile a player is on
     */
    public String getCurrentStringPos() 
    {
        return currentStringPos;
    }

    /**
     * Change the name of the tile a player is on
     * @param currentStringPos updated name of the tile a player is on
     */
    public void setCurrentStringPos(String currentStringPos) 
    {
        this.currentStringPos = currentStringPos;
    }

    /**
     * Identify the current GUI
     * @return currentGUI return GUI used currently
     */
    public TycoonGUI getCurrentGUI() 
    {
        return currentGUI;
    }

    /**
     * Change the current GUI
     * @param currentGUI updated GUI
     */
    public void setCurrentGUI(TycoonGUI currentGUI) 
    {
        this.currentGUI = currentGUI;
    }
    
    
    /**
     * Has the player take a turn.
     * Checks for doubles or triples, as well as the effects of each tile the player lands on
     * @return dice return Dice returns the dice that has been rolled
     */
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
    
    /**
     * Updates the player's string position + checks if player's cash is negative.
     */
    public void update()
    {
        currentStringPos = board.getPositionName(currentPos);
        
        if (cash < 0)
        {
            System.out.println("Sell or give up");
        }
    }

    /**
     * The effect given when a player lands on a particular tile.
     */
    public void landing()
    {
        boolean buy = false;
        boolean auction = false;
        
        // If the player lands on Pot Luck
        if (getCurrentPos() == 2 || getCurrentPos() == 17 || getCurrentPos() == 33)
        {
            currentTycoonGame.getOKPL().drawPot(this);
        }

        // If the player lands on Opportunity Knocks
        if (getCurrentPos() == 7 || getCurrentPos() == 22 || getCurrentPos() == 36)
        {
            currentTycoonGame.getOKPL().drawOpportunity(this);
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
                //if property is there and it is buyable
                if (currentTycoonGame.getPropertyInGame(i).getName().equals("Brighton Station") && currentTycoonGame.getPropertyInGame(i).isBuyable())
                {
                    if (getCash() >= currentTycoonGame.getPropertyInGame(i).getCost())
                    {
                        System.out.println("Would you like to buy Brighton Station for £200?");
                        
                        if (buy)
                        {
                            buyProperty(getPropertyByID(getCurrentPos()));
                        }
                        else if (auction)
                        {
                            //Set up an auction and end turn
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Represents a player purchasing a property in the game.
     * @param p property to be bought
     */    
    public void buyProperty(Property p)
    {
        //Check if can be bought done in landing()
        
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
        
        //set property to not buyable in a global way
        for (int i = 0; i < currentTycoonGame.getPropertiesInGame().size(); i++)
        {
            //if the property found is equal to the property being bought
            if (currentTycoonGame.getPropertyInGame(i).equals(p))
            {
                currentTycoonGame.getPropertyInGame(i).setBuyable(false);
            }
        }
        // Give the player the option of selling, buying houses, trading or mortgaging
        managing();
    }
    
    /**
     * Represents a player auctioning a property in the game
     * @param p property to be auctioned
     */    
    public void auctionProperty(Property p)
    {

    }
    
    public void managing()
    {
        //Selling
        //Trading
        //Mortgaging
        //Houses
    }
}
