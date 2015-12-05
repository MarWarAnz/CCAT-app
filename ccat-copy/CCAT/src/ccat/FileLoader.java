/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ccat;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class FileLoader {
    
    private final Scanner fileLoader;
    private final HashMap<String, HashMap<String, ArrayList<String>>> content;
    
    public FileLoader(File file) throws FileNotFoundException{
        fileLoader = new Scanner(file);
        content = new HashMap<>();
    }
    
    public HashMap getContent(){return content;}
    
    public boolean loadFile(){
        while (fileLoader.hasNextLine()){
            
        }
        return true;
    }
}
