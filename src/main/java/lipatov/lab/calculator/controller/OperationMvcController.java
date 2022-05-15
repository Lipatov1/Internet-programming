package lipatov.lab.calculator.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import lipatov.lab.calculator.service.OperationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import java.util.List;

@Controller
@RequestMapping("/calculator")
public class OperationMvcController {
    private final OperationService operationService;
    private final List<String> operations;

    public OperationMvcController(OperationService operationService) {
        this.operationService = operationService;
        this.operations = List.of("plus", "minus", "div", "multiplication", "divv");
    }

    @GetMapping
    public String calculate(@RequestParam(value = "num1", defaultValue = "15") int num1,
                            @RequestParam(value = "num2", defaultValue = "5") int num2,
                            @RequestParam(value = "typeOperation", defaultValue = "plus") String typeOperation,
                            Model model) {
        model.addAttribute("operations", operations);
        model.addAttribute("num1", num1);
        model.addAttribute("num2", num2);
        model.addAttribute("typeOperation", typeOperation);
        model.addAttribute("calculate", operationService.calculate(typeOperation, num1, num2));
        return "calculator";
    }
}