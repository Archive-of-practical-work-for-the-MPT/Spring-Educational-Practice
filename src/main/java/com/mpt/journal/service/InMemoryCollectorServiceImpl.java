package com.mpt.journal.service;

import com.mpt.journal.model.CollectorModel;
import com.mpt.journal.repository.InMemoryCollectorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// Реализация сервиса для работы с коллекционерами
@Service
public class InMemoryCollectorServiceImpl implements CollectorService {
    
    private final InMemoryCollectorRepository collectorRepository;
    
    public InMemoryCollectorServiceImpl(InMemoryCollectorRepository collectorRepository) {
        this.collectorRepository = collectorRepository;
    }
    
    @Override
    public List<CollectorModel> findAllCollectors() {
        return collectorRepository.findAllCollectors(false);
    }
    
    @Override
    public List<CollectorModel> findAllCollectors(boolean includeDeleted) {
        return collectorRepository.findAllCollectors(includeDeleted);
    }
    
    @Override
    public CollectorModel findCollectorById(int id) {
        return collectorRepository.findCollectorById(id);
    }
    
    @Override
    public CollectorModel addCollector(CollectorModel collector) {
        return collectorRepository.addCollector(collector);
    }
    
    @Override
    public CollectorModel updateCollector(CollectorModel collector) {
        return collectorRepository.updateCollector(collector);
    }
    
    @Override
    public void deleteCollector(int id) {
        collectorRepository.deleteCollector(id);
    }
    
    @Override
    public void softDeleteCollector(int id) {
        collectorRepository.softDeleteCollector(id);
    }
    
    @Override
    public List<CollectorModel> searchCollectorsByFirstName(String firstName) {
        return collectorRepository.searchCollectorsByFirstName(firstName);
    }
    
    @Override
    public List<CollectorModel> searchCollectorsByLastName(String lastName) {
        return collectorRepository.searchCollectorsByLastName(lastName);
    }
    
    @Override
    public List<CollectorModel> searchCollectorsByEmail(String email) {
        return collectorRepository.searchCollectorsByEmail(email);
    }
    
    @Override
    public List<CollectorModel> searchCollectorsByVinylCount(int minCount, int maxCount) {
        return collectorRepository.searchCollectorsByVinylCount(minCount, maxCount);
    }
    
    @Override
    public List<CollectorModel> filterCollectorsByVinylCount(Integer minCount, Integer maxCount) {
        return collectorRepository.filterCollectorsByVinylCount(minCount, maxCount);
    }
    
    @Override
    public void deleteMultipleCollectors(List<Integer> ids) {
        collectorRepository.deleteMultipleCollectors(ids);
    }
    
    @Override
    public void softDeleteMultipleCollectors(List<Integer> ids) {
        collectorRepository.softDeleteMultipleCollectors(ids);
    }
    
    @Override
    public List<CollectorModel> findCollectorsWithPagination(int page, int size) {
        List<CollectorModel> allCollectors = collectorRepository.findAllCollectors(false);
        return getPage(allCollectors, page, size);
    }
    
    @Override
    public List<CollectorModel> findCollectorsWithPaginationAndFilter(Integer minCount, Integer maxCount, int page, int size) {
        List<CollectorModel> filteredCollectors = collectorRepository.filterCollectorsByVinylCount(minCount, maxCount);
        return getPage(filteredCollectors, page, size);
    }
    
    // Вспомогательный метод для получения страницы из списка
    private List<CollectorModel> getPage(List<CollectorModel> collectors, int page, int size) {
        int start = page * size;
        int end = Math.min(start + size, collectors.size());
        
        if (start > collectors.size()) {
            return List.of(); // Возвращаем пустой список, если страница выходит за пределы
        }
        
        return collectors.subList(start, end);
    }
    
    @Override
    public int getTotalCollectorsCount() {
        return collectorRepository.findAllCollectors(false).size();
    }
    
    @Override
    public int getFilteredCollectorsCount(Integer minCount, Integer maxCount) {
        return collectorRepository.filterCollectorsByVinylCount(minCount, maxCount).size();
    }
    
    @Override
    public List<String> getAllFirstNames() {
        return collectorRepository.getAllFirstNames();
    }
    
    @Override
    public List<String> getAllLastNames() {
        return collectorRepository.getAllLastNames();
    }
    
    @Override
    public List<String> getAllEmails() {
        return collectorRepository.getAllEmails();
    }
    
    @Override
    public List<Integer> getAllVinylCounts() {
        return collectorRepository.getAllVinylCounts();
    }
}