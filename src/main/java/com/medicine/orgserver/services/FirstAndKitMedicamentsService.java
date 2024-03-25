package com.medicine.orgserver.services;

import com.medicine.orgserver.dto.AddMedIntoFirstAndKitDTO;
import com.medicine.orgserver.dto.MedicamentDTO;
import com.medicine.orgserver.entities.FirstAndKit;
import com.medicine.orgserver.entities.Medicament;
import com.medicine.orgserver.exceptions.AppError;
import com.medicine.orgserver.repositories.FirstAndKitRepository;
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
public class FirstAndKitMedicamentsService {

    private final UserRepository userRepository;
    private final MedicamentRepository medicamentRepository;
    private final MedicamentService medicamentService;
    private final FirstAndKitRepository firstAndKitRepository;

    public ResponseEntity<?> getMedicamentsCollectionByFirstAndKitId(Long id) {
        Optional<FirstAndKit> firstAndKit = firstAndKitRepository.findById(id);
        if (firstAndKit.isEmpty()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(),
                    "Аптечка с переданным id не существует"), HttpStatus.BAD_REQUEST);
        }
        return  ResponseEntity.ok(firstAndKit.get().getMedicaments());
    }

    public ResponseEntity<?> addMedicamentIntoFirstAndKitById(AddMedIntoFirstAndKitDTO addMedToUserDTO) {
        Optional<FirstAndKit> firstAndKit = firstAndKitRepository.findById(addMedToUserDTO.getId());
        if (firstAndKit.isEmpty()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(),
                    "Аптечка с переданным id не существует"), HttpStatus.BAD_REQUEST);
        }
        Medicament medicament;
        if (medicamentRepository.findByName(addMedToUserDTO.getNameOfTheMedicament()).isEmpty()) {
            medicament = medicamentService.createNewMedicament(new MedicamentDTO(addMedToUserDTO.getNameOfTheMedicament()));
        } else {
            medicament = medicamentRepository.findByName(addMedToUserDTO.getNameOfTheMedicament()).get();
        }

        Collection<Medicament> medicamentCollection = firstAndKit.get().getMedicaments();
        medicamentCollection.add(medicament);
        firstAndKit.get().setMedicaments(medicamentCollection);
        firstAndKitRepository.save(firstAndKit.get());

        return  ResponseEntity.ok(firstAndKit.get().getMedicaments());
    }

    public ResponseEntity<?> deleteMedicamentForUser(Long id, String medicamentName) {
        Optional<FirstAndKit> firstAndKit = firstAndKitRepository.findById(id);
        if (firstAndKit.isEmpty()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(),
                    "Аптечка с переданным id не существует"), HttpStatus.BAD_REQUEST);
        }
        if (firstAndKit.get().getMedicaments().isEmpty()
            || firstAndKit.get().getMedicaments().stream()
                .filter(medicament -> medicament
                        .getName().equals(medicamentName)).findFirst().isEmpty()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(),
                    "Медикамент с указанным именем не найден в аптечке с переданным id"), HttpStatus.BAD_REQUEST);
        }

        Collection<Medicament> medicamentCollection = firstAndKit.get().getMedicaments();
        Medicament medicamentToBeRemoved = medicamentCollection.stream()
                .filter(medicament -> medicament.getName().equals(medicamentName)).findFirst().orElseThrow();
        medicamentCollection.remove(medicamentToBeRemoved);
        firstAndKit.get().setMedicaments(medicamentCollection);
        firstAndKitRepository.save(firstAndKit.get());

        return  ResponseEntity.ok(firstAndKit.get().getMedicaments());
    }

}
