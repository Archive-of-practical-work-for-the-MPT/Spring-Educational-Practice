package com.example.project2;

import io.swagger.v3.oas.models.OpenAPI;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SwaggerTest {

    @Autowired
    private OpenAPI openAPI;

    @Test
    public void testSwaggerConfiguration() {
        assertThat(openAPI).isNotNull();
        assertThat(openAPI.getInfo()).isNotNull();
        assertThat(openAPI.getInfo().getTitle()).isEqualTo("API системы управления проектами");
        assertThat(openAPI.getInfo().getVersion()).isEqualTo("1.0");
    }
}