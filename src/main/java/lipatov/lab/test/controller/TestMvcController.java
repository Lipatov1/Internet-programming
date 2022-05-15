package lipatov.lab.test.controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.validation.BindingResult;
import org.springframework.stereotype.Controller;
import lipatov.lab.test.model.TestDto;
import org.springframework.ui.Model;
import javax.validation.Valid;

@Controller
@RequestMapping("/test")
public class TestMvcController {
    @GetMapping
    public String test(Model model) {
        model.addAttribute("testDto", new TestDto());
        return "test";
    }

    @PostMapping
    public String testValidation(@ModelAttribute @Valid TestDto testDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "test";
        }
        model.addAttribute("testDto", testDto);
        return "test-result";
    }
}