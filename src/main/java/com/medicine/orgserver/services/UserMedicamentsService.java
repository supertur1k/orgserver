package com.medicine.orgserver.services;

import com.medicine.orgserver.dto.AddMedToUserDTO;
import com.medicine.orgserver.dto.MedicamentDTO;
import com.medicine.orgserver.entities.Medicament;
import com.medicine.orgserver.entities.User;
import com.medicine.orgserver.exceptions.AppError;
import com.medicine.orgserver.repositories.MedicamentRepository;
import com.medicine.orgserver.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserMedicamentsService {

    private final UserRepository userRepository;
    private final MedicamentRepository medicamentRepository;
    private final MedicamentService medicamentService;

    public ResponseEntity<?> getMedicamentsCollectionByUserName(String name) {
        Optional<User> user = userRepository.findByUsername(name);
        if (user.isEmpty()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Пользователь с указанным именем не найден . . . "), HttpStatus.BAD_REQUEST);
        }
        return  ResponseEntity.ok(user.get().getMedicaments());
    }

    public ResponseEntity<?> setMedicamentToUserByName(AddMedToUserDTO addMedToUserDTO) {
        Optional<User> user = userRepository.findByUsername(addMedToUserDTO.getUsername());
        if (user.isEmpty()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Пользователь с указанным именем не найден . . . "), HttpStatus.BAD_REQUEST);
        }
        Medicament medicament;
        if (medicamentRepository.findByName(addMedToUserDTO.getNameOfTheMedicament()).isEmpty()) {
            medicament = medicamentService.createNewMedicament(new MedicamentDTO(addMedToUserDTO.getNameOfTheMedicament()));
        } else {
            medicament = medicamentRepository.findByName(addMedToUserDTO.getNameOfTheMedicament()).get();
        }

        Collection<Medicament> medicamentCollection = user.get().getMedicaments();
        medicamentCollection.add(medicament);
        user.get().setMedicaments(medicamentCollection);
        userRepository.save(user.get());

        return  ResponseEntity.ok(user.get().getMedicaments());
    }

    public ResponseEntity<?> deleteMedicamentForUser(String username, String medicamentName) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Пользователь с указанным именем не найден . . . "), HttpStatus.BAD_REQUEST);
        }
        if (user.get().getMedicaments().isEmpty()
            || user.get().getMedicaments().stream()
                .filter(medicament -> medicament
                        .getName().equals(medicamentName)).findFirst().isEmpty()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Медикамент с указанным именем не найден у пользователя . . . "), HttpStatus.BAD_REQUEST);
        }

        Collection<Medicament> medicamentCollection = user.get().getMedicaments();
        Medicament medicamentToBeRemoved = medicamentCollection.stream().filter(medicament -> medicament.getName().equals(medicamentName)).findFirst().orElseThrow();
        medicamentCollection.remove(medicamentToBeRemoved);
        user.get().setMedicaments(medicamentCollection);
        userRepository.save(user.get());

        return  ResponseEntity.ok(user.get().getMedicaments());
    }

}
