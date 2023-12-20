package com.example.demo.Services;

import com.example.demo.Entities.Localite;
import com.example.demo.Entities.MInfrastructure;
import com.example.demo.Repository.LocaliteRepository;
import com.example.demo.Repository.MInfrastructureRepository;
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
public class MInfrastructureService {

    @Autowired
    private MInfrastructureRepository mInfrastructureRepository;

    public List<MInfrastructure> getAllMInfrastructures() {
        return mInfrastructureRepository.findAll();
    }

    public Optional<MInfrastructure> getMInfrastructureById(int id) {
        return mInfrastructureRepository.findById(id);
    }

    public MInfrastructure saveMInfrastructure(MInfrastructure mInfrastructure) {
        return mInfrastructureRepository.save(mInfrastructure);
    }

    public void deleteMInfrastructure(int id) {
        mInfrastructureRepository.deleteById(id);
    }

    public void saveExcelData(MInfrastructureRepository mInfrastructureRepository, InputStream inputStream) throws IOException {
        Set<MInfrastructure> mInfrastructures = new HashSet<MInfrastructure>();
        try {
            Workbook workbook = WorkbookFactory.create(inputStream);

            workbook.getSheetAt(0).forEach(row -> {
                MInfrastructure mInfrastructure= new MInfrastructure();
                mInfrastructure.setMInfrastructure(row.getCell(0).getStringCellValue());
                mInfrastructures.add(mInfrastructure);
            });

            mInfrastructureRepository.saveAll(mInfrastructures);

            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
