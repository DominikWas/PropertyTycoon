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
public class Station 
{
    
    private boolean buyable;
    public String stationName;
    
    /**
     * Initialise a station
     * @param stationName name of a station
     * @param buyable is the station for public sale
     */
    public Station(String stationName, boolean buyable)
    {
        this.stationName = stationName;
        this.buyable = buyable;
    }

}
