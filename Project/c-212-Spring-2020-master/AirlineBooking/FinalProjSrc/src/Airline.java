import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
/*
 * Group 54 Final Project
 * Class that contains the functions that belong to the Airline administrators
 */

public class Airline {
	private String username;
	private String password;
	private String airline;
	private ArrayList<String> blacklist= new ArrayList<String>();
	private ArrayList<String> otherBL= new ArrayList<String>();
	
	private File file= new File("projects/flightData.txt");
	private File blacklistFile = new File("projects/blacklist.txt");
	private PrintWriter pw=new PrintWriter(new FileWriter(file,true));

	//I'm thinking we might need some sort of data file to store the blacklist, that way it persists even after the program closes
	
	public Airline(String u, String p, String airline) throws IOException {
		username=u;
		password=p;
		this.airline=airline;
		//The pw object will need to be written like this so it appends to the data file and does not rewrite it every time
		
		
		//something here that populates the blacklist from a data file
		Scanner sc1= new Scanner(new File("projects/blacklist.txt"));
		while( sc1.hasNextLine()) {
			String line= sc1.nextLine();
			//Separate by commas, Airline should be index [0]
			String[] linesplit= line.split(" ");
			if(linesplit[0].equals(this.airline)) {
				for(String username: linesplit) {
					blacklist.add(username);
				}
			}else {
				otherBL.add(line);
			}
		}
		
		
		
		
	}
	
	//Add a flight and its data to the FlightData file
	public void addFlight() throws FileNotFoundException {
		System.out.println("Enter flightnumber");
		Scanner sc2= new Scanner(System.in);
		String fn= sc2.next();
		Scanner myReader= new Scanner(file);
		while(myReader.hasNextLine()) {
			String line= myReader.nextLine();
			String[] linesplit= line.split(" ");
			if(Integer.parseInt(linesplit[0])== Integer.parseInt(fn)) {
				System.out.println("This flightnumber already belongs to a flight, please try again");
				addFlight();
			}
		}
		
		System.out.println("To: ");
		String to= sc2.next();
		System.out.println("From: ");
		String from= sc2.next();
		
		//need some sort of converter here to change the string into a Date object
		System.out.println("Enter departure date Format: hour:min-month-day-year");
		String datedep=sc2.next();
		
		System.out.println("Enter arrival date. Format: hour:min-month-day-year");
		String datearr=sc2.next();
		System.out.println("Enter number of seats on the flight");
		String seats= sc2.next();
		System.out.println("Enter miles from origin to destination");
		String miles= sc2.next();
		System.out.println("Enter long/short trip");
		String lOrS= sc2.next();
		System.out.println("Enter starting price:");
		String price= sc2.next();
		Flight flight= new Flight(Integer.parseInt(fn),this.airline,datedep,datearr,to,from,Integer.parseInt(seats),Integer.parseInt(seats),lOrS,Integer.parseInt(miles),Integer.parseInt(price));
		//after creating the flight object, this adds it to the file 
		pw.println(flight.toString());
		pw.flush();
		pw.close();
	}
	//Remove a flight and its data to the FlightData file
	public void cancelFlight() throws FileNotFoundException {
		ArrayList<String> list= new ArrayList<>();
		System.out.println("Enter flightnumber");
		Scanner sc3= new Scanner(System.in);
		String fn= sc3.next();
		Scanner myReader= new Scanner(file);
		while(myReader.hasNextLine()) {
			String line= myReader.nextLine().trim();
			String[] linesplit= line.split(" ");
			if(Integer.parseInt(linesplit[0])== Integer.parseInt(fn)) {
				if(!linesplit[1].equals(this.airline)) {
					System.out.println("This flight number belongs to a different airline, you cannot cancel it, try again");
					cancelFlight();
				}
			}else {
				list.add(line);
			}
		}
		
		myReader.close();
		PrintWriter pw2= new PrintWriter(file);
		for(String s: list) {
			pw2.println(s);
		}
		pw2.close();
		
		Scanner scanner1= new Scanner(System.in);
		System.out.println("How many miles would you like to gift your affected customers?");
		int gMiles= scanner1.nextInt();
		Scanner us= new Scanner(new File("projects/usersFlights.txt"));
		ArrayList<String> customers= new ArrayList<>();
		while(us.hasNextLine()) {
			String mline= us.nextLine();
			String[] msplit= mline.split(",");
			for(String s: msplit) {
				if(s.contains(fn)) {
					customers.add(msplit[0]);
					break;
				}
			}
		}
		ArrayList<String> temp= new ArrayList<>();
		String tempString="";
		Scanner thisReader= new Scanner(new File("projects/userMiles.txt"));
		for(String user: customers) {
			while(thisReader.hasNextLine()) {
				String line= thisReader.nextLine();
				String[] linesplit= line.split(" ");
				if(linesplit[0].equals(user)) {
					linesplit[1]=""+ (Integer.parseInt(linesplit[1])+gMiles);
					for(String el: linesplit) {
						tempString+=" "+el;
					}
				}else {
					temp.add(line);
				}
			}
			PrintWriter pdw= new PrintWriter(new File("projects/userMiles.txt"));
			for(String i: temp) {
				pdw.println(i);
			}
			pdw.println(tempString);
			pdw.flush();
			pdw.close();
		}
		System.out.println("Flight cancelled, customers awarded miles");
		
		thisReader.close();
		
		
		
		
		
	}
	//Edit an existing flight in the FlightData file, rewrite it
	public void updateFlight() throws FileNotFoundException {
		ArrayList<String> list= new ArrayList<>();
		//scan the file for the flight, cancel the old one using cancelFlight() , add new one using addFlight()
		Scanner sc4= new Scanner(System.in);
		System.out.println("What is the flightnumber?");
		int flightnumber= sc4.nextInt();
		System.out.println("What is the new departure time? Format: hour:min-month-day-year");
		String newDep= sc4.next();
		System.out.println("What is the new arrival time? Format: hour:min-month-day-year");
		String newArr=sc4.next();
		System.out.println("To: ");
		String to= sc4.next();
		System.out.println("From: ");
		String from = sc4.next();
		System.out.println("How many miles will this trip be?");
		String miles= sc4.next();
		System.out.println("Is this a long or short trip?");
		String lOrS= sc4.next();
		System.out.println("What is the starting price of the ticket?");
		String price= sc4.next();
		int numSeats=0;
		int numSeatsLeft=0;
		
		Scanner reader= new Scanner(file);
		while(reader.hasNextLine()) {
			String line= reader.nextLine();
			String[] flightarr= line.split(" ");
			if(flightarr.length>0) {
				if(Integer.parseInt(flightarr[0])==flightnumber) {
					if(!flightarr[1].equals(this.airline)) {
						System.out.println("This flight belongs to a different airline, you cannot update it");
						updateFlight();
					}
					numSeats= Integer.parseInt(flightarr[flightarr.length-2]);
					numSeatsLeft= Integer.parseInt(flightarr[flightarr.length-1]);
					
					
				}else {
				//write all other lines to new file
				list.add(line);
			}
			}
		}
		
		Flight updatedFlight= new Flight(flightnumber,this.airline,newDep,newArr,to,from,numSeats,numSeatsLeft,lOrS,Integer.parseInt(miles),Integer.parseInt(price));
		list.add(updatedFlight.toString());
		
		reader.close();
		PrintWriter pw2= new PrintWriter(file);
		for(String s: list) {
			pw2.println(s);
		}
		pw2.close();
		
		
		
	}
	
	
	//Gift a user miles
	public void addMiles() throws FileNotFoundException {
		Scanner scanner1= new Scanner(System.in);
		System.out.println("Enter the username to whom you're gifting the miles:");
		String user= scanner1.next();
		System.out.println("How many miles?");
		int miles= scanner1.nextInt();
		ArrayList<String> temp= new ArrayList<>();
		String tempString="";
		Scanner thisReader= new Scanner(new File("projects/userMiles.txt"));
		while(thisReader.hasNextLine()) {
			String line= thisReader.nextLine();
			String[] linesplit= line.split(" ");
			if(linesplit[0].equals(user)) {
				linesplit[1]=""+ (Integer.parseInt(linesplit[1])+miles);
				for(String el: linesplit) {
					tempString+=" "+el;
				}
			}else {
				temp.add(line);
			}
		}
		PrintWriter pdw= new PrintWriter(new File("projects/userMiles.txt"));
		for(String i: temp) {
			pdw.println(i);
		}
		System.out.println("Done");
		pdw.println(tempString.substring(1));
		pdw.flush();
		pdw.close();
		thisReader.close();
		
	}
	public ArrayList<String> getBlacklist() {
		return this.blacklist;
	}
	//Add users to a list that blocks them from booking a particular airline.
	public void blacklist() throws FileNotFoundException {
		Scanner sc9= new Scanner(System.in);
		System.out.println("what is the username of the person you would like to blacklist?");
		String uname= sc9.next();
		blacklist.add(uname);
		PrintWriter out= new PrintWriter(blacklistFile);
		for(String line: otherBL) {
			out.println(line);
		}
		String bl="";
		for(String el: blacklist) {
			bl+=el+" ";
		}
		out.println(bl);
		out.flush();
		out.close();
		//write to data file that the new blacklist, delete the old one maybe?
		
	}
	public void viewRatings() throws FileNotFoundException {
		Scanner scan= new Scanner(new File("projects/ratings.txt"));
		ArrayList<String> comments= new ArrayList<>();
		while(scan.hasNextLine()) {
			String line= scan.nextLine();
			String[] linesplit= line.split(",");
			if(linesplit[0].equals(this.airline)) {
				for(String s: linesplit) {
					comments.add(s);
				}
			}
		}
		scan.close();
		System.out.println("Here are the comments for your airline");
		for(String s: comments) {
			System.out.println(s);
		}
	}
}
