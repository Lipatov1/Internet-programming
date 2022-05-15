package lipatov.lab.calculator.service;

import org.springframework.context.ApplicationContext;
import lipatov.lab.calculator.domain.Operation;
import org.springframework.stereotype.Service;

@Service
public class OperationService {
    private final ApplicationContext applicationContext;

    public OperationService(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public int calculate(String typeOperation, int num1, int num2) {
        final Operation operation = (Operation) applicationContext.getBean(typeOperation);
        return operation.calculate(num1, num2);
    }
}