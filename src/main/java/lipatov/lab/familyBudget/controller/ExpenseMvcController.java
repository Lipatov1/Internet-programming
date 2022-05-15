package lipatov.lab.familyBudget.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import lipatov.lab.familyBudget.service.ExpenseService;
import org.springframework.validation.BindingResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import javax.validation.Valid;

@Controller
@RequestMapping("/expense")
public class ExpenseMvcController {
    private final ExpenseService expenseService;

    public ExpenseMvcController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping
    public String getExpenses(Model model) {
        model.addAttribute("expenses",
                expenseService.findAllExpenses().stream()
                        .map(ExpenseDto::new)
                        .toList());
        return "expense";
    }

    @GetMapping(value = {"/edit", "/edit/{id}"})
    public String editExpense(@PathVariable(required = false) Long id, Model model) {
        if (id == null || id <= 0) {
            model.addAttribute("expenseDto", new ExpenseDto());
        }
        else {
            model.addAttribute("expenseId", id);
            model.addAttribute("expenseDto", new ExpenseDto(expenseService.findExpense(id)));
        }
        return "expense-edit";
    }

    @PostMapping(value = {"/edit", "/edit/{id}"})
    public String saveExpense(@PathVariable(required = false) Long id,
                              @ModelAttribute @Valid ExpenseDto expenseDto,
                              BindingResult bindingResult,
                              Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "expense-edit";
        }
        if (id == null || id <= 0) {
            expenseService.addExpense(expenseDto.getTransactionDate(), expenseDto.getAmount(), expenseDto.getComment(), expenseDto.getFamilyMemberId());
        } else {
            expenseService.updateExpense(id, expenseDto.getTransactionDate(), expenseDto.getAmount(), expenseDto.getComment(), expenseDto.getFamilyMemberId());
        }
        return "redirect:/expense";
    }

    @PostMapping("/delete/{id}")
    public String deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return "redirect:/expense";
    }
}