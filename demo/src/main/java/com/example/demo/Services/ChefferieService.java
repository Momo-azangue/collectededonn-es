package com.example.demo.Services;

import com.example.demo.Entities.Chefferie;
import com.example.demo.Entities.Localite;
import com.example.demo.Repository.ChefferieRepository;
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
public class ChefferieService {

    @Autowired
    private ChefferieRepository chefferieRepository;

    public List<Chefferie> getAllChefferies() {
        return chefferieRepository.findAll();
    }

    public Optional<Chefferie> getChefferieById(int id) {
        return chefferieRepository.findById(id);
    }

    public Chefferie saveChefferie(Chefferie chefferie) {
        return chefferieRepository.save(chefferie);
    }

    public void deleteChefferie(int id) {
        chefferieRepository.deleteById(id);
    }

    public void saveExcelData(ChefferieRepository chefferieRepository, InputStream inputStream) throws IOException {
        Set<Chefferie> chefferies = new HashSet<Chefferie>();
        try {
            Workbook workbook = WorkbookFactory.create(inputStream);

            workbook.getSheetAt(0).forEach(row -> {
                Chefferie chefferie= new Chefferie();
                chefferie.setLibelleChefferie(row.getCell(0).getStringCellValue());
                chefferie.setClassification(row.getCell(1).getStringCellValue());
                chefferie.setNActeDeterminant(row.getCell(2).getStringCellValue());
                chefferie.setNomDuChef(row.getCell(3).getStringCellValue());
                chefferie.setQualification(row.getCell(4).getStringCellValue());
                chefferie.setAnneAuTrone(row.getCell(5).getStringCellValue());

                chefferies.add(chefferie);
            });

            chefferieRepository.saveAll(chefferies);

            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
