package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller()
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/calc")
    String calc() {
        return "calc";
    }

    @PostMapping("/calc")
    String calculate(@RequestParam("num1") double num1,
                     @RequestParam("num2") double num2,
                     @RequestParam("operation") String operation,
                     Model model) {
        double result = 0;
        switch (operation) {
            case "add":
                result = num1 + num2;
                break;
            case "subtract":
                result = num1 - num2;
                break;
            case "multiply":
                result = num1 * num2;
                break;
            case "divide":
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    model.addAttribute("error", "Нельзя делить на ноль!");
                    return "calc";
                }
                break;
        }
        model.addAttribute("result", result);
        return "result";
    }

    @GetMapping("/converter")
    public String converterForm(Model model) {
        // Adding currency options for the dropdowns
        String[] currencies = {"USD", "EUR", "RUB", "CNY"};
        model.addAttribute("currencies", currencies);
        return "converter";
    }

    @PostMapping("/converter")
    public String convertCurrency(@RequestParam("amount") double amount,
                                  @RequestParam("fromCurrency") String fromCurrency,
                                  @RequestParam("toCurrency") String toCurrency,
                                  Model model) {
        double convertedAmount = convertCurrency(amount, fromCurrency, toCurrency);
        model.addAttribute("convertedAmount", convertedAmount);
        model.addAttribute("amount", amount);
        model.addAttribute("fromCurrency", fromCurrency);
        model.addAttribute("toCurrency", toCurrency);
        return "converter";
    }

    private double convertCurrency(double amount, String fromCurrency, String toCurrency) {
        // Конвертация по отношению к USD
        double usdRate = 1.0;
        double eurRate = 1 / 0.8589;  // 1 EUR = 1/0.8589 USD
        double rubRate = 1 / 80.43;   // 1 RUB = 1/80.43 USD
        double cnyRate = 1 / 7.14;    // 1 CNY = 1/7.14 USD

        double amountInUSD = switch (fromCurrency) {
            case "USD" -> amount;
            case "EUR" -> amount * eurRate;
            case "RUB" -> amount * rubRate;
            case "CNY" -> amount * cnyRate;
            default -> 0;
        };

        return switch (toCurrency) {
            case "USD" -> amountInUSD;
            case "EUR" -> amountInUSD / eurRate;
            case "RUB" -> amountInUSD / rubRate;
            case "CNY" -> amountInUSD / cnyRate;
            default -> amountInUSD;
        };
    }
}