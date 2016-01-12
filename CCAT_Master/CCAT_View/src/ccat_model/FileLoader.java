/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ccat_model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class FileLoader {
    
    private final Scanner fileLoader;
    private final Map<String, Map<String, List<String>>> content;
    private List<String> orderedSubheaders;
    
    public FileLoader(FileReader file) throws FileNotFoundException{
        fileLoader = new Scanner(file);
        content = new HashMap<>();
    }
    
    public Map<String, Map<String, List<String>>> getContent(){return Collections.unmodifiableMap(content);}
    
    public void traverseMap(){
        for (String header : content.keySet()){
            System.out.println(header);
            for (String subheader : content.get(header).keySet()){
                System.out.println(subheader);
                for (String field : content.get(header).get(subheader))
                    System.out.println(field);
            }
        }
    }
    
    public void loadTemplate(){
        String header = "", subHeader = "", temp;
        Map<String, List<String>> sections = new HashMap<>();
        List<String> fields = new ArrayList<>();
        orderedSubheaders = new ArrayList<>();
        
        while (fileLoader.hasNextLine()){
            temp = fileLoader.nextLine();
            //System.out.println(temp);
            if (temp.isEmpty()) {}
            else if  (temp.charAt(0) == '['){
                if (!header.isEmpty()) content.put(header, sections);
                sections = new HashMap<>();
                header = temp.split("\\[")[1].split("\\]")[0];
                //subHeader = fileLoader.nextLine().split("-")[1];
            }
            else if (temp.charAt(0) == '-'){
                
                //TODO: make sure the last read in subheader is put to the map properly
                if (!sections.isEmpty()) sections.put(subHeader, fields);
                sections.put(subHeader, fields);
                fields = new ArrayList<>();
                orderedSubheaders.add(subHeader);
                //System.out.println(subHeader + " dwvmwpo jgwrj ");
                subHeader = temp.split("-")[1];
                //orderedSubheaders.add(subHeader);
                //System.out.println(subHeader + " ======== ");
            }
            
            else {
                fields.add(temp.substring(2));
            }
            
        }
        content.put(header, sections);
        
    }
    
    public List<String> getOrderedSubheaders(){
        return orderedSubheaders;
    } 
    
    //TODO: make headers return in the same order as they are in questions.txt
    public List<String> getHeaders(){
        List<String> headers = new ArrayList<>();
        for (String key : content.keySet()){
            headers.add(key);
            System.out.println(key);
        }
        return headers;
    }
}
