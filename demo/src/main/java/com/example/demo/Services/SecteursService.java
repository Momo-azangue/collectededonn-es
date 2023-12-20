package com.example.demo.Services;

import com.example.demo.Entities.PaysagesUrbains;
import com.example.demo.Entities.Secteurs;
import com.example.demo.Repository.PaysagesUrbainsRepository;
import com.example.demo.Repository.SecteursRepository;
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
public class SecteursService {

    @Autowired
    private SecteursRepository secteursRepository;

    public List<Secteurs> getAllSecteurs() {
        return secteursRepository.findAll();
    }

    public Optional<Secteurs> getSecteursById(int id) {
        return secteursRepository.findById(id);
    }

    public Secteurs saveSecteurs(Secteurs secteurs) {
        return secteursRepository.save(secteurs);
    }

    public void deleteSecteurs(int id) {
        secteursRepository.deleteById(id);
    }

    public void saveExcelData(SecteursRepository secteursRepository, InputStream inputStream) throws IOException {
        Set<Secteurs> secteurs = new HashSet<Secteurs>();
        try {
            Workbook workbook = WorkbookFactory.create(inputStream);

            workbook.getSheetAt(0).forEach(row -> {
                Secteurs secteurs1= new Secteurs();
                secteurs1.setLibelle(row.getCell(0).getStringCellValue());

                secteurs.add(secteurs1);
            });

            secteursRepository.saveAll(secteurs);

            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
