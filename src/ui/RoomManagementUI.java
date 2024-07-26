package ui;

import dao.RoomDAO;
import model.Room;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class RoomManagementUI extends JFrame {
    private final JTextField nameField;
    private final JTextField capacityField;
    private final JCheckBox projectorCheckBox;
    private final RoomDAO roomDAO;

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
