package com.example.demo.Services;

import com.example.demo.Entities.PaysagesUrbains;
import com.example.demo.Entities.Zone;
import com.example.demo.Repository.PaysagesUrbainsRepository;
import com.example.demo.Repository.ZoneRepository;
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
public class ZoneService {

    @Autowired
    private ZoneRepository zoneRepository;

    public List<Zone> getAllZones() {
        return zoneRepository.findAll();
    }

    public Optional<Zone> getZoneById(int id) {
        return zoneRepository.findById(id);
    }

    public Zone saveZone(Zone zone) {
        return zoneRepository.save(zone);
    }

    public void deleteZone(int id) {
        zoneRepository.deleteById(id);
    }

    public void saveExcelData(ZoneRepository zoneRepository, InputStream inputStream) throws IOException {
        Set<Zone> zones = new HashSet<Zone>();
        try {
            Workbook workbook = WorkbookFactory.create(inputStream);

            workbook.getSheetAt(0).forEach(row -> {
                Zone zone= new Zone();
                zone.setZone(row.getCell(0).getStringCellValue());
                zone.setZNiveau(row.getCell(1).getStringCellValue());
                zone.setNbDepartement((int) row.getCell(2).getNumericCellValue());
                zone.setNbCommune((int) row.getCell(3).getNumericCellValue());
                zone.setNbLocalite((int) row.getCell(4).getNumericCellValue());
                zone.setSuperficies((int) row.getCell(5).getNumericCellValue());

                zones.add(zone);
            });

            zoneRepository.saveAll(zones);

            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
