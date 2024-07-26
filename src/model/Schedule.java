package model;

public class Schedule {
    private final int id;
    private final int roomId;
    private final String semester;
    private final String className;
    private final boolean occupied;

    public Schedule(int id, int roomId, String semester, String className, boolean occupied) {
        this.id = id;
        this.roomId = roomId;
        this.semester = semester;
        this.className = className;
        this.occupied = occupied;
    }

    public int getId() {
        return id;
    }

    public int getRoomId() {
        return roomId;
    }

    public String getSemester() {
        return semester;
    }

    public String getClassName() {
        return className;
    }

    public boolean isOccupied() {
        return occupied;
    }
}


// for the above code I can change this Schedule class to a record class and make this code less in number of lines
// then the record class will be like...
// public record Schedule(int id, int roomId, String semester, String className, boolean occupied) {
// }