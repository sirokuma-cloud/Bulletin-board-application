package com.example.board.controller;

import com.example.board.entity.User;
import com.example.board.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegisterController {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public RegisterController(
            UserRepository userRepository,
            BCryptPasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // 登録画面表示
    @GetMapping("/register")
    public String showRegisterForm(Model model) {

        model.addAttribute("user", new User());

        return "register";
    }

    // ユーザー保存
    @PostMapping("/register")
    public String registerUser(
            @ModelAttribute User user,
            Model model) {

        // username重複確認
        if (userRepository
                .findByUsername(user.getUsername())
                .isPresent()) {

            model.addAttribute(
                    "error",
                    "Username already exists"
            );

            return "register";
        }

        user.setPassword(
                passwordEncoder.encode(
                        user.getPassword()
                )
        );

        userRepository.save(user);

        return "redirect:/login";
    }
}
