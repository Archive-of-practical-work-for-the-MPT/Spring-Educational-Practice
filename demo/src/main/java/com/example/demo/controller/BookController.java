package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import com.example.demo.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// Контроллер для работы с сущностью Book
@Controller
@RequestMapping("/books")
public class BookController {
    
    private final BookService bookService;
    private final AuthorService authorService;
    
    @Autowired
    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }
    
    // Отображение списка всех книг с поддержкой поиска, пагинации, фильтрации и сортировки
    @GetMapping
    public String getAllBooks(
            @RequestParam(defaultValue = "") String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            Model model) {
        
        // Определение направления сортировки
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        
        // Создание объекта Pageable для пагинации
        Pageable pageable = PageRequest.of(page, 5, sort);
        
        Page<Book> bookPage;
        
        // Если есть поисковый запрос, используем поиск, иначе получаем все записи
        if (search != null && !search.isEmpty()) {
            bookPage = bookService.searchBooksByTitle(search, pageable);
        } else {
            bookPage = bookService.getBooks(pageable);
        }
        
        model.addAttribute("books", bookPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", bookPage.getTotalPages());
        model.addAttribute("totalItems", bookPage.getTotalElements());
        model.addAttribute("search", search);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("authors", authorService.getAllAuthors());
        
        // Для переключения направления сортировки
        model.addAttribute("reverseSortDir", sortDir.equalsIgnoreCase("asc") ? "desc" : "asc");
        
        return "books/list";
    }
    
    // Отображение формы для создания новой книги
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authorService.getAllAuthors());
        return "books/create";
    }
    
    // Обработка создания новой книги
    @PostMapping
    public String createBook(@Valid @ModelAttribute("book") Book book, 
                            BindingResult result, 
                            Model model) {
        if (result.hasErrors()) {
            model.addAttribute("authors", authorService.getAllAuthors());
            return "books/create";
        }
        
        bookService.saveBook(book);
        return "redirect:/books";
    }
    
    // Отображение формы для редактирования книги
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Optional<Book> book = bookService.getBookById(id);
        if (book.isPresent()) {
            model.addAttribute("book", book.get());
            model.addAttribute("authors", authorService.getAllAuthors());
            return "books/edit";
        } else {
            return "redirect:/books";
        }
    }
    
    // Обработка обновления книги
    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable("id") Long id, 
                            @Valid @ModelAttribute("book") Book book, 
                            BindingResult result, 
                            Model model) {
        if (result.hasErrors()) {
            book.setId(id);
            model.addAttribute("authors", authorService.getAllAuthors());
            return "books/edit";
        }
        
        bookService.saveBook(book);
        return "redirect:/books";
    }
    
    // Удаление книги
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBookById(id);
        return "redirect:/books";
    }
}