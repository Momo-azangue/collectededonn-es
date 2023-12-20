package com.example.demo.Services;

import com.example.demo.Entities.Localite;
import com.example.demo.Entities.PaysagesUrbains;
import com.example.demo.Repository.LocaliteRepository;
import com.example.demo.Repository.PaysagesUrbainsRepository;
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
public class PaysagesUrbainsService {

    @Autowired
    private PaysagesUrbainsRepository paysagesUrbainsRepository;

    public List<PaysagesUrbains> getAllPaysagesUrbains() {
        return paysagesUrbainsRepository.findAll();
    }

    public Optional<PaysagesUrbains> getPaysagesUrbainsById(int id) {
        return paysagesUrbainsRepository.findById(id);
    }

    public PaysagesUrbains savePaysagesUrbains(PaysagesUrbains paysagesUrbains) {
        return paysagesUrbainsRepository.save(paysagesUrbains);
    }

    public void deletePaysagesUrbains(int id) {
        paysagesUrbainsRepository.deleteById(id);
    }

    public void saveExcelData(PaysagesUrbainsRepository paysagesUrbainsRepository, InputStream inputStream) throws IOException {
        Set<PaysagesUrbains> paysagesUrbains = new HashSet<PaysagesUrbains>();
        try {
            Workbook workbook = WorkbookFactory.create(inputStream);

            workbook.getSheetAt(0).forEach(row -> {
                PaysagesUrbains paysagesUrbains1= new PaysagesUrbains();
               paysagesUrbains1.setUnitePaysage(row.getCell(0).getStringCellValue());
               paysagesUrbains1.setUtilisation(row.getCell(1).getStringCellValue());
               paysagesUrbains1.setPotentialite(row.getCell(2).getStringCellValue());
               paysagesUrbains1.setUtilisateur(row.getCell(3).getStringCellValue());
               paysagesUrbains1.setProbleme(row.getCell(4).getStringCellValue());
               paysagesUrbains1.setCauses(row.getCell(5).getStringCellValue());
               paysagesUrbains1.setConsequences(row.getCell(6).getStringCellValue());
                paysagesUrbains1.setSolutions(row.getCell(7).getStringCellValue());

                paysagesUrbains.add(paysagesUrbains1);
            });

            paysagesUrbainsRepository.saveAll(paysagesUrbains);

            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
