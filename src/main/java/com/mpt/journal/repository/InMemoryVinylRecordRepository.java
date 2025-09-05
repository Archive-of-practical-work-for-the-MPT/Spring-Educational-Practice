package com.mpt.journal.repository;

import com.mpt.journal.model.VinylRecordModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
// Репозиторий отвечает за хранение и управление данными виниловых пластинок в памяти. 
// Он предоставляет методы для выполнения операций (обычные CRUD действия с данными)
public class InMemoryVinylRecordRepository {
    private List<VinylRecordModel> records = new ArrayList<>();
    private AtomicInteger idCounter = new AtomicInteger(1); // Генерация уникального ID

    public VinylRecordModel addRecord(VinylRecordModel record) {
        record.setId(idCounter.getAndIncrement()); // Установка уникального ID
        records.add(record);
        return record;
    }

    public VinylRecordModel updateRecord(VinylRecordModel record) {
        for (int i = 0; i < records.size(); i++) {
            if (records.get(i).getId() == record.getId()) {
                records.set(i, record);
                return record;
            }
        }
        return null; // Пластинка не найдена
    }

    // Физическое удаление
    public void deleteRecord(int id) {
        records.removeIf(record -> record.getId() == id);
    }

    // Логическое удаление
    public void softDeleteRecord(int id) {
        records.stream()
                .filter(record -> record.getId() == id)
                .findFirst()
                .ifPresent(record -> record.setDeleted(true));
    }

    // Множественное физическое удаление
    public void deleteMultipleRecords(List<Integer> ids) {
        records.removeIf(record -> ids.contains(record.getId()));
    }

    // Множественное логическое удаление
    public void softDeleteMultipleRecords(List<Integer> ids) {
        records.stream()
                .filter(record -> ids.contains(record.getId()))
                .forEach(record -> record.setDeleted(true));
    }

    // Получение всех записей (с возможностью включения удаленных)
    public List<VinylRecordModel> findAllRecords(boolean includeDeleted) {
        if (includeDeleted) {
            return new ArrayList<>(records);
        } else {
            return records.stream()
                    .filter(record -> !record.isDeleted())
                    .collect(Collectors.toList());
        }
    }

    public VinylRecordModel findRecordById(int id) {
        return records.stream()
                .filter(record -> record.getId() == id && !record.isDeleted())
                .findFirst()
                .orElse(null);
    }

    // Поиск по названию
    public List<VinylRecordModel> searchRecordsByTitle(String title) {
        return records.stream()
                .filter(record -> !record.isDeleted() && 
                        record.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    // Поиск по исполнителю
    public List<VinylRecordModel> searchRecordsByArtist(String artist) {
        return records.stream()
                .filter(record -> !record.isDeleted() && 
                        record.getArtist().toLowerCase().contains(artist.toLowerCase()))
                .collect(Collectors.toList());
    }

    // Поиск по жанру
    public List<VinylRecordModel> searchRecordsByGenre(String genre) {
        return records.stream()
                .filter(record -> !record.isDeleted() && 
                        record.getGenre().toLowerCase().contains(genre.toLowerCase()))
                .collect(Collectors.toList());
    }
    
    // Поиск по состоянию винила
    public List<VinylRecordModel> searchRecordsByVinylCondition(String vinylCondition) {
        return records.stream()
                .filter(record -> !record.isDeleted() && 
                        record.getVinylCondition().toLowerCase().contains(vinylCondition.toLowerCase()))
                .collect(Collectors.toList());
    }
    
    // Поиск по состоянию конверта
    public List<VinylRecordModel> searchRecordsByCoverCondition(String coverCondition) {
        return records.stream()
                .filter(record -> !record.isDeleted() && 
                        record.getCoverCondition().toLowerCase().contains(coverCondition.toLowerCase()))
                .collect(Collectors.toList());
    }

    // Фильтрация по трем критериям (жанр, состояние винила, состояние конверта)
    public List<VinylRecordModel> filterRecords(String genre, String vinylCondition, String coverCondition) {
        return records.stream()
                .filter(record -> !record.isDeleted())
                .filter(record -> genre == null || genre.isEmpty() || 
                        record.getGenre().toLowerCase().contains(genre.toLowerCase()))
                .filter(record -> vinylCondition == null || vinylCondition.isEmpty() || 
                        record.getVinylCondition().toLowerCase().contains(vinylCondition.toLowerCase()))
                .filter(record -> coverCondition == null || coverCondition.isEmpty() || 
                        record.getCoverCondition().toLowerCase().contains(coverCondition.toLowerCase()))
                .collect(Collectors.toList());
    }
    
    // Получение уникальных названий для комбобокса
    public List<String> getAllTitles() {
        return records.stream()
                .filter(record -> !record.isDeleted())
                .map(VinylRecordModel::getTitle)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }
    
    // Получение уникальных исполнителей для комбобокса
    public List<String> getAllArtists() {
        return records.stream()
                .filter(record -> !record.isDeleted())
                .map(VinylRecordModel::getArtist)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }
    
    // Получение уникальных жанров для комбобокса
    public List<String> getAllGenres() {
        return records.stream()
                .filter(record -> !record.isDeleted())
                .map(VinylRecordModel::getGenre)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }
    
    // Получение уникальных состояний винила для комбобокса
    public List<String> getAllVinylConditions() {
        return records.stream()
                .filter(record -> !record.isDeleted())
                .map(VinylRecordModel::getVinylCondition)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }
    
    // Получение уникальных состояний конверта для комбобокса
    public List<String> getAllCoverConditions() {
        return records.stream()
                .filter(record -> !record.isDeleted())
                .map(VinylRecordModel::getCoverCondition)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }
}