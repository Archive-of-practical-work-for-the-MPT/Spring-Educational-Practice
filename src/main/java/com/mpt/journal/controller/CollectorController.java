package com.mpt.journal.controller;

import com.mpt.journal.model.CollectorModel;
import com.mpt.journal.service.CollectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// Контроллер для работы с коллекционерами
@Controller
@RequestMapping("/collectors")
public class CollectorController {

    @Autowired
    private CollectorService collectorService;

    @GetMapping
    public String getAllCollectors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(required = false) Integer minVinylCount,
            @RequestParam(required = false) Integer maxVinylCount,
            Model model) {
        
        List<CollectorModel> collectors;
        int totalCollectors;
        
        // Если есть параметры фильтрации по количеству пластинок, используем их
        if (minVinylCount != null || maxVinylCount != null) {
            collectors = collectorService.findCollectorsWithPaginationAndFilter(minVinylCount, maxVinylCount, page, size);
            totalCollectors = collectorService.getFilteredCollectorsCount(minVinylCount, maxVinylCount);
        } else {
            collectors = collectorService.findCollectorsWithPagination(page, size);
            totalCollectors = collectorService.getTotalCollectorsCount();
        }
        
        int totalPages = (int) Math.ceil((double) totalCollectors / size);
        
        // Получаем уникальные значения для комбобоксов
        List<String> allFirstNames = collectorService.getAllFirstNames();
        List<String> allLastNames = collectorService.getAllLastNames();
        List<String> allEmails = collectorService.getAllEmails();
        List<Integer> allVinylCounts = collectorService.getAllVinylCounts();
        
        model.addAttribute("collectors", collectors);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalCollectors);
        model.addAttribute("size", size);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("minVinylCount", minVinylCount);
        model.addAttribute("maxVinylCount", maxVinylCount);
        model.addAttribute("allFirstNames", allFirstNames);
        model.addAttribute("allLastNames", allLastNames);
        model.addAttribute("allEmails", allEmails);
        model.addAttribute("allVinylCounts", allVinylCounts);
        
        return "collectorList";
    }

    @PostMapping("/add")
    public String addCollector(@RequestParam String firstName,
                              @RequestParam String lastName,
                              @RequestParam String email,
                              @RequestParam String phoneNumber,
                              @RequestParam(defaultValue = "0") int vinylCount) {
        CollectorModel newCollector = new CollectorModel(0, firstName, lastName, email, phoneNumber, vinylCount);
        collectorService.addCollector(newCollector);
        return "redirect:/collectors";
    }

    @PostMapping("/update")
    public String updateCollector(@RequestParam int id,
                                 @RequestParam String firstName,
                                 @RequestParam String lastName,
                                 @RequestParam String email,
                                 @RequestParam String phoneNumber,
                                 @RequestParam(defaultValue = "0") int vinylCount) {
        CollectorModel updatedCollector = new CollectorModel(id, firstName, lastName, email, phoneNumber, vinylCount);
        collectorService.updateCollector(updatedCollector);
        return "redirect:/collectors";
    }

    @PostMapping("/delete")
    public String deleteCollector(@RequestParam int id,
                                 @RequestParam(required = false, defaultValue = "false") boolean softDelete) {
        if (softDelete) {
            collectorService.softDeleteCollector(id);
        } else {
            collectorService.deleteCollector(id);
        }
        return "redirect:/collectors";
    }

    @PostMapping("/deleteMultiple")
    public String deleteMultipleCollectors(@RequestParam String ids,
                                          @RequestParam(required = false, defaultValue = "false") boolean softDelete) {
        // Преобразуем строку с ID в список целых чисел
        List<Integer> idList = Arrays.stream(ids.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        
        if (softDelete) {
            collectorService.softDeleteMultipleCollectors(idList);
        } else {
            collectorService.deleteMultipleCollectors(idList);
        }
        return "redirect:/collectors";
    }

    @GetMapping("/search")
    public String searchCollectors(@RequestParam String query,
                                  @RequestParam String searchType,
                                  Model model) {
        List<CollectorModel> searchResults;
        
        switch (searchType) {
            case "firstName":
                searchResults = collectorService.searchCollectorsByFirstName(query);
                break;
            case "lastName":
                searchResults = collectorService.searchCollectorsByLastName(query);
                break;
            case "email":
                searchResults = collectorService.searchCollectorsByEmail(query);
                break;
            case "vinylCount":
                try {
                    int count = Integer.parseInt(query);
                    searchResults = collectorService.searchCollectorsByVinylCount(count, count);
                } catch (NumberFormatException e) {
                    searchResults = collectorService.findAllCollectors();
                }
                break;
            default:
                searchResults = collectorService.findAllCollectors();
        }
        
        // Получаем уникальные значения для комбобоксов
        List<String> allFirstNames = collectorService.getAllFirstNames();
        List<String> allLastNames = collectorService.getAllLastNames();
        List<String> allEmails = collectorService.getAllEmails();
        List<Integer> allVinylCounts = collectorService.getAllVinylCounts();
        
        model.addAttribute("collectors", searchResults);
        model.addAttribute("searchQuery", query);
        model.addAttribute("searchType", searchType);
        model.addAttribute("allFirstNames", allFirstNames);
        model.addAttribute("allLastNames", allLastNames);
        model.addAttribute("allEmails", allEmails);
        model.addAttribute("allVinylCounts", allVinylCounts);
        
        return "collectorList";
    }
}