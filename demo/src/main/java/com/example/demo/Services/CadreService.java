package com.example.demo.Services;

import com.example.demo.Entities.Cadre;
import com.example.demo.Entities.Localite;
import com.example.demo.Repository.CadreRepository;
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
public class CadreService {

    @Autowired
    private CadreRepository cadreRepository;

    public List<Cadre> getAllCadres() {
        return cadreRepository.findAll();
    }

    public Optional<Cadre> getCadreById(int id) {
        return cadreRepository.findById(id);
    }

    public Cadre saveCadre(Cadre cadre) {
        return cadreRepository.save(cadre);
    }

    public void deleteCadre(int id) {
        cadreRepository.deleteById(id);
    }

    public void saveExcelData(CadreRepository cadreRepository, InputStream inputStream) throws IOException {
        Set<Cadre> cadres = new HashSet<Cadre>();
        try {
            Workbook workbook = WorkbookFactory.create(inputStream);

            workbook.getSheetAt(0).forEach(row -> {
                Cadre cadre= new Cadre();
                cadre.setIdCadre((int) row.getCell(0).getNumericCellValue());
                cadre.setCNiveau((int)row.getCell(1).getNumericCellValue());
                cadre.setCadre(row.getCell(2).getStringCellValue());
                cadre.setAccessible((Boolean) row.getCell(3).getBooleanCellValue());

                cadres.add(cadre);
            });

            cadreRepository.saveAll(cadres);

            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
