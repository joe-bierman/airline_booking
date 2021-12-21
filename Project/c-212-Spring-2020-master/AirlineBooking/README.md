# c-212-Spring-2020
Group 54

The src folder that is next to FinalProjGroup54 folder is NOT the project to be grading. This can be ignored. The src folder INSIDE the FinalProjGroup54 folder contains the final project to be graded.


Format of flight inputs/outputs is "FlightNumber Airline Hour:Min-Month-Day-Year Hour:Min-Month-Day-Year To(City) From(City) Seats SeatsLeft long/short miles price"

For searching by price, look to the last element in the string to see that it has been ordered by price, vice versa for flightnumber

When adding flights, distance in miles between flight locations, specifying the long/short, and the price is all arbitrary and up to the Airline who adds them. 

A user can choose to purchase a First-class seat for $50 more than a coach ticket. Seats left is a combined number of first class and coach. Each time a ticket is booked, a seat is removed from seats left, regardless of first class or coach.

It is assumed that when a user books a flight twice, they have bought two tickets. However, if the flight is cancelled, they will only be rewarded miles for 1 ticket.

It is assummed reward miles are equal to miles flown. Users get miles from being gifted miles from an airline, or booking a flight( it is assumed that booking a flight is equivalent to flying it, unless the ticket is cancelled), and lose miles when cancelling a ticket or purchasing a ticket with their miles.


When logging in, be sure to follow script EXACTLY (spelling is important)

ALL inputs (other than comment for rating) should be with NO SPACES. This includes cities for the To/From and Airline Names.
Different spellings of airlines (including different capitalizations) refers to a different Airline entity.

flightData.txt contains all flights. Look here for a real time change after adding, cancelling, or updating a flightor you can use the appropriate methods to view it 

userMiles.txt contains users miles. Look here for real time changes in miles after booking and cancelling and payment, or you can use the appropriate methods to view it 

usersFlights.txt contains users flighthistory. Look here for real time changes in user's booking history after booking a flight, or you can use the appropriate methods to view it 

blacklist.txt contains the users each airline has blacklisted. Look here for real time changes after adding someone to a blacklist, or you can use the appropriate methods to view it 

up1.txt and up2.txt contain the usernames and passwords for users and airliners, respectively. Look here for real time changes after registering a new account, or you can logout and re-login to see that it works.


This program is purely menu based, so there are no return functions. That said, it is impossible to run JUnit Tests on this code. We hope that the video demonstration will suffice as a demonstration of testing the code.


I implore the grader of this to please look at the commit history of each of Group 54's members. This will be elaborated upon in the group evaluation.


