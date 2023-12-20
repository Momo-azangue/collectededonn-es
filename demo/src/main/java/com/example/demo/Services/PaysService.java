package com.example.demo.Services;

import com.example.demo.Entities.Pays;
import com.example.demo.Entities.PaysagesUrbains;
import com.example.demo.Repository.PaysRepository;
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
public class PaysService {

    @Autowired
    private PaysRepository paysRepository;

    public List<Pays> getAllPays() {
        return paysRepository.findAll();
    }

    public Optional<Pays> getPaysByCode(int code) {
        return paysRepository.findById(code);
    }

    public Pays savePays(Pays pays) {
        return paysRepository.save(pays);
    }

    public void deletePays(int code) {
        paysRepository.deleteById(code);
    }

    public void saveExcelData(PaysRepository paysRepository, InputStream inputStream) throws IOException {
        Set<Pays> pays = new HashSet<Pays>();
        try {
            Workbook workbook = WorkbookFactory.create(inputStream);

            workbook.getSheetAt(0).forEach(row -> {
                Pays pays1= new Pays();
               pays1.setCodePays((int) row.getCell(0).getNumericCellValue());
               pays1.setLibelle(row.getCell(1).getStringCellValue());
               pays1.setAccessible(row.getCell(2).getBooleanCellValue());
               pays1.setDensite(row.getCell(3).getStringCellValue());
               pays1.setSuperficie((int) row.getCell(4).getNumericCellValue());
               pays1.setDateIndependance(row.getCell(5).getDateCellValue());
               pays1.setDateReunification(row.getCell(6).getDateCellValue());
               pays1.setDateUnification(row.getCell(7).getDateCellValue());

                pays.add(pays1);
            });

            paysRepository.saveAll(pays);

            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
