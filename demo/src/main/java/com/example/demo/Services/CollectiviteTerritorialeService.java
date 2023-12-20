package com.example.demo.Services;

import com.example.demo.Entities.CollectiviteTerritoriale;
import com.example.demo.Entities.Localite;
import com.example.demo.Repository.CollectiviteTerritorialeRepository;
import com.example.demo.Repository.LocaliteRepository;
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
public class CollectiviteTerritorialeService {

    @Autowired
    private CollectiviteTerritorialeRepository collectiviteTerritorialeRepository;

    public List<CollectiviteTerritoriale> getAllCollectivitesTerritoriales() {
        return collectiviteTerritorialeRepository.findAll();
    }

    public Optional<CollectiviteTerritoriale> getCollectiviteTerritorialeById(int id) {
        return collectiviteTerritorialeRepository.findById(id);
    }

    public CollectiviteTerritoriale saveCollectiviteTerritoriale(CollectiviteTerritoriale collectiviteTerritoriale) {
        return collectiviteTerritorialeRepository.save(collectiviteTerritoriale);
    }

    public void deleteCollectiviteTerritoriale(int id) {
        collectiviteTerritorialeRepository.deleteById(id);
    }

    public void saveExcelData(CollectiviteTerritorialeRepository collectiviteTerritorialeRepository, InputStream inputStream) throws IOException {
        Set<CollectiviteTerritoriale> collectiviteTerritoriales = new HashSet<CollectiviteTerritoriale>();
        try {
            Workbook workbook = WorkbookFactory.create(inputStream);

            workbook.getSheetAt(0).forEach(row -> {
                CollectiviteTerritoriale collectiviteTerritoriale= new CollectiviteTerritoriale();
                collectiviteTerritoriale.setFiled(row.getCell(1).getStringCellValue());

                collectiviteTerritoriales.add(collectiviteTerritoriale);
            });

            collectiviteTerritorialeRepository.saveAll(collectiviteTerritoriales);

            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
