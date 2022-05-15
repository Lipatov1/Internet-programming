package lipatov.lab.familyBudget.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import lipatov.lab.familyBudget.service.FamilyMemberService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.validation.BindingResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import javax.validation.Valid;

@Controller
@RequestMapping("/familymember")
public class FamilyMemberMvcController {
    private final FamilyMemberService familyMemberService;

    public FamilyMemberMvcController(FamilyMemberService familyMemberService) {
        this.familyMemberService = familyMemberService;
    }

    @GetMapping
    public String getFamilyMembers(Model model) {
        model.addAttribute("familymembers",
                familyMemberService.findAllFamilyMembers().stream()
                        .map(FamilyMemberDto::new)
                        .toList());
        return "familymember";
    }

    @GetMapping(value = {"/edit", "/edit/{id}"})
    public String editFamilyMember(@PathVariable(required = false) Long id, Model model) {
        if (id == null || id <= 0) {
            model.addAttribute("familymemberDto", new FamilyMemberDto());
        }
        else {
            model.addAttribute("familymemberId", id);
            model.addAttribute("familymemberDto", new FamilyMemberDto(familyMemberService.findFamilyMember(id)));
        }
        return "familymember-edit";
    }

    @PostMapping(value = {"", "/{id}"})
    public String saveFamilyMember(@PathVariable(required = false) Long id,
                                   @ModelAttribute @Valid FamilyMemberDto familyMemberDto,
                                   BindingResult bindingResult,
                                   Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "familymember-edit";
        }
        if (id == null || id <= 0) {
            familyMemberService.addFamilyMember(familyMemberDto.getFirstName(), familyMemberDto.getLastName(), familyMemberDto.getPatronymic(), familyMemberDto.getGender(), familyMemberDto.getBirthday());
        }
        else {
            familyMemberService.updateFamilyMember(id, familyMemberDto.getFirstName(), familyMemberDto.getLastName(), familyMemberDto.getPatronymic(), familyMemberDto.getGender(), familyMemberDto.getBirthday());
        }
        return "redirect:/familymember";
    }

    @PostMapping("/delete/{id}")
    public String deleteFamilyMember(@PathVariable Long id) {
        familyMemberService.deleteFamilyMember(id);
        return "redirect:/familymember";
    }
}