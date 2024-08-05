import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class StudentManager {
    private List<Student> students;
    private Scanner scanner;
    private static final String FILE_NAME = "students.txt";

    public StudentManager() {
        students = new ArrayList<>();
        scanner = new Scanner(System.in);
        loadStudentsFromFile();
    }

    public void addStudent() {
        System.out.println("Enter student code: ");
        String code = scanner.nextLine();
        System.out.println("Enter student name: ");
        String name = scanner.nextLine();
        System.out.println("Enter age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("Enter gender: ");
        String gender = scanner.nextLine();
        System.out.println("Enter address: ");
        String address = scanner.nextLine();
        System.out.println("Enter grade: ");
        float grade = scanner.nextFloat();
        scanner.nextLine(); // Consume newline

        Student student = new Student(code, name, age, gender, address, grade);
        students.add(student);
        saveStudentsToFile();
        System.out.println("Student added successfully!");
    }

    public void removeStudent() {
        System.out.println("Enter student code to remove: ");
        String code = scanner.nextLine();
        students.removeIf(student -> student.getCode().equals(code));
        saveStudentsToFile();
        System.out.println("Student removed successfully!");
    }

    public void sortStudentsByGradeDescending() {
        students.sort(Comparator.comparingDouble(Student::getGrade).reversed());
        System.out.println("Students sorted by grade in descending order:");
        students.forEach(System.out::println);
    }

    public void findStudentByCodeOrName() {
        System.out.println("Enter student code or name to find: ");
        String input = scanner.nextLine();
        students.stream()
                .filter(student -> student.getCode().equals(input) || student.getName().equalsIgnoreCase(input))
                .forEach(System.out::println);
    }

    public void findStudentsByMinimumGrade() {
        System.out.println("Enter minimum grade: ");
        float minGrade = scanner.nextFloat();
        scanner.nextLine(); // Consume newline

        students.stream()
                .filter(student -> student.getGrade() >= minGrade)
                .forEach(System.out::println);
    }

    private void saveStudentsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Student student : students) {
                writer.write(student.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving students to file: " + e.getMessage());
        }
    }

    private void loadStudentsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Student student = Student.fromFileString(line);
                students.add(student);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while loading students from file: " + e.getMessage());
        }
    }

    public void displayMenu() {
        while (true) {
            System.out.println("\nStudent Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Sort Students by Grade (Descending)");
            System.out.println("4. Find Student by Code or Name");
            System.out.println("5. Find Students by Minimum Grade");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> removeStudent();
                case 3 -> sortStudentsByGradeDescending();
                case 4 -> findStudentByCodeOrName();
                case 5 -> findStudentsByMinimumGrade();
                case 6 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        StudentManager manager = new StudentManager();
        manager.displayMenu();
    }
}
