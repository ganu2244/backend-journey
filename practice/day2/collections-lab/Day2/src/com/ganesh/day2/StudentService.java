package com.ganesh.day2;

import java.util.List;
import java.util.Set;

public class StudentService {
    private final StudentRepository repo = new StudentRepository();

    public void addStudent(Student s) {
        if (repo.existsById(s.getId())) {
            throw new IllegalArgumentException("Student id already exists: " + s.getId());
        }
        validate(s);
        repo.save(s);
    }

    public void updateStudent(int id, String name, int age, String course) {
        Student existing = repo.findById(id);
        if (existing == null) {
            throw new IllegalArgumentException("Student not found: " + id);
        }
        existing.setName(name);
        existing.setAge(age);
        existing.setCourse(course);
        validate(existing);
        repo.save(existing);
    }

    public Student getStudent(int id) {
        Student s = repo.findById(id);
        if (s == null) throw new IllegalArgumentException("Student not found: " + id);
        return s;
    }

    public void deleteStudent(int id) {
        Student removed = repo.deleteById(id);
        if (removed == null) throw new IllegalArgumentException("Student not found: " + id);
    }

    public List<Student> listStudents() {
        return repo.findAll();
    }

    public List<Student> searchByName(String q) {
        return repo.findByNameContains(q);
    }

    public Set<String> uniqueCourses() {
        return repo.getUniqueCourses();
    }

    private void validate(Student s) {
        if (s.getName() == null || s.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (s.getAge() < 1 || s.getAge() > 120) {
            throw new IllegalArgumentException("Age must be between 1 and 120");
        }
        if (s.getCourse() == null || s.getCourse().trim().isEmpty()) {
            throw new IllegalArgumentException("Course cannot be empty");
        }
    }
}
