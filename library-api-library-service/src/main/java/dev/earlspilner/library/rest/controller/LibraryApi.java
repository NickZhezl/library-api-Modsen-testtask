package dev.earlspilner.library.rest.controller;

import dev.earlspilner.library.dto.BookRecordDto;
import org.springframework.http.ResponseEntity;

/**
 * @author Nikita Zhelezko
 */
public interface LibraryApi {
    ResponseEntity<BookRecordDto> addBookRecord(BookRecordDto dto);
    ResponseEntity<BookRecordDto> getBookRecord(Integer bookId);
    ResponseEntity<BookRecordDto> updateBookRecord(Integer bookId, BookRecordDto dto);
    ResponseEntity<Void> deleteBookRecord(Integer bookId);
}
