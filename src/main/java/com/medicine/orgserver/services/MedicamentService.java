package com.medicine.orgserver.services;

import com.medicine.orgserver.dto.MedicamentDTO;
import com.medicine.orgserver.entities.Medicament;
import com.medicine.orgserver.repositories.MedicamentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor ()
public class MedicamentService {

    private final MedicamentRepository medicamentRepository;

    public Optional<Medicament> findByName(String name) {
        return medicamentRepository.findByName(name);
    }

    public Medicament createNewMedicament(MedicamentDTO medicamentDTO) {
        Medicament medicament = new Medicament();
        medicament.setName(medicamentDTO.getName());

        return medicamentRepository.save(medicament);
    }
}
