package lipatov.lab.familyBudget.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import lipatov.lab.familyBudget.service.ExpenseService;
import lipatov.lab.familyBudget.model.Expense;
import java.util.List;
import java.sql.Date;

@RestController
@RequestMapping("/expense")
public class ExpenseController {
    private final ExpenseService expenseService;


    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping("/{id}")
    public Expense getExpense(@PathVariable Long id) {
        return expenseService.findExpense(id);
    }

    @GetMapping("/")
    public List<Expense> getExpenses() {
        return expenseService.findAllExpenses();
    }

    @PostMapping("/")
    public Expense createExpense(@RequestParam("transactionDate") Date transactionDate,
                                 @RequestParam("amount") int amount,
                                 @RequestParam("comment") String comment,
                                 @RequestParam("familyMemberId") Long familyMemberId) {
        return expenseService.addExpense(transactionDate, amount, comment, familyMemberId);
    }

    @PatchMapping("/{id}")
    public Expense updateExpense(@PathVariable Long id,
                                 @RequestParam("transactionDate") Date transactionDate,
                                 @RequestParam("amount") int amount,
                                 @RequestParam("comment") String comment,
                                 @RequestParam("familyMemberId") Long familyMemberId) {
        return expenseService.updateExpense(id, transactionDate, amount, comment, familyMemberId);
    }

    @DeleteMapping("/{id}")
    public Expense deleteExpense(@PathVariable Long id) {
        return expenseService.deleteExpense(id);
    }

    @DeleteMapping("/")
    public void deleteAllExpenses() {
        expenseService.deleteAllExpenses();
    }
}