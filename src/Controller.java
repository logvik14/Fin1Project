import java.util.*;
import java.util.stream.Collectors;

public class Controller {
    private UserDao <User> userDao = new UserDao<>();
    private HotelDao <Hotel> hotelDao = new HotelDao<>();
    private RoomDao <Room> roomDao = new RoomDao<>();


    public Controller() throws Exception {
        hotelDao.addRoom(hotelDao.getGrand(), roomDao.getRoom3());
        hotelDao.addRoom(hotelDao.getGrand(), roomDao.getRoom6());
        hotelDao.addRoom(hotelDao.getMariott1(), roomDao.getRoom1());
        hotelDao.addRoom(hotelDao.getMariott1(), roomDao.getRoom4());
        hotelDao.addRoom(hotelDao.getMariott2(), roomDao.getRoom2());
        hotelDao.addRoom(hotelDao.getMariott2(), roomDao.getRoom5());
    }

    public Set<Hotel> findHotelByCity(String city) {
        Set<Hotel> hotels = hotelDao.getAll();
        try {
            Set<Hotel> hotelsByCity = hotels.stream().filter(hotel -> hotel.getCity().equals(city)).collect(Collectors.toSet());
            if (hotelsByCity.isEmpty()) {
                throw new NullPointerException ("There are no hotels in selected city");
            }
            return hotelsByCity;
        }
        catch (NullPointerException e){
            e.printStackTrace();
            return new HashSet<>();
        }
    }

    public Set<Hotel> findHotelByName(String name) {
        Set<Hotel> hotels = hotelDao.getAll();
        try {
            Set<Hotel> hotelsByName = hotels.stream().filter(hotel -> hotel.getName().equals(name)).collect(Collectors.toSet());

            if (hotelsByName.isEmpty()) {
                throw new NullPointerException("There are no hotels with such name");
            }
            return hotelsByName;
        }
        catch (NullPointerException e){
            e.printStackTrace();
            return new HashSet<>();
        }
    }

    private List <Room> listCheckNullOrEmpty(List <Room> listToCheck){
        List <Room> list = new ArrayList<>();
        list.addAll(roomDao.getAll());
        if (listToCheck.isEmpty() || listToCheck.size() == 0) return list;
        else return listToCheck;
    }

    private String convertEmptyToZero (String s){
        if(s.equals("")) return "0";
        else return s;
    }

    public List <Room> findRoom(Map<String, String> params) {
        Set <Room> rooms = roomDao.getAll();
        try {
            List <Room> hotelNamesSet = rooms.stream()
                    .filter(room -> room.getHotelName().equalsIgnoreCase(params.get("hotelName")))
                    .collect(Collectors.toList());
            hotelNamesSet = listCheckNullOrEmpty(hotelNamesSet);

            List <Room> cityNameSet = rooms.stream()
                    .filter(room -> room.getCityName().equalsIgnoreCase(params.get("cityName")))
                    .collect(Collectors.toList());
            cityNameSet = listCheckNullOrEmpty(cityNameSet);


            List <Room> priceSet = rooms.stream()
                    .filter(room -> room.getPrice() == Integer.parseInt(convertEmptyToZero(params.get("price"))))
                    .collect(Collectors.toList());
            priceSet = listCheckNullOrEmpty(priceSet);

            List <Room> personsSet = rooms.stream()
                    .filter(room -> room.getPersons() == Integer.parseInt(convertEmptyToZero(params.get("persons"))))
                    .collect(Collectors.toList());
            personsSet = listCheckNullOrEmpty(personsSet);

            hotelNamesSet.retainAll(cityNameSet);
            hotelNamesSet.retainAll(priceSet);
            hotelNamesSet.retainAll(personsSet);
            return hotelNamesSet;
        }
        catch (NullPointerException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
        catch (NumberFormatException e){
            System.out.println("Please enter price and persons as a number");
            return Collections.emptyList();
        }
    }

    private User findUserById (long userId){
        return userDao.getAll().stream()
                .filter(user -> user.getUserId() == userId).findFirst()
                .get();
    }

    private Room findRoomById (long roomId){
        return roomDao.getAll().stream()
                .filter(room -> room.getRoomId() == roomId).findFirst()
                .get();
    }

    private Hotel findHotelById (long hotelId){
        return hotelDao.getAll().stream()
                .filter(hotel -> hotel.getHotelId() == hotelId).findFirst()
                .get();
    }

    public boolean bookRoom (long roomId, long userId, long hotelId){
        try {
            Room room = findRoomById(roomId);
            User user = findUserById(userId);
            Hotel hotel = findHotelById(hotelId);
            if (!room.isBooked()) {
                room.setBooked(true);
                room.setHotelName(hotel.getName());
                room.setCityName(hotel.getCity());
                room.setUserReserved(user);
                return true;
            }
            else {
                throw new RoomIsBookedException ("Selected room is already booked");
            }
        }
        catch (RoomIsBookedException e){
            e.printStackTrace();
            return false;
        }
        catch (NoSuchElementException e){
            System.out.println("Only registered users can make reservation");
            return false;
        }
        catch (Exception e){
            System.out.println("Cannot make reservation");
            return false;
        }
    }

    public boolean cancelBooking (long roomId, long userId, long hotelId){
        try {
            Room room = findRoomById(roomId);
            User user = findUserById(userId);
            Hotel hotel = findHotelById(hotelId);
            if (!room.isBooked()) {
                throw new NullPointerException("Room is not booked");
            } else if (!room.getUserReserved().equals(user) || !room.getHotelName().equalsIgnoreCase(hotel.getName())) {
                throw new NoSuchElementException();
            } else {
                room.setBooked(false);
                room.setHotelName("");
                room.setCityName("");
                room.setUserReserved(null);
                return true;
            }
        }
        catch (NullPointerException e){
            e.printStackTrace();
            return false;
        }
        catch (NoSuchElementException e){
            System.out.println("Wrong user or hotel selected during cancelation");
            return false;
        }
        catch (Exception e){
            System.out.println("Cannot cancel booking");
            return false;
        }
    }

    public User registerUser (User user) throws Exception {
        userDao.save(user);
        return user;
    }


}



