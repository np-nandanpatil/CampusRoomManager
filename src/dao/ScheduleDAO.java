package dao;

import db.DBConnection;
import model.Schedule;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ScheduleDAO {
    public void addSchedule(Schedule schedule) throws SQLException {
        String query = "INSERT INTO Schedule (room_id, semester, class, occupied) VALUES (?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, schedule.getRoomId());
            ps.setString(2, schedule.getSemester());
            ps.setString(3, schedule.getClassName());
            ps.setBoolean(4, schedule.isOccupied());
            ps.executeUpdate();
        }
    }

    public Schedule getScheduleById(int id) throws SQLException {
        String query = "SELECT * FROM Schedule WHERE schedule_id = ?";
        Schedule schedule = null;
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    schedule = new Schedule(rs.getInt("schedule_id"), rs.getInt("room_id"), rs.getString("semester"), rs.getString("class"), rs.getBoolean("occupied"));
                }
            }
        }
        return schedule;
    }

    public void updateSchedule(Schedule schedule) throws SQLException {
        String query = "UPDATE Schedule SET room_id = ?, semester = ?, class = ?, occupied = ? WHERE schedule_id = ?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, schedule.getRoomId());
            ps.setString(2, schedule.getSemester());
            ps.setString(3, schedule.getClassName());
            ps.setBoolean(4, schedule.isOccupied());
            ps.setInt(5, schedule.getId());
            ps.executeUpdate();
        }
    }

    public List<Schedule> searchSchedules(int capacity, boolean hasProjector) throws SQLException {
        String query = "SELECT s.* FROM Schedule s JOIN Room r ON s.room_id = r.room_id " + "WHERE r.capacity >= ? AND r.has_projector = ?";
        List<Schedule> schedules = new ArrayList<>();
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, capacity);
            ps.setBoolean(2, hasProjector);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Schedule schedule = new Schedule(rs.getInt("schedule_id"), rs.getInt("room_id"), rs.getString("semester"), rs.getString("class"), rs.getBoolean("occupied"));
                    schedules.add(schedule);
                }
            }
        }
        return schedules;
    }

    public boolean isRoomOccupied(int roomId, String semester) {
        String query = "SELECT COUNT(*) FROM schedule WHERE room_id = ? AND semester = ? AND occupied = 1";

        try (Connection connection = DBConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, roomId);
            preparedStatement.setString(2, semester);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void deleteSchedule(int scheduleId) {
        String query = "DELETE FROM schedule WHERE schedule_id = ?";

        try (Connection connection = DBConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, scheduleId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Schedule> getAllSchedules() {
        List<Schedule> schedules = new ArrayList<>();
        String query = "SELECT * FROM schedule";

        try (Connection connection = DBConnection.getConnection(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int scheduleId = resultSet.getInt("schedule_id");
                int roomId = resultSet.getInt("room_id");
                String semester = resultSet.getString("semester");
                String className = resultSet.getString("class");
                boolean occupied = resultSet.getBoolean("occupied");

                Schedule schedule = new Schedule(scheduleId, roomId, semester, className, occupied);
                schedules.add(schedule);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schedules;
    }

}
