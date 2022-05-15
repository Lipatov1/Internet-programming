package lipatov.lab.familyBudget.service;

public class ExpenseNotFoundException extends RuntimeException {
    public ExpenseNotFoundException(Long id) {
        super(String.format("Expense with id [%s] is not found", id));
    }
}