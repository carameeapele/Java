

class ZonaTampon
{
	private int valoare;
	private boolean disponibil = false;
	
	public synchronized int aPreluat()
	{
		if(!disponibil)
		{
			try
			{
				wait();
			}
			catch (InterruptedException e) {}
		}
		
		disponibil = false;
		notify();
		
		System.out.println("- a fost extrasa valoarea " + valoare + " din resursa");
		return valoare;
	}
	
	public synchronized void aTrimis(int valoare)
	{
		if(disponibil)
		{
			try
			{
				wait();
			}
			catch(InterruptedException e) {}
		}
		
		this.valoare = valoare;
		disponibil = true;
		
		System.out.println("+ a fost depusa valoarea " + valoare + " in resursa");
		notify();
	}
}

class Producator extends Thread
{
	private ZonaTampon z;
	
	public Producator(ZonaTampon z)
	{
		this.z = z;
	}
	
	public void run()
	{
		for(int i = 0; i < 10; i++)
		{
			z.aTrimis(i);
			
			try
			{
				sleep((int)(Math.random() * 1000));
			}
			catch(InterruptedException e) {}
		}
	}
}

class Consumator extends Thread
{
	private ZonaTampon z;
	
	public Consumator(ZonaTampon z)
	{
		this.z = z;
	}
	
	public void run()
	{
		int valoare = 0;
		for(int i = 0; i < 10; i++)
		{
			valoare = z.aPreluat();
		}
	}
}

public class Exemplu1
{
	public static void main(String[] args)
	{
		ZonaTampon r = new ZonaTampon();
		Producator p = new Producator(r);
		Consumator c = new Consumator(r);
		
		p.start();
		c.start();
	}
}
