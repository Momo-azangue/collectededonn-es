package com.example.demo.Services;

import com.example.demo.Entities.PossibiliteDeZone;
import com.example.demo.Entities.PotentialitesDesZones;
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
public class PotentialitesDesZonesService {

    @Autowired
    private PotentialitesDesZonesRepository potentialitesDesZonesRepository;

    public List<PotentialitesDesZones> getAllPotentialitesDesZones() {
        return potentialitesDesZonesRepository.findAll();
    }

    public Optional<PotentialitesDesZones> getPotentialitesDesZonesById(int id) {
        return potentialitesDesZonesRepository.findById(id);
    }

    public PotentialitesDesZones savePotentialitesDesZones(PotentialitesDesZones potentialitesDesZones) {
        return potentialitesDesZonesRepository.save(potentialitesDesZones);
    }

    public void deletePotentialitesDesZones(int id) {
        potentialitesDesZonesRepository.deleteById(id);
    }

    public void saveExcelData(PotentialitesDesZonesRepository potentialitesDesZonesRepository, InputStream inputStream) throws IOException {
        Set<PotentialitesDesZones> possibiliteDeZones = new HashSet<PotentialitesDesZones>();
        try {
            Workbook workbook = WorkbookFactory.create(inputStream);

            workbook.getSheetAt(0).forEach(row -> {
                PotentialitesDesZones potentialitesDesZones= new PotentialitesDesZones();
                potentialitesDesZones.setPotentialite(row.getCell(0).getStringCellValue());
                potentialitesDesZones.setRessource(row.getCell(1).getStringCellValue());

                possibiliteDeZones.add(potentialitesDesZones);
            });

            potentialitesDesZonesRepository.saveAll(possibiliteDeZones);

            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
