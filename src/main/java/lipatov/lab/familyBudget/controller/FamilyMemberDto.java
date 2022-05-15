package lipatov.lab.familyBudget.controller;

import lipatov.lab.familyBudget.model.FamilyMember;
import java.sql.Date;

public class FamilyMemberDto {
    private final long id;
    private final String name;
    private final String gender;
    private final Date birthday;

    public FamilyMemberDto(FamilyMember familyMember) {
        this.id = familyMember.getId();
        this.name = String.format("%s %s %s", familyMember.getFirstName(), familyMember.getLastName(), familyMember.getPatronymic());
        this.gender = familyMember.getGender();
        this.birthday = familyMember.getBirthday();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public Date getBirthday() {
        return birthday;
    }
}