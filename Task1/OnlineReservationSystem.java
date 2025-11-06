import java.util.*;
public class OnlineReservationSystem {
   static  Scanner sc = new Scanner(System.in);
   static Ticket[] tickets = new Ticket[50];
   static int ticketCount = 0;
   static int[] trainNumbers = {101,102,103,104};
   static String[] trainNames = {"Chennai Express","Mumbai Express","Howrah Express","Vande Bharat"};
   static boolean login(){
    String username = "UmaUdumula";
    String password = "9392";
    int attempts = 3;
    if (sc.hasNextLine()) sc.nextLine(); 
    while (attempts>0){
        System.out.println("Enter Login ID:");
        String id = sc.nextLine().trim();
        System.out.println("Enter Password:");
        String pass = sc.nextLine().trim();
        if(id.equals(username)&&pass.equals(password))
        {
            System.out.println("\n Login Successful!\n");
            return true;
        }
        else {
            attempts--;
            System.out.println("Invalid credentials.Attempts left:"+attempts);
        }
    }
    System.out.println("Too many failed attempts.Exiting...");
    return false;
   }
   static void reverseTicket()
   {
    System.out.println("\n Reservation Form");
    System.out.print("Enter Passenger Name:");
    String name = sc.nextLine().trim();
    if (name.isEmpty())
    {
        System.out.println("Name cannot be empty");
        return;
    }
    System.out.println("Available Trains");
    for (int i =0;i<trainNumbers.length;i++)
    {
        System.out.println(trainNumbers[i]+"-"+trainNames[i]);
    }
    System.out.println("Enter Train Number:");
    int tno;
    try{
        tno=Integer.parseInt(sc.nextLine().trim());
    }
    catch(Exception e)
    {
        System.out.println("Invalid Train Number.");
        return;
    }
    String tname = null;
    for(int i=0;i<trainNumbers.length;i++)
    {
        if (tno == trainNumbers[i])
        {
            tname = trainNames[i];
            break;
        }
    }
    if (tname==null)
    {
        System.out.println("Train not found.");
        return;
    }
    System.out.print("Enter Class Type(Sleeper/AC):");
    String classType=sc.nextLine().trim();
    if(!classType.equalsIgnoreCase("Sleeper")&&!classType.equalsIgnoreCase("AC"))
    {
        System.out.println("Invalid Class Type:");
        return;
    }
    System.out.print("Enter Date of Journey(dd-mm-yyyy):");
    String doj = sc.nextLine().trim();
    if (doj.isEmpty()){
        System.out.println("Date cannot be empty.");
        return;
    }
    System.out.print("From: ");
    String from = sc.nextLine().trim();
    System.out.print("To: ");
    String to = sc.nextLine().trim();

    if(from.isEmpty()|| to.isEmpty()||from.equalsIgnoreCase(to))
    {
        System.out.println("Invalid From/To entries");
        return;
    }
    int pnr = 100000+new Random().nextInt(900000);
    tickets[ticketCount++]=new Ticket(pnr,name,tno,tname,classType,doj,from,to);
    System.out.println("\n Ticket Reserved Successfully!");
    tickets[ticketCount-1].display();
  }
  static void cancelTicket(){
    if(ticketCount==0){
        System.out.println("No tickets to cancel.");
        return;
    }
    System.out.println("Enter PNR to Cancel:");
    int pnr;
    try{
        pnr=Integer.parseInt(sc.nextLine().trim());
    }
    catch(Exception e){
        System.out.println("Invalid PNR number.");
        return;
    }
    int index = -1;
    for(int i =0;i<ticketCount;i++)
    {
        if(tickets[i].pnr==pnr)
        {
            index = i;
            break;
        }
    }
    if(index==-1)
    {
        System.out.println("PNR not found.");
        return;
    }
    System.out.println("\nTicket Found:");
    tickets[index].display();
    System.out.println("Confirm Cancellation?(yes/no):");
    String confirm = sc.nextLine().trim().toLowerCase();
    if(confirm.equals("yes")){
        for(int i=index;i<ticketCount-1;i++){
            tickets[i]=tickets[i+1];
        }
        tickets[ticketCount]=null;
        System.out.println("Ticket Cancelled Successfully.");
    }
    else{
        System.out.println("Cancellation Aborted.");
    }
    }
    static void viewTickets(){
        if(ticketCount==0)
        {
            System.out.println("No tickets reserved yet.");
            return;
        }
        System.out.println("\n All Booked Tickets");
        for(int i =0;i<ticketCount;i++)
        {
            tickets[i].display();
        }
    }
    public static void main(String[] args) {
        System.out.println("Online Reservation System");
        if(!login()) return;
        int choice;
        do {
            System.out.println("\n Main Menu");
            System.out.println("1.Reservation Form");
            System.out.println("2.Cancellation Form");
            System.out.println("3.View All Tickets");
            System.out.println("4.Exit");
            System.out.println("Enter your choice:");
            try{
                choice = Integer.parseInt(sc.nextLine().trim());
            }
            catch(Exception e)
            {
                System.out.println("Invalid input.");
                choice = 0;
            }
            switch(choice){
                case 1:
                reverseTicket();
                break;
                case 2:
                cancelTicket();
                break;
                case 3:
                viewTickets();
                break;
                case 4:
                System.out.println("Exiting...Thank you");
                break;
                default:
                System.out.println("Invalid choice.Try again.");
            }

        }
        while(choice!=4);
    }
}
class Ticket
{
    int pnr,trainNo;
    String name,trainName,classType,dateOfJourney,from,to;
    Ticket(int pnr,String name,int trainNo,String trainName,String classType,String dateOfJourney,String from,String to){
        this.pnr=pnr;
        this.name=name;
        this.trainNo = trainNo;
        this.trainName=trainName;
        this.classType=classType;
        this.dateOfJourney=dateOfJourney;
        this.from=from;
        this.to=to;
    }
    void display()
    {
        System.out.println("\n");
        System.out.println("PNR:"+pnr);
        System.out.println("Name:"+name);
        System.out.println("Train No:"+trainNo);
        System.out.println("Train Name"+trainName);
        System.out.println("Class:"+classType);
        System.out.println("Date:"+dateOfJourney);
        System.out.println("From:"+from);
        System.out.println("To:"+to);
        System.out.println(" "); 
    }
}
 

