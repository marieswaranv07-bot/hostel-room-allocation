import java.io.*;
import java.util.*;

class Room implements Serializable {
    int roomNumber;
    String type;
    int capacity;
    boolean isAllocated;

    Room(int roomNumber, String type, int capacity) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.capacity = capacity;
        this.isAllocated = false;
    }

    @Override
    public String toString() {
        return "Room " + roomNumber + " [" + type + ", capacity=" + capacity +
                ", allocated=" + isAllocated + "]";
    }
}

class Student implements Serializable {
    String name;
    String rollNumber;
    int year;
    String course;
    Room allocatedRoom;

    Student(String name, String rollNumber, int year, String course) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.year = year;
        this.course = course;
    }

    @Override
    public String toString() {
        String roomInfo = (allocatedRoom != null) ? "Room " + allocatedRoom.roomNumber : "Not Allocated";
        return name + " (" + rollNumber + ", Year " + year + ", " + course + ") -> " + roomInfo;
    }
}

class HostelSystem {
    List<Room> rooms = new ArrayList<>();
    List<Student> students = new ArrayList<>();

    void addRoom(Room room) {
        rooms.add(room);
    }

    void addStudent(Student student) {
        students.add(student);
    }

    void allocateRooms() {
        for (Student s : students) {
            if (s.allocatedRoom == null) {
                for (Room r : rooms) {
                    if (!r.isAllocated) {
                        s.allocatedRoom = r;
                        r.isAllocated = true;
                        break;
                    }
                }
            }
        }
    }

    void generateReport() {
        System.out.println("\n--- Occupancy Report ---");
        for (Room r : rooms) {
            System.out.println(r);
        }
        System.out.println("\n--- Student Allocation ---");
        for (Student s : students) {
            System.out.println(s);
        }
    }
}

public class HostelRoomAllocationSystem3 {
    public static void main(String[] args) {
        HostelSystem system = new HostelSystem();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Hostel Room Allocation Menu =====");
            System.out.println("1. Add Room");
            System.out.println("2. Add Student");
            System.out.println("3. Allocate Rooms");
            System.out.println("4. Show Report");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Room Number: ");
                    int num = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Type (Single/Double): ");
                    String type = sc.nextLine();
                    System.out.print("Enter Capacity: ");
                    int cap = sc.nextInt();
                    system.addRoom(new Room(num, type, cap));
                    System.out.println("Room added successfully!");
                    break;

                case 2:
                    System.out.print("Enter Student Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Roll Number: ");
                    String roll = sc.nextLine();
                    System.out.print("Enter Year: ");
                    int year = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Course: ");
                    String course = sc.nextLine();
                    system.addStudent(new Student(name, roll, year, course));
                    System.out.println("Student added successfully!");
                    break;

                case 3:
                    system.allocateRooms();
                    System.out.println("Rooms allocated successfully!");
                    break;

                case 4:
                    system.generateReport();
                    break;

                case 5:
                    System.out.println("Exiting... Goodbye!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
