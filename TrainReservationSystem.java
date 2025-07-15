import java.util.*;
class Train 
{
    int trainNumber;
    String trainName;
    String from;
    String to;
    int availableSeats;

    Train(int trainNum, String trainNm, String from, String to, int seats) 
    {
        this.trainNumber = trainNum;
        this.trainName = trainNm;
        this.from = from;
        this.to = to;
        this.availableSeats = seats;
    }
}

class Reservation 
{
    String name;
    int age;
    Train train;
    String pnr;

    Reservation(String nm, int age, Train train, String pnr) 
    {
        this.name = nm;
        this.age = age;
        this.train = train;
        this.pnr = pnr;
    }
}

public class TrainReservationSystem 
{
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Train> trains = new ArrayList<>();
    static HashMap<String, Reservation> bookings = new HashMap<>();
    static Random rand = new Random();

    public static void main(String[] args)
    {
        setupTrains();

        System.out.println("Welcome to the Train Reservation System");

        while (true) 
        {
            System.out.println("\n1. View Trains\n2. Book Ticket\n3. Cancel Ticket\n4. Exit");
            System.out.print("Enter your choice: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1 -> viewTrains();
                case 2 -> bookTicket();
                case 3 -> cancelTicket();
                case 4 -> 
                {
                    System.out.println("Thank you for using the system!");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    static void setupTrains() 
    {
        trains.add(new Train(101, "Koyna Express", "Kolhapur", "Mumbai", 50));
        trains.add(new Train(102, "Sahyadri Express", "Pune", "Nagpur", 40));
        trains.add(new Train(103, "Deccan Queen", "Mumbai", "Pune", 60));
    }

    static void viewTrains() 
    {
        System.out.println("\nAvailable Trains:");
        for (Train t : trains) 
        {
            System.out.println("Train No: " + t.trainNumber + " | " + t.trainName + " | From: " +
                    t.from + " To: " + t.to + " | Seats: " + t.availableSeats);
        }
    }

    static void bookTicket() 
    {
        System.out.print("Enter your name: ");
        sc.nextLine();
        String name = sc.nextLine();
        System.out.print("Enter your age: ");
        int age = sc.nextInt();

        viewTrains();
        System.out.print("Enter train number to book: ");
        int tn = sc.nextInt();
        Train selected = null;
        for (Train t : trains) 
        {
            if (t.trainNumber == tn) 
            {
                selected = t;
                break;
            }
        }

        if (selected == null) 
        {
            System.out.println("Train not found.");
            return;
        }

        if (selected.availableSeats <= 0) 
        {
            System.out.println("No seats available!");
            return;
        }

        selected.availableSeats--;
        String pnr = "PNR" + (1000 + rand.nextInt(9000));
        bookings.put(pnr, new Reservation(name, age, selected, pnr));
        System.out.println("Booking Confirmed! Your PNR: " + pnr);
    }

    static void cancelTicket() 
    {
        System.out.print("Enter your PNR number: ");
        sc.nextLine(); 
        String pnr = sc.nextLine();

        if (bookings.containsKey(pnr)) 
        {
            Reservation r = bookings.remove(pnr);
            r.train.availableSeats++;
            System.out.println("Ticket cancelled for " + r.name + " (PNR: " + pnr + ")");
        } else 
        {
            System.out.println("Invalid PNR number!");
        }
    }
}
