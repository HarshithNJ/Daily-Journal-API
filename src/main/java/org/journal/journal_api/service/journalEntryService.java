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

    public ResponseEntity<Object> fetchByTitle(String title) {
        Optional<journalEntry> entry = repository.findByTitle(title);

        if(entry.isPresent()){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("success", "Journal Found");
            map.put("Journal Data", entry.get());

            return new ResponseEntity<Object>(map, HttpStatus.FOUND);
        }else{
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("error", "No Journal Data found with title: " + title);

            return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Object> fetchByPublisher(String publisher) {
        List<journalEntry> entry = repository.findByPublisher(publisher);

        if(entry.isEmpty()){
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("error", "No Journals Data found by publisher: " + publisher);

            return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
        }else{
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("success", "Journals found by publisher: ");
            map.put("Journal Data", entry);

            return new ResponseEntity<Object>(map, HttpStatus.FOUND);
        }
    }

    public ResponseEntity<Object> fetchByDateRange(String date1, String date2) {
        List<journalEntry> entry = repository.findByDateBetween(date1, date2);

        if(entry.isEmpty()){
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("error", "No Journals Data found between " + date1 + " and " + date2);

            return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
        }else{
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("success", "Journals found between " + date1 + " and " + date2);
            map.put("Journal Data", entry);

            return new ResponseEntity<Object>(map, HttpStatus.FOUND);
        }
    }










    
    public ResponseEntity<Object> deleteJournal(long id) {
        Optional<journalEntry> entry = repository.findById(id);

        if(entry.isPresent()){
            repository.deleteById(id);
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("success", "Journal deleted successfully");

            return new ResponseEntity<Object>(map, HttpStatus.OK);
        }else{
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("error", "No Journal found with id: " + id);

            return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
        }
    }











    public ResponseEntity<Object> updateJournal(long id, journalEntry journal) {
        Optional<journalEntry> entry = repository.findById(id);

        if(entry.isPresent()){
            journalEntry je = entry.get();

            if(journal.getTitle() != null)
                je.setTitle(journal.getTitle());

            if(journal.getContent() != null)
                je.setContent(journal.getContent());

            if(journal.getPublisher() != null)
                je.setPublisher(journal.getPublisher());

            if(journal.getDate() != null)
                je.setDate(journal.getDate());

            repository.save(je);

            Map<String,Object> map = new HashMap<String, Object>();
            map.put("success", "Journal updated successfully");
            map.put("Journal Data", je);

            return new ResponseEntity<Object>(map, HttpStatus.OK);
        }else{
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("error", "No Journal found with id: " + id);

            return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
        }
    }
}
