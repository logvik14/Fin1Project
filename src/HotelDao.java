
import java.util.HashSet;
import java.util.Set;

public class HotelDao <T extends Hotel> implements AbstractDao<Hotel> {
    private Set<Hotel> allHotels = new HashSet<>();

    private Hotel grand = new Hotel("Grand Hotel", "Kyiv");
    private Hotel mariott1 = new Hotel("Mariott", "Paris");
    private Hotel mariott2 = new Hotel("Mariott", "Paris");

    public HotelDao () throws Exception {
        allHotels.add(grand);
        allHotels.add(mariott1);
        allHotels.add(mariott2);
    }

    @Override
    public Hotel save(Hotel hotel) throws Exception {
        if (!allHotels.add(hotel)) {
            throw new Exception ("Cannot save hotel "+ hotel.getName());
        }
        else allHotels.add(hotel);
        return hotel;
    }

    @Override
    public Hotel delete(Hotel hotel) throws Exception {
        if (!allHotels.remove(hotel)) {
            throw new Exception("Cannot delete hotel " + hotel.getName());
        }
        else allHotels.remove(hotel);
        return hotel;
    }

    @Override
    public Set<Hotel> getAll() {
        return allHotels;
    }


    public boolean addRoom (Hotel hotel, Room room) throws Exception{
        try {
            hotel.getRooms().add(room);
            room.setHotelName(hotel.getName());
            room.setCityName(hotel.getCity());
            return true;
        }
        catch (Exception e){
            System.out.println("Cannot add room " + room + " into " + hotel);
            return false;
        }
    }

    public Hotel getGrand() {
        return grand;
    }

    public Hotel getMariott1() {
        return mariott1;
    }

    public Hotel getMariott2() {
        return mariott2;
    }

}

