package dev.earlspilner.loans.repository;

import dev.earlspilner.loans.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Nikita Zhelezko
 */
public interface LoanRepository extends JpaRepository<Loan, Integer> {
    Optional<Loan> findByBookIdAndReturnedAtIsNull(Integer bookId);
}
