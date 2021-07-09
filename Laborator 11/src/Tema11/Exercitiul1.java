package Tema11;

import java.util.Random;

class ContBancar extends Thread
{
	private int suma;
	private int valoare;
	
	public ContBancar() { this.suma = 0; }
	public void setSuma(int suma) { this.suma = suma; }
	public void setValoare(int valoare) { this.valoare = valoare; }
	
	public synchronized void extragere()
	{
		Random random = new Random();
		valoare = random.nextInt(1000);
		
		while(this.suma < valoare)
		{
			try { wait(); }
			catch(InterruptedException e) {}	
		}
		
		notify();
		this.suma = this.suma - valoare;
		System.out.println("- a fost retrasa suma " + valoare + " RON; in cont sunt: " + this.suma + " RON");
		
	}
	
	public synchronized void depunere(int valoare)
	{
		this.suma = this.suma + valoare;
		
		System.out.println("+ a fost depusa suma " + valoare + " RON; in cont sunt: " + this.suma + " RON");
		notify();
	}
}

class Extragere extends Thread
{
	private ContBancar cont;
	
	public Extragere(ContBancar cont) { this.cont = cont; }
	
	public void run()
	{		
		for(int i = 0; i < 10; i++)
		{
			cont.extragere();
		}
	}
}

class Depunere extends Thread
{
	private ContBancar cont;
	
	public Depunere(ContBancar cont) { this.cont = cont; }
	
	public void run()
	{
		Random random = new Random();
		
		for(int i = 0; i < 10; i++)
		{
			cont.depunere(random.nextInt(1000));
			
			try { sleep((int)(Math.random() * 1000)); }
			catch(InterruptedException e) {}
		}
	}
}

public class Exercitiul1
{
	public static void main(String[] args)
	{
		ContBancar cont = new ContBancar();
		cont.setSuma(1000);
		
		Depunere depunere = new Depunere(cont);
		Extragere extragere = new Extragere(cont);
		
		depunere.start();
		extragere.start();
	}

}
