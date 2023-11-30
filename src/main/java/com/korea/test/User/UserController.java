package com.korea.test.User;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/signup")
    public String signup() {
        return "signupForm";
    }

    @PostMapping("/signup")
    public String signup(String username, String password, String nickname, String email, Model model) {
        try {
            this.userService.createUser(username, password, nickname, email);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            model.addAttribute("signupFailed", "이미 등록된 사용자입니다.");
            return "signupForm";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("signupFailed", e.getMessage());
            return "signupForm";
        }
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        return "loginForm";
    }
}
