package lipatov.lab.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import lipatov.lab.test.model.TestDto;
import lipatov.lab.WebConfiguration;
import javax.validation.Valid;

@RestController
@RequestMapping(WebConfiguration.REST_API + "/test")
public class TestController {
    @PostMapping
    public TestDto testValidation(@RequestBody @Valid TestDto testDto) {
        return testDto;
    }
}