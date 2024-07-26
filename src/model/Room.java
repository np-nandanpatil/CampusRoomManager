package model;

public class Room {
    private int id;
    private String name;
    private int capacity;
    private boolean hasProjector;

    public Room(int id, String name, int capacity, boolean hasProjector) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.hasProjector = hasProjector;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean hasProjector() {
        return hasProjector;
    }
    @Override
    public String toString() {
        return "Room ID: " + id + ", Name: " + name + ", Capacity: " + capacity + ", Has Projector: " + hasProjector;
    }
}
