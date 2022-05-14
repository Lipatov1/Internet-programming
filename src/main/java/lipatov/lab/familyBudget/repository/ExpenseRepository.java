package lipatov.lab.familyBudget.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import lipatov.lab.familyBudget.model.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}