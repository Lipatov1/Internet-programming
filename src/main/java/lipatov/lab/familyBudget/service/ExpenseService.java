package lipatov.lab.familyBudget.service;

import lipatov.lab.familyBudget.repository.FamilyMemberRepository;
import org.springframework.transaction.annotation.Transactional;
import lipatov.lab.familyBudget.repository.ExpenseRepository;
import lipatov.lab.util.validation.ValidatorUtil;
import org.springframework.stereotype.Service;
import lipatov.lab.familyBudget.model.Expense;
import java.util.Optional;
import java.util.List;
import java.sql.Date;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final FamilyMemberRepository familyMemberRepository;
    private final ValidatorUtil validatorUtil;

    public ExpenseService(ExpenseRepository expenseRepository, FamilyMemberRepository familyMemberRepository, ValidatorUtil validatorUtil) {
        this.familyMemberRepository = familyMemberRepository;
        this.expenseRepository = expenseRepository;
        this.validatorUtil = validatorUtil;
    }

    @Transactional
    public Expense addExpense(Date transactionDate, int amount, String comment, Long familyMemberId) {
        final Expense expense = new Expense(transactionDate, amount, comment, familyMemberRepository.findById(familyMemberId).get());
        validatorUtil.validate(expense);
        return expenseRepository.save(expense);
    }

    @Transactional(readOnly = true)
    public Expense findExpense(Long id) {
        final Optional<Expense> expense = expenseRepository.findById(id);
        return expense.orElseThrow(() -> new ExpenseNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public List<Expense> findAllExpenses() {
        return expenseRepository.findAll();
    }

    @Transactional
    public Expense updateExpense(Long id, Date transactionDate, int amount, String comment, Long familyMemberId) {
        final Expense currentExpense = findExpense(id);
        currentExpense.setTransactionDate(transactionDate);
        currentExpense.setAmount(amount);
        currentExpense.setComment(comment);
        currentExpense.setFamilyMember(familyMemberRepository.findById(familyMemberId).get());
        validatorUtil.validate(currentExpense);
        return expenseRepository.save(currentExpense);
    }

    @Transactional
    public Expense deleteExpense(Long id) {
        final Expense expense = findExpense(id);
        expenseRepository.delete(expense);
        return expense;
    }

    @Transactional
    public void deleteAllExpenses() {
        expenseRepository.deleteAll();
    }
}