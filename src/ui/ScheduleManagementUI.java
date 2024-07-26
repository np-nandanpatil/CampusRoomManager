package ui;

import dao.RoomDAO;
import dao.ScheduleDAO;
import model.Room;
import model.Schedule;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class ScheduleManagementUI extends JFrame {
    private final JComboBox<Room> roomComboBox;
    private final JTextField semesterField;
    private final JTextField classField;
    private final JCheckBox occupiedCheckBox;
    private final ScheduleDAO scheduleDAO;

    public ScheduleManagementUI() {
        scheduleDAO = new ScheduleDAO();
        setTitle("Schedule Management");
        setSize(400, 300);
        setLayout(new GridLayout(5, 2));

        add(new JLabel("Room:"));
        roomComboBox = new JComboBox<>();
        loadRooms();
        add(roomComboBox);

        add(new JLabel("Semester:"));
        semesterField = new JTextField();
        add(semesterField);

        add(new JLabel("Class:"));
        classField = new JTextField();
        add(classField);

        add(new JLabel("Occupied:"));
        occupiedCheckBox = new JCheckBox();
        add(occupiedCheckBox);

        JButton addButton = new JButton("Add Schedule");
        addButton.addActionListener(e -> addSchedule());
        add(addButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose());
        add(cancelButton);
    }

    private void loadRooms() {
        RoomDAO roomDAO = new RoomDAO();
        try {
            List<Room> rooms = roomDAO.getAllRooms();
            for (Room room : rooms) {
                roomComboBox.addItem(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading rooms: " + e.getMessage());
        }
    }

    private void addSchedule() {
        Room room = (Room) roomComboBox.getSelectedItem();
        String semester = semesterField.getText();
        String className = classField.getText();
        boolean occupied = occupiedCheckBox.isSelected();

        Schedule schedule = new Schedule(0, room.getId(), semester, className, occupied);
        try {
            scheduleDAO.addSchedule(schedule);
            JOptionPane.showMessageDialog(this, "Schedule added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding schedule: " + e.getMessage());
        }
    }
}
