/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ccat_model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author JRebo_000
 */
public abstract class Account {
    
    protected File accounts;
    protected String uname;
    protected String pass;
    
    public Account() throws FileNotFoundException{
        this.accounts = new File("accounts.txt");
    }
    
    public boolean validate(String uname, String pass) throws FileNotFoundException{
        FileReader reader = new FileReader(accounts);
        Scanner scanner = new Scanner(reader);
        String[] validator;
        //TO-DO: create md5 of password + salt to compare to
        while (scanner.hasNext()){
            validator = scanner.nextLine().split(" ");
            if(uname.compareTo(validator[0]) == 0 && 
               pass.compareTo(validator[1]) == 0){
                return true;
            }
        }
        return false;
    }
    
    public void create(String uname, String passwd){
        this.uname = uname;
        this.pass = passwd;
        
        //TO-DO: create md5 hash + salt to passwd
        try
        {
            String filename= "accounts.txt";
            FileWriter fw = new FileWriter(filename,true); //the true will append the new data
            fw.write(uname + " " + passwd + "\n");//appends the string to the file
            fw.close();
        }
        catch(IOException ioe)
        {
            System.err.println("IOException: " + ioe.getMessage());
        }
    }
}
