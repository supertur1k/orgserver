package com.medicine.orgserver.services;

import com.medicine.orgserver.dto.AddMedIntoFirstAidKitDTO;
import com.medicine.orgserver.dto.MedicamentDTO;
import com.medicine.orgserver.entities.FirstAidKit;
import com.medicine.orgserver.entities.Medicament;
import com.medicine.orgserver.exceptions.AppError;
import com.medicine.orgserver.repositories.FirstAidKitRepository;
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
public class FirstAidKitMedicamentsService {

    private final UserRepository userRepository;
    private final MedicamentRepository medicamentRepository;
    private final MedicamentService medicamentService;
    private final FirstAidKitRepository firstAidKitRepository;

    public ResponseEntity<?> getMedicamentsCollectionByFirstAidKitId(Long id) {
        Optional<FirstAidKit> firstAidKit = firstAidKitRepository.findById(id);
        if (firstAidKit.isEmpty()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(),
                    "Аптечка с переданным id не существует"), HttpStatus.BAD_REQUEST);
        }
        return  ResponseEntity.ok(firstAidKit.get().getMedicaments());
    }

    public ResponseEntity<?> addMedicamentIntoFirstAidKitById(AddMedIntoFirstAidKitDTO addMedToUserDTO) {
        Optional<FirstAidKit> firstAidKit = firstAidKitRepository.findById(addMedToUserDTO.getId());
        if (firstAidKit.isEmpty()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(),
                    "Аптечка с переданным id не существует"), HttpStatus.BAD_REQUEST);
        }
        Medicament medicament;
        if (medicamentRepository.findByName(addMedToUserDTO.getNameOfTheMedicament()).isEmpty()) {
            medicament = medicamentService.createNewMedicament(new MedicamentDTO(addMedToUserDTO.getNameOfTheMedicament()));
        } else {
            medicament = medicamentRepository.findByName(addMedToUserDTO.getNameOfTheMedicament()).get();
        }

        Collection<Medicament> medicamentCollection = firstAidKit.get().getMedicaments();
        medicamentCollection.add(medicament);
        firstAidKit.get().setMedicaments(medicamentCollection);
        firstAidKitRepository.save(firstAidKit.get());

        return  ResponseEntity.ok(firstAidKit.get().getMedicaments());
    }

    public ResponseEntity<?> deleteMedicamentForUser(Long id, String medicamentName) {
        Optional<FirstAidKit> firstAidKit = firstAidKitRepository.findById(id);
        if (firstAidKit.isEmpty()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(),
                    "Аптечка с переданным id не существует"), HttpStatus.BAD_REQUEST);
        }
        if (firstAidKit.get().getMedicaments().isEmpty()
            || firstAidKit.get().getMedicaments().stream()
                .filter(medicament -> medicament
                        .getName().equals(medicamentName)).findFirst().isEmpty()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(),
                    "Медикамент с указанным именем не найден в аптечке с переданным id"), HttpStatus.BAD_REQUEST);
        }

        Collection<Medicament> medicamentCollection = firstAidKit.get().getMedicaments();
        Medicament medicamentToBeRemoved = medicamentCollection.stream()
                .filter(medicament -> medicament.getName().equals(medicamentName)).findFirst().orElseThrow();
        medicamentCollection.remove(medicamentToBeRemoved);
        firstAidKit.get().setMedicaments(medicamentCollection);
        firstAidKitRepository.save(firstAidKit.get());

        return  ResponseEntity.ok(firstAidKit.get().getMedicaments());
    }

}
