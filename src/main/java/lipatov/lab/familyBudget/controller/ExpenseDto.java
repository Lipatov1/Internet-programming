package lipatov.lab.familyBudget.controller;

import lipatov.lab.familyBudget.model.Expense;
import java.sql.Date;

public class ExpenseDto {
    private final long id;
    private final Date transactionDate;
    private final int amount;
    private final String comment;
    private final Long familyMemberId;

    public ExpenseDto(Expense expense) {
        this.id = expense.getId();
        this.transactionDate = expense.getTransactionDate();
        this.amount = expense.getAmount();
        this.comment = expense.getComment();
        this.familyMemberId = expense.getFamilyMemberId();
    }

    public long getId() {
        return id;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public int getAmount() {
        return amount;
    }

    public String getComment() {
        return comment;
    }

    public Long getFamilyMemberId() {
        return familyMemberId;
    }
}