import java.util.Calendar;
import java.util.Date;

/*
 *
 * Data class that constructs a Flight object , used for data organization
 */

public class Flight {
	private String departure;
	private String arrival;
	private int flightNumber;
	private int numberOfSeats;
	private int numberOfSeatsLeft;
	private String to;
	private String from;
	private String airline;
	private String lOrS;
	private int miles;
	private int price;
	
	//constructors to set up object
	public Flight(int flightNumber, String airline, String departure, String arrival,String to,String from ,int numberOfSeats, int numberOfSeatsLeft, String lOrS, int miles,int price) {
		this.flightNumber=flightNumber;
		this.departure=departure;
		this.arrival=arrival;
		this.numberOfSeats=numberOfSeats;
		this.numberOfSeatsLeft=numberOfSeatsLeft;
		this.to=to;
		this.from=from;
		this.airline=airline;
		this.lOrS=lOrS;
		this.miles=miles;
		this.price=price;
	}
	public String toString() {
		return ""+flightNumber+" "+airline+" "+departure+" "+arrival+" "+to + " "+from+" "+numberOfSeats+" "+numberOfSeatsLeft+" "+lOrS+" "+miles+" "+price;
		
		
		

	}
	
}
