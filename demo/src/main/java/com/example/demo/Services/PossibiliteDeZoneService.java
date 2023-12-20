package com.example.demo.Services;

import com.example.demo.Entities.PaysagesUrbains;
import com.example.demo.Entities.PossibiliteDeZone;
import com.example.demo.Repository.PossibiliteDeZoneRepository;
import com.example.demo.Repository.PotentialitesDesZonesRepository;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PossibiliteDeZoneService {

    @Autowired
    private PossibiliteDeZoneRepository possibiliteDeZoneRepository;

    public List<PossibiliteDeZone> getAllPossibilitesDeZone() {
        return possibiliteDeZoneRepository.findAll();
    }

    public Optional<PossibiliteDeZone> getPossibiliteDeZoneById(int id) {
        return possibiliteDeZoneRepository.findById(id);
    }

    public PossibiliteDeZone savePossibiliteDeZone(PossibiliteDeZone possibiliteDeZone) {
        return possibiliteDeZoneRepository.save(possibiliteDeZone);
    }

    public void deletePossibiliteDeZone(int id) {
        possibiliteDeZoneRepository.deleteById(id);
    }

    public void saveExcelData(PotentialitesDesZonesRepository potentialitesDesZonesRepository, InputStream inputStream) throws IOException {
        Set<PossibiliteDeZone> possibiliteDeZones = new HashSet<PossibiliteDeZone>();
        try {
            Workbook workbook = WorkbookFactory.create(inputStream);

            workbook.getSheetAt(0).forEach(row -> {
                PossibiliteDeZone possibiliteDeZone= new PossibiliteDeZone();
               possibiliteDeZone.setSpecification(row.getCell(0).getStringCellValue());
               possibiliteDeZone.setPossibiliteEmploi(row.getCell(1).getStringCellValue());
               possibiliteDeZone.setAxeStrategique(row.getCell(2).getStringCellValue());

                possibiliteDeZones.add(possibiliteDeZone);
            });

            possibiliteDeZoneRepository.saveAll(possibiliteDeZones);

            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
