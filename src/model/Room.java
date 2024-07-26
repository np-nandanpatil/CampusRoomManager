package model;

public class Room {
    private final int id;
    private final String name;
    private final int capacity;
    private final boolean hasProjector;

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


// This code just contains the getter and setter methods and constructor to assign the values
// This entire code can be written as a Record class. The code for the record class will be as follows

// public record Room(int id, String name, int capacity, boolean hasProjector) {
//  @Override
//  public String toString() {
//  return "Room ID: " + id + ", Name: " + name + ", Capacity: " + capacity + ", Has Projector: " + hasProjector;
//  }
// }
