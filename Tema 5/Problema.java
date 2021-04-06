package Tema5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Game
{
	private List<String> Options;
	private int score;
	private String player1;
	private String player2;
	
	public Game()
	{
		this.Options = new ArrayList<String>();
		Options.add("rock");
		Options.add("paper");
		Options.add("scrissors");
		this.player1 = "";
		this.player2 = "";
		this.score = 0;
	}
	
	public void addOption(String option)
	{
		Options.add(option);
	}
	
	public void setPlayer1(String option)
	{
		//verify if the given option is in the List
		player1 = option.toLowerCase();
	}
	
	public void setPlayer2(String option)
	{
		player2 = option.toLowerCase();
	}
}

public class Problema
{
	public static void main(String[] args) throws IOException
	{
		Game game = new Game();
		
		BufferedReader fluxIn = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("1st Player move: ");
		game.setPlayer1(fluxIn.readLine());
		
		System.out.println("2nd Player move: ");
		game.setPlayer2(fluxIn.readLine());
		
		fluxIn.close();
	}

}
