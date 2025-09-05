package com.mpt.journal.service;

import com.mpt.journal.model.CollectorModel;

import java.util.List;

// Интерфейс сервиса для работы с коллекционерами
public interface CollectorService {
    // Базовые CRUD операции
    List<CollectorModel> findAllCollectors();
    List<CollectorModel> findAllCollectors(boolean includeDeleted);
    CollectorModel findCollectorById(int id);
    CollectorModel addCollector(CollectorModel collector);
    CollectorModel updateCollector(CollectorModel collector);
    
    // Удаление
    void deleteCollector(int id); // Физическое удаление
    void softDeleteCollector(int id); // Логическое удаление
    
    // Поиск по параметрам
    List<CollectorModel> searchCollectorsByFirstName(String firstName);
    List<CollectorModel> searchCollectorsByLastName(String lastName);
    List<CollectorModel> searchCollectorsByEmail(String email);
    List<CollectorModel> searchCollectorsByVinylCount(int minCount, int maxCount);
    
    // Фильтрация по количеству пластинок
    List<CollectorModel> filterCollectorsByVinylCount(Integer minCount, Integer maxCount);
    
    // Множественное удаление
    void deleteMultipleCollectors(List<Integer> ids); // Физическое множественное удаление
    void softDeleteMultipleCollectors(List<Integer> ids); // Логическое множественное удаление
    
    // Пагинация
    List<CollectorModel> findCollectorsWithPagination(int page, int size);
    List<CollectorModel> findCollectorsWithPaginationAndFilter(Integer minCount, Integer maxCount, int page, int size);
    
    // Получение общего количества записей
    int getTotalCollectorsCount();
    int getFilteredCollectorsCount(Integer minCount, Integer maxCount);
    
    // Получение уникальных значений для комбобоксов
    List<String> getAllFirstNames();
    List<String> getAllLastNames();
    List<String> getAllEmails();
    List<Integer> getAllVinylCounts();
}