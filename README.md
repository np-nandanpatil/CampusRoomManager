
# CampusRoomManager

CampusRoomManager is a simple and user-friendly application designed to manage classroom schedules and room occupancy.

## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Introduction
Scheduling classrooms and managing room occupancy can be a headache for educational institutions. CampusRoomManager aims to make this process easy. With this tool, you can add, delete, and search for schedules.

## Features
- **Room Management**: Add, update, and delete rooms with details like room ID, name, capacity, and whether it has a projector.
- **Schedule Management**: Add new schedules, delete existing ones, and search for available rooms. Prevents scheduling conflicts by checking room occupancy status.
- **User Interface**: Simple and intuitive interface built with Swing.

## Technologies Used
- **Java**: For both front-end and back-end development.
- **MySQL**: For database management.
- **Swing**: For the graphical user interface.
- **JDBC**: For connecting to the MySQL database.

## Installation
To get started with CampusRoomManager, follow these steps:

1. **Clone the repository**:
    ```sh
    git clone https://github.com/np-nandanpatil/CampusRoomManager.git
    ```
2. **Open the project in your preferred Java IDE** (I'd prefer IntelliJ IDEA/VS code).

3. **Set up the MySQL database**:
    - Create a database named `collegeroommanagementdb`.
    - Import the provided SQL script to set up the necessary tables.

4. **Update the database connection settings**:
    - Open the `DBConnection.java` file.
    - Update the database URL, username, and password to match your MySQL setup.

5. **Build and run the project**:
    - Compile and run the `Main.java` or `MainUI.java` to start the application.

## Usage
Once the application is running, you can:
- **Manage Rooms**: Add, update, and delete room details.
- **Manage Schedules**: Add new schedules, delete existing ones, and search for available rooms.

### Adding a Room
1. Click on Room Management.
2. Enter the room details (Room name(Room number), capacity, projector availability).
3. Click "Add Room".

### Adding a Schedule
1. Click on Schedule Management.
2. Enter the schedule details
    * Select room
    * Enter semester, class name(subject name)
3. Click "Add Schedule".

### Searching for Available Rooms
1. Click on Search Available Rooms.
2. Enter your search criteria in the search field (Minimum capacity, projector needed or not).
3. Click "Search".

## Contributing
If you'd like to help improve CampusRoomManager, please fork the repository and create a pull request.<br>
**Well as a student this was all that I could come up with while I built this along with learning these technologies for the first time, so it is not so efficient, there are a lot things that can be upgraded in this project and It'd be great if you like to do thosse upgrades**
