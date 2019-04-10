/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;


import Model.State;
import java.util.List;

/**
 *
 * @author mac
 */
public interface FileProcessor {
    public boolean isAlpha(String data); 
    public boolean isDigit(String data); 
    public boolean isASCII(String data); 
    public List<String> readFile(); 
    public List<State> setModel(List<String> list); 
    public void writeToFile(List<String> list); 
    public void print(List<State> list);
}
