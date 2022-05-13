package lipatov.lab;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.boot.SpringApplication;

@SpringBootApplication
@RestController
public class LabApplication {
	public static void main(String[] args) {
		SpringApplication.run(LabApplication.class, args);
	}

	@GetMapping("/plus")
	public int plus(@RequestParam(value = "num1", defaultValue = "15") int num1,
					@RequestParam(value = "num2", defaultValue = "5") int num2) {
		return num1+num2;
	}

	@GetMapping("minus")
	public int minus(@RequestParam(value = "num1", defaultValue = "15") int num1,
					 @RequestParam(value = "num2", defaultValue = "5") int num2) {
		return num1-num2;
	}

	@GetMapping("div")
	public int div(@RequestParam(value = "num1", defaultValue = "15") int num1,
				   @RequestParam(value = "num2", defaultValue = "5") int num2) {
		return num1/num2;
	}

	@GetMapping("/multiplication")
	public int multiplication(@RequestParam(value = "num1", defaultValue = "15") int num1,
							  @RequestParam(value = "num2", defaultValue = "5") int num2) {
		return num1*num2;
	}
}