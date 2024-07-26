package ui;

import javax.swing.*;
import java.awt.*;

public class MainUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("College Room Management System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setLayout(new GridLayout(3, 1));

            JButton roomManagementButton = new JButton("Room Management");
            roomManagementButton.addActionListener(e -> new RoomManagementUI().setVisible(true));

            JButton scheduleManagementButton = new JButton("Schedule Management");
            scheduleManagementButton.addActionListener(e -> new ScheduleManagementUI().setVisible(true));

            JButton searchButton = new JButton("Search Available Rooms");
            searchButton.addActionListener(e -> new SearchUI().setVisible(true));

            frame.add(roomManagementButton);
            frame.add(scheduleManagementButton);
            frame.add(searchButton);

            frame.setVisible(true);
        });
    }
}
