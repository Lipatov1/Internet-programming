package lipatov.lab.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import lipatov.lab.user.service.UserService;
import org.springframework.data.domain.Page;
import lipatov.lab.user.model.UserRole;
import lipatov.lab.user.model.UserDto;
import org.springframework.ui.Model;
import java.util.stream.IntStream;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserMvcController {
    private final UserService userService;

    public UserMvcController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @Secured({UserRole.AsString.ADMIN})
    public String getUsers(@RequestParam(defaultValue = "1") int page,
                           @RequestParam(defaultValue = "5") int size,
                           Model model) {
        final Page<UserDto> users = userService.findAllPages(page, size)
                .map(UserDto::new);

        model.addAttribute("users", users);
        final int totalPages = users.getTotalPages();

        final List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                .boxed()
                .toList();

        model.addAttribute("pages", pageNumbers);
        model.addAttribute("totalPages", totalPages);
        return "users";
    }
}