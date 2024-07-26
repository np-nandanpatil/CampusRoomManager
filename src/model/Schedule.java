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


// Or I can change this Schedule class to a record class and make this code even less number of lines
// and then the code will be something like
// public record Schedule(int id, int roomId, String semester, String className, boolean occupied) {
// }