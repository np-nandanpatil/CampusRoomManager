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
                resultArea.append("Room ID: " + schedule.getRoomId() + ", Semester: " + schedule.getSemester() +
                        ", Class: " + schedule.getClassName() + ", Occupied: " + schedule.isOccupied() + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error searching rooms: " + e.getMessage());
        }
    }
}


//
//package ui;
//
//import dao.RoomDAO;
//import model.Room;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.List;
//
//public class SearchUI extends JFrame {
//    private JTextField searchField;
//    private JTextArea resultArea;
//    private RoomDAO roomDAO;
//
//    public SearchUI() {
//        roomDAO = new RoomDAO();
//
//        setTitle("Search Available Rooms");
//        setSize(500, 400);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
//
//        searchField = new JTextField(20);
//        resultArea = new JTextArea();
//
//        JButton searchButton = new JButton("Search");
//        searchButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                searchAndDisplayRooms();
//            }
//        });
//
//        JPanel panel = new JPanel();
//        panel.add(new JLabel("Search:"));
//        panel.add(searchField);
//        panel.add(searchButton);
//
//        add(panel, BorderLayout.NORTH);
//        add(new JScrollPane(resultArea), BorderLayout.CENTER);
//    }
//
//    private void searchAndDisplayRooms() {
//        String searchTerm = searchField.getText();
//        List<Room> rooms = roomDAO.searchAvailableRooms(searchTerm); // Implement this method in RoomDAO
//        resultArea.setText("");
//
//        for (Room room : rooms) {
//            resultArea.append(room.toString() + "\n");
//        }
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new SearchUI().setVisible(true);
//            }
//        });
//    }
//}
