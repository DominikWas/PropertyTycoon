/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package propertytycoon;

import java.util.*;

/**
 *
 * @author Group 22
 */
public class Card 
{
    
    private ArrayList opportunityKnocks;
    private ArrayList potLuck;
    private Tycoon tycoon;
    
    public Card()
    {
        opportunityKnocks = new ArrayList<Integer>();
        potLuck = new ArrayList<Integer>();
        
        for (int i = 0; i < 16; i++)
        {
            //16 cards
            opportunityKnocks.add(i);
            potLuck.add(i);
        }
        
        //Shuffle both deques of cards
        Collections.shuffle(opportunityKnocks);
        Collections.shuffle(potLuck);
        
        System.out.println(opportunityKnocks);
        System.out.println(potLuck);
        System.out.println();
    }
    
    
    /**
    *   Player draws an Opportunity Knocks card
    *   @param p player who draws the card
    */   
    public void drawOpportunity(Player p)
    {
        int cardTaken = (Integer) opportunityKnocks.get(0);
        opportunityKnocks.remove(0);
        opportunityKnocks.add(opportunityKnocks.size() - 1, cardTaken);
        
        opportunityKnocksEffects(p, cardTaken);
        
    }
    
    
    /**
    *   Player draws a Pot Luck card
    *   @param p player who draws the card
    */   
    public void drawPot(Player p)
    {
        int cardTaken = (Integer) potLuck.get(0);
        potLuck.remove(0);
        potLuck.add(potLuck.size() - 1, cardTaken);
        
        potLuckEffects(p, cardTaken);
    }
    
    
    /**
     * Player follows the instructions given by the card drawn
     * @param p player who drew the card
     * @param card card the player drew
     */
    public void potLuckEffects(Player p, int card)
    {
        if (card == 0)
        {
            System.out.println("You inherit £100");
            p.setCash(p.getCash() + 100);
        }
        
        if (card == 1)
        {
            System.out.println("You have won 2nd prize in a beauty contest, collect £20");
            p.setCash(p.getCash() + 20);            
        }
        
        if (card == 2)
        {
            System.out.println("Go back to Crapper Street");
            p.setCurrentPos(1);
        }
        
        if (card == 3)
        {
            System.out.println("Student loan refund. Collect £20");
            p.setCash(p.getCash() + 20);
        }
        
        if (card == 4)
        {
            System.out.println("Bank error in your favour. Collect £200");
            p.setCash(p.getCash() + 200);
        }
        
        if (card == 5)
        {
            System.out.println("Pay bill for text books of £100");
            p.setCash(p.getCash() - 100);
        }
        
        if (card == 6)
        {
            System.out.println("Mega late night taxi bill pay £50");
            p.setCash(p.getCash() - 50);
        }
        
        if (card == 7)
        {
            System.out.println("Advance to go");
            p.setCurrentPos(0);
        }
        
        if (card == 8)
        {
            System.out.println("From sale of Bitcoin you get £50");
            p.setCash(p.getCash() + 50);
        }
        
        if (card == 9)
        {
            System.out.println("Pay a £10 fine or take opportunity knocks");
            boolean fine = true;
            if (fine = true)
            {
                p.setCash(p.getCash() - 10);
            }
            else
            {
                drawOpportunity(p);
            }
        }
        
        if (card == 10)
        {
            System.out.println("Pay insurance fee of £50");
            p.setCash(p.getCash() - 50);
        }
        
        if (card == 11)
        {
            System.out.println("Savings bond matures, collect £100");
            p.setCash(p.getCash() + 100);
        }
        
        if (card == 12)
        {
            System.out.println("Go to jail. Do not pass GO, do not collect £200");
            p.setCurrentPos(10);
            p.setIsInJail(true);
            // calls endturn?
        }
        
        if (card == 13)
        {
            System.out.println("Received interest on shares of £25");
            p.setCash(p.getCash() + 25);
        }
        
        if (card == 14)
        {
            System.out.println("It's your birthday. Collect £10 from each player");
            
            for (int i = 1; i < tycoon.getNumberOfPlayers(); i++) 
            {
                tycoon.getPlayerInGameByID(i)
                        .setCash(tycoon.getPlayerInGameByID(i).getCash() - 10);
                //take away £10 from each player
                p.setCash(p.getCash() + 10); //give the player £10 for each player
                
            }
        }
        
        if (card == 15)
        {
            System.out.println("Get out of jail free");
            p.setJailCards(p.getJailCards() + 1);
            potLuck.remove(potLuck.size() - 1);
            // remove the card at the bottom because GOOJF has just been sent there
            // (add it to the back once used)
        }
        
        p.update();
    }    
    
    
     /**
     * Player follows the instructions given by the card drawn
     * @param p player who drew the card
     * @param card card the player drew
     */
    public void opportunityKnocksEffects(Player p, int card)
    {
        if (card == 0)
        {
            System.out.println("Bank pays you dividend of £50");
            p.setCash(p.getCash() + 50);
        }
        
        if (card == 1)
        {
            System.out.println("You have won a lip sync battle. Collect £100");
            p.setCash(p.getCash() + 100);
        }
        
        if (card == 2)
        {
            System.out.println("Advance to Turing Heights");
            p.setCurrentPos(39);
        }
        
        if (card == 3)
        {
            System.out.println("Advance to Han Xin Gardens. If you pass GO, collect £200");
            if (p.getCurrentPos() > 24) 
            {
                p.setCash(p.getCash() + 200); //pass GO
            }
            p.setCurrentPos(24);
        }
        
        if (card == 4)
        {
            System.out.println("Fined £15 for speeding");
            p.setCash(p.getCash() - 15);
        }
        
        if (card == 5)
        {
            System.out.println("Pay university fees of £150");
            p.setCash(p.getCash() - 150);
        }
        
        if (card == 6)
        {
            System.out.println("Take a trip to Hove station. If you pass GO collect £200");
            if (p.getCurrentPos() > 15) {
                p.setCash(p.getCash() + 200); //pass GO
            }
            p.setCurrentPos(15);
        }
        
        if (card == 7)
        {
            System.out.println("Loan matures, collect £150");
            p.setCash(p.getCash() + 150);
        }
        
        if (card == 8)
        {
            System.out.println("You are assessed for repairs, £40/house, £115/hotel");
            
            int repairCost = 0;
            
            for (int i = 0; i < p.getProperties().length; i++)
            {
                if (p.getPropertyByID(i).getRentLevel() == 2)
                {
                    repairCost = repairCost + 40;
                }
                
                if (p.getPropertyByID(i).getRentLevel() == 3)
                {
                    repairCost = repairCost + 80;
                }
                
                if (p.getPropertyByID(i).getRentLevel() == 4)
                {
                    repairCost = repairCost + 120;
                }

                if (p.getPropertyByID(i).getRentLevel() == 5)
                {
                    repairCost = repairCost + 160;
                }  

                if (p.getPropertyByID(i).getRentLevel() == 6)
                {
                    repairCost = repairCost + 115;
                }                           
            }
            
            p.setCash(p.getCash() - repairCost);   
        }
        
        if (card == 9)
        {
            System.out.println("Advance to GO");
            p.setCurrentPos(0);
            p.setCash(p.getCash() + 200);
        }
        
        if (card == 10)
        {
            System.out.println("You are assessed for repairs, £25/house, £100/hotel");
            
            int repairCost = 0;
            
            for (int i = 0; i < p.getProperties().length; i++)
            {
                if (p.getPropertyByID(i).getRentLevel() == 2)
                {
                    repairCost = repairCost + 25;
                }
                
                if (p.getPropertyByID(i).getRentLevel() == 3)
                {
                    repairCost = repairCost + 50;
                }
                
                if (p.getPropertyByID(i).getRentLevel() == 4)
                {
                    repairCost = repairCost + 75;
                }

                if (p.getPropertyByID(i).getRentLevel() == 5)
                {
                    repairCost = repairCost + 100;
                }  

                if (p.getPropertyByID(i).getRentLevel() == 6)
                {
                    repairCost = repairCost + 100;
                }                           
            }
            
            p.setCash(p.getCash() - repairCost);   
        }
        
        if (card == 11)
        {
            System.out.println("Go back 3 spaces");
            p.setCurrentPos(p.getCurrentPos() - 3);
        }
        
        if (card == 12)
        {
            System.out.println("Advance to Skywalker Drive. If you pass GO collect £200");
            if (p.getCurrentPos() > 11) 
            {
                p.setCash(p.getCash() + 200); //pass GO
            }
            p.setCurrentPos(11);
        }
        
        if (card == 13)
        {
            System.out.println("Go to jail. Do not pass GO, do not collect £200");
            p.setCurrentPos(10);
            p.setIsInJail(true);
            // calls endturn?
        }
        
        if (card == 14)
        {
            System.out.println("Drunk in charge of a skateboard. Fine £20");
            p.setCash(p.getCash() - 20);
        }
        
        if (card == 15)
        {
            System.out.println("Get out of jail free");
            p.setJailCards(p.getJailCards() + 1);
            opportunityKnocks.remove(opportunityKnocks.size() - 1);
            // remove the card at the bottom because GOOJF has just been sent there
            // (add it to the back once used)
        }
        
        p.update();
    }    
}
