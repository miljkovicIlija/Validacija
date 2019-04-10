/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.likan.fv;

import Backend.FileProcessorImpl;
import Model.State;
import java.util.List;

/**
 *
 * @author mac
 */
public class Application {
    public static void main(String[] args) {
        FileProcessorImpl obj = new FileProcessorImpl(); 
        List<String> stringList = obj.readFile(); 
        obj.writeToFile(stringList); 
        List<State> finalList = obj.setModel(stringList); 
        obj.print(finalList);
        
    }
}
