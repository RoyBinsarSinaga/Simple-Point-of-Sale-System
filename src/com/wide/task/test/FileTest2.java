package com.wide.task.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileTest2 {

	public static void main(String[] args) {
		try {
			FileReader fileReader = new FileReader("customer.txt");
			BufferedReader bufReader = new BufferedReader(fileReader);
			 String line = null;
			 while ((line = bufReader.readLine())!=null) {
				 String[] tokens = line.split(";");
				 System.out.println(tokens[1].trim() + " "+tokens[2].trim());
				 System.out.println(tokens[3].trim());
			 }
			 
			 System.out.println("jalan");
		} catch (FileNotFoundException e) {
			System.out.println("File tidak ketemu");
		} catch (IOException e) {
			System.out.println("File Corrupted");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
