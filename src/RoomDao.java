
import java.util.HashSet;
import java.util.Set;

public class RoomDao <T extends Room> implements AbstractDao<Room> {
    private Set <Room> allRooms = new HashSet<>();

    private Room room1 = new Room(2, 500);
    private Room room2 = new Room(2, 500);
    private Room room3 = new Room(2, 1000);
    private Room room4 = new Room(2, 300);
    private Room room5 = new Room(4, 1500);
    private Room room6 = new Room(4, 1500);

    public Room getRoom1() {
        return room1;
    }

    public Room getRoom2() {
        return room2;
    }

    public Room getRoom3() {
        return room3;
    }

    public Room getRoom4() {
        return room4;
    }

    public Room getRoom5() {
        return room5;
    }

    public Room getRoom6() {
        return room6;
    }

    public RoomDao (){
        allRooms.add(room1);
        allRooms.add(room2);
        allRooms.add(room3);
        allRooms.add(room4);
        allRooms.add(room5);
        allRooms.add(room6);
    }


    @Override
    public Room save(Room room) throws Exception {
        if (!allRooms.add(room)) throw new Exception ("Cannot save room");
        else allRooms.add(room);
        return room;
    }

    @Override
    public Room delete(Room room) throws Exception {
        if(!allRooms.remove(room)) throw new Exception("Cannot delete room");
        else allRooms.remove(room);
        return room;
    }

    @Override
    public Set<Room> getAll() {
        return allRooms;
    }
}


