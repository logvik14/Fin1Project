import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hotel {

    private String name;
    private String city;
    private long hotelId;
    private List<Room> rooms = new ArrayList<>();
    private static long count =0;


    public Hotel(String name, String city) {
        this.name = name;
        this.city = city;
        this.hotelId = count + 1;
        this.rooms = new ArrayList<>();
        count ++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public long getHotelId() {
        return hotelId;
    }

    public void setHotelId(long hotelId) {
        this.hotelId = hotelId;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hotel hotel = (Hotel) o;

        if (hotelId != hotel.hotelId) return false;
        if (name != null ? !name.equals(hotel.name) : hotel.name != null) return false;
        return city != null ? city.equals(hotel.city) : hotel.city == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (int) (hotelId ^ (hotelId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Hotel: " + name + " ID (" +hotelId + ") in " +city + ". Rooms available: " + System.getProperty("line.separator") +rooms;
    }
}


