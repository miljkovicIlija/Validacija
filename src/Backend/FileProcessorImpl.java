/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import Model.State;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;
import java.util.stream.Stream;

/**
 *
 * @author mac
 */
public class FileProcessorImpl implements FileProcessor{
    
    @Override
    public boolean isAlpha(String data) {
        return checkRegex("^(?=.*[a-z])(?=.*[A-Z]).+$",data);
    }

    @Override
    public boolean isDigit(String data) {
        return checkRegex("(.)*(\\d)(.)*", data);
    }

    @Override
    public boolean isASCII(String data) {
        return checkRegex("^[\\u0000-\\u007F]*$",data);
    } 
    private boolean condition(String line){
        boolean result = false;
        if(isAlpha(line) && isDigit(line) && isASCII(line)){
            result = true;
        }
        return result;
    }

    @Override
    public List<String> readFile() {
        List<String> stringList = new ArrayList<>(); 
        File inputFile = new File("FileToRead.txt"); 
        try(BufferedReader reader = new BufferedReader(new FileReader(inputFile))){
            String line = reader.readLine(); 
            while(line != null){
               stringList.add(line); 
               line = reader.readLine();
            }
        } catch(IOException ioe){
            System.out.println("Error occured while reading the file: " + ioe);
        }
        
        return stringList;
    }

    @Override
    public List<State> setModel(List<String> list) {
      return list.stream() 
                 .map(e -> {
                    State st = new State(); 
                    st.setName(e);
                    st.setCity(e);
                    return st;
                 })
                 .collect(toList()); 
    }

    @Override
    public void writeToFile(List<String> list) {
        File outputFile = new File("ValidationResult.txt"); 
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))){
            int lineNum = 0; 
            for(String line: list){
                if(lineNum++ > 0){
                    writer.newLine();
                } 
                if(condition(line)){
                    writer.write(line + "VALID");
                } else {
                    writer.write(line + "INVALID");
                }
            }
        }catch(IOException ioe){
            System.out.println("Error occured while writing to the file: " + ioe);
        }
    } 
    @Override 
    public void print(List<State> list){
           Map<String, List<State>> stateByName = list.stream() 
                                                        .collect(groupingBy(State::getName)); 
           for(Map.Entry<String, List<State>> entry: stateByName.entrySet()){
                System.out.println(entry.getKey() + " : " + entry.getValue()); 
                System.out.println("---------------------------------------------------------------");
    }
            
    }
    private boolean checkRegex(String pattern, String mathcer){
        boolean result = false; 
        Pattern p = Pattern.compile(pattern); 
        Matcher m = p.matcher(mathcer); 
        if(m.find()){
            result = true;
        }
        return result;
    }
}
