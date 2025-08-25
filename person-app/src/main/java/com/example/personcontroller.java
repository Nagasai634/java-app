package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping
    public ResponseEntity<String> createPerson(@RequestBody Person person) {
        try {
            personService.savePerson(person);
            return ResponseEntity.ok("Person saved successfully");
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Error saving person: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Person>> getAllPersons() {
        try {
            List<Person> persons = personService.getAllPersons();
            return ResponseEntity.ok(persons);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity<Person> getPersonByName(@PathVariable String name) {
        try {
            return personService.getPersonByName(name)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }
}