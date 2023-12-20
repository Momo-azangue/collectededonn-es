package com.example.demo.Services;

import com.example.demo.Entities.Departement;
import com.example.demo.Entities.Localite;
import com.example.demo.Repository.DepartementRepository;
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
public class DepartementService {

    @Autowired
    private DepartementRepository departementRepository;

    public List<Departement> getAllDepartements() {
        return departementRepository.findAll();
    }

    public Optional<Departement> getDepartementById(int id) {
        return departementRepository.findById(id);
    }

    public Departement saveDepartement(Departement departement) {
        return departementRepository.save(departement);
    }

    public void deleteDepartement(int id) {
        departementRepository.deleteById(id);
    }

    public void saveExcelData(DepartementRepository departementRepository, InputStream inputStream) throws IOException {
        Set<Departement> departements = new HashSet<Departement>();
        try {
            Workbook workbook = WorkbookFactory.create(inputStream);

            workbook.getSheetAt(0).forEach(row -> {
                Departement departement= new Departement();
                departement.setNomDepartement(row.getCell(1).getStringCellValue());

                departements.add(departement);
            });

            departementRepository.saveAll(departements);

            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
