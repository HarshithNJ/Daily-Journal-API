package org.journal.journal_api.controller;

import org.journal.journal_api.service.journalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class journalEntryController {
    
    @Autowired
    journalEntryService service;
}
