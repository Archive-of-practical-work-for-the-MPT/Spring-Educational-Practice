package com.mpt.journal.config;

import com.mpt.journal.model.CollectorModel;
import com.mpt.journal.model.VinylRecordModel;
import com.mpt.journal.service.CollectorService;
import com.mpt.journal.service.VinylRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private VinylRecordService vinylRecordService;
    
    @Autowired
    private CollectorService collectorService;

    @Override
    public void run(String... args) throws Exception {
        // Добавляем тестовые данные о виниловых пластинках
        vinylRecordService.addRecord(new VinylRecordModel(0, "Abbey Road", "The Beatles", "Rock", 1969, "VG", "VG"));
        vinylRecordService.addRecord(new VinylRecordModel(0, "Dark Side of the Moon", "Pink Floyd", "Progressive Rock", 1973, "NM", "M"));
        vinylRecordService.addRecord(new VinylRecordModel(0, "Hotel California", "Eagles", "Rock", 1976, "M", "NM"));
        vinylRecordService.addRecord(new VinylRecordModel(0, "Led Zeppelin IV", "Led Zeppelin", "Hard Rock", 1971, "VG", "B"));
        vinylRecordService.addRecord(new VinylRecordModel(0, "Rumours", "Fleetwood Mac", "Pop Rock", 1977, "B", "VG"));
        vinylRecordService.addRecord(new VinylRecordModel(0, "Born to Run", "Bruce Springsteen", "Rock", 1975, "M", "M"));
        vinylRecordService.addRecord(new VinylRecordModel(0, "Sticky Fingers", "The Rolling Stones", "Rock", 1971, "P", "P"));
        vinylRecordService.addRecord(new VinylRecordModel(0, "What's Going On", "Marvin Gaye", "Soul", 1971, "NM", "VG"));
        vinylRecordService.addRecord(new VinylRecordModel(0, "Blue", "Joni Mitchell", "Folk", 1971, "VG", "M"));
        vinylRecordService.addRecord(new VinylRecordModel(0, "Pet Sounds", "The Beach Boys", "Pop", 1966, "M", "NM"));
        vinylRecordService.addRecord(new VinylRecordModel(0, "London Calling", "The Clash", "Punk Rock", 1979, "B", "B"));
        vinylRecordService.addRecord(new VinylRecordModel(0, "Who's Next", "The Who", "Hard Rock", 1971, "VG", "VG"));
        
        // Добавляем тестовые данные о коллекционерах
        collectorService.addCollector(new CollectorModel(0, "Иван", "Иванов", "ivan@example.com", "+7(999)123-45-67", 15));
        collectorService.addCollector(new CollectorModel(0, "Петр", "Петров", "petr@example.com", "+7(999)234-56-78", 25));
        collectorService.addCollector(new CollectorModel(0, "Мария", "Сидорова", "maria@example.com", "+7(999)345-67-89", 8));
        collectorService.addCollector(new CollectorModel(0, "Елена", "Козлова", "elena@example.com", "+7(999)456-78-90", 32));
        collectorService.addCollector(new CollectorModel(0, "Алексей", "Морозов", "alexey@example.com", "+7(999)567-89-01", 12));
    }
}