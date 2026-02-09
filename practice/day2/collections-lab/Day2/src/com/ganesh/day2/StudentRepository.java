package com.ganesh.day2;

import java.util.*;

public class StudentRepository {
    private final Map<Integer, Student> store = new HashMap<>();

    public boolean existsById(int id) {
        return store.containsKey(id);
    }

    public void save(Student s) {
        store.put(s.getId(), s);
    }

    public Student findById(int id) {
        return store.get(id);
    }

    public Student deleteById(int id) {
        return store.remove(id);
    }

    public List<Student> findAll() {
        return new ArrayList<>(store.values());
    }

    public List<Student> findByNameContains(String q) {
        String query = q.toLowerCase();
        List<Student> result = new ArrayList<>();
        for (Student s : store.values()) {
            if (s.getName().toLowerCase().contains(query)) {
                result.add(s);
            }
        }
        return result;
    }

    public Set<String> getUniqueCourses() {
        Set<String> courses = new HashSet<>();
        for (Student s : store.values()) {
            courses.add(s.getCourse());
        }
        return courses;
    }
}
