package ru.practicum.shareit.item.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.exceptions.ItemDataInputErrorException;
import ru.practicum.shareit.exceptions.SharerUserIdException;
import ru.practicum.shareit.exceptions.UserNotFoundException;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.dto.UserMapper;
import ru.practicum.shareit.user.repository.UserRepository;
import ru.practicum.shareit.user.repository.UserStorage;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class ValidateItem {

   private final UserRepository userRepository;

    @Autowired
    public ValidateItem(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean isUserIdNotNull(Long userId) {
        return userId == null;
    }

    public boolean isUserIdUnknown(long userId) {
        List<UserDto> userIds = UserMapper.toUserDtoList(userRepository.findAll());

        Optional<UserDto> userOptional = userIds.stream()
                .filter(i -> userId == i.getId())
                .findFirst();

        return userOptional.isEmpty();
    }

    public boolean isItemAvailable(Item item) {
        Boolean isAvailable = item.getAvailable();
        return isAvailable == null;
    }

    public boolean isItemNameEmpty(Item item) {
        return item.getName().isEmpty();
    }

    public boolean isDescriptionEmpty(Item item) {
        return item.getDescription() == null;
    }

    public void validateItemForCreate(long ownerId, Item item) {
        if (isUserIdNotNull(ownerId)) {
            throw new SharerUserIdException("missing userId parameter in request header");
        }

        if (isUserIdUnknown(ownerId)) {
            throw new UserNotFoundException("user not found");
        }

        if (isItemAvailable(item)) {
            throw new ItemDataInputErrorException("item availability status is missing");
        }

        if (isItemNameEmpty(item)) {
            throw new ItemDataInputErrorException("item name cannot be empty");
        }

        if (isDescriptionEmpty(item)) {
            throw new ItemDataInputErrorException("item description cannot be empty");
        }
    }

    public void validateItemForUpdate(long ownerId, Item existingItem) {
        if (isUserIdNotNull(ownerId)) {
            throw new SharerUserIdException("missing userId parameter in request header");
        }

        if (isUserIdUnknown(ownerId)) {
            throw new UserNotFoundException("user not found");
        }

        if (existingItem.getOwner().getId() != ownerId) {
            throw new UserNotFoundException("the current and new owners of the item do not match");
        }
    }
}
