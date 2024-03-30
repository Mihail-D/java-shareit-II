package ru.practicum.shareit.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ItemDataInputErrorException extends RuntimeException {

    public ItemDataInputErrorException(String message) {
        super(message);
    }
}
