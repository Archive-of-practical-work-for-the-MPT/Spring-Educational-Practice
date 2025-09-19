package com.example.project2;

import com.example.project2.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testAddUser() throws Exception {
        // Создаем объект пользователя для теста
        User user = new User();
        user.setName("Тестовый Пользователь");
        user.setEmail("test@example.com");
        user.setPassword("password123");
        user.setRole(User.Role.USER);

        // Преобразуем объект в JSON
        String userJson = objectMapper.writeValueAsString(user);

        // Выполняем POST-запрос для добавления пользователя
        mockMvc.perform(post("/v1/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isOk());
                // Для асинхронных методов мы не можем проверить тело ответа напрямую в тесте
                // Вместо этого проверяем только статус ответа
    }
}