package com.example.demo.Services;

import com.example.demo.Entities.Localite;
import com.example.demo.Repository.LocaliteRepository;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class LocaliteService {

    @Autowired
    private LocaliteRepository localiteRepository;

    public List<Localite> getAllLocalites() {
        return localiteRepository.findAll();
    }

    public Optional<Localite> getLocaliteByCode(int codeLocalite) {
        return localiteRepository.findById(codeLocalite);
    }

    public Localite saveLocalite(Localite localite) {
        return localiteRepository.save(localite);
    }

    public void deleteLocalite(int codeLocalite) {
        localiteRepository.deleteById(codeLocalite);
    }

    private static final String EXCEL_FILE_PATH = "G:/test_localite.xlsx";

    private InputStream getFilePath() throws IOException {
        return new ClassPathResource(EXCEL_FILE_PATH).getInputStream();
    }

    public void saveExcelData(LocaliteRepository localiteRepository, InputStream inputStream) throws IOException {
        Set<Localite> localites = new HashSet<Localite>();
        try {
            Workbook workbook = WorkbookFactory.create(inputStream);

            workbook.getSheetAt(0).forEach(row -> {
                Localite localite= new Localite();
                localite.setCodeLocalite((int) row.getCell(0).getNumericCellValue());
                localite.setLibelle(row.getCell(1).getStringCellValue());
                localite.setPNombreMenage((int) row.getCell(2).getNumericCellValue());
                localite.setPPolutaion((float) row.getCell(3).getNumericCellValue());
                localite.setIEEcodeMaternelle(row.getCell(4).getStringCellValue());
                localite.setIEEcodePrimaire(row.getCell(5).getStringCellValue());
                localite.setIEEcodeSecondaire(row.getCell(6).getStringCellValue());

                localites.add(localite);
            });

            localiteRepository.saveAll(localites);

            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
