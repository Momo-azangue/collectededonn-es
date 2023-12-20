package com.example.demo.Services;

import com.example.demo.Entities.Gouverneur;
import com.example.demo.Entities.Localite;
import com.example.demo.Repository.GouverneurRepository;
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
public class GouverneurService {

    @Autowired
    private GouverneurRepository gouverneurRepository;

    public List<Gouverneur> getAllGouverneurs() {
        return gouverneurRepository.findAll();
    }

    public Optional<Gouverneur> getGouverneurById(int id) {
        return gouverneurRepository.findById(id);
    }

    public Gouverneur saveGouverneur(Gouverneur gouverneur) {
        return gouverneurRepository.save(gouverneur);
    }

    public void deleteGouverneur(int id) {
        gouverneurRepository.deleteById(id);
    }

    public void saveExcelData(GouverneurRepository gouverneurRepository, InputStream inputStream) throws IOException {
        Set<Gouverneur> gouverneurs = new HashSet<Gouverneur>();
        try {
            Workbook workbook = WorkbookFactory.create(inputStream);

            workbook.getSheetAt(0).forEach(row -> {
                Gouverneur gouverneur= new Gouverneur();
               gouverneur.setNomGouverneur(row.getCell(0).getStringCellValue());
               gouverneur.setPrenomGouverneur(row.getCell(1).getStringCellValue());
               gouverneur.setElu_Nomme(row.getCell(2).getBooleanCellValue());

                gouverneurs.add(gouverneur);
            });

            gouverneurRepository.saveAll(gouverneurs);

            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
