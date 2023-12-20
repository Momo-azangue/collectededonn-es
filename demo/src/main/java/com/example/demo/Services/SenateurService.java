package com.example.demo.Services;

import com.example.demo.Entities.Senateur;
import com.example.demo.Repository.SenateurRepository;
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
public class SenateurService {

    @Autowired
    private SenateurRepository senateurRepository;

    public List<Senateur> getAllSenateurs() {
        return senateurRepository.findAll();
    }

    public Optional<Senateur> getSenateurById(int id) {
        return senateurRepository.findById(id);
    }

    public Senateur saveSenateur(Senateur senateur) {
        return senateurRepository.save(senateur);
    }

    public void deleteSenateur(int id) {
        senateurRepository.deleteById(id);
    }

    public void saveExcelData(SenateurRepository secteursRepository, InputStream inputStream) throws IOException {
        Set<Senateur> senateurs = new HashSet<Senateur>();
        try {
            Workbook workbook = WorkbookFactory.create(inputStream);

            workbook.getSheetAt(0).forEach(row -> {
                Senateur senateur= new Senateur();
                senateur.setQualite(row.getCell(0).getStringCellValue());
                senateur.setNom(row.getCell(1).getStringCellValue());
                senateur.setPrenom(row.getCell(2).getStringCellValue());
                senateur.setMandat(row.getCell(3).getStringCellValue());
                senateur.setElu_Nomme(row.getCell(4).getStringCellValue());

                senateurs.add(senateur);
            });

            senateurRepository.saveAll(senateurs);

            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
