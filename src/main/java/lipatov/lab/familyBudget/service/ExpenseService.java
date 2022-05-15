package lipatov.lab.familyBudget.service;

import org.springframework.transaction.annotation.Transactional;
import lipatov.lab.familyBudget.model.FamilyMember;
import javax.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import lipatov.lab.familyBudget.model.Expense;
import javax.persistence.PersistenceContext;
import org.springframework.util.StringUtils;
import javax.persistence.EntityManager;
import java.util.List;
import java.sql.Date;

@Service
public class ExpenseService {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Expense addExpense(Date transactionDate, int amount, String comment, Long familyMemberId) {
        if (transactionDate == null || amount <= 0 || !StringUtils.hasText(comment) || em.find(FamilyMember.class, familyMemberId) == null) {
            throw new IllegalArgumentException("One or more parameters are entered incorrectly");
        }
        final Expense expense = new Expense(transactionDate, amount, comment, em.find(FamilyMember.class, familyMemberId));
        em.persist(expense);
        return expense;
    }

    @Transactional(readOnly = true)
    public Expense findExpense(Long id) {
        final Expense expense = em.find(Expense.class, id);
        if (expense == null) {
            throw new EntityNotFoundException(String.format("Expense with id [%s] is not found", id));
        }
        return expense;
    }

    @Transactional(readOnly = true)
    public List<Expense> findAllExpenses() {
        return em.createQuery("select s from Expense s", Expense.class).getResultList();
    }

    @Transactional
    public Expense updateExpense(Long id, Date transactionDate, int amount, String comment, Long familyMemberId) {
        if (transactionDate == null || amount <= 0 || !StringUtils.hasText(comment) || em.find(FamilyMember.class, familyMemberId) == null) {
            throw new IllegalArgumentException("One or more parameters are entered incorrectly");
        }
        final Expense currentExpense = findExpense(id);
        currentExpense.setTransactionDate(transactionDate);
        currentExpense.setAmount(amount);
        currentExpense.setComment(comment);
        currentExpense.setFamilyMember(em.find(FamilyMember.class, familyMemberId));
        return em.merge(currentExpense);
    }

    @Transactional
    public Expense deleteExpense(Long id) {
        final Expense expense = findExpense(id);
        em.remove(expense);
        return expense;
    }

    @Transactional
    public void deleteAllExpenses() {
        em.createQuery("delete from Expense").executeUpdate();
    }
}