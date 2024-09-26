package dev.earlspilner.library.service;

import dev.earlspilner.library.dto.BookRecordDto;

/**
 * @author Nikita Zhelezko
 */
public interface LibraryService {
    BookRecordDto addBookRecord(BookRecordDto dto);
    BookRecordDto getBookRecord(Integer id);
    BookRecordDto updateBookRecord(Integer bookId, BookRecordDto dto);
    void deleteBookRecord(Integer bookId);
}
