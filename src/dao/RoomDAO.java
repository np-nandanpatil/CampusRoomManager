package dao;

import db.DBConnection;
import model.Room;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {
    public void addRoom(Room room) throws SQLException {
        String query = "INSERT INTO Room (name, capacity, has_projector) VALUES (?, ?, ?)";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, room.getName());
            ps.setInt(2, room.getCapacity());
            ps.setBoolean(3, room.hasProjector());
            ps.executeUpdate();
        }
    }

    public List<Room> getAllRooms() throws SQLException {
        String query = "SELECT * FROM Room";
        List<Room> rooms = new ArrayList<>();
        try (Connection con = DBConnection.getConnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                Room room = new Room(rs.getInt("room_id"), rs.getString("name"), rs.getInt("capacity"), rs.getBoolean("has_projector"));
                rooms.add(room);
            }
        }
        return rooms;
    }

    public Room getRoomById(int id) throws SQLException {
        String query = "SELECT * FROM Room WHERE room_id = ?";
        Room room = null;
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    room = new Room(rs.getInt("room_id"), rs.getString("name"), rs.getInt("capacity"), rs.getBoolean("has_projector"));
                }
            }
        }
        return room;
    }

    public void updateRoom(Room room) throws SQLException {
        String query = "UPDATE Room SET name = ?, capacity = ?, has_projector = ? WHERE room_id = ?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, room.getName());
            ps.setInt(2, room.getCapacity());
            ps.setBoolean(3, room.hasProjector());
            ps.setInt(4, room.getId());
            ps.executeUpdate();
        }
    }

    public void deleteRoom(int id) throws SQLException {
        String query = "DELETE FROM Room WHERE room_id = ?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    // Added code
    public List<Room> searchAvailableRooms(String searchTerm) {
        List<Room> rooms = new ArrayList<>();
        String query = "SELECT * FROM room WHERE name LIKE ? OR capacity LIKE ?";

        try (Connection connection = DBConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, "%" + searchTerm + "%");
            preparedStatement.setString(2, "%" + searchTerm + "%");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int roomId = resultSet.getInt("room_id");
                String name = resultSet.getString("name");
                int capacity = resultSet.getInt("capacity");
                boolean hasProjector = resultSet.getBoolean("has_projector");

                Room room = new Room(roomId, name, capacity, hasProjector);
                rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

}
