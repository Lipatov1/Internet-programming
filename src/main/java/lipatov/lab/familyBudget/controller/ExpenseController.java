package lipatov.lab.familyBudget.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import lipatov.lab.familyBudget.service.ExpenseService;
import lipatov.lab.WebConfiguration;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(WebConfiguration.REST_API + "/expense")
public class ExpenseController {
    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping("/{id}")
    public ExpenseDto getExpense(@PathVariable Long id) {
        return new ExpenseDto(expenseService.findExpense(id));
    }

    @GetMapping
    public List<ExpenseDto> getExpenses() {
        return expenseService.findAllExpenses().stream()
                .map(ExpenseDto::new)
                .toList();
    }

    @PostMapping
    public ExpenseDto createExpense(@RequestBody @Valid ExpenseDto expenseDto) {
        return new ExpenseDto(expenseService.addExpense(expenseDto.getTransactionDate(), expenseDto.getAmount(), expenseDto.getComment(), expenseDto.getFamilyMemberId()));
    }

    @PutMapping("/{id}")
    public ExpenseDto updateExpense(@PathVariable Long id,
                                    @RequestBody @Valid ExpenseDto expenseDto) {
        return new ExpenseDto(expenseService.updateExpense(id, expenseDto.getTransactionDate(), expenseDto.getAmount(), expenseDto.getComment(), expenseDto.getFamilyMemberId()));
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