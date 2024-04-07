package com.medicine.orgserver.services;

import com.medicine.orgserver.dto.MedicamentDTO;
import com.medicine.orgserver.dto.MedicamentFromAptekaRuInfo;
import com.medicine.orgserver.entities.Medicament;
import com.medicine.orgserver.getInfoByBarCode.parser.getInfoByNameFromEapteka;
import com.medicine.orgserver.repositories.MedicamentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor ()
public class MedicamentService {

    private final MedicamentRepository medicamentRepository;

    public Optional<Medicament> findByName(String name) {
        return medicamentRepository.findByName(name);
    }

    public Medicament createNewMedicament(MedicamentDTO medicamentDTO) {
        Medicament medicament = new Medicament();
        medicament.setName(medicamentDTO.getName());
        if (medicamentDTO.getBarcode() != null) medicament.setBarcode(medicamentDTO.getBarcode());

        MedicamentFromAptekaRuInfo medicamentFromAptekaRuInfo = new getInfoByNameFromEapteka().getInfoOfMedicament(medicamentDTO.getName());

        if (medicamentFromAptekaRuInfo.getReleaseForm() != null)
            medicament.setReleaseForm(medicamentFromAptekaRuInfo.getReleaseForm());

        if (medicamentFromAptekaRuInfo.getAmount() != null)
            medicament.setAmount(medicamentFromAptekaRuInfo.getAmount());

        if (medicamentFromAptekaRuInfo.getDirectionsForUse() != null)
            medicament.setDirectionsForUse(medicamentFromAptekaRuInfo.getDirectionsForUse());

        if (medicamentFromAptekaRuInfo.getIndicationsForUse() != null)
            medicament.setIndicationsForUse(medicamentFromAptekaRuInfo.getIndicationsForUse());

        if (medicamentFromAptekaRuInfo.getContraindications() != null)
            medicament.setContraindications(medicamentFromAptekaRuInfo.getContraindications());


        return medicamentRepository.save(medicament);
    }
}
