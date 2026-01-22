package com.setis.avaliacao.service;

import com.setis.avaliacao.domain.User;
import com.setis.avaliacao.domain.UserRepository;
import com.setis.avaliacao.dto.UserRequest;
import com.setis.avaliacao.exception.BusinessException;
import com.setis.avaliacao.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldCreateUser() {
        UserRequest request = new UserRequest();
        request.setName("User");
        request.setEmail("user@example.com");
        request.setDateOfBirth(LocalDate.now().minusYears(20));

        User saved = new User();
        saved.setId(1L);
        saved.setName(request.getName());
        saved.setEmail(request.getEmail());
        saved.setDateOfBirth(request.getDateOfBirth());

        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(saved);

        var response = userService.create(request);

        assertThat(response.getId()).isEqualTo(1L);
        assertThat(response.getEmail()).isEqualTo("user@example.com");
        verify(userRepository).save(any(User.class));
    }

    @Test
    void shouldNotAllowFutureBirthDate() {
        UserRequest request = new UserRequest();
        request.setName("User");
        request.setEmail("user@example.com");
        request.setDateOfBirth(LocalDate.now().plusDays(1));

        assertThrows(BusinessException.class, () -> userService.create(request));
        verify(userRepository, never()).save(any());
    }

    @Test
    void shouldNotAllowDuplicateEmailOnCreate() {
        UserRequest request = new UserRequest();
        request.setName("User");
        request.setEmail("user@example.com");
        request.setDateOfBirth(LocalDate.now().minusYears(20));

        User existing = new User();
        existing.setId(1L);
        existing.setEmail(request.getEmail());

        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(existing));

        assertThrows(BusinessException.class, () -> userService.create(request));
    }

    @Test
    void shouldThrowNotFoundOnUpdate() {
        UserRequest request = new UserRequest();
        request.setName("User");
        request.setEmail("user@example.com");
        request.setDateOfBirth(LocalDate.now().minusYears(20));

        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userService.update(1L, request));
    }

    @Test
    void shouldReturnPagedUsers() {
        User user = new User();
        user.setId(1L);
        user.setName("User");
        user.setEmail("user@example.com");
        user.setDateOfBirth(LocalDate.now().minusYears(20));

        when(userRepository.findAll(PageRequest.of(0, 10)))
                .thenReturn(new PageImpl<>(List.of(user)));

        var page = userService.findAll(PageRequest.of(0, 10));

        assertThat(page.getTotalElements()).isEqualTo(1);
        assertThat(page.getContent().getFirst().getId()).isEqualTo(1L);
    }
}
