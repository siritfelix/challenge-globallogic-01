package com.challenge.users.unit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.challenge.users.dto.request.PhoneUserSingUpRequestDto;
import com.challenge.users.entity.Phone;

@ExtendWith(MockitoExtension.class)
public class PhoneTest {

    @DisplayName("Phone entity equals")
    @Test
    void phone_entity_equals() {
        Phone phone_1 = new Phone(1L, 1L, 1, "01", null);
        Phone phone_2 = new Phone(2L, 1L, 1, "01", null);
        Phone phone_3 = new Phone(3L, 3L, 1, "01", null);
        Phone phone_4 = new Phone(4L, 1L, 4, "01", null);
        Phone phone_5 = new Phone(1L, 1L, 1, "05", null);
        Phone phone_6 = new Phone(1L, null, 1, "01", null);
        Phone phone_7 = new Phone(1L, 1L, null, "01", null);
        Phone phone_8 = new Phone(1L, 1L, 1, null, null);
        assertTrue(phone_1.equals(phone_1), "Phones equals");
        assertTrue(phone_1.equals(phone_2), "Phones equals");
        assertFalse(phone_1.equals(phone_3), "Phones different");
        assertFalse(phone_1.equals(phone_4), "Phones different");
        assertFalse(phone_1.equals(phone_5), "Phones different");
        assertFalse(phone_6.equals(phone_1), "Phones different");
        assertFalse(phone_7.equals(phone_1), "Phones different");
        assertFalse(phone_8.equals(phone_1), "Phones different");

    }

    @DisplayName("Phone entity equals")
    @Test
    void phone_request_equals() {
        PhoneUserSingUpRequestDto PhoneUserSingUpRequestDto_1 = new PhoneUserSingUpRequestDto(1L, 1, "1");
        PhoneUserSingUpRequestDto PhoneUserSingUpRequestDto_2 = new PhoneUserSingUpRequestDto(1L, 1, "1");
        PhoneUserSingUpRequestDto PhoneUserSingUpRequestDto_3 = new PhoneUserSingUpRequestDto(3L, 1, "1");
        PhoneUserSingUpRequestDto PhoneUserSingUpRequestDto_4 = new PhoneUserSingUpRequestDto(1L, 4, "1");
        PhoneUserSingUpRequestDto PhoneUserSingUpRequestDto_5 = new PhoneUserSingUpRequestDto(1L, 1, "5");
        PhoneUserSingUpRequestDto PhoneUserSingUpRequestDto_6 = new PhoneUserSingUpRequestDto(1L, null, "1");
        PhoneUserSingUpRequestDto PhoneUserSingUpRequestDto_7 = new PhoneUserSingUpRequestDto(1L, 1, null);

        assertTrue(PhoneUserSingUpRequestDto_1.equals(PhoneUserSingUpRequestDto_1),
                "PhoneUserSingUpRequestDtos equals");
        assertTrue(PhoneUserSingUpRequestDto_1.equals(PhoneUserSingUpRequestDto_2),
                "PhoneUserSingUpRequestDtos equals");
        assertFalse(PhoneUserSingUpRequestDto_1.equals(PhoneUserSingUpRequestDto_3),
                "PhoneUserSingUpRequestDtos different");
        assertFalse(PhoneUserSingUpRequestDto_1.equals(PhoneUserSingUpRequestDto_4),
                "PhoneUserSingUpRequestDtos different");
        assertFalse(PhoneUserSingUpRequestDto_1.equals(PhoneUserSingUpRequestDto_5),
                "PhoneUserSingUpRequestDtos different");
        assertFalse(PhoneUserSingUpRequestDto_6.equals(PhoneUserSingUpRequestDto_1),
                "PhoneUserSingUpRequestDtos different");
        assertFalse(PhoneUserSingUpRequestDto_7.equals(PhoneUserSingUpRequestDto_1),
                "PhoneUserSingUpRequestDtos different");

    }
}
