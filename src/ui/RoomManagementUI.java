package ui;

import dao.RoomDAO;
import model.Room;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class RoomManagementUI extends JFrame {
    private JTextField nameField;
    private JTextField capacityField;
    private JCheckBox projectorCheckBox;
    private RoomDAO roomDAO;

    public RoomManagementUI() {
        roomDAO = new RoomDAO();
        setTitle("Room Management");
        setSize(400, 300);
        setLayout(new GridLayout(5, 2));

        add(new JLabel("Room Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Capacity:"));
        capacityField = new JTextField();
        add(capacityField);

        add(new JLabel("Has Projector:"));
        projectorCheckBox = new JCheckBox();
        add(projectorCheckBox);

        JButton addButton = new JButton("Add Room");
        addButton.addActionListener(e -> addRoom());
        add(addButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose());
        add(cancelButton);
    }

    private void addRoom() {
        String name = nameField.getText();
        int capacity = Integer.parseInt(capacityField.getText());
        boolean hasProjector = projectorCheckBox.isSelected();

        Room room = new Room(0, name, capacity, hasProjector);
        try {
            roomDAO.addRoom(room);
            JOptionPane.showMessageDialog(this, "Room added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding room: " + e.getMessage());
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
//import java.sql.SQLException;
//import java.util.List;
//
//public class RoomManagementUI extends JFrame {
//    private JTextArea roomDisplayArea;
//    private RoomDAO roomDAO;
//
//    public RoomManagementUI() {
//        roomDAO = new RoomDAO();
//
//        setTitle("Room Management");
//        setSize(500, 400);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
//
//        roomDisplayArea = new JTextArea();
//        add(new JScrollPane(roomDisplayArea), BorderLayout.CENTER);
//
//        JButton fetchRoomsButton = new JButton("Fetch Rooms");
//        fetchRoomsButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    fetchAndDisplayRooms();
//                } catch (SQLException ex) {
//                    throw new RuntimeException(ex);
//                }
//            }
//        });
//
//        add(fetchRoomsButton, BorderLayout.SOUTH);
//    }
//
//    private void fetchAndDisplayRooms() throws SQLException {
//        List<Room> rooms = roomDAO.getAllRooms();
//        roomDisplayArea.setText(""); // Clear previous content
//
//        for (Room room : rooms) {
//            roomDisplayArea.append(room.toString() + "\n");
//        }
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new RoomManagementUI().setVisible(true);
//            }
//        });
//    }
//}
