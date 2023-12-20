package com.example.demo.Services;

import com.example.demo.Entities.PaysagesUrbains;
import com.example.demo.Entities.Region;
import com.example.demo.Repository.PaysagesUrbainsRepository;
import com.example.demo.Repository.RegionRepository;
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
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }

    public Optional<Region> getRegionByCode(int code) {
        return regionRepository.findById(code);
    }

    public Region saveRegion(Region region) {
        return regionRepository.save(region);
    }

    public void deleteRegion(int code) {
        regionRepository.deleteById(code);
    }

    public void saveExcelData(RegionRepository regionRepository, InputStream inputStream) throws IOException {
        Set<Region> regions = new HashSet<Region>();
        try {
            Workbook workbook = WorkbookFactory.create(inputStream);

            workbook.getSheetAt(0).forEach(row -> {
                Region region= new Region();
                region.setCodeRegion((int) row.getCell(0).getNumericCellValue());
                region.setLibelle(row.getCell(1).getStringCellValue());
                region.setAccessible(row.getCell(2).getBooleanCellValue());
                region.setDateCreation(row.getCell(3).getDateCellValue());
                region.setDensite((int) row.getCell(4).getNumericCellValue());
                region.setSuperficie((int) row.getCell(5).getNumericCellValue());

                regions.add(region);
            });

            regionRepository.saveAll(regions);

            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
