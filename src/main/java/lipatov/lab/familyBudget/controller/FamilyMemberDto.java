package lipatov.lab.familyBudget.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lipatov.lab.familyBudget.model.FamilyMember;
import javax.validation.constraints.NotBlank;
import java.sql.Date;

public class FamilyMemberDto {
    private long id;
    @NotBlank(message = "Firstname can't be null or empty")
    private String firstName;
    @NotBlank(message = "Lastname can't be null or empty")
    private String lastName;
    @NotBlank(message = "Patronymic can't be null or empty")
    private String patronymic;
    @NotBlank(message = "Gender can't be null or empty")
    private String gender;
    private Date birthday;

    public FamilyMemberDto() {
    }

    public FamilyMemberDto(FamilyMember familyMember) {
        this.id = familyMember.getId();
        this.firstName = familyMember.getFirstName();
        this.lastName = familyMember.getLastName();
        this.patronymic = familyMember.getPatronymic();
        this.gender = familyMember.getGender();
        this.birthday = familyMember.getBirthday();
    }

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}