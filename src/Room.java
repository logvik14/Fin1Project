
public class Room {
    private int persons;
    private int price;
    private long roomId;
    private String hotelName;
    private String cityName;
    private boolean isBooked;
    private User userReserved;
    private static int count = 0;


    public Room(int persons, int price) {
        this.persons = persons;
        this.price = price;
        this.roomId = count +1;
        this.hotelName = "";
        this.cityName = "";
        this.isBooked = false;
        this.userReserved = null;
        count ++;
    }

    public int getPersons() {
        return persons;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setPersons(int persons) {
        this.persons = persons;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    public User getUserReserved() {
        return userReserved;
    }

    public void setUserReserved(User userReserved) {
        this.userReserved = userReserved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;

        if (persons != room.persons) return false;
        if (price != room.price) return false;
        if (roomId != room.roomId) return false;
        return hotelName != null ? hotelName.equals(room.hotelName) : room.hotelName == null;

    }

    @Override
    public int hashCode() {
        int result = persons;
        result = 31 * result + price;
        result = 31 * result + (int) (roomId ^ (roomId >>> 32));
        result = 31 * result + (hotelName != null ? hotelName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        String availability =". Not availabe for booking";
        if (!isBooked) availability = ". Available for booking";
        return " { Room is for " + persons + " persons. Price is "+ price + ". ID is " + roomId + availability + " } " + System.getProperty("line.separator") ;
    }
}

