package dev.earlspilner.loans.service;

import dev.earlspilner.loans.dto.BookRecordDto;
import dev.earlspilner.loans.dto.LoanDto;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author Nikita Zhelezko
 */
public interface LoanService {
    LoanDto addLoan(LoanDto dto, HttpServletRequest request);
    LoanDto getLoan(Integer loanId);
    LoanDto returnBook(Integer bookId, HttpServletRequest request);
}
