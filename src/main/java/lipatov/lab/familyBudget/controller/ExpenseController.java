package lipatov.lab.familyBudget.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public ExpenseDto getExpense(@PathVariable Long id) {
        return new ExpenseDto(expenseService.findExpense(id));
    }

    @GetMapping("/")
    public List<ExpenseDto> getExpenses() {
        return expenseService.findAllExpenses().stream()
                .map(ExpenseDto::new)
                .toList();
    }

    @PostMapping("/")
    public ExpenseDto createExpense(@RequestParam("transactionDate") Date transactionDate,
                                    @RequestParam("amount") int amount,
                                    @RequestParam("comment") String comment,
                                    @RequestParam("familyMemberId") Long familyMemberId) {
        return new ExpenseDto(expenseService.addExpense(transactionDate, amount, comment, familyMemberId));
    }

    @PutMapping("/{id}")
    public ExpenseDto updateExpense(@PathVariable Long id,
                                    @RequestParam("transactionDate") Date transactionDate,
                                    @RequestParam("amount") int amount,
                                    @RequestParam("comment") String comment,
                                    @RequestParam("familyMemberId") Long familyMemberId) {
        return new ExpenseDto(expenseService.updateExpense(id, transactionDate, amount, comment, familyMemberId));
    }

    @DeleteMapping("/{id}")
    public ExpenseDto deleteExpense(@PathVariable Long id) {
        return new ExpenseDto(expenseService.deleteExpense(id));
    }

    @DeleteMapping("/")
    public void deleteAllExpenses() {
        expenseService.deleteAllExpenses();
    }
}