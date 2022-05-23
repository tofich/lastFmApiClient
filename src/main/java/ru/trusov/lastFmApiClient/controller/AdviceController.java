package ru.trusov.lastFmApiClient.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import java.net.UnknownHostException;

@ControllerAdvice
public class AdviceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdviceController.class);

    @ExceptionHandler(UnknownHostException.class)
    public String unknownHostException(UnknownHostException ex, Model model){
        LOGGER.error("UnknownHostException", ex);
        model.addAttribute("ex", ex);
        model.addAttribute("message", "Проверьте соединение с интернетом или повторите позднее.");
        return "errorPage";
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public String httpClientErrorException(HttpClientErrorException ex, Model model){
        LOGGER.error("HttpClientErrorException", ex);
        LOGGER.warn("-----" + ex.getMessage() + " with code " + ex.getStatusCode().toString());
        model.addAttribute("ex", ex);
        model.addAttribute("message", ex.getMessage());
        return "errorPage";

    }

    @ExceptionHandler(NullPointerException.class)
    public String httpNullPointerException(NullPointerException ex, Model model){
        LOGGER.error("NullPointerException", ex);
        model.addAttribute("ex", ex);
        model.addAttribute("message", "Ничего не найдено, повторите попытку или измените запрос.");
        return "errorPage";
    }

}
