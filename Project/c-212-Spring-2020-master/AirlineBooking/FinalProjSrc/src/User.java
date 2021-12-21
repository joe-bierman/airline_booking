import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Set;
/*
 * Group 54 Final Project
 * Class that houses functions available to 'everyday' users
 */
public class User {
	private int mileage;
	private String username;
	private String password;
	private ArrayList<String> flightHistory= new ArrayList<>();
	private ArrayList<String> otherFlights= new ArrayList<>();
	private ArrayList<String> myMiles= new ArrayList<>();
	private ArrayList<String> otherMiles= new ArrayList<>();
	private File flightData= new File("projects/flightData.txt");
	private File userFlights= new File("projects/usersFlights.txt");
	private File userMiles= new File("projects/userMiles.txt");
	private boolean blacklist= false;
	
	
	
	
	public User(String u, String p) throws FileNotFoundException {
		username=u;
		password=p;
		Scanner sc1= new Scanner(new File("projects/usersFlights.txt"));
		flightHistory=new ArrayList<>();
		while( sc1.hasNextLine()) {
			String line= sc1.nextLine().trim();
			String[] linesplit= line.split(",");
			if(linesplit[0].equals(this.username)) {
				for(String flights: linesplit) {
					flightHistory.add(flights);
				}
			}else {

				otherFlights.add(line);
			}
		}
		if(flightHistory.size()==0) {
			flightHistory.add(this.username);
		}
		Scanner sc2= new Scanner( new File("projects/userMiles.txt"));
		while( sc2.hasNextLine()) {
			String line= sc2.nextLine().trim();
			String[] linesplit= line.split(" ");
			if(linesplit[0].equals(this.username)) {
				for(String miles: linesplit) {
					myMiles.add(miles);
				}
			}else {
				otherMiles.add(line);
			}
		
		}
	}
	
	// need to add sorting at the end of each case
	
	public void search() throws FileNotFoundException {
		Scanner reader= new Scanner(new File("projects/flightData.txt"));
		Scanner sc= new Scanner(System.in);
		System.out.println("What would you like to search by?");
		System.out.println("A: Flight Number");
		System.out.println("B: Destination");
		System.out.println("C: Airline");
		String option= sc.next();
		switch(option) {
		case "A":
			ArrayList<String[]> searchList= new ArrayList<>();
			ArrayList<Integer> searchIndex= new ArrayList<>();
			System.out.println("What is the flightnumber you are searching for?");
			String choice1= sc.next();
			ArrayList<String> list= new ArrayList<>();
			while(reader.hasNextLine()) {
				String line= reader.nextLine();
				String[] linesplit= line.split(" ");
				if(linesplit[0].equals(choice1)) {
					if(Integer.parseInt(linesplit[7])>0) {
						list.add(line);
					}
				}
			}
			
			
			
			System.out.println("How would you like to sort your results? ");
			System.out.println("A: FlightNumber");
			System.out.println("B: Cost");
			String mchoice1= sc.next();
			for(String l: list) {
				searchList.add(l.split(" "));
			}
			if(mchoice1.equals("A")) {
				System.out.println("Your search results are: ");
				for(int i=0;i<searchList.size();i++) {
					searchIndex.add(Integer.parseInt(searchList.get(i)[0]));
				}
				Collections.sort(searchIndex);
				for(int i=0;i<searchIndex.size();i++) {
					for(int j=0;j<searchList.size();j++) {
						if(Integer.parseInt(searchList.get(j)[0]) == searchIndex.get(i)){
							System.out.println(list.get(j));
						}
						
					}
				}
			}else {
				ArrayList<Integer> fn= new ArrayList<>();
				System.out.println("Your search results are: ");
				for(int i=0;i<searchList.size();i++) {
					searchIndex.add(Integer.parseInt(searchList.get(i)[10]));
				}
				Collections.sort(searchIndex);
				for(int i=0;i<searchIndex.size();i++) {
					for(int j=0;j<searchList.size();j++) {
						if((Integer.parseInt(searchList.get(j)[10]) == searchIndex.get(i))&&!fn.contains(Integer.parseInt(searchList.get(j)[0]))){
							System.out.println(list.get(j));
							fn.add(Integer.parseInt(searchList.get(j)[0]));
						}
						
					}
				}
			}
			break;
		case "B":
			ArrayList<String[]> searchList2= new ArrayList<>();
			ArrayList<Integer> searchIndex2= new ArrayList<>();
			System.out.println("What is the destination of the flight?");
			String choice2= sc.next();
			ArrayList<String> list2= new ArrayList<>();
			while(reader.hasNextLine()) {
				String line= reader.nextLine();
				String[] linesplit= line.split(" ");
				if(linesplit[4].equals(choice2)) {
					if(Integer.parseInt(linesplit[7])>0) {
						list2.add(line);
					}
				}
			}
			System.out.println("How would you like to sort your results? ");
			System.out.println("A: FlightNumber");
			System.out.println("B: Cost");
			String mchoice2= sc.next();
			for(String l: list2) {
				searchList2.add(l.split(" "));
			}
			if(mchoice2.equals("A")) {
				System.out.println("Your search results are: ");
				for(int i=0;i<searchList2.size();i++) {
					searchIndex2.add(Integer.parseInt(searchList2.get(i)[0]));
				}
				Collections.sort(searchIndex2);
				for(int i=0;i<searchIndex2.size();i++) {
					for(int j=0;j<searchList2.size();j++) {
						if(Integer.parseInt(searchList2.get(j)[0]) == searchIndex2.get(i)){
							System.out.println(list2.get(j));
						}
						
					}
				}
			}else {
				ArrayList<Integer> fn2= new ArrayList<>();
				System.out.println("Your search results are: ");
				for(int i=0;i<searchList2.size();i++) {
					searchIndex2.add(Integer.parseInt(searchList2.get(i)[10]));
				}
				Collections.sort(searchIndex2);
				for(int i=0;i<searchIndex2.size();i++) {
					for(int j=0;j<searchList2.size();j++) {
						if((Integer.parseInt(searchList2.get(j)[10]) == searchIndex2.get(i))&&!fn2.contains(Integer.parseInt(searchList2.get(j)[0]))){
							System.out.println(list2.get(j));
							fn2.add(Integer.parseInt(searchList2.get(j)[0]));
						}
						
					}
				}
			}
			break;
			
		case "C":
			ArrayList<String[]> searchList3= new ArrayList<>();
			ArrayList<Integer> searchIndex3= new ArrayList<>();
			System.out.println("What is the airline you would like to travel on?");
			String choice3= sc.next();
			ArrayList<String> list3= new ArrayList<>();
			while(reader.hasNextLine()) {
				String line= reader.nextLine();
				String[] linesplit= line.split(" ");
				if(linesplit[1].equals(choice3)) {
					if(Integer.parseInt(linesplit[7])>0) {
						list3.add(line);
					}
				}
			}
			System.out.println("How would you like to sort your results? ");
			System.out.println("A: FlightNumber");
			System.out.println("B: Cost");
			String mchoice3= sc.next();
			for(String l: list3) {
				searchList3.add(l.split(" "));
			}
			if(mchoice3.equals("A")) {
				System.out.println("Your search results are: ");
				for(int i=0;i<searchList3.size();i++) {
					searchIndex3.add(Integer.parseInt(searchList3.get(i)[0]));
				}
				Collections.sort(searchIndex3);
				for(int i=0;i<searchIndex3.size();i++) {
					for(int j=0;j<searchList3.size();j++) {
						if(Integer.parseInt(searchList3.get(j)[0]) == searchIndex3.get(i)){
							System.out.println(list3.get(j));
						}
						
					}
				}
			}else {
				ArrayList<Integer> fn3= new ArrayList<>();
				System.out.println("Your search results are: ");
				for(int i=0;i<searchList3.size();i++) {
					searchIndex3.add(Integer.parseInt(searchList3.get(i)[10]));
				}
				Collections.sort(searchIndex3);
				for(int i=0;i<searchIndex3.size();i++) {
					for(int j=0;j<searchList3.size();j++) {
						if((Integer.parseInt(searchList3.get(j)[10]) == searchIndex3.get(i))&&!fn3.contains(Integer.parseInt(searchList3.get(j)[0]))){
							System.out.println(list3.get(j));
							fn3.add(Integer.parseInt(searchList3.get(j)[0]));
						}
						
					}
				}
			}
			break;
		}
			

		reader.close();
	}
	

	//allow person logged in to book a flight
	//this does allow you to book the same flight mulitple times, which will account for multiple tickets being bought.
	public void book() throws IOException {
		Scanner reader2= new Scanner(flightData);
		Scanner myOtherReader= new Scanner(new File("projects/blacklist.txt"));
		System.out.println("What is the flight number of the flight you wish to book?");
		ArrayList<String> list= new ArrayList<>();
		String newline= "";
		Scanner sc= new Scanner(System.in);
		String fn= sc.next();
		while(reader2.hasNextLine()) {
			String line= reader2.nextLine();
			String[] linesplit= line.split(" ");
			if(Integer.parseInt(linesplit[0])==Integer.parseInt(fn)) {
				while(myOtherReader.hasNextLine()) {
					String line2= myOtherReader.nextLine();
					String[] linesplit2= line2.split(" ");
					if(linesplit[1].equals(linesplit2[0])) {
						if(line2.contains(this.username)) {
							System.out.println("You cannot book this flight, you are blacklisted");
							this.blacklist=true;
							break;
						}
					}
				}
				
					if(Integer.parseInt(linesplit[7])>0) {
						if(this.blacklist==false) {
							linesplit[7]= "" + (Integer.parseInt(linesplit[7])-1);
							if(linesplit[8].equals("long")) {
								System.out.println("This is long flight, what is your meal preference (chicken or fish)?");
								Scanner sc12= new Scanner(System.in);
								String whoCares= sc12.next();
							}
							pay(Integer.parseInt(linesplit[9]),Integer.parseInt(linesplit[10]));
								if(myMiles.size()==0) {
									myMiles.add(this.username);
									myMiles.add(""+linesplit[9]);
								}else {
									String miles= myMiles.get(1);
									String newMiles= ""+ (Integer.parseInt(miles)+ Integer.parseInt(linesplit[9]));
									myMiles.set(1, newMiles);
								}
								PrintWriter pow= new PrintWriter(userMiles);
								for(String l: otherMiles) {
									pow.println(l);
								}
								String fin="";
								for(String i: myMiles) {
									fin+=" "+i;
								}
								pow.println(fin.substring(1));
								pow.flush();
								pow.close();
								
								
								
							
						}
						
						for(String el: linesplit) {
							newline+=el+ " ";
						}
						
					}else {
						System.out.println("Sorry, this flight is currently booked");
					}
				
				System.out.println("Done");
			}else {
				list.add(line);
			}
		
		}
		PrintWriter pw= new PrintWriter(userFlights);
		PrintWriter pw2= new PrintWriter(flightData);
		flightHistory.add(newline);
		list.add(newline);
		for(String m: otherFlights) {
			pw.println(m);
		}
		String res= "";
		for(String i: flightHistory) {
			res+=i+",";
		}
		pw.println(res);
		for(String l: list) {
			pw2.println(l);
		}
		
		pw.flush();
		pw.close();
		pw2.flush();
		pw2.close();
		myOtherReader.close();
		reader2.close();
		
		
	}
	//allow person logged in to cancel a flight
	public void cancelTicket() throws FileNotFoundException {
		int milecount=0;
		Scanner sc6= new Scanner(System.in);
		ArrayList<String> lista=new ArrayList<>();
		String newline="";
		System.out.println("Enter the flightnumber to cancel");
		String fn= sc6.next();
		Scanner scanner= new Scanner(flightData);
		while(scanner.hasNextLine()) {
			String line= scanner.nextLine();
			String[] linesplit= line.split(" ");
			if(Integer.parseInt(linesplit[0])==Integer.parseInt(fn)) {
				linesplit[7]= ""+(Integer.parseInt(linesplit[7])+1);
				milecount= Integer.parseInt(linesplit[9]);
				for(String el: linesplit) {
					newline+=" "+ el;
				}
			}else {
				lista.add(line);
			}
			
		}
		PrintWriter outpw= new PrintWriter(flightData);
		for(String i: lista) {
			outpw.println(i);
		}
		outpw.println(newline.substring(1));
		outpw.flush();
		outpw.close();
		myMiles.set(1, ""+(Integer.parseInt(myMiles.get(1))-milecount));
		System.out.println("Done");
		
	}
	
	
	public void getHistory() throws FileNotFoundException {
		System.out.println("These are your previous bookings");
		for(String el: flightHistory) {
			if(!el.equals(this.username)) {
				System.out.println(el);
			}
		}
	}
	//return the mileage associated with their account
	public int viewMileage() {
		int result=0;
		if(myMiles.size()<=1) {
			
			System.out.println("You have 0 miles");
		}else {
			
			System.out.println("You have "+myMiles.get(1)+" miles");
			result= Integer.parseInt(myMiles.get(1));
		}
		return result;
		}
	
	//allows the user to access the Ratings class and leave ratings/ comments
	public void rateFlight() {
		
	}
	public void pay(int flightMiles, int price) {
		Scanner sc7= new Scanner(System.in);
		System.out.println("Would you like a first class seat ("+(price+50)+") or a coach seat ("+price+")? (Type F or C)");
		String c= sc7.next();
		int myMile=viewMileage();
		System.out.println("How would you like to pay?");
		System.out.println("A: Credit Card");
		System.out.println("B: Use Miles");
		System.out.println("C: Split between miles and card");
		String option= sc7.next();
		switch(option) {
		case "A":
			if(c.equals("F")) {
				System.out.println("$"+(price+50)+"has been charged to your card. Thank you");
			}else {
				System.out.println("$"+(price)+"has been charged to your card. Thank you");
			}
			break;
		case "B":
			if(myMile<flightMiles) {
				System.out.println("You don't have enough miles for this. Try a different payment method");
				pay(flightMiles,price);
			}else {
				myMiles.set(1,""+(myMile-flightMiles));
				System.out.println("You have "+(myMile-flightMiles)+" miles remaining. Thank you");
			}
			
			
			break;
		case "C":
			System.out.println("How many miles would you like to use?");
			String mchoice= sc7.next();
			int choiceMi= Integer.parseInt(mchoice);
			if(myMile<choiceMi) {
				System.out.println("You don't have enough miles for this. Try implementing a different payment method");
				pay(flightMiles,price);
			}else {
				myMiles.set(1,""+(myMile-choiceMi));
				System.out.println("You have "+(myMile-choiceMi)+" miles remaining. The rest of the ticket has been charged to your card. Thank you");
			}
			break;
		}
		
	}
	public void addRating() throws FileNotFoundException {
		Scanner scanscan= new Scanner(System.in);
		System.out.println("What airline would you like to leave a comment for?");
		String al= scanscan.next();
		scanscan.nextLine();
		System.out.println("Enter your comment here");
		String comment= scanscan.nextLine();
		Scanner read= new Scanner(userFlights);
		boolean hasFlown= false;
		String tempString="";
		ArrayList<String> list= new ArrayList<>();
		while(read.hasNextLine()) {
			String line= read.nextLine();
			String[] linesplit= line.split(",");
			for(int i=0;i<linesplit.length;i++) {
				if(i>0) {
					if(linesplit[0].equals(this.username)) {
						String[] split2= linesplit[i].split(" ");
						if(split2[1].equals(al)) {
							hasFlown=true;
						}
					}
				}
			}
		}
		if(hasFlown==true) {
			Scanner read2= new Scanner(new File("projects/ratings.txt"));
			while(read2.hasNextLine()) {
				String line2= read2.nextLine();
				String[] linesplit2= line2.split(",");
				if(linesplit2[0].equals(al)) {
					for(String l: linesplit2) {
						tempString+=l+",";
					}
				}else {
					list.add(line2);
				}
			}
			PrintWriter powpow= new PrintWriter(new File("projects/ratings.txt"));
			for(String line: list) {
				powpow.println(line);
			}
			if(tempString.equals("")) {
				tempString+=al+",";
			}
			tempString+=comment+"-"+this.username+",";
			powpow.println(tempString);
			powpow.flush();
			powpow.close();
			read2.close();
			read.close();
			System.out.println("Thank you for your input");
			
		}else {
			System.out.println("You cannot leave a rating for this airline, you have not flown on it");
		}
	}
	

	
}
