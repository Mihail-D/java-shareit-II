package ru.practicum.shareit.request.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.shareit.request.model.ItemRequest;

import java.util.List;
import java.util.Optional;

public interface ItemRequestRepository extends JpaRepository<ItemRequest, Long> {

    Optional<ItemRequest> findByRequestorAndId(Long requestor, Long id);

    List<ItemRequest> findByRequestor(Long requestor, Sort sort);

    Page<ItemRequest> findByRequestorNot(Long userId, Pageable pageable);
}
