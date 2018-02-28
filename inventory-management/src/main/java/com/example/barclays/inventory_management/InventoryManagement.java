package com.example.barclays.inventory_management;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.example.barclays.inventory_management.utilities.CommandParser;
public class InventoryManagement 
{
    public static void main( String[] args ) throws FileNotFoundException
    {
    
    		CommandParser commandParser = new CommandParser();
    		Scanner sc =  new Scanner(new File("D:\\ip.txt"));
    		while(sc.hasNextLine()){
    			String commandLine= sc.nextLine();
    			commandParser.command(commandLine);
    		}
    		sc.close();
    	
    }
}
