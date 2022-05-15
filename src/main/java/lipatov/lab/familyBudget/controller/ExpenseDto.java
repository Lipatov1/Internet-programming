package lipatov.lab.familyBudget.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lipatov.lab.familyBudget.model.Expense;
import javax.validation.constraints.NotBlank;
import java.sql.Date;

public class ExpenseDto {
    private long id;
    private Date transactionDate;
    private int amount;
    @NotBlank(message = "Comment can't be null or empty")
    private String comment;
    private Long familyMemberId;

    public ExpenseDto() {
    }

    public ExpenseDto(Expense expense) {
        this.id = expense.getId();
        this.transactionDate = expense.getTransactionDate();
        this.amount = expense.getAmount();
        this.comment = expense.getComment();
        this.familyMemberId = expense.getFamilyMemberId();
    }

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public long getId() {
        return id;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getFamilyMemberId() {
        return familyMemberId;
    }

    public void setFamilyMemberId(Long familyMemberId) {
        this.familyMemberId = familyMemberId;
    }
}