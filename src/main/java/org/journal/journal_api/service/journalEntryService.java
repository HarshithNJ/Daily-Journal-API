package org.journal.journal_api.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.journal.journal_api.dto.journalEntry;
import org.journal.journal_api.repository.journalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class journalEntryService {
    
    @Autowired
    journalEntryRepository repository;

    public ResponseEntity<Object> saveJournal(journalEntry journal) {
        if(repository.existsByTitle(journal.getTitle())){
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("error", "Title already exists");

            return new ResponseEntity<Object>(map, HttpStatus.BAD_REQUEST);
        }else{
            repository.save(journal);

            Map<String,Object> map = new HashMap<String, Object>();
            map.put("message", "Journal saved successfully");
            map.put("Journal Data", journal);

            return new ResponseEntity<Object>(map, HttpStatus.CREATED);
        }
    }

    public ResponseEntity<Object> saveMultipleJournals(List<journalEntry> journals) {
        for(journalEntry journal: journals){
            if(repository.existsByTitle(journal.getTitle())){
                Map<String,Object> map = new HashMap<String, Object>();
                map.put("error", "Title already exists");
    
                return new ResponseEntity<Object>(map, HttpStatus.BAD_REQUEST);
            }
        }
        repository.saveAll(journals);

        Map<String,Object> map = new HashMap<String, Object>();
        map.put("success", "Journals saved successfully");
        map.put("Journal Data", journals);

        return new ResponseEntity<Object>(map, HttpStatus.CREATED);
    }












    public ResponseEntity<Object> fetchAllJournals() {
        List<journalEntry> entry = repository.findAll();

        if(entry.isEmpty()){
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("error", "No Journals Data found");

            return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
        }else{
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("success", "Journals found successfully");
            map.put("Journal Data", entry);

            return new ResponseEntity<Object>(map, HttpStatus.FOUND);
        }
    }

    
}
