package com.example.demo.Services;

import com.example.demo.Entities.Cadre;
import com.example.demo.Entities.Commune;
import com.example.demo.Repository.CadreRepository;
import com.example.demo.Repository.CommuneRepository;
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
public class CommuneService {

    @Autowired
    private CommuneRepository communeRepository;


    public List<Commune> getAllCommunes() {return  communeRepository.findAll(); }

    public Optional<Commune> getCommuneById(int id){ return  communeRepository.findById(id);}

    public  Commune saveCommune(Commune commune) {return communeRepository.save(commune);}

    public void delete(int id){communeRepository.deleteById(id);}

    public void saveExcelData(CommuneRepository communeRepository, InputStream inputStream) throws IOException {
        Set<Commune> communes = new HashSet<Commune>();
        try {
            Workbook workbook = WorkbookFactory.create(inputStream);

            workbook.getSheetAt(0).forEach(row -> {
                Commune commune = new Commune();

                commune.setCodeCommune((int) row.getCell(0).getNumericCellValue());
                commune.setDensitee((int) row.getCell(13).getNumericCellValue());
                commune.setNbLocalite((int)row.getCell(14).getNumericCellValue());
                commune.setLibelle(row.getCell(3).getStringCellValue());
                commune.setAccessible( row.getCell(12).getBooleanCellValue());
                commune.setSuperficie((int)row.getCell(9).getNumericCellValue());
                commune.setAdresse(row.getCell(10).getStringCellValue());
                commune.setLocalisation(row.getCell(11).getStringCellValue());
                commune.setEmail(row.getCell(8).getStringCellValue());
                commune.setDateCreation(row.getCell(7).getDateCellValue());
                communes.add(commune);
            });

            communeRepository.saveAll(communes);

            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}
