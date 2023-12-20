package com.example.demo.Services;

import com.example.demo.Entities.Infrastructure;
import com.example.demo.Entities.Localite;
import com.example.demo.Repository.InfrastructureRepository;
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
public class InfrastructureService {

    @Autowired
    private InfrastructureRepository infrastructureRepository;

    public List<Infrastructure> getAllInfrastructures() {
        return infrastructureRepository.findAll();
    }

    public Optional<Infrastructure> getInfrastructureById(int id) {
        return infrastructureRepository.findById(id);
    }

    public Infrastructure saveInfrastructure(Infrastructure infrastructure) {
        return infrastructureRepository.save(infrastructure);
    }

    public void deleteInfrastructure(int id) {
        infrastructureRepository.deleteById(id);
    }

    public void saveExcelData(InfrastructureRepository infrastructureRepository, InputStream inputStream) throws IOException {
        Set<Infrastructure> infrastructures = new HashSet<Infrastructure>();
        try {
            Workbook workbook = WorkbookFactory.create(inputStream);

            workbook.getSheetAt(0).forEach(row -> {
                Infrastructure infrastructure= new Infrastructure();
                infrastructure.setLibelle(row.getCell(1).getStringCellValue());
                infrastructure.setEtat(row.getCell(2).getStringCellValue());
                infrastructure.setQte((int) row.getCell(3).getNumericCellValue());
                infrastructure.setBeneficiaire(row.getCell(4).getStringCellValue());
                infrastructure.setObservation(row.getCell(5).getStringCellValue());
                infrastructure.setGroupeInfrastructure(row.getCell(6).getStringCellValue());

                infrastructures.add(infrastructure);
            });

            infrastructureRepository.saveAll(infrastructures);

            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
