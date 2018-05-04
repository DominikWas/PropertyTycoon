/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propertytycoon;

import java.util.ArrayList;

/**
 *
 * @author Group 22
 */
public class Board 
{
    private String[] positionName;
    //private int[][] boardPosition;
    
    public Board()
    {
        //boardPosition = new int[40][6]; //current theory: 40 positions, 6 player ID values (NOT player quantity)
        positionName = new String[40];
        positionName[0] = "Go";
        positionName[1] = "Crapper Street";
        positionName[2] = "Pot Luck";
        positionName[3] = "Gangsters Paradise";
        positionName[4] = "Income Tax";
        positionName[5] = "Brighton Station";
        positionName[6] = "Weeping Angel";
        positionName[7] = "Opportunity Knocks";
        positionName[8] = "Potts Avenue";
        positionName[9] = "Nardole Drive";  
        positionName[10] = "Jail/Just visiting";  
        positionName[11] = "Skywalker Drive";  
        positionName[12] = "Tesla Power Co";  
        positionName[13] = "Wookie Hole";  
        positionName[14] = "Rey Lane";  
        positionName[15] = "Hove Station";
        positionName[16] = "Cooper Drive";
        positionName[17] = "Pot Luck";
        positionName[18] = "Wolowitz Street";
        positionName[19] = "Penny Lane";
        positionName[20] = "Free Parking";
        positionName[21] = "Yue Fei Square";
        positionName[22] = "Opportunity Knocks";
        positionName[23] = "Mulan Rouge";
        positionName[24] = "Han Xin Gardens";
        positionName[25] = "Falmer Station";
        positionName[26] = "Kirk Close";
        positionName[27] = "Picard Avenue"; 
        positionName[28] = "Edison Water"; 
        positionName[29] = "Crusher Creek"; 
        positionName[30] = "Go to Jail"; 
        positionName[31] = "Sirat Mews"; 
        positionName[32] = "Ghengis Crescent"; 
        positionName[33] = "Pot Luck"; 
        positionName[34] = "Ibis Close"; 
        positionName[35] = "Lewes Station"; 
        positionName[36] = "Opportunity Knocks"; 
        positionName[37] = "Hawking Way";
        positionName[38] = "Super Tax"; 
        positionName[39] = "Turing Heights";         
    }

    public String[] getPositionList() 
    {
        return positionName;
    }
    
    public String getPositionName(int id)
    {
        return positionName[id];
    }
    
//    public int getPositionID(int id)
//    {
//        return positionName[id];
//    }
    
}
