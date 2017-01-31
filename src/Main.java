
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {

        Controller controller = new Controller();
        User Vladymyr = new User("Vladymyr", "Logvinov", 23, "email");

        System.out.println(controller.findHotelByCity("Paris"));
        System.out.println(controller.findHotelByName("Mariott"));

        System.out.println("Which hotel are you looking for?");
        BufferedReader hotelReader = new BufferedReader(new InputStreamReader(System.in));
        String hotelName = hotelReader.readLine();

        System.out.println("In which city you want to stay?");
        BufferedReader cityReader = new BufferedReader(new InputStreamReader(System.in));
        String cityName = cityReader.readLine();

        System.out.println("At what price?");
        BufferedReader priceReader = new BufferedReader(new InputStreamReader(System.in));
        String price = priceReader.readLine();

        System.out.println("How many persons will stay in room?");
        BufferedReader personsReader = new BufferedReader(new InputStreamReader(System.in));
        String persons = personsReader.readLine();


        Map <String, String> request1 = new HashMap<>();
        request1.put("hotelName", hotelName);
        request1.put("cityName", cityName);
        request1.put("price", price);
        request1.put("persons", persons);

        List<Room> listOfAvailableRooms = controller.findRoom(request1);
        System.out.println("requested room: " + listOfAvailableRooms);

        controller.bookRoom(2 ,Vladymyr.getUserId(),3);

        controller.registerUser(Vladymyr);
        controller.bookRoom(2 ,Vladymyr.getUserId(),3);
        controller.bookRoom(2 ,Vladymyr.getUserId(),3);

        System.out.println("requested room update: " + listOfAvailableRooms);
        controller.cancelBooking(2 ,Vladymyr.getUserId(),4);
        controller.cancelBooking(2 ,Vladymyr.getUserId(),3);
        System.out.println("requested room update: " + listOfAvailableRooms);

    }
}

