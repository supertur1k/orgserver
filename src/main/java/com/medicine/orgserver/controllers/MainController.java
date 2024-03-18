package com.medicine.orgserver.controllers;


import com.medicine.orgserver.dto.CatDTO;
import com.medicine.orgserver.entities.Cat;
import com.medicine.orgserver.repositories.CatRepo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Tag(name = "main_methods")
@Slf4j
@RestController
@RequiredArgsConstructor
public class MainController {
    private final CatRepo catRepo;


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

    @Operation(
            summary = "add Cat method do sth post http",
            description = "full description of method opaaa"
    )
    @PostMapping("/api/add")
    public void addCat(@RequestBody CatDTO catDTO) {
        log.info("New raw: " + catRepo.save(Cat.builder()
                .age(catDTO.getAge())
                .weight(catDTO.getWeight())
                .name(catDTO.getName())
                .build())
        );
    }

    @SneakyThrows
    @GetMapping("/api/all")
    public List<Cat> getAll() {
        return catRepo.findAll();
    }

    @GetMapping("/api")
    public Cat getCat(@RequestParam int id) {
        return catRepo.findById(id).orElseThrow();
    }

    @DeleteMapping("/api")
    public void deleteCat(@RequestParam int id) {
        catRepo.deleteById(id);
    }

    @PutMapping("/api/add")
    public String changeCat(@RequestBody Cat cat) {
        if (!catRepo.existsById(cat.getId())) {
            return "No such raw";
        }
        return catRepo.save(cat).toString();
    }
}
