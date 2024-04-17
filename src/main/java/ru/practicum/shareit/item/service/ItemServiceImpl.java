package ru.practicum.shareit.item.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.shareit.booking.enums.Status;
import ru.practicum.shareit.booking.mapper.BookingMapper;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.repository.BookingRepository;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.exception.ValidationException;
import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.mappers.CommentMapper;
import ru.practicum.shareit.item.mappers.ItemMapper;
import ru.practicum.shareit.item.model.Comment;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.repository.CommentRepository;
import ru.practicum.shareit.item.repository.ItemRepository;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.repository.UserRepository;
import ru.practicum.shareit.util.UnionService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;
    private final CommentRepository commentRepository;
    private final UnionService unionService;

    @Transactional
    @Override
    public ItemDto addItem(long userId, ItemDto itemDto) {

        unionService.checkUser(userId);

        User user = userRepository.findById(userId).get();

        Item item = ItemMapper.toItem(itemDto, user);

        itemRepository.save(item);

        return ItemMapper.toItemDto(item);
    }

    @Transactional
    @Override
    public ItemDto updateItem(ItemDto itemDto, long itemId, long userId) {

        unionService.checkUser(userId);
        User user = userRepository.findById(userId).get();

        unionService.checkItem(itemId);
        Item item = ItemMapper.toItem(itemDto, user);

        item.setId(itemId);

        if (!itemRepository.findByOwnerId(userId).contains(item)) {
            throw new NotFoundException(Item.class, "the item was not found with the user id " + userId);
        }

        Item newItem = itemRepository.findById(item.getId()).get();

        if (item.getName() != null) {
            newItem.setName(item.getName());
        }

        if (item.getDescription() != null) {
            newItem.setDescription(item.getDescription());
        }

        if (item.getAvailable() != null) {
            newItem.setAvailable(item.getAvailable());
        }

        itemRepository.save(newItem);

        return ItemMapper.toItemDto(newItem);
    }

    @Transactional(readOnly = true)
    @Override
    public ItemDto getItemById(long itemId, long userId) {

        unionService.checkItem(itemId);
        Item item = itemRepository.findById(itemId).get();

        ItemDto itemDto = ItemMapper.toItemDto(item);

        unionService.checkUser(userId);

        if (item.getOwner().getId() == userId) {

            Optional<Booking> lastBooking = bookingRepository.getFirstByItemIdAndStatusAndStartBeforeOrderByStartDesc(itemId, Status.APPROVED, LocalDateTime.now());
            Optional<Booking> nextBooking = bookingRepository.getFirstByItemIdAndStatusAndStartAfterOrderByStartAsc(itemId, Status.APPROVED, LocalDateTime.now());

            if (lastBooking.isPresent()) {
                itemDto.setLastBooking(BookingMapper.toBookingShortDto(lastBooking.get()));
            } else {
                itemDto.setLastBooking(null);
            }

            if (nextBooking.isPresent()) {
                itemDto.setNextBooking(BookingMapper.toBookingShortDto(nextBooking.get()));
            } else {
                itemDto.setNextBooking(null);
            }
        }

        List<Comment> commentList = commentRepository.findAllByItemId(itemId);

        if (!commentList.isEmpty()) {
            itemDto.setComments(CommentMapper.toCommentDtoList(commentList));
        } else {
            itemDto.setComments(Collections.emptyList());
        }

        return itemDto;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ItemDto> getItemsUser(long userId) {

        unionService.checkUser(userId);

        List<ItemDto> resultList = new ArrayList<>();

        for (ItemDto itemDto : ItemMapper.toItemDtoList(itemRepository.findByOwnerId(userId))) {

            Optional<Booking> lastBooking = bookingRepository.getFirstByItemIdAndStatusAndStartBeforeOrderByStartDesc(itemDto.getId(), Status.APPROVED, LocalDateTime.now());
            Optional<Booking> nextBooking = bookingRepository.getFirstByItemIdAndStatusAndStartAfterOrderByStartAsc(itemDto.getId(), Status.APPROVED, LocalDateTime.now());

            if (lastBooking.isPresent()) {
                itemDto.setLastBooking(BookingMapper.toBookingShortDto(lastBooking.get()));
            } else {
                itemDto.setLastBooking(null);
            }

            if (nextBooking.isPresent()) {
                itemDto.setNextBooking(BookingMapper.toBookingShortDto(nextBooking.get()));
            } else {
                itemDto.setNextBooking(null);
            }

            resultList.add(itemDto);
        }

        for (ItemDto itemDto : resultList) {

            List<Comment> commentList = commentRepository.findAllByItemId(itemDto.getId());

            if (!commentList.isEmpty()) {
                itemDto.setComments(CommentMapper.toCommentDtoList(commentList));
            } else {
                itemDto.setComments(Collections.emptyList());
            }
        }

        return resultList;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ItemDto> searchItem(String text) {

        if (text.equals("")) {
            return Collections.emptyList();
        } else {
            return ItemMapper.toItemDtoList(itemRepository.search(text));
        }
    }

    @Transactional
    @Override
    public CommentDto addComment(long userId, long itemId, CommentDto commentDto) {

        unionService.checkUser(userId);
        User user = userRepository.findById(userId).get();

        unionService.checkItem(itemId);
        Item item = itemRepository.findById(itemId).get();

        LocalDateTime dateTime = LocalDateTime.now();

        Optional<Booking> booking = bookingRepository.getFirstByItemIdAndBookerIdAndStatusAndEndBefore(itemId, userId, Status.APPROVED, dateTime);

        if (booking.isEmpty()) {
            throw new ValidationException("User " + userId + " not booking this item " + itemId);
        }

        Comment comment = CommentMapper.toComment(commentDto, item, user, dateTime);
        commentRepository.save(comment);

        return CommentMapper.toCommentDto(comment);
    }
}