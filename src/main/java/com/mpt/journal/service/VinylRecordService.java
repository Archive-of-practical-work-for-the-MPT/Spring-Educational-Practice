package com.mpt.journal.service;

import com.mpt.journal.model.VinylRecordModel;

import java.util.List;

// Интерфейс сервиса для работы с виниловыми пластинками
public interface VinylRecordService {
    // Базовые CRUD операции
    List<VinylRecordModel> findAllRecords();
    List<VinylRecordModel> findAllRecords(boolean includeDeleted);
    VinylRecordModel findRecordById(int id);
    VinylRecordModel addRecord(VinylRecordModel record);
    VinylRecordModel updateRecord(VinylRecordModel record);
    
    // Удаление
    void deleteRecord(int id); // Физическое удаление
    void softDeleteRecord(int id); // Логическое удаление
    
    // Поиск по параметрам
    List<VinylRecordModel> searchRecordsByTitle(String title);
    List<VinylRecordModel> searchRecordsByArtist(String artist);
    List<VinylRecordModel> searchRecordsByGenre(String genre);
    List<VinylRecordModel> searchRecordsByVinylCondition(String vinylCondition);
    List<VinylRecordModel> searchRecordsByCoverCondition(String coverCondition);
    
    // Фильтрация по критериям (жанр, состояние винила, состояние конверта)
    List<VinylRecordModel> filterRecords(String genre, String vinylCondition, String coverCondition);
    
    // Множественное удаление
    void deleteMultipleRecords(List<Integer> ids); // Физическое множественное удаление
    void softDeleteMultipleRecords(List<Integer> ids); // Логическое множественное удаление
    
    // Пагинация
    List<VinylRecordModel> findRecordsWithPagination(int page, int size);
    List<VinylRecordModel> findRecordsWithPaginationAndFilter(String genre, String vinylCondition, String coverCondition, int page, int size);
    
    // Получение общего количества записей
    int getTotalRecordsCount();
    int getFilteredRecordsCount(String genre, String vinylCondition, String coverCondition);
    
    // Получение уникальных значений для комбобоксов
    List<String> getAllTitles();
    List<String> getAllArtists();
    List<String> getAllGenres();
    List<String> getAllVinylConditions();
    List<String> getAllCoverConditions();
}