package org.journal.journal_api.repository;

import java.util.List;
import java.util.Optional;

import org.journal.journal_api.dto.journalEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface journalEntryRepository extends JpaRepository<journalEntry, Long> {

    boolean existsByTitle(String title);

    Optional<journalEntry> findByTitle(String title);

    List<journalEntry> findByPublisher(String publisher);

    List<journalEntry> findByDateBetween(String date1, String date2);

}
