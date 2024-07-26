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

//        assert room != null;

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
//
//
//package ui;
//
//import dao.ScheduleDAO;
//import model.Schedule;
//import model.Room;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.sql.SQLException;
//import java.util.List;
//
//public class ScheduleManagementUI extends JFrame {
//    private JTextField roomIdField;
//    private JTextField semesterField;
//    private JTextField classField;
//    private ScheduleDAO scheduleDAO;
//    private JTextArea resultArea;
//
//    public ScheduleManagementUI() {
//        scheduleDAO = new ScheduleDAO();
//
//        setTitle("Schedule Management");
//        setSize(600, 500);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
//
//        roomIdField = new JTextField(10);
//        semesterField = new JTextField(10);
//        classField = new JTextField(10);
//        resultArea = new JTextArea();
//
//        JButton addButton = new JButton("Add Schedule");
//        addButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    addSchedule();
//                } catch (SQLException ex) {
//                    throw new RuntimeException(ex);
//                }
//            }
//        });
//
//        JButton deleteButton = new JButton("Delete Schedule");
//        deleteButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                deleteSchedule();
//            }
//        });
//
//        JButton refreshButton = new JButton("Refresh Schedules");
//        refreshButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                refreshSchedules();
//            }
//        });
//
//        JPanel panel = new JPanel();
//        panel.add(new JLabel("Room ID:"));
//        panel.add(roomIdField);
//        panel.add(new JLabel("Semester:"));
//        panel.add(semesterField);
//        panel.add(new JLabel("Class:"));
//        panel.add(classField);
//        panel.add(addButton);
//        panel.add(deleteButton);
//        panel.add(refreshButton);
//
//        add(panel, BorderLayout.NORTH);
//        add(new JScrollPane(resultArea), BorderLayout.CENTER);
//    }
//
//    private void addSchedule() throws SQLException {
//        int roomId = Integer.parseInt(roomIdField.getText());
//        String semester = semesterField.getText();
//        String className = classField.getText();
//
//        if (scheduleDAO.isRoomOccupied(roomId, semester)) {
//            JOptionPane.showMessageDialog(this, "Room is already occupied for the given semester.");
//            return;
//        }
//
//        Schedule schedule = new Schedule(0, roomId, semester, className, true);
//        scheduleDAO.addSchedule(schedule);
//        refreshSchedules();
//    }
//
//    private void deleteSchedule() {
//        String scheduleIdStr = JOptionPane.showInputDialog(this, "Enter Schedule ID to delete:");
//        int scheduleId = Integer.parseInt(scheduleIdStr);
//        scheduleDAO.deleteSchedule(scheduleId);
//        refreshSchedules();
//    }
//
//    private void refreshSchedules() {
//        List<Schedule> schedules = scheduleDAO.getAllSchedules();
//        resultArea.setText("");
//        for (Schedule schedule : schedules) {
//            resultArea.append(schedule.toString() + "\n");
//        }
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new ScheduleManagementUI().setVisible(true);
//            }
//        });
//    }
//}


//
//package ui;
//
//import dao.RoomDAO;
//import dao.ScheduleDAO;
//import model.Room;
//import model.Schedule;
//
//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.List;
//
//public class ScheduleManagementUI extends JFrame {
//    private JTextField searchField;
//    private JTextField roomIdField;
//    private JTextField semesterField;
//    private JTextField classNameField;
//    private JTextArea resultArea;
//    private JTable scheduleTable;
//    private DefaultTableModel tableModel;
//    private ScheduleDAO scheduleDAO;
//    private RoomDAO roomDAO;
//
//    public ScheduleManagementUI() {
//        scheduleDAO = new ScheduleDAO();
//        roomDAO = new RoomDAO();
//
//        setTitle("Schedule Management");
//        setSize(800, 600);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
//        setLayout(new BorderLayout());
//
//        JPanel inputPanel = new JPanel();
//        inputPanel.setLayout(new GridLayout(3, 2));
//
//        inputPanel.add(new JLabel("Room ID:"));
//        roomIdField = new JTextField();
//        inputPanel.add(roomIdField);
//
//        inputPanel.add(new JLabel("Semester:"));
//        semesterField = new JTextField();
//        inputPanel.add(semesterField);
//
//        inputPanel.add(new JLabel("Class Name:"));
//        classNameField = new JTextField();
//        inputPanel.add(classNameField);
//
//        JPanel buttonPanel = new JPanel();
//        buttonPanel.setLayout(new GridLayout(1, 3));
//
//        JButton searchButton = new JButton("Search Available Rooms");
//        searchButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                searchAndDisplayAvailableRooms();
//            }
//        });
//        buttonPanel.add(searchButton);
//
//        JButton addButton = new JButton("Add Schedule");
//        addButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                addSchedule();
//            }
//        });
//        buttonPanel.add(addButton);
//
//        JButton deleteButton = new JButton("Delete Schedule");
//        deleteButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                deleteSchedule();
//            }
//        });
//        buttonPanel.add(deleteButton);
//
//        resultArea = new JTextArea(5, 20);
//        resultArea.setEditable(false);
//        JScrollPane resultScrollPane = new JScrollPane(resultArea);
//
//        tableModel = new DefaultTableModel(new Object[]{"Schedule ID", "Room ID", "Semester", "Class", "Occupied"}, 0);
//        scheduleTable = new JTable(tableModel);
//        JScrollPane tableScrollPane = new JScrollPane(scheduleTable);
//
//        JPanel topPanel = new JPanel(new BorderLayout());
//        topPanel.add(inputPanel, BorderLayout.NORTH);
//        topPanel.add(buttonPanel, BorderLayout.SOUTH);
//
//        add(topPanel, BorderLayout.NORTH);
//        add(resultScrollPane, BorderLayout.CENTER);
//        add(tableScrollPane, BorderLayout.SOUTH);
//
//        loadSchedules();
//    }
//
//    private void searchAndDisplayAvailableRooms() {
//        String searchTerm = searchField.getText();
//        List<Room> rooms = roomDAO.searchAvailableRooms(searchTerm);
//        resultArea.setText("");
//
//        for (Room room : rooms) {
//            resultArea.append(room.toString() + "\n");
//        }
//    }
//
//    private void addSchedule() {
//        try {
//            int roomId = Integer.parseInt(roomIdField.getText());
//            String semester = semesterField.getText();
//            String className = classNameField.getText();
//            boolean occupied = true; // Mark as occupied when adding a schedule
//
//            if (scheduleDAO.isRoomOccupied(roomId)) {
//                JOptionPane.showMessageDialog(this, "Room is already occupied.", "Error", JOptionPane.ERROR_MESSAGE);
//                return;
//            }
//
//            Schedule schedule = new Schedule(0, roomId, semester, className, occupied);
//            scheduleDAO.addSchedule(schedule);
//            loadSchedules();
//        } catch (NumberFormatException e) {
//            JOptionPane.showMessageDialog(this, "Invalid Room ID", "Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }
//
//    private void deleteSchedule() {
//        try {
//            int selectedRow = scheduleTable.getSelectedRow();
//            if (selectedRow == -1) {
//                JOptionPane.showMessageDialog(this, "Select a schedule to delete", "Error", JOptionPane.ERROR_MESSAGE);
//                return;
//            }
//
//            int scheduleId = (int) tableModel.getValueAt(selectedRow, 0);
//            scheduleDAO.deleteSchedule(scheduleId);
//            loadSchedules();
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, "Error deleting schedule", "Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }
//
//    private void loadSchedules() {
//        List<Schedule> schedules = scheduleDAO.getAllSchedules();
//        tableModel.setRowCount(0); // Clear the table
//
//        for (Schedule schedule : schedules) {
//            tableModel.addRow(new Object[]{schedule.getScheduleId(), schedule.getRoomId(), schedule.getSemester(), schedule.getClassName(), schedule.isOccupied()});
//        }
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new ScheduleManagementUI().setVisible(true);
//            }
//        });
//    }
//}
