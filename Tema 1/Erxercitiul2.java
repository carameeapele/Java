package tema;

import java.io.File;
//import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Erxercitiul2 
{

	public static void main(String[] args) throws IOException 
	{
		int Suma = 0, Minim = 100, Maxim = 0, x, nr = 0;
		float Media;
		
		File fileIn = new File("in1.txt");
		File fileOut = new File("out.txt");
		
		Scanner scanner = new Scanner(fileIn);
		FileWriter fileWriter = new FileWriter(fileOut);
		
		while(scanner.hasNext())
		{
			x = scanner.nextInt();
			nr++;
			Suma = Suma + x;
			
			if(Minim > x)
			{
				Minim = x;
			}
			
			if(Maxim < x)
			{
				Maxim = x;
			}
		}
		
		Media = Suma/nr;
		
		System.out.println("Suma: "+Suma);
		System.out.println("Media aritmetica: "+Media);
		System.out.println("Minim: "+Minim);
		System.out.println("Maxim "+Maxim);
		
		fileWriter.write("Suma: "+Suma);
		fileWriter.write("\nMedia: "+Media);
		fileWriter.write("\nMinim: "+Minim);
		fileWriter.write("\nMaxim: "+Maxim);
		
		scanner.close();
		fileWriter.close();
	}
}
