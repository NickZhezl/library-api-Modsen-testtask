package dev.earlspilner.library.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.earlspilner.library.model.BookStatus;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

/**
 * @author Nikita Zhelezko
 */
@JsonInclude(NON_NULL)
public record BookRecordDto(
        Integer bookId,
        BookStatus status
) { }
