package lipatov.lab.familyBudget.service;

public class FamilyMemberNotFoundException extends RuntimeException {
    public FamilyMemberNotFoundException(Long id) {
        super(String.format("Family member with id [%s] is not found", id));
    }
}