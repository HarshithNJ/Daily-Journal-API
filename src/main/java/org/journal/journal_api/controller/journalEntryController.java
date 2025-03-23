package org.journal.journal_api.controller;

import java.util.List;

import org.journal.journal_api.dto.journalEntry;
import org.journal.journal_api.service.journalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class journalEntryController {
    
    @Autowired
    journalEntryService service;

    //TO Store the Journal information
    @PostMapping("/journals")
    public ResponseEntity<Object> saveJournal(@RequestBody journalEntry journal){
        return service.saveJournal(journal);
    }

    @PostMapping("/journals/multiple")
    public ResponseEntity<Object> saveMultipleJournals(@RequestBody List<journalEntry> journals){
        return service.saveMultipleJournals(journals);
    }





    //To fetch records
    @GetMapping("/journals")
    public ResponseEntity<Object> fetchAllJournals(){
        return service.fetchAllJournals();
    }

    @GetMapping("/journals/{title}")
    public ResponseEntity<Object> fetchByTitle(@PathVariable String title){
        return service.fetchByTitle(title);
    }

    @GetMapping("/journals/publisher/{publisher}")
    public ResponseEntity<Object> fetchByPublisher(@PathVariable String publisher){
        return service.fetchByPublisher(publisher);
    }

    @GetMapping("/journals/{date1}/{date2}")
    public ResponseEntity<Object> fetchByDateRange(@PathVariable String date1, @PathVariable String date2){
        return service.fetchByDateRange(date1, date2);
    }





    // To Delete a Record
    @DeleteMapping("/journals/{id}")
    public ResponseEntity<Object> deleteJournal(@PathVariable long id){
        return service.deleteJournal(id);
    }







    // To Update a Record
    @PatchMapping("/journals/{id}")
    public ResponseEntity<Object> updateJournal(@PathVariable long id, @RequestBody journalEntry journal){
        return service.updateJournal(id, journal);
    }
}
