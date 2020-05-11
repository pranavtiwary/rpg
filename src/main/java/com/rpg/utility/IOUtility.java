package com.rpg.utility;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * IO utility
 * 
 * @author pranav.tiwary@vuclip.com
 *
 */
public class IOUtility{

	private static final PrintStream outputWriter = new PrintStream(System.out);
	private static final Scanner inputReader =  new Scanner(System.in);


	public static void writeOnConsole(String msg){
		try {
			outputWriter.write(msg.getBytes());
		} catch (IOException e) {
			throw new RuntimeException("Error in i/o");
		}
	}

	public static String readLine(){
		return inputReader.nextLine();
	}
	
	public static int readInteger(){
		return inputReader.nextInt();
	}
}
