package Tema5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

interface Operatiuni
{
	public float calculeazaDobanda();

	public float actualizareSuma();
	
	public void depunere(float suma);
	
	public void extragere(float suma);
}

class ContBancar extends Client implements Operatiuni
{
	private String numarCont;
	private float suma;
	private String moneda;
	private Calendar dataDeschiderii;
	private Calendar dataUltimeiOperatiuni;
	
	public ContBancar(Banca banca, Client client, 
			String numarCont, float suma, String moneda, Calendar dataDeschiderii, Calendar dataUltimeiOperatiuni)
	{
		super(banca, client);
		
		this.numarCont = numarCont;
		this.suma = suma;
		this.moneda = moneda;
		this.dataDeschiderii = dataDeschiderii;
		this.dataUltimeiOperatiuni = dataUltimeiOperatiuni;
	}
	
	public ContBancar(Banca banca, Client client, ContBancar cont)
	{
		super(banca, client);
		
		this.numarCont = cont.numarCont;
		this.suma = cont.suma;
		this.moneda = cont.moneda;
		this.dataDeschiderii = cont.dataDeschiderii;
		this.dataUltimeiOperatiuni = cont.dataUltimeiOperatiuni;
	}
	
	public String getNumarCont() { return this.numarCont; }
	public float getSuma() { return this.suma; };
	public String getMoneda() { return this.moneda; }
	public Calendar getDataDeschiderii() { return this.dataDeschiderii; }
	public Calendar getDataUltimeiOperatiuni() { return this.dataUltimeiOperatiuni; }
	
	public String toString()
	{
		return this.numarCont + " " + this.suma + " " + this.moneda + " " 
	+ this.dataDeschiderii.get(Calendar.DAY_OF_MONTH) + "." + this.dataDeschiderii.get(Calendar.MONTH) + "." + this.dataDeschiderii.get(Calendar.YEAR) + " " 
				+ this.dataUltimeiOperatiuni.get(Calendar.DAY_OF_MONTH) + "." + this.dataUltimeiOperatiuni.get(Calendar.MONTH) + "." + this.dataUltimeiOperatiuni.get(Calendar.YEAR);
	}

	@Override
	public float calculeazaDobanda()
	{
		Calendar current = Calendar.getInstance();
		current.set(current.get(Calendar.YEAR), (current.get(Calendar.MONTH)+1), current.get(Calendar.DAY_OF_MONTH));
		long c = current.getTimeInMillis();
		long d = this.dataUltimeiOperatiuni.getTimeInMillis();
		
		long days = ((c-d) / (1000*60*60*24));
		//long days = TimeUnit.MILLISECONDS.toDays((c-d));
		
		float dobanda = 0;
		
		if(this.moneda.equals("RON"))
		{
			if(this.suma < 500)
				dobanda = (float) (days * 0.3);
			else
				dobanda = (float) (days * 0.8);
		}
		else if(this.moneda.equals("EUR"))
			dobanda = (float) (days * 0.1);
		
		return dobanda;
	}

	@Override
	public float actualizareSuma()
	{
		float dobanda = calculeazaDobanda();
		this.suma = dobanda + this.suma;
		dataUltimeiOperatiuni = Calendar.getInstance();
		dataUltimeiOperatiuni.set(dataUltimeiOperatiuni.get(Calendar.YEAR), (dataUltimeiOperatiuni.get(Calendar.MONTH)+1), dataUltimeiOperatiuni.get(Calendar.DAY_OF_MONTH));
		
		return suma;
	}

	@Override
	public void depunere(float suma)
	{
		this.suma = actualizareSuma();
		dataUltimeiOperatiuni = Calendar.getInstance();
		dataUltimeiOperatiuni.set(dataUltimeiOperatiuni.get(Calendar.YEAR), (dataUltimeiOperatiuni.get(Calendar.MONTH)+1), dataUltimeiOperatiuni.get(Calendar.DAY_OF_MONTH));
		
		this.suma = this.suma + suma;
	}

	@Override
	public void extragere(float suma)
	{
		this.suma = actualizareSuma();
		dataUltimeiOperatiuni = Calendar.getInstance();
		dataUltimeiOperatiuni.set(dataUltimeiOperatiuni.get(Calendar.YEAR), (dataUltimeiOperatiuni.get(Calendar.MONTH)+1), dataUltimeiOperatiuni.get(Calendar.DAY_OF_MONTH));
		
		this.suma = this.suma - suma;
	}
	
	/*@Override
	public boolean equals(Object obj)
	{
		return numarCont.equals(((ContBancar) obj).numarCont);
	}*/
}

class Client extends Banca
{
	private String nume;
	private String adresa;
	private Set<ContBancar> conturi;
	
	public Client(Banca banca, String nume, String adresa)
	{
		super(banca);
		
		this.nume = nume;
		this.adresa = adresa;
		this.conturi = new HashSet<ContBancar>();
	}
	
	public Client(Banca banca, Client client)
	{
		super(banca);
		
		this.nume = client.nume;
		this.adresa = client.adresa;
		this.conturi = client.conturi;
	}
	
	public void addCont(ContBancar cont)
	{
		if(conturi.size() <= 5)
			this.conturi.add(cont);
		else
			System.out.println("Clientul are deja 5 conturi");
	}
	
	public ContBancar cautaCont(String numarCont)
	{
		for(Iterator<ContBancar> i = conturi.iterator(); i.hasNext();)
		{
			ContBancar cont = i.next();
			if(cont.getNumarCont().equals(numarCont))
				return cont;
		}
		
		return null;
	}
	
	public String getNume() { return this.nume; }
	public String getAdresa() { return this.adresa; }
	
	public String toString()
	{
		return "\n\t" + this.nume + " " + this.adresa + " " + this.conturi.toString();
	}
}

class Banca extends ClientiiBancilor
{
	private String denumireBanca;
	private List<Client> clienti;
	
	public Banca(String denumireBanca)
	{
		this.denumireBanca = denumireBanca;
		this.clienti = new ArrayList<Client>();
	}
	
	public Banca(Banca banca)
	{
		this.denumireBanca = banca.denumireBanca;
		this.clienti = new ArrayList<Client>();
	}
	
	public void addClient(Client client)
	{
		this.clienti.add(client);
	}
	
	public String getDenumireBanca() { return this.denumireBanca; }
	
	public Client cautaClient(String nume)
	{
		for(int i = 0; i < clienti.size(); i++)
			if(clienti.get(i).getNume().equals(nume))
				return clienti.get(i);
		
		return null;
	}
	
	public String toString()
	{
		return this.denumireBanca + "\n" + this.clienti.toString() + "\n";
	}
}

class ClientiiBancilor
{
	private Vector<Banca> banci = new Vector<Banca>();
	
	public void adaugareDinFisier(String file) throws NumberFormatException, IOException
	{
		File fileIn = new File(file);
		BufferedReader buffer = new BufferedReader(new FileReader(fileIn));
		
		String linie;
		
		while((linie = buffer.readLine()) != null)
		{
			String s[] = linie.split(";");
			
			Banca banca = new Banca(s[0]);
			Client client = new Client(banca, s[1], s[2]);
			
			String c[] = s[6].split("-");
			int day = Integer.parseInt(c[0]);
			int month = Integer.parseInt(c[1]);
			int year = Integer.parseInt(c[2]);
			
			Calendar dataDeschiderii = Calendar.getInstance();
			dataDeschiderii.set(year, month, day);
			
			c = s[7].split("-");
			day = Integer.parseInt(c[0]);
			month = Integer.parseInt(c[1]);
			year = Integer.parseInt(c[2]);
			
			Calendar dataUltimeiOperatiuni = Calendar.getInstance();
			dataUltimeiOperatiuni.set(year, month, day);
			
			ContBancar cont = new ContBancar(banca, client, s[3], Float.parseFloat(s[4]), s[5], dataDeschiderii, dataUltimeiOperatiuni);
			
			if(banci.size() < 1)
			{
				client.addCont(cont);
				banca.addClient(client);
				banci.add(banca);
			}
			else
			{
				for(int i = 0; i < banci.size(); i++)
				{
					if(banci.elementAt(i).getDenumireBanca().equals(banca.getDenumireBanca()))
					{
						if(banci.elementAt(i).cautaClient(client.getNume()) != null)
							banci.elementAt(i).cautaClient(client.getNume()).addCont(cont);
						else
						{
							client.addCont(cont);
							banci.elementAt(i).addClient(client);
						}
					}
					else
					{
						client.addCont(cont);
						banca.addClient(client);
						banci.add(banca);
					}
				}
			}
		}
		buffer.close();
	}
	
	public void afisare()
	{
		for(int i = 0; i < banci.size(); i++)
			System.out.println(banci.elementAt(i).toString());
	}
	
	public void depunereSuma(String nume, String numarCont, float suma)
	{
		Client client;
		ContBancar cont;
		int gasit = 0;
		
		for(int i = 0; i < banci.size(); i++)
		{
			if((client = banci.elementAt(i).cautaClient(nume)) != null)
				if((cont = client.cautaCont(numarCont)) != null)
				{
					cont.depunere(suma);
					gasit = 1;
				}
		}
		
		if(gasit == 0)
			System.out.println("Contul nu exista");
	}
	
	public void extragereSuma(String nume, String numarCont, float suma)
	{
		Client client;
		ContBancar cont;
		int gasit = 0;
		
		for(int i = 0; i < banci.size(); i++)
		{
			if((client = banci.elementAt(i).cautaClient(nume)) != null)
				if((cont = client.cautaCont(numarCont)) != null)
				{
					cont.extragere(suma);
					gasit = 1;
				}
		}
		
		if(gasit == 0)
			System.out.println("Contul nu exista");
	}
	
	public void transferSuma(String nume1, String numarCont1, String nume2, String numarCont2, float suma)
	{
		Client client;
		ContBancar cont;
		
		int gasit = 0;
		for(int i = 0; i < banci.size(); i++)
		{
			if((client = banci.elementAt(i).cautaClient(nume1)) != null)
				if((cont = client.cautaCont(numarCont1)) != null)
				{
					cont.extragere(suma);
					gasit = 1;
				}
		}
		
		if(gasit == 0)
		{
			System.out.println("Contul 1 nu exista");
			return;
		}
		
		gasit = 0;
		for(int i = 0; i < banci.size(); i++)
		{
			if((client = banci.elementAt(i).cautaClient(nume2)) != null)
				if((cont = client.cautaCont(numarCont2)) != null)
				{
					cont.depunere(suma);
					gasit = 1;
				}
		}
		
		if(gasit == 0)
			System.out.println("Contul 2 nu exista");
	}
}

public class Exercitiul1
{
	static void meniu()
	{
		System.out.println("0. Iesire");
		System.out.println("1. Adaugare banci, clienti si conturi din fisier");
		System.out.println("2. Afisare date");
		System.out.println("3. Depunerea unei sume intr-un cont");
		System.out.println("4. Extragerea unei sume sintr-un cont");
		System.out.println("5. Transfer de bani intre doua conturi");
		
	}
	
	public static void main(String[] args) throws IOException
	{
		Scanner scanner = new Scanner(System.in);
		BufferedReader fluxIn = new BufferedReader(new InputStreamReader(System.in));
		ClientiiBancilor cBancilor = new ClientiiBancilor(); 
		
		String nume, numarCont, nume2, numarCont2;
		float suma;
		
		int opt;
		do
		{
			meniu();
			System.out.println("\nDati optiune: ");
			
			opt = scanner.nextInt();
			
			switch(opt)
			{
			case 0:
				//exit
				break;
			case 1:
				//adding from file
				cBancilor.adaugareDinFisier("fisierBanci.txt");
				System.out.println("Datele au fost citite");
				break;
			case 2:
				//display
				cBancilor.afisare();
				break;
			case 3:
				//deposit
				System.out.println("Dati nume: ");
				nume = fluxIn.readLine();
				System.out.println("Dati numar cont: ");
				numarCont = fluxIn.readLine();
				System.out.println("Dati suma depusa: ");
				suma = scanner.nextFloat();
				
				cBancilor.depunereSuma(nume, numarCont, suma);
				break;
			case 4:
				//draw
				System.out.println("Dati nume: ");
				nume = fluxIn.readLine();
				System.out.println("Dati numar cont: ");
				numarCont = fluxIn.readLine();
				System.out.println("Dati suma extrasa: ");
				suma = scanner.nextFloat();
				
				cBancilor.extragereSuma(nume, numarCont, suma);
				break;
			case 5:
				//transfer
				System.out.println("Dati nume client 1: ");
				nume = fluxIn.readLine();
				System.out.println("Dati numar cont: ");
				numarCont = fluxIn.readLine();
				System.out.println("Dati nume client 2: ");
				nume2 = fluxIn.readLine();
				System.out.println("Dati numar cont: ");
				numarCont2 = fluxIn.readLine();
				System.out.println("Dati suma transferata: ");
				suma = scanner.nextFloat();
				
				cBancilor.transferSuma(nume, numarCont, nume2, numarCont2, suma);
				break;
			default:
				System.out.println("\nOptiune gresita");
				break;
			}
		}while(opt != 0);
		
		scanner.close();
		fluxIn.close();
	}
}
