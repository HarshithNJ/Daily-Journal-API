package org.journal.journal_api.controller;

import java.util.List;

import org.journal.journal_api.dto.journalEntry;
import org.journal.journal_api.service.journalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
}
