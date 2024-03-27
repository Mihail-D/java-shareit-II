package ru.practicum.shareit.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class SharerUserIdException extends RuntimeException {

    public SharerUserIdException(String message) {
        super(message);
    }
}
