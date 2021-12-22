/*
 *

 * Driver Class that houses the menu functionality system of the program
 */
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class MenuDriver {
	
	static ArrayList<String> usernames;
	static Map<String, String> passwords;
	static ArrayList<String> airUsernames;
	static Map<String, String> airPasswords;
	static User user=null;
	static Airline airline=null;
	static boolean logout=false;
	
	
	//allow a person to login and access their respective functionalities
	public static void login() throws IOException {
		Scanner sc= new Scanner(System.in);
		System.out.println("If you are a user, type U, if you are an Airline, type A");
		String uOrA= sc.next();
		if(uOrA.equals("U")) {
			System.out.println("Please input your username");
			String username= sc.next();
			System.out.println("Please input your password");
			String password= sc.next();
			if(!usernames.contains(username)) {
				System.out.println("This username is not registered, try again, or close the program to register a new account");
				login();
			}else {
				if(!passwords.get(username).equals(password)) {
					System.out.println("This password is not accepted, please try again");
					login();
				}else {
					user= new User(username,password);
				}
			}
		}else {
			System.out.println("Please input your username");
			String username= sc.next();
			System.out.println("Please input your password");
			String password= sc.next();
			System.out.println("Please input your airline");
			String al= sc.next();
			if(!airUsernames.contains(username)) {
				System.out.println("This username is not registered, try again, or close the program to register a new account");
				login();
			}else {
				if(!airPasswords.get(username).equals(password)) {
					System.out.println("This password is not accepted, please try again");
					login();
				}else {
					airline= new Airline(username, password,al);
				}
			}
		}
		
	}
	public static void register() {
		Scanner sc= new Scanner(System.in);
		System.out.println("Would you like to register as a user or an airline?");
		String uOrA= sc.next();
		if(uOrA.equals("user")){
			System.out.println("What will be your username?");
			String username= sc.next();
			if(usernames.contains(username)) {
				System.out.println("This username is already taken, please try again");
				register();
			}else {
				usernames.add(username);
				System.out.println("What will be your password?");
				String pw= sc.next();
				passwords.put(username,pw);
			}
		}else {
			System.out.println("What will be your username?");
			String username= sc.next();
			if(airUsernames.contains(username)) {
				System.out.println("This username is already taken, please try again");
				register();
			}else {
				airUsernames.add(username);
				System.out.println("What will be your password?");
				String pw= sc.next();
				airPasswords.put(username,pw);
			}
		}
		
	}
	//end the program
	public static void logout() {
		
		System.out.println("The program will now close");
		logout=true;
	}
	
	public static void main(String[] args) throws IOException {
		//First thing to do here is populate the sets and maps with the usernames that have been saved in the system
		//Do that here
		usernames= new ArrayList<String>();
		passwords= new HashMap<String, String>();
		airUsernames= new ArrayList<String>();
		airPasswords= new HashMap<String, String>();
		
		Scanner reader= new Scanner(new File("Project/c-212-Spring-2020-master/AirlineBooking/FinalProjSrc/projects/up1.txt"));
		int i =0;
		while(i<2) {
			if(i==0) {
				String[] unames= reader.nextLine().split(",");
				for(String username: unames) {
					usernames.add(username);
				}
			}else {
				String[] ups= reader.nextLine().split(",");
				for(int j=0;j<ups.length-1;j++) {
					if(j%2==0) {
						passwords.put(ups[j],ups[j+1]);
					}
				}
			}
			i++;
		}
		Scanner reader2= new Scanner(new File("Project/c-212-Spring-2020-master/AirlineBooking/FinalProjSrc/projects/up2.txt"));
		int k =0;
		while(k<2) {
			if(k==0) {
				String[] unames= reader2.nextLine().split(",");
				for(String username: unames) {
					airUsernames.add(username);
				}
			}else {
				String[] ups= reader2.nextLine().split(",");
				for(int l=0;l<ups.length-1;l++) {
					if(l%2==0) {
						airPasswords.put(ups[l],ups[l+1]);
					}
				}
			}
			k++;
		}
		
		
		
		
		
		
		System.out.println("Welcome to AirPro");
		Scanner sc= new Scanner(System.in);
		System.out.println("Would you like to login or register for a new account?");
		String lOrR= sc.next();
		switch(lOrR) {
		case "login":
			login();
			break;
		case "register":
			register();
			System.out.println("You can now login");
			login();
			break;
		
		}
		while(logout!=true) {
			if(airline==null) {
				System.out.println();
				System.out.println("Type the letter of what you want to do");
				System.out.println("A: Search for a flight");
				System.out.println("B: Book a flight");
				System.out.println("C: View your booking history");
				System.out.println("D: View your mileage");
				System.out.println("E: Cancel your ticket");
				System.out.println("F: Leave a rating/ comment");
				System.out.println("G: Logout");
				String choice= sc.next();
				switch(choice) {
				case "A":
					user.search();
					break;
				case "B":
					user.book();
					break;
				case "C":
					user.getHistory();
					break;
				case "D":
					user.viewMileage();
					break;
				case "E": user.cancelTicket();
					
					break;
				case "F": user.addRating();
				break;
				case "G": logout();
				break;
				}
			}else {
				System.out.println();
				System.out.println("Type the letter of what you want to do");
				System.out.println("A: Add a flight");
				System.out.println("B: Cancel a flight");
				System.out.println("C: Update/ Change a flight");
				System.out.println("D: Blacklist a user");
				System.out.println("E: Gift a user miles");
				System.out.println("F: View ratings");
				System.out.println("G: Logout");
				String choice= sc.next();
				switch(choice) {
				case "A": airline.addFlight();
				break;
					
				case "B":
					airline.cancelFlight();
					break;
				case "C":
					airline.updateFlight();
					break;
				case "D":
					airline.blacklist();
					break;
					
				case "E": airline.addMiles();
					
					break;
				case "F": airline.viewRatings();
				break;
				case "G": logout();
				break;
				}
			}
		}
		
	//Last thing to do is to update the data files by writing the sets and maps to them
	// Do that here:
		PrintWriter pw= new PrintWriter("Project/c-212-Spring-2020-master/AirlineBooking/FinalProjSrc/projects/up1.txt");
		PrintWriter pw2= new PrintWriter("Project/c-212-Spring-2020-master/AirlineBooking/FinalProjSrc/projects/up2.txt");
		
		for(int j=0;j<usernames.size();j++) {
			if(j==usernames.size()-1) {
				pw.print(usernames.get(j)+",");
				pw.println();
			}else {
				pw.print(usernames.get(j)+",");
			}
		}
		pw.flush();
		for(String username: passwords.keySet()) {
			pw.print(username+","+passwords.get(username)+",");
		}
		pw.flush();
		for(int j=0;j<airUsernames.size();j++) {
			if(j==airUsernames.size()-1) {
				pw2.print(airUsernames.get(j)+",");
				pw2.println();
			}else {
				pw2.print(airUsernames.get(j)+",");
			}
		}
		pw2.flush();
		for(String username:airPasswords.keySet()) {
			pw2.print(username+","+airPasswords.get(username)+",");
		}
		pw2.flush();
		
		pw.close();
		pw2.close();
		reader.close();
		reader2.close();
		sc.close();
	}
}
	
	

