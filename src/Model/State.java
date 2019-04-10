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
public class State {
    private String name; 
    private City city; 
    
    public State(){
        
    } 
    public State(String name, City city){
        this.name = name; 
        this.city = city;
    } 
    
    public String getName(){
        return name;
    } 
    public City getCity (){
        return city;
    } 
    
    public void setName(String rowInFile){
        this.name = getParam("^(.*?);", rowInFile).toUpperCase();
    } 
    public void setCity(String rowInFile){ 
        City newCity = new City(); 
        newCity.setName(rowInFile); 
        newCity.setPopulation(rowInFile);  
        this.city = newCity;
    } 
    @Override 
    public String toString(){
        return  " " + getCity();
    }
    private String getParam(String pattern, String rowInFile){
        String result = ""; 
        
        Pattern p = Pattern.compile(pattern); 
        Matcher m = p.matcher(rowInFile); 
        if(m.find()){
            result = m.group(1);
        }
        
        return result;
    }
}
