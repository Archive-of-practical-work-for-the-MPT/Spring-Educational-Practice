package com.mpt.journal.service;

import com.mpt.journal.model.VinylRecordModel;
import com.mpt.journal.repository.InMemoryVinylRecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryVinylRecordServiceImplTest {

    private InMemoryVinylRecordRepository repository;
    private InMemoryVinylRecordServiceImpl service;

    @BeforeEach
    void setUp() {
        repository = new InMemoryVinylRecordRepository();
        service = new InMemoryVinylRecordServiceImpl(repository);
    }

    @Test
    void testAddAndFindRecord() {
        VinylRecordModel record = new VinylRecordModel(0, "Test Album", "Test Artist", "Test Genre", 2020, "VG", "VG");
        VinylRecordModel savedRecord = service.addRecord(record);
        
        assertNotNull(savedRecord);
        assertTrue(savedRecord.getId() > 0);
        assertEquals("Test Album", savedRecord.getTitle());
        
        VinylRecordModel foundRecord = service.findRecordById(savedRecord.getId());
        assertNotNull(foundRecord);
        assertEquals(savedRecord.getId(), foundRecord.getId());
    }

    @Test
    void testUpdateRecord() {
        VinylRecordModel record = new VinylRecordModel(0, "Original Album", "Original Artist", "Original Genre", 2020, "VG", "VG");
        VinylRecordModel savedRecord = service.addRecord(record);
        
        savedRecord.setTitle("Updated Album");
        VinylRecordModel updatedRecord = service.updateRecord(savedRecord);
        
        assertNotNull(updatedRecord);
        assertEquals("Updated Album", updatedRecord.getTitle());
    }

    @Test
    void testDeleteRecord() {
        VinylRecordModel record = new VinylRecordModel(0, "Test Album", "Test Artist", "Test Genre", 2020, "VG", "VG");
        VinylRecordModel savedRecord = service.addRecord(record);
        
        service.deleteRecord(savedRecord.getId());
        VinylRecordModel foundRecord = service.findRecordById(savedRecord.getId());
        assertNull(foundRecord);
    }

    @Test
    void testSoftDeleteRecord() {
        VinylRecordModel record = new VinylRecordModel(0, "Test Album", "Test Artist", "Test Genre", 2020, "VG", "VG");
        VinylRecordModel savedRecord = service.addRecord(record);
        
        service.softDeleteRecord(savedRecord.getId());
        VinylRecordModel foundRecord = service.findRecordById(savedRecord.getId());
        assertNull(foundRecord);
        
        // Проверяем, что запись все еще существует в репозитории, но помечена как удаленная
        List<VinylRecordModel> allRecords = service.findAllRecords(true);
        boolean found = allRecords.stream().anyMatch(r -> r.getId() == savedRecord.getId() && r.isDeleted());
        assertTrue(found);
    }

    @Test
    void testSearchRecordsByTitle() {
        service.addRecord(new VinylRecordModel(0, "Abbey Road", "The Beatles", "Rock", 1969, "VG", "VG"));
        service.addRecord(new VinylRecordModel(0, "Dark Side of the Moon", "Pink Floyd", "Progressive Rock", 1973, "VG", "VG"));
        
        List<VinylRecordModel> results = service.searchRecordsByTitle("Abbey");
        assertEquals(1, results.size());
        assertEquals("Abbey Road", results.get(0).getTitle());
    }

    @Test
    void testFilterRecords() {
        service.addRecord(new VinylRecordModel(0, "Abbey Road", "The Beatles", "Rock", 1969, "VG", "VG"));
        service.addRecord(new VinylRecordModel(0, "Dark Side of the Moon", "Pink Floyd", "Progressive Rock", 1973, "VG", "VG"));
        service.addRecord(new VinylRecordModel(0, "Hotel California", "Eagles", "Rock", 1976, "VG", "VG"));
        
        List<VinylRecordModel> results = service.filterRecords("California", "Eagles", "Rock");
        assertEquals(1, results.size());
        assertEquals("Hotel California", results.get(0).getTitle());
    }

    @Test
    void testDeleteMultipleRecords() {
        VinylRecordModel record1 = service.addRecord(new VinylRecordModel(0, "Album 1", "Artist 1", "Genre 1", 2020, "VG", "VG"));
        VinylRecordModel record2 = service.addRecord(new VinylRecordModel(0, "Album 2", "Artist 2", "Genre 2", 2021, "VG", "VG"));
        VinylRecordModel record3 = service.addRecord(new VinylRecordModel(0, "Album 3", "Artist 3", "Genre 3", 2022, "VG", "VG"));
        
        List<Integer> idsToDelete = Arrays.asList(record1.getId(), record2.getId());
        service.deleteMultipleRecords(idsToDelete);
        
        List<VinylRecordModel> remainingRecords = service.findAllRecords();
        assertEquals(1, remainingRecords.size());
        assertEquals(record3.getId(), remainingRecords.get(0).getId());
    }

    @Test
    void testPagination() {
        // Добавляем 15 записей
        for (int i = 1; i <= 15; i++) {
            service.addRecord(new VinylRecordModel(0, "Album " + i, "Artist " + i, "Genre " + i, 2000 + i, "VG", "VG"));
        }
        
        List<VinylRecordModel> page = service.findRecordsWithPagination(0, 10);
        
        assertEquals(10, page.size());
        // Проверяем, что у нас есть вторая страница
        List<VinylRecordModel> secondPage = service.findRecordsWithPagination(1, 10);
        assertEquals(5, secondPage.size());
    }
}