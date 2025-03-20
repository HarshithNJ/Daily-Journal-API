package org.journal.journal_api.service;

import org.journal.journal_api.repository.journalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class journalEntryService {
    
    @Autowired
    journalEntryRepository repository;
}
