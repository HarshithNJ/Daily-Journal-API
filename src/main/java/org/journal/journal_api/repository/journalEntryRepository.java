package org.journal.journal_api.repository;

import org.journal.journal_api.dto.journalEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface journalEntryRepository extends JpaRepository<journalEntry, Long> {

    boolean existsByTitle(String title);

}
