package com.mpt.journal.repository;

import com.mpt.journal.model.CollectorModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
// Репозиторий отвечает за хранение и управление данными коллекционеров в памяти. 
// Он предоставляет методы для выполнения операций (обычные CRUD действия с данными)
public class InMemoryCollectorRepository {
    private List<CollectorModel> collectors = new ArrayList<>();
    private AtomicInteger idCounter = new AtomicInteger(1); // Генерация уникального ID

    public CollectorModel addCollector(CollectorModel collector) {
        collector.setId(idCounter.getAndIncrement()); // Установка уникального ID
        collectors.add(collector);
        return collector;
    }

    public CollectorModel updateCollector(CollectorModel collector) {
        for (int i = 0; i < collectors.size(); i++) {
            if (collectors.get(i).getId() == collector.getId()) {
                collectors.set(i, collector);
                return collector;
            }
        }
        return null; // Коллекционер не найден
    }

    // Физическое удаление
    public void deleteCollector(int id) {
        collectors.removeIf(collector -> collector.getId() == id);
    }

    // Логическое удаление
    public void softDeleteCollector(int id) {
        collectors.stream()
                .filter(collector -> collector.getId() == id)
                .findFirst()
                .ifPresent(collector -> collector.setDeleted(true));
    }

    // Множественное физическое удаление
    public void deleteMultipleCollectors(List<Integer> ids) {
        collectors.removeIf(collector -> ids.contains(collector.getId()));
    }

    // Множественное логическое удаление
    public void softDeleteMultipleCollectors(List<Integer> ids) {
        collectors.stream()
                .filter(collector -> ids.contains(collector.getId()))
                .forEach(collector -> collector.setDeleted(true));
    }

    // Получение всех коллекционеров (с возможностью включения удаленных)
    public List<CollectorModel> findAllCollectors(boolean includeDeleted) {
        if (includeDeleted) {
            return new ArrayList<>(collectors);
        } else {
            return collectors.stream()
                    .filter(collector -> !collector.isDeleted())
                    .collect(Collectors.toList());
        }
    }

    public CollectorModel findCollectorById(int id) {
        return collectors.stream()
                .filter(collector -> collector.getId() == id && !collector.isDeleted())
                .findFirst()
                .orElse(null);
    }

    // Поиск по имени
    public List<CollectorModel> searchCollectorsByFirstName(String firstName) {
        return collectors.stream()
                .filter(collector -> !collector.isDeleted() && 
                        collector.getFirstName().toLowerCase().contains(firstName.toLowerCase()))
                .collect(Collectors.toList());
    }

    // Поиск по фамилии
    public List<CollectorModel> searchCollectorsByLastName(String lastName) {
        return collectors.stream()
                .filter(collector -> !collector.isDeleted() && 
                        collector.getLastName().toLowerCase().contains(lastName.toLowerCase()))
                .collect(Collectors.toList());
    }

    // Поиск по email
    public List<CollectorModel> searchCollectorsByEmail(String email) {
        return collectors.stream()
                .filter(collector -> !collector.isDeleted() && 
                        collector.getEmail().toLowerCase().contains(email.toLowerCase()))
                .collect(Collectors.toList());
    }
    
    // Поиск по количеству пластинок
    public List<CollectorModel> searchCollectorsByVinylCount(int minCount, int maxCount) {
        return collectors.stream()
                .filter(collector -> !collector.isDeleted() && 
                        collector.getVinylCount() >= minCount && collector.getVinylCount() <= maxCount)
                .collect(Collectors.toList());
    }

    // Фильтрация по количеству пластинок
    public List<CollectorModel> filterCollectorsByVinylCount(Integer minCount, Integer maxCount) {
        return collectors.stream()
                .filter(collector -> !collector.isDeleted())
                .filter(collector -> minCount == null || collector.getVinylCount() >= minCount)
                .filter(collector -> maxCount == null || collector.getVinylCount() <= maxCount)
                .collect(Collectors.toList());
    }
    
    // Старая фильтрация для совместимости
    public List<CollectorModel> filterCollectors(String firstName, String lastName, String email) {
        return collectors.stream()
                .filter(collector -> !collector.isDeleted())
                .filter(collector -> firstName == null || firstName.isEmpty() || 
                        collector.getFirstName().toLowerCase().contains(firstName.toLowerCase()))
                .filter(collector -> lastName == null || lastName.isEmpty() || 
                        collector.getLastName().toLowerCase().contains(lastName.toLowerCase()))
                .filter(collector -> email == null || email.isEmpty() || 
                        collector.getEmail().toLowerCase().contains(email.toLowerCase()))
                .collect(Collectors.toList());
    }
    
    // Получение уникальных имен для комбобокса
    public List<String> getAllFirstNames() {
        return collectors.stream()
                .filter(collector -> !collector.isDeleted())
                .map(CollectorModel::getFirstName)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }
    
    // Получение уникальных фамилий для комбобокса
    public List<String> getAllLastNames() {
        return collectors.stream()
                .filter(collector -> !collector.isDeleted())
                .map(CollectorModel::getLastName)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }
    
    // Получение уникальных email для комбобокса
    public List<String> getAllEmails() {
        return collectors.stream()
                .filter(collector -> !collector.isDeleted())
                .map(CollectorModel::getEmail)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }
    
    // Получение уникальных значений количества пластинок для комбобокса
    public List<Integer> getAllVinylCounts() {
        return collectors.stream()
                .filter(collector -> !collector.isDeleted())
                .map(CollectorModel::getVinylCount)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }
}