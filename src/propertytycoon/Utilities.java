/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propertytycoon;

/**
 *
 * @author dw294
 */
public class Utilities 
{
    
    private boolean buyable;
    public String utilitiesName;
    
    /**
     * Initialise a Utility
     * @param utilitiesName name of a utility
     * @param buyable is the utility for public sale
     */
    public Utilities(String utilitiesName, boolean buyable)
    {
        this.utilitiesName = utilitiesName;
        this.buyable = buyable;
    }
}
