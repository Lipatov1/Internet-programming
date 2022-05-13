package lipatov.lab.familyBudget.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import lipatov.lab.familyBudget.service.FamilyMemberService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import lipatov.lab.familyBudget.model.FamilyMember;
import java.util.List;
import java.sql.Date;

@RestController
@RequestMapping("/familymember")
public class FamilyMemberController {
    private final FamilyMemberService familyMemberService;


    public FamilyMemberController(FamilyMemberService familyMemberService) {
        this.familyMemberService = familyMemberService;
    }

    @GetMapping("/{id}")
    public FamilyMember getFamilyMember(@PathVariable Long id) {
        return familyMemberService.findFamilyMember(id);
    }

    @GetMapping("/")
    public List<FamilyMember> getFamilyMembers() {
        return familyMemberService.findAllFamilyMembers();
    }

    @PostMapping("/")
    public FamilyMember createFamilyMember(@RequestParam("firstName") String firstName,
                                           @RequestParam("lastName") String lastName,
                                           @RequestParam("patronymic") String patronymic,
                                           @RequestParam("gender") String gender,
                                           @RequestParam("birthday") Date birthday) {
        return familyMemberService.addFamilyMember(firstName, lastName, patronymic, gender, birthday);
    }

    @PatchMapping("/{id}")
    public FamilyMember updateFamilyMember(@PathVariable Long id,
                                           @RequestParam("firstName") String firstName,
                                           @RequestParam("lastName") String lastName,
                                           @RequestParam("patronymic") String patronymic,
                                           @RequestParam("gender") String gender,
                                           @RequestParam("birthday") Date birthday) {
        return familyMemberService.updateFamilyMember(id, firstName, lastName, patronymic, gender, birthday);
    }

    @DeleteMapping("/{id}")
    public FamilyMember deleteFamilyMember(@PathVariable Long id) {
        return familyMemberService.deleteFamilyMember(id);
    }

    @DeleteMapping("/")
    public void deleteAllFamilyMembers() {
        familyMemberService.deleteAllFamilyMembers();
    }
}