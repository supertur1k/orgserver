package com.medicine.orgserver.controllers;


import com.medicine.orgserver.repositories.UserRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Tag(name = "main_methods")
@Slf4j
@RestController
@RequiredArgsConstructor
public class MainController {
    private final UserRepository userRepository;


    @GetMapping("/unsecured")
    public String hello() {
        return "unsecured data";
    }

    @GetMapping("/secured")
    public String user() {
        return "secured data";
    }

    @GetMapping("/admin")
    public String admin() {
        return "hey admin";
    }

    @GetMapping("/info")
    public String userData(Principal principal) {
        return principal.getName();
    }

}
