package lipatov.lab;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import lipatov.lab.calculator.service.OperationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@SpringBootTest
class LabApplicationTests {
	@Autowired
	OperationService operationService;

	@Test
	void contextLoads() {
	}

	@Test
	void testOperationPlus() {
		final int res = operationService.calculate("plus", 15, 5);
		Assertions.assertEquals(20, res);
	}

	@Test
	void testOperationMinus() {
		final int res = operationService.calculate("minus", 15, 5);
		Assertions.assertEquals(10, res);
	}

	@Test
	void testOperationDiv() {
		final int res = operationService.calculate("div", 15, 5);
		Assertions.assertEquals(3, res);
	}

	@Test
	void testOperationMultiplication() {
		final int res = operationService.calculate("multiplication", 15, 5);
		Assertions.assertEquals(75, res);
	}

	@Test
	void testOperationErrorWired() {
		Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> operationService.calculate("pluss", 15, 5));
	}
}