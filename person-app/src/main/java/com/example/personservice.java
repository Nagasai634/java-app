package com.example.demo;

import org.springframework.stereotype.Service;
import java.io.*;
import java.nio.file.*;
import java.util.*;

@Service
public class PersonService {
    private static final String DATA_DIR = "/nsv";
    private static final String FILE_EXTENSION = ".txt";

    public PersonService() {
        createDataDirectory();
    }

    private void createDataDirectory() {
        try {
            Files.createDirectories(Paths.get(DATA_DIR));
        } catch (IOException e) {
            System.err.println("Error creating directory: " + e.getMessage());
        }
    }

    public void savePerson(Person person) throws IOException {
        String filename = person.getName().toLowerCase().replace(" ", "_") + FILE_EXTENSION;
        Path filePath = Paths.get(DATA_DIR, filename);
        
        Files.write(filePath, Collections.singleton(person.toString()));
    }

    public List<Person> getAllPersons() throws IOException {
        List<Person> persons = new ArrayList<>();
        File directory = new File(DATA_DIR);
        
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles((dir, name) -> name.endsWith(FILE_EXTENSION));
            
            if (files != null) {
                for (File file : files) {
                    try {
                        String content = new String(Files.readAllBytes(file.toPath()));
                        Person person = Person.fromString(content.trim());
                        if (person != null) {
                            persons.add(person);
                        }
                    } catch (IOException e) {
                        System.err.println("Error reading file: " + file.getName());
                    }
                }
            }
        }
        return persons;
    }

    public Optional<Person> getPersonByName(String name) throws IOException {
        String filename = name.toLowerCase().replace(" ", "_") + FILE_EXTENSION;
        Path filePath = Paths.get(DATA_DIR, filename);
        
        if (Files.exists(filePath)) {
            String content = new String(Files.readAllBytes(filePath));
            return Optional.ofNullable(Person.fromString(content.trim()));
        }
        return Optional.empty();
    }
}