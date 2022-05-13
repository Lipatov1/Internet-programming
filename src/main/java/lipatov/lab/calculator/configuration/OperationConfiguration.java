package lipatov.lab.calculator.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import lipatov.lab.calculator.domain.*;

@Configuration
public class OperationConfiguration {
    @Bean(value = "plus")
    public Operation createOperationPlus() {
        return new OperationPlus();
    }

    @Bean(value = "minus")
    public Operation createOperationMinus() {
        return new OperationMinus();
    }

    @Bean(value = "div")
    public Operation createOperationDiv() {
        return new OperationDiv();
    }

    @Bean(value = "multiplication")
    public Operation OperationMultiplication() {
        return new OperationMultiplication();
    }
}