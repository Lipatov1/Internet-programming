package lipatov.lab.calculator.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import lipatov.lab.calculator.service.OperationService;
import lipatov.lab.configuration.WebConfiguration;

@RestController
@RequestMapping(WebConfiguration.REST_API + "/calculator")
public class OperationController {
    private final OperationService operationService;

    public OperationController(OperationService operationService) {
        this.operationService = operationService;
    }

    @GetMapping
    public int calculate(@RequestParam(value = "typeOperation", defaultValue = "plus") String typeOperation,
                         @RequestParam(value = "num1", defaultValue = "15") int num1,
                         @RequestParam(value = "num2", defaultValue = "5") int num2) {
        return operationService.calculate(typeOperation, num1, num2);
    }
}