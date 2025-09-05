package com.mpt.journal.service;

import com.mpt.journal.model.VinylRecordModel;
import com.mpt.journal.repository.InMemoryVinylRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// Реализация сервиса для работы с виниловыми пластинками
@Service
public class InMemoryVinylRecordServiceImpl implements VinylRecordService {
    
    private final InMemoryVinylRecordRepository vinylRecordRepository;
    
    public InMemoryVinylRecordServiceImpl(InMemoryVinylRecordRepository vinylRecordRepository) {
        this.vinylRecordRepository = vinylRecordRepository;
    }
    
    @Override
    public List<VinylRecordModel> findAllRecords() {
        return vinylRecordRepository.findAllRecords(false);
    }
    
    @Override
    public List<VinylRecordModel> findAllRecords(boolean includeDeleted) {
        return vinylRecordRepository.findAllRecords(includeDeleted);
    }
    
    @Override
    public VinylRecordModel findRecordById(int id) {
        return vinylRecordRepository.findRecordById(id);
    }
    
    @Override
    public VinylRecordModel addRecord(VinylRecordModel record) {
        return vinylRecordRepository.addRecord(record);
    }
    
    @Override
    public VinylRecordModel updateRecord(VinylRecordModel record) {
        return vinylRecordRepository.updateRecord(record);
    }
    
    @Override
    public void deleteRecord(int id) {
        vinylRecordRepository.deleteRecord(id);
    }
    
    @Override
    public void softDeleteRecord(int id) {
        vinylRecordRepository.softDeleteRecord(id);
    }
    
    @Override
    public List<VinylRecordModel> searchRecordsByTitle(String title) {
        return vinylRecordRepository.searchRecordsByTitle(title);
    }
    
    @Override
    public List<VinylRecordModel> searchRecordsByArtist(String artist) {
        return vinylRecordRepository.searchRecordsByArtist(artist);
    }
    
    @Override
    public List<VinylRecordModel> searchRecordsByGenre(String genre) {
        return vinylRecordRepository.searchRecordsByGenre(genre);
    }
    
    @Override
    public List<VinylRecordModel> searchRecordsByVinylCondition(String vinylCondition) {
        return vinylRecordRepository.searchRecordsByVinylCondition(vinylCondition);
    }
    
    @Override
    public List<VinylRecordModel> searchRecordsByCoverCondition(String coverCondition) {
        return vinylRecordRepository.searchRecordsByCoverCondition(coverCondition);
    }
    
    @Override
    public List<VinylRecordModel> filterRecords(String genre, String vinylCondition, String coverCondition) {
        return vinylRecordRepository.filterRecords(genre, vinylCondition, coverCondition);
    }
    
    @Override
    public void deleteMultipleRecords(List<Integer> ids) {
        vinylRecordRepository.deleteMultipleRecords(ids);
    }
    
    @Override
    public void softDeleteMultipleRecords(List<Integer> ids) {
        vinylRecordRepository.softDeleteMultipleRecords(ids);
    }
    
    @Override
    public List<VinylRecordModel> findRecordsWithPagination(int page, int size) {
        List<VinylRecordModel> allRecords = vinylRecordRepository.findAllRecords(false);
        return getPage(allRecords, page, size);
    }
    
    @Override
    public List<VinylRecordModel> findRecordsWithPaginationAndFilter(String genre, String vinylCondition, String coverCondition, int page, int size) {
        List<VinylRecordModel> filteredRecords = vinylRecordRepository.filterRecords(genre, vinylCondition, coverCondition);
        return getPage(filteredRecords, page, size);
    }
    
    // Вспомогательный метод для получения страницы из списка
    private List<VinylRecordModel> getPage(List<VinylRecordModel> records, int page, int size) {
        int start = page * size;
        int end = Math.min(start + size, records.size());
        
        if (start > records.size()) {
            return List.of(); // Возвращаем пустой список, если страница выходит за пределы
        }
        
        return records.subList(start, end);
    }
    
    @Override
    public int getTotalRecordsCount() {
        return vinylRecordRepository.findAllRecords(false).size();
    }
    
    @Override
    public int getFilteredRecordsCount(String genre, String vinylCondition, String coverCondition) {
        return vinylRecordRepository.filterRecords(genre, vinylCondition, coverCondition).size();
    }
    
    @Override
    public List<String> getAllTitles() {
        return vinylRecordRepository.getAllTitles();
    }
    
    @Override
    public List<String> getAllArtists() {
        return vinylRecordRepository.getAllArtists();
    }
    
    @Override
    public List<String> getAllGenres() {
        return vinylRecordRepository.getAllGenres();
    }
    
    @Override
    public List<String> getAllVinylConditions() {
        return vinylRecordRepository.getAllVinylConditions();
    }
    
    @Override
    public List<String> getAllCoverConditions() {
        return vinylRecordRepository.getAllCoverConditions();
    }
}