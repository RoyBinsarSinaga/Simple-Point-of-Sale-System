package come.wide.task2.test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileTest {

	public static void main(String[] args) {
		try {
			FileReader fileReader = new FileReader("customer.txt");
			
			int c = 0;
			while((c = fileReader.read())!= -1) {
				System.out.print((char) c);
			}
			
			System.out.println("jalan");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("File Corrupted");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
