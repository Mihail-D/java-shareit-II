package ru.practicum.shareit.item;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.user.dto.UserDto;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@JsonTest
class ItemDtoTest {

    @Autowired
    private JacksonTester<ItemDto> json;

    @Test
    void testItemDto() throws Exception {

        UserDto userDto = UserDto.builder()
                .id(1L)
                .name("Barby")
                .email("barby@gmail.com")
                .build();

        ItemDto itemDto = ItemDto.builder()
                .id(1L)
                .name("slippers")
                .description("Step into comfort with our cozy slippers!")
                .available(true)
                .requestId(userDto.getId())
                .build();

        JsonContent<ItemDto> result = json.write(itemDto);

        assertThat(result).extractingJsonPathNumberValue("$.id").isEqualTo(1);
        assertThat(result).extractingJsonPathStringValue("$.name").isEqualTo("slippers");
        assertThat(result).extractingJsonPathStringValue("$.description").isEqualTo("Step into comfort with our cozy slippers!");
        assertThat(result).extractingJsonPathBooleanValue("$.available").isEqualTo(true);
        assertThat(result).extractingJsonPathNumberValue("$.requestId").isEqualTo(1);
    }
}