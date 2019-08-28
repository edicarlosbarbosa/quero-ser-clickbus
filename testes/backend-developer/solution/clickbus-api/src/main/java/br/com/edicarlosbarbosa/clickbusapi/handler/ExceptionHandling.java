package br.com.edicarlosbarbosa.clickbusapi.handler;

import br.com.edicarlosbarbosa.clickbusapi.handler.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class ExceptionHandling {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public Map<String, String> handleApplicationException(NotFoundException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse
                .put(messageSource.getMessage("error.label", null, LocaleContextHolder.getLocale()),
                        messageSource.getMessage("error.not-found", null, LocaleContextHolder.getLocale()));
        return errorResponse;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String, String> handleApplicationException(Exception ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put(messageSource.getMessage("error.label", null, LocaleContextHolder.getLocale()),
                messageSource.getMessage("error.internal", null, LocaleContextHolder.getLocale()));
        return errorResponse;
    }
}
