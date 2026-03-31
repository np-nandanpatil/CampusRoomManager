Looking at the existing README against the actual codebase, I found several inaccuracies and missing information. Here's a corrected and more comprehensive README:

# CampusRoomManager

A Java Swing-based desktop application for managing classroom schedules and room occupancy in educational institutions. This project implements a complete CRUD system with MySQL database integration using a layered architecture pattern.

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Architecture](#architecture)
- [Technologies Used](#technologies-used)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Database Setup](#database-setup)
- [Configuration](#configuration)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [Contributing](#contributing)

## Overview

CampusRoomManager is designed to streamline classroom scheduling and room management for educational institutions. The application provides a simple graphical interface for administrators to manage rooms, create schedules, and search for available rooms based on specific criteria.

## Features

- **Room Management**: 
  - Add new rooms with name, capacity, and projector availability
  - View all existing rooms
  - Room data persistence with MySQL database

- **Schedule Management**: 
  - Create schedules linking rooms with semesters and classes
  - Track room occupancy status
  - Dropdown selection of available rooms

- **Room Search**: 
  - Search for available rooms by minimum capacity
  - Filter rooms by projector availability
  - Display search results with schedule information

- **Database Integration**: 
  - Full CRUD operations using DAO pattern
  - MySQL database with proper connection management
  - Prepared statements for SQL injection prevention

## Architecture

The application follows a layered architecture:
- **Presentation Layer**: Swing UI components (`ui` package)
- **Business Layer**: Model classes (`model` package)
- **Data Access Layer**: DAO classes for database operations (`dao` package)
- **Database Layer**: MySQL connection management (`db` package)

## Technologies Used

- **Java SE**: Core application development
- **MySQL**: Database management system
- **JDBC**: Database connectivity
- **Swing**: GUI framework
- **MySQL Connector/J 9.0.0**: MySQL JDBC driver

## Prerequisites

- Java Development Kit (JDK) 8 or higher
- MySQL Server 5.7 or higher
- MySQL Connector/J 9.0.0 (included in project)
- IDE (IntelliJ IDEA, Eclipse, or VS Code recommended)

## Installation

1. **Clone the repository**:
   ```bash
   git clone https://github.com/np-nandanpatil/CampusRoomManager.git
   cd CampusRoomManager
   ```

2. **Open the project**:
   - Import the project into your preferred Java IDE
   - Ensure the MySQL Connector/J library is properly loaded

3. **Verify dependencies**:
   - Check that `mysql-connector-j-9.0.0.jar` is in your classpath
   - The library configuration should be in `.idea/libraries/mysql_connector_j_9_0_0.xml`

## Database Setup

1. **Create the database**:
   ```sql
   CREATE DATABASE CollegeRoomManagementDB;
   USE CollegeRoomManagementDB;
   ```

2. **Run the SQL script**:
   - Execute the SQL script located at `DBcode/db.sql`
   - This will create the necessary `Room` and `Schedule` tables

3. **Expected table structure**:
   - **Room**: `room_id`, `name`, `capacity`, `has_projector`
   - **Schedule**: `schedule_id`, `room_id`, `semester`, `class`, `occupied`

## Configuration

1. **Update database credentials**:
   - Open `src/db/DBConnection.java`
   - Modify the following constants:
   ```java
   private static final String URL = "jdbc:mysql://localhost:3306/CollegeRoomManagementDB?serverTimezone=UTC";
   private static final String USER = "your_username";
   private static final String PASSWORD = "your_password";
   ```

2. **Ensure MySQL service is running** on your system

## Usage

### Running the Application

1. **Compile and run**:
   ```bash
   javac -cp ".:mysql-connector-j-9.0.0.jar" src/**/*.java
   java -cp ".:mysql-connector-j-9.0.0.jar:src" Main
   ```

2. **Or run from IDE**:
   - Execute `src/Main.java` or `src/ui/MainUI.java`

### Application Workflow

#### Main Menu
The application opens with three main options:
- Room Management
- Schedule Management  
- Search Available Rooms

#### Room Management
1. Click "Room Management"
2. Fill in room details:
   - **Room Name**: Room identifier/number
   - **Capacity**: Maximum occupancy
   - **Has Projector**: Check if projector available
3. Click "Add Room" to save

#### Schedule Management
1. Click "Schedule Management"
2. Configure schedule:
   - **Room**: Select from dropdown of existing rooms
   - **Semester**: Enter semester information
   - **Class**: Enter subject/class name
   - **Occupied**: Set current occupancy status
3. Click "Add Schedule" to save

#### Searching Rooms
1. Click "Search Available Rooms"
2. Set search criteria:
   - **Minimum Capacity**: Required room size
   - **Has Projector**: Filter by projector availability
3. Click "Search" to view results
4. Results display room schedules matching criteria

## Project Structure

```
CampusRoomManager/
├── .idea/                          # IntelliJ IDEA configuration
├── DBcode/
│   └── db.sql                      # Database schema script
├── src/
│   ├── Main.java                   # Application entry point
│   ├── dao/
│   │   ├── RoomDAO.java           # Room database operations
│   │   └── ScheduleDAO.java       # Schedule database operations
│   ├── db/
│   │   └── DBConnection.java      # Database connection utility
│   ├── model/
│   │   ├── Room.java              # Room entity class
│   │   └── Schedule.java          # Schedule entity class
│   └── ui/
│       ├── MainUI.java            # Main application window
│       ├── RoomManagementUI.java  # Room management interface
│       ├── ScheduleManagementUI.java # Schedule management interface
│       └── SearchUI.java          # Room search interface
├── CampusRoomManager.iml          # IntelliJ module file
└── README.md
```

## Contributing

This project was developed as a learning exercise for Advanced Java concepts. Contributions are welcome to improve:

- Code efficiency and best practices
- Additional features (room updates, schedule deletion, etc.)
- UI/UX improvements
- Error handling and validation
- Unit tests implementation

To contribute:
1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Submit a pull request

## Notes

- The application currently supports basic CRUD operations
- Some advanced features like room updates and schedule deletion may need implementation
- Input validation could be enhanced for better user experience
- Consider implementing connection pooling for production use