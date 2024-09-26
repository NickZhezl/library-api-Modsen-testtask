package dev.earlspilner.library.repository;

import dev.earlspilner.library.model.BookRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Nikita Zhelezko
 */
public interface BookRecordRepository extends JpaRepository<BookRecord, Integer> {
    Optional<BookRecord> findByBookId(Integer bookId);
}
