package lipatov.lab.familyBudget.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import lipatov.lab.familyBudget.service.FamilyMemberService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import lipatov.lab.configuration.WebConfiguration;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(WebConfiguration.REST_API + "/familymember")
public class FamilyMemberController {
    private final FamilyMemberService familyMemberService;

    public FamilyMemberController(FamilyMemberService familyMemberService) {
        this.familyMemberService = familyMemberService;
    }

    @GetMapping("/{id}")
    public FamilyMemberDto getFamilyMember(@PathVariable Long id) {
        return new FamilyMemberDto(familyMemberService.findFamilyMember(id));
    }

    @GetMapping
    public List<FamilyMemberDto> getFamilyMembers() {
        return familyMemberService.findAllFamilyMembers().stream()
                .map(FamilyMemberDto::new)
                .toList();
    }

    @PostMapping
    public FamilyMemberDto createFamilyMember(@RequestBody @Valid FamilyMemberDto familyMemberDto) {
        return new FamilyMemberDto(familyMemberService.addFamilyMember(familyMemberDto.getFirstName(), familyMemberDto.getLastName(), familyMemberDto.getPatronymic(), familyMemberDto.getGender(), familyMemberDto.getBirthday()));
    }

    @PutMapping("/{id}")
    public FamilyMemberDto updateFamilyMember(@PathVariable Long id,
                                              @RequestBody @Valid FamilyMemberDto familyMemberDto) {
        return new FamilyMemberDto(familyMemberService.updateFamilyMember(id, familyMemberDto.getFirstName(), familyMemberDto.getLastName(), familyMemberDto.getPatronymic(), familyMemberDto.getGender(), familyMemberDto.getBirthday()));
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