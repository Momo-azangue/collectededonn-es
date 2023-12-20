package com.example.demo.Services;

import com.example.demo.Entities.PaysagesUrbains;
import com.example.demo.Entities.RessourcesZone;
import com.example.demo.Repository.PaysagesUrbainsRepository;
import com.example.demo.Repository.RessourcesZoneRepository;
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
public class RessourcesZoneService {

    @Autowired
    private RessourcesZoneRepository ressourcesZoneRepository;

    public List<RessourcesZone> getAllRessourcesZones() {
        return ressourcesZoneRepository.findAll();
    }

    public Optional<RessourcesZone> getRessourcesZoneById(int id) {
        return ressourcesZoneRepository.findById(id);
    }

    public RessourcesZone saveRessourcesZone(RessourcesZone ressourcesZone) {
        return ressourcesZoneRepository.save(ressourcesZone);
    }

    public void deleteRessourcesZone(int id) {
        ressourcesZoneRepository.deleteById(id);
    }

    public void saveExcelData(RessourcesZoneRepository ressourcesZoneRepository, InputStream inputStream) throws IOException {
        Set<RessourcesZone> ressourcesZones = new HashSet<RessourcesZone>();
        try {
            Workbook workbook = WorkbookFactory.create(inputStream);

            workbook.getSheetAt(0).forEach(row -> {
                RessourcesZone ressourcesZone= new RessourcesZone();
                ressourcesZone.setRessource(row.getCell(0).getStringCellValue());
                ressourcesZone.setCarateristique(row.getCell(1).getStringCellValue());
                ressourcesZone.setUtilisationActuelle(row.getCell(2).getStringCellValue());
                ressourcesZone.setAccesControler(row.getCell(3).getBooleanCellValue());
                ressourcesZone.setArchive(row.getCell(4).getBooleanCellValue());

                ressourcesZones.add(ressourcesZone);
            });

            ressourcesZoneRepository.saveAll(ressourcesZones);

            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
