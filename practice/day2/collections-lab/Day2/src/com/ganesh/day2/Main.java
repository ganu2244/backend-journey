package com.ganesh.day2;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {
    static void main() {
        StudentService service = new StudentService();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Student Registry ---");
            System.out.println("1) Add Student");
            System.out.println("2) Update Student");
            System.out.println("3) Delete Student");
            System.out.println("4) Get Student by ID");
            System.out.println("5) List All");
            System.out.println("6) Search by Name");
            System.out.println("7) Unique Courses");
            System.out.println("0) Exit");
            System.out.print("Choose: ");

            int choice = Integer.parseInt(sc.nextLine());

            try {
                if (choice == 1) {
                    System.out.print("id: "); int id = Integer.parseInt(sc.nextLine());
                    System.out.print("name: "); String name = sc.nextLine();
                    System.out.print("age: "); int age = Integer.parseInt(sc.nextLine());
                    System.out.print("course: "); String course = sc.nextLine();
                    service.addStudent(new Student(id, name, age, course));
                    System.out.println("Added.");
                } else if (choice == 2) {
                    System.out.print("id: "); int id = Integer.parseInt(sc.nextLine());
                    System.out.print("name: "); String name = sc.nextLine();
                    System.out.print("age: "); int age = Integer.parseInt(sc.nextLine());
                    System.out.print("course: "); String course = sc.nextLine();
                    service.updateStudent(id, name, age, course);
                    System.out.println("Updated.");
                } else if (choice == 3) {
                    System.out.print("id: "); int id = Integer.parseInt(sc.nextLine());
                    service.deleteStudent(id);
                    System.out.println("Deleted.");
                } else if (choice == 4) {
                    System.out.print("id: "); int id = Integer.parseInt(sc.nextLine());
                    System.out.println(service.getStudent(id));
                } else if (choice == 5) {
                    List<Student> all = service.listStudents();
                    if (all.isEmpty()) System.out.println("(empty)");
                    else all.forEach(System.out::println);
                } else if (choice == 6) {
                    System.out.print("query: ");
                    String q = sc.nextLine();
                    List<Student> found = service.searchByName(q);
                    if (found.isEmpty()) System.out.println("(no matches)");
                    else found.forEach(System.out::println);
                } else if (choice == 7) {
                    Set<String> courses = service.uniqueCourses();
                    if (courses.isEmpty()) System.out.println("(empty)");
                    else courses.forEach(System.out::println);
                } else if (choice == 0) {
                    break;
                } else {
                    System.out.println("Invalid choice");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        sc.close();
        System.out.println("Bye!");
    }
}
