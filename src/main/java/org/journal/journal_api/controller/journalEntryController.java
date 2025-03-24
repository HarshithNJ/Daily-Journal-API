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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Journal API", description = "Journal API Information Management")
public class journalEntryController {
    
    @Autowired
    journalEntryService service;

    //TO Store the Journal information
    @PostMapping("/journals")
    @Operation(summary = "Save Journal Information", description = "Save Journal Information")
    @ApiResponse(responseCode = "201", description = "Journal Information Saved Successfully")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    public ResponseEntity<Object> saveJournal(@RequestBody journalEntry journal){
        return service.saveJournal(journal);
    }

    @PostMapping("/journals/multiple")
    @Operation(summary = "Save Multiple Journals Information", description = "Save Multiple Journals Information")
    @ApiResponse(responseCode = "201", description = "Journals Information Saved Successfully")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    public ResponseEntity<Object> saveMultipleJournals(@RequestBody List<journalEntry> journals){
        return service.saveMultipleJournals(journals);
    }





    //To fetch records
    @GetMapping("/journals")
    @Operation(summary = "Fetch All Journals Information", description = "Fetch All Journals Information")
    @ApiResponse(responseCode = "302", description = "Journals Information Fetched Successfully")
    @ApiResponse(responseCode = "404", description = "Database is Empty")
    public ResponseEntity<Object> fetchAllJournals(){
        return service.fetchAllJournals();
    }

    @GetMapping("/journals/{title}")
    @Operation(summary = "Fetch Journal Information By Title", description = "Fetch Journal Information By Title")
    @ApiResponse(responseCode = "302", description = "Journal Information Fetched Successfully")
    @ApiResponse(responseCode = "404", description = "Journal Information Not Found by Title")
    public ResponseEntity<Object> fetchByTitle(@PathVariable String title){
        return service.fetchByTitle(title);
    }

    @GetMapping("/journals/publisher/{publisher}")
    @Operation(summary = "Fetch Journal Information By Publisher", description = "Fetch Journal Information By Publisher")
    @ApiResponse(responseCode = "302", description = "Journal Information Fetched Successfully")
    @ApiResponse(responseCode = "404", description = "Journal Information Not Found by Publisher")
    public ResponseEntity<Object> fetchByPublisher(@PathVariable String publisher){
        return service.fetchByPublisher(publisher);
    }

    @GetMapping("/journals/{date1}/{date2}")
    @Operation(summary = "Fetch Journal Information By Date Range", description = "Fetch Journal Information By Date Range")
    @ApiResponse(responseCode = "302", description = "Journal Information Fetched Successfully")
    @ApiResponse(responseCode = "404", description = "Journal Information Not Found by Date Range")
    public ResponseEntity<Object> fetchByDateRange(@PathVariable String date1, @PathVariable String date2){
        return service.fetchByDateRange(date1, date2);
    }





    // To Delete a Record
    @DeleteMapping("/journals/{id}")
    @Operation(summary = "Delete Journal Information", description = "Delete Journal Information")
    @ApiResponse(responseCode = "200", description = "Journal Information Deleted Successfully")
    @ApiResponse(responseCode = "404", description = "Journal Information Not Found")
    public ResponseEntity<Object> deleteJournal(@PathVariable long id){
        return service.deleteJournal(id);
    }







    // To Update a Record
    @PatchMapping("/journals/{id}")
    @Operation(summary = "Update Journal Information", description = "Update Journal Information")
    @ApiResponse(responseCode = "200", description = "Journal Information Updated Successfully")
    @ApiResponse(responseCode = "404", description = "Journal Information Not Found")
    public ResponseEntity<Object> updateJournal(@PathVariable long id, @RequestBody journalEntry journal){
        return service.updateJournal(id, journal);
    }
}
