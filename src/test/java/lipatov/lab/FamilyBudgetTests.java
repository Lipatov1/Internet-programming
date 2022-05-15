package lipatov.lab;

import org.springframework.beans.factory.annotation.Autowired;
import lipatov.lab.familyBudget.service.FamilyMemberService;
import org.springframework.boot.test.context.SpringBootTest;
import lipatov.lab.familyBudget.service.ExpenseService;
import lipatov.lab.familyBudget.model.FamilyMember;
import javax.persistence.EntityNotFoundException;
import lipatov.lab.familyBudget.model.Expense;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import java.util.List;
import java.sql.Date;

@SpringBootTest
public class FamilyBudgetTests {
    private static final Logger log = LoggerFactory.getLogger(FamilyBudgetTests.class);

    @Autowired
    private FamilyMemberService familyMemberService;
    @Autowired
    private ExpenseService expenseService;

    @Test
    void testFamilyMemberCreate() {
        expenseService.deleteAllExpenses();
        familyMemberService.deleteAllFamilyMembers();
        final FamilyMember familyMember = familyMemberService.addFamilyMember("Иван", "Иванов", "Иванович", "Мужской", new Date(System.currentTimeMillis()));
        log.info(familyMember.toString());
        Assertions.assertNotNull(familyMember.getId());
    }

    @Test
    void testFamilyMemberRead() {
        familyMemberService.deleteAllFamilyMembers();
        final FamilyMember familyMember = familyMemberService.addFamilyMember("Иван", "Иванов", "Иванович", "Мужской", new Date(System.currentTimeMillis()));
        log.info(familyMember.toString());
        final FamilyMember findFamilyMember = familyMemberService.findFamilyMember(familyMember.getId());
        log.info(findFamilyMember.toString());
        Assertions.assertEquals(familyMember, findFamilyMember);
    }

    @Test
    void testFamilyMemberReadNotFound() {
        familyMemberService.deleteAllFamilyMembers();
        Assertions.assertThrows(EntityNotFoundException.class, () -> familyMemberService.findFamilyMember(-1L));
    }

    @Test
    void testFamilyMemberReadAll() {
        familyMemberService.deleteAllFamilyMembers();
        familyMemberService.addFamilyMember("Иван", "Иванов", "Иванович", "Мужской", new Date(System.currentTimeMillis()));
        familyMemberService.addFamilyMember("Петр", "Петров", "Петрович", "Мужской", new Date(System.currentTimeMillis()));
        final List<FamilyMember> familyMembers = familyMemberService.findAllFamilyMembers();
        log.info(familyMembers.toString());
        Assertions.assertEquals(familyMembers.size(), 2);
    }

    @Test
    void testFamilyMemberReadAllEmpty() {
        familyMemberService.deleteAllFamilyMembers();
        final List<FamilyMember> familyMembers = familyMemberService.findAllFamilyMembers();
        log.info(familyMembers.toString());
        Assertions.assertEquals(familyMembers.size(), 0);
    }

    @Test
    void testExpenseCreate() {
        expenseService.deleteAllExpenses();
        final FamilyMember familyMember =familyMemberService.addFamilyMember("Иван", "Иванов", "Иванович", "Мужской", new Date(System.currentTimeMillis()));
        final Expense expense = expenseService.addExpense(new Date(System.currentTimeMillis()), 500, "Такси", familyMember.getId());
        log.info(expense.toString());
        Assertions.assertNotNull(expense.getId());
    }

    @Test
    void testExpenseRead() {
        expenseService.deleteAllExpenses();
        final FamilyMember familyMember =familyMemberService.addFamilyMember("Иван", "Иванов", "Иванович", "Мужской", new Date(System.currentTimeMillis()));
        final Expense expense = expenseService.addExpense(new Date(System.currentTimeMillis()), 500, "Такси", familyMember.getId());
        log.info(expense.toString());
        final Expense findExpense = expenseService.findExpense(expense.getId());
        log.info(findExpense.toString());
        Assertions.assertEquals(expense, findExpense);
    }

    @Test
    void testExpenseReadNotFound() {
        expenseService.deleteAllExpenses();
        Assertions.assertThrows(EntityNotFoundException.class, () -> expenseService.findExpense(-1L));
    }

    @Test
    void testExpenseReadAll() {
        expenseService.deleteAllExpenses();
        final FamilyMember familyMember =familyMemberService.addFamilyMember("Иван", "Иванов", "Иванович", "Мужской", new Date(System.currentTimeMillis()));
        expenseService.addExpense(new Date(System.currentTimeMillis()), 500, "Такси", familyMember.getId());
        expenseService.addExpense(new Date(System.currentTimeMillis()), 1000, "Кино", familyMember.getId());
        final List<Expense> expenses = expenseService.findAllExpenses();
        log.info(expenses.toString());
        Assertions.assertEquals(expenses.size(), 2);
    }

    @Test
    void testExpenseReadAllEmpty() {
        expenseService.deleteAllExpenses();
        final List<Expense> expenses = expenseService.findAllExpenses();
        log.info(expenses.toString());
        Assertions.assertEquals(expenses.size(), 0);
    }
}