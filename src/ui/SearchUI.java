package ui;

import dao.ScheduleDAO;
import model.Schedule;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class SearchUI extends JFrame {
    private final JTextField capacityField;
    private final JCheckBox projectorCheckBox;
    private final JTextArea resultArea;
    private final ScheduleDAO scheduleDAO;

    public SearchUI() {
        scheduleDAO = new ScheduleDAO();
        setTitle("Search Available Rooms");
        setSize(400, 300);
        setLayout(new GridLayout(4, 2));

        add(new JLabel("Minimum Capacity:"));
        capacityField = new JTextField();
        add(capacityField);

        add(new JLabel("Has Projector:"));
        projectorCheckBox = new JCheckBox();
        add(projectorCheckBox);

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> searchRooms());
        add(searchButton);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        add(new JScrollPane(resultArea));

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose());
        add(cancelButton);
    }

    private void searchRooms() {
        int capacity = Integer.parseInt(capacityField.getText());
        boolean hasProjector = projectorCheckBox.isSelected();

        try {
            List<Schedule> schedules = scheduleDAO.searchSchedules(capacity, hasProjector);
            resultArea.setText("");
            for (Schedule schedule : schedules) {
                resultArea.append("Room ID: " + schedule.getRoomId() + ", Semester: " + schedule.getSemester() + ", Class: " + schedule.getClassName() + ", Occupied: " + schedule.isOccupied() + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error searching rooms: " + e.getMessage());
        }
    }
}
