/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author mac
 */
public class City {
    
    private String name; 
    private int population; 
    
    public City(){
        
    } 
    public City(String name, int population){
        this.name = name; 
        this.population = population;
    } 
    
    public String getName(){
        return name;
    } 
    public int getPopulation(){
        return population;
    } 
    
    public void setName(String rowInFile){
        this.name = findParam(";(.*?)=", rowInFile);
    } 
    public void setPopulation(String rowInFile){
        String cityPop = findParam("=(.*?);", rowInFile); 
        cityPop = cityPop.replaceAll("[^\\d.]", ""); 
        this.population = Integer.parseInt(cityPop);
    } 
    
    private String findParam(String pattern,String rowInFile){
        String param = ""; 
        Pattern p = Pattern.compile(pattern); 
        Matcher m = p.matcher(rowInFile); 
        if(m.find()){
            param = m.group(1);
        }
        return param;
    } 
    @Override 
    public String toString(){
        return getName() + "(" + getPopulation() + ")";
    }
    
}
