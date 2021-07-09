package Tema11;


class Parcare extends Thread
{
	private int totalLocuri;
	private int locuriOcupate;
	private boolean disponibil;
	
	public Parcare()
	{
		this.totalLocuri = 0;
		this.locuriOcupate = 0;
	}
	
	public void setTotalLocuri(int totalLocuri) { this.totalLocuri = totalLocuri; }
	public void setLocuriOcupate(int locuriOcupate) { this.locuriOcupate = locuriOcupate; }
	
	public int getTotalLocuri() { return this.totalLocuri; }
	public int getLocuriOcupate() { return this.locuriOcupate; }
	
	// preluat
	public synchronized void iesire()
	{
		if(locuriOcupate < totalLocuri)
		{
			try { wait(); }
			catch(InterruptedException e) {}
		}
		
		locuriOcupate--;
		
		System.out.println("- a iesit o masina; in parcare sunt " + this.locuriOcupate + " masini");
		notify();
	}
	
	// trimis
	public synchronized void intrare()
	{
		while(locuriOcupate == totalLocuri)
		{
			try { wait(); }
			catch(InterruptedException e) {}
		}
		
		notify();
		locuriOcupate++;
		
		Thread thread = Thread.currentThread();
		String nume = thread.getName();
		
		System.out.println("+ a intrat o masina pe intrarea " + nume.charAt(nume.length()-1) + "; in parcare sunt " + locuriOcupate + " masini");
	}
}

// producatorul
class Intrare extends Thread
{
	private Parcare parcare;
	
	public Intrare(Parcare parcare) { this.parcare = parcare; }
	
	public void run()
	{
		for(int i = 0; i < 10; i++)
		{
			parcare.intrare();
			
			try { sleep((int)(Math.random() * 1000)); }
			catch(InterruptedException e) {}
		}
	}
	
}

// consumatorul
class Iesire extends Thread
{
	private Parcare parcare;
	
	public Iesire(Parcare parcare) { this.parcare = parcare; }
	
	public void run()
	{
		for(int i = 0; i < 10; i++)
		{
			parcare.iesire();
			
			try { sleep((int)(Math.random() * 1000)); }
			catch(InterruptedException e) {}
		}
	}
}

public class Exercitiul2
{
	public static void main(String[] args)
	{
		Parcare parcare = new Parcare();
		parcare.setLocuriOcupate(0);
		parcare.setTotalLocuri(10);
		
		Intrare poarta1 = new Intrare(parcare);
		Intrare poarta2 = new Intrare(parcare);
		Intrare poarta3 = new Intrare(parcare);
		Iesire iesire = new Iesire(parcare);
		
		poarta1.start();
		poarta2.start();
		poarta3.start();
		iesire.start();
	}

}
