package lipatov.lab.familyBudget.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import lipatov.lab.familyBudget.service.FamilyMemberService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
    public FamilyMemberDto getFamilyMember(@PathVariable Long id) {
        return new FamilyMemberDto(familyMemberService.findFamilyMember(id));
    }

    @GetMapping("/")
    public List<FamilyMemberDto> getFamilyMembers() {
        return familyMemberService.findAllFamilyMembers().stream()
                .map(FamilyMemberDto::new)
                .toList();
    }

    @PostMapping("/")
    public FamilyMemberDto createFamilyMember(@RequestParam("firstName") String firstName,
                                              @RequestParam("lastName") String lastName,
                                              @RequestParam("patronymic") String patronymic,
                                              @RequestParam("gender") String gender,
                                              @RequestParam("birthday") Date birthday) {
        return new FamilyMemberDto(familyMemberService.addFamilyMember(firstName, lastName, patronymic, gender, birthday));
    }

    @PutMapping("/{id}")
    public FamilyMemberDto updateFamilyMember(@PathVariable Long id,
                                              @RequestParam("firstName") String firstName,
                                              @RequestParam("lastName") String lastName,
                                              @RequestParam("patronymic") String patronymic,
                                              @RequestParam("gender") String gender,
                                              @RequestParam("birthday") Date birthday) {
        return new FamilyMemberDto(familyMemberService.updateFamilyMember(id, firstName, lastName, patronymic, gender, birthday));
    }

    @DeleteMapping("/{id}")
    public FamilyMemberDto deleteFamilyMember(@PathVariable Long id) {
        return new FamilyMemberDto(familyMemberService.deleteFamilyMember(id));
    }

    @DeleteMapping("/")
    public void deleteAllFamilyMembers() {
        familyMemberService.deleteAllFamilyMembers();
    }
}