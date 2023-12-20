package com.example.demo.Services;

import com.example.demo.Entities.PaysagesUrbains;
import com.example.demo.Entities.Probleme;
import com.example.demo.Repository.PaysagesUrbainsRepository;
import com.example.demo.Repository.ProblemeRepository;
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
public class ProblemeService {

    @Autowired
    private ProblemeRepository problemeRepository;

    public List<Probleme> getAllProblemes() {
        return problemeRepository.findAll();
    }

    public Optional<Probleme> getProblemeById(int id) {
        return problemeRepository.findById(id);
    }

    public Probleme saveProbleme(Probleme probleme) {
        return problemeRepository.save(probleme);
    }

    public void deleteProbleme(int id) {
        problemeRepository.deleteById(id);
    }

    public void saveExcelData(ProblemeRepository problemeRepository, InputStream inputStream) throws IOException {
        Set<Probleme> problemes = new HashSet<Probleme>();
        try {
            Workbook workbook = WorkbookFactory.create(inputStream);

            workbook.getSheetAt(0).forEach(row -> {
                Probleme probleme= new Probleme();
                probleme.setProbleme(row.getCell(0).getStringCellValue());
                probleme.setArchive(row.getCell(1).getBooleanCellValue());
                problemes.add(probleme);
            });

            problemeRepository.saveAll(problemes);

            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
