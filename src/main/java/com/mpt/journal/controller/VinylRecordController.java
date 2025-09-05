package com.mpt.journal.controller;

import com.mpt.journal.model.VinylRecordModel;
import com.mpt.journal.service.VinylRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// Контроллер для работы с виниловыми пластинками
@Controller
@RequestMapping("/vinyls")
public class VinylRecordController {

    @Autowired
    private VinylRecordService vinylRecordService;

    @GetMapping
    public String getAllRecords(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String artist,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String vinylCondition,
            @RequestParam(required = false) String coverCondition,
            Model model) {
        
        List<VinylRecordModel> records;
        int totalRecords;
        
        // Если есть параметры фильтрации по трем критериям, используем их
        if ((genre != null && !genre.isEmpty()) || 
            (vinylCondition != null && !vinylCondition.isEmpty()) || 
            (coverCondition != null && !coverCondition.isEmpty())) {
            records = vinylRecordService.findRecordsWithPaginationAndFilter(genre, vinylCondition, coverCondition, page, size);
            totalRecords = vinylRecordService.getFilteredRecordsCount(genre, vinylCondition, coverCondition);
        } else if ((title != null && !title.isEmpty()) || 
            (artist != null && !artist.isEmpty())) {
            // Сохраняем старую фильтрацию для совместимости
            records = vinylRecordService.findRecordsWithPaginationAndFilter(title, artist, genre, page, size);
            totalRecords = vinylRecordService.getFilteredRecordsCount(title, artist, genre);
        } else {
            records = vinylRecordService.findRecordsWithPagination(page, size);
            totalRecords = vinylRecordService.getTotalRecordsCount();
        }
        
        int totalPages = (int) Math.ceil((double) totalRecords / size);
        
        // Получаем уникальные значения для комбобоксов
        List<String> allTitles = vinylRecordService.getAllTitles();
        List<String> allArtists = vinylRecordService.getAllArtists();
        List<String> allGenres = vinylRecordService.getAllGenres();
        List<String> allVinylConditions = vinylRecordService.getAllVinylConditions();
        List<String> allCoverConditions = vinylRecordService.getAllCoverConditions();
        
        model.addAttribute("records", records);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalRecords);
        model.addAttribute("size", size);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("title", title);
        model.addAttribute("artist", artist);
        model.addAttribute("genre", genre);
        model.addAttribute("vinylCondition", vinylCondition);
        model.addAttribute("coverCondition", coverCondition);
        model.addAttribute("allTitles", allTitles);
        model.addAttribute("allArtists", allArtists);
        model.addAttribute("allGenres", allGenres);
        model.addAttribute("allVinylConditions", allVinylConditions);
        model.addAttribute("allCoverConditions", allCoverConditions);
        
        return "vinylRecordList";
    }

    @PostMapping("/add")
    public String addRecord(@RequestParam String title,
                           @RequestParam String artist,
                           @RequestParam String genre,
                           @RequestParam int year,
                           @RequestParam String vinylCondition,
                           @RequestParam String coverCondition) {
        VinylRecordModel newRecord = new VinylRecordModel(0, title, artist, genre, year, vinylCondition, coverCondition);
        vinylRecordService.addRecord(newRecord);
        return "redirect:/vinyls";
    }

    @PostMapping("/update")
    public String updateRecord(@RequestParam int id,
                              @RequestParam String title,
                              @RequestParam String artist,
                              @RequestParam String genre,
                              @RequestParam int year,
                              @RequestParam String vinylCondition,
                              @RequestParam String coverCondition) {
        VinylRecordModel updatedRecord = new VinylRecordModel(id, title, artist, genre, year, vinylCondition, coverCondition);
        vinylRecordService.updateRecord(updatedRecord);
        return "redirect:/vinyls";
    }

    @PostMapping("/delete")
    public String deleteRecord(@RequestParam int id,
                              @RequestParam(required = false, defaultValue = "false") boolean softDelete) {
        if (softDelete) {
            vinylRecordService.softDeleteRecord(id);
        } else {
            vinylRecordService.deleteRecord(id);
        }
        return "redirect:/vinyls";
    }

    @PostMapping("/deleteMultiple")
    public String deleteMultipleRecords(@RequestParam String ids,
                                       @RequestParam(required = false, defaultValue = "false") boolean softDelete) {
        // Преобразуем строку с ID в список целых чисел
        List<Integer> idList = Arrays.stream(ids.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        
        if (softDelete) {
            vinylRecordService.softDeleteMultipleRecords(idList);
        } else {
            vinylRecordService.deleteMultipleRecords(idList);
        }
        return "redirect:/vinyls";
    }

    @GetMapping("/search")
    public String searchRecords(@RequestParam String query,
                               @RequestParam String searchType,
                               Model model) {
        List<VinylRecordModel> searchResults;
        
        switch (searchType) {
            case "title":
                searchResults = vinylRecordService.searchRecordsByTitle(query);
                break;
            case "artist":
                searchResults = vinylRecordService.searchRecordsByArtist(query);
                break;
            case "genre":
                searchResults = vinylRecordService.searchRecordsByGenre(query);
                break;
            case "vinylCondition":
                searchResults = vinylRecordService.searchRecordsByVinylCondition(query);
                break;
            case "coverCondition":
                searchResults = vinylRecordService.searchRecordsByCoverCondition(query);
                break;
            default:
                searchResults = vinylRecordService.findAllRecords();
        }
        
        // Получаем уникальные значения для комбобоксов
        List<String> allTitles = vinylRecordService.getAllTitles();
        List<String> allArtists = vinylRecordService.getAllArtists();
        List<String> allGenres = vinylRecordService.getAllGenres();
        List<String> allVinylConditions = vinylRecordService.getAllVinylConditions();
        List<String> allCoverConditions = vinylRecordService.getAllCoverConditions();
        
        model.addAttribute("records", searchResults);
        model.addAttribute("searchQuery", query);
        model.addAttribute("searchType", searchType);
        model.addAttribute("allTitles", allTitles);
        model.addAttribute("allArtists", allArtists);
        model.addAttribute("allGenres", allGenres);
        model.addAttribute("allVinylConditions", allVinylConditions);
        model.addAttribute("allCoverConditions", allCoverConditions);
        
        return "vinylRecordList";
    }
}