package com.example.NeoflexTestTask;

import com.example.NeoflexTestTask.entity.Date; // Ваш класс Date
import com.example.NeoflexTestTask.model.Vacation;
import com.example.NeoflexTestTask.repository.DateRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private DateRepository dateRepository;

	@Autowired
	private ObjectMapper objectMapper;

	// Тест для расчета зарплаты при отпуске
	@Test
	public void testGetSalary() throws Exception {
		Vacation vacation = new Vacation();
		vacation.setSalary(50000.0);
		vacation.setDays(10);
		SimpleDateFormat parser = new SimpleDateFormat("dd.MM.yyyy");
		// Используем java.util.Date для Vacation
		vacation.setDate(parser.parse("21.09.2024"));

		mockMvc.perform(MockMvcRequestBuilders.get("/calculacte")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(vacation)))
				.andExpect(status().isOk())
				.andExpect(content().string(notNullValue())); // Проверяем, что результат не null
	}

	// Тест для добавления новой даты
	@Test
	public void testAddDate() throws Exception {
		String dateJson = "{\"date\":\"25.12.2024\"}";

		mockMvc.perform(MockMvcRequestBuilders.post("/date")
						.contentType(MediaType.APPLICATION_JSON)
						.content(dateJson))
				.andExpect(status().isOk())
				.andExpect(content().string("Date added successfully"));
	}

	// Тест для проверки существования даты
	@Test
	public void testDateExists() throws Exception {
		// Используем ваш класс Date
		Date customDate = createCustomDate("01.01.2024");
		dateRepository.save(customDate);

		mockMvc.perform(MockMvcRequestBuilders.get("/date/exists")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"date\":\"01.01.2024\"}"))
				.andExpect(status().isOk())
				.andExpect(content().string("true"));
	}

	// Тест для получения всех дат
	@Test
	public void testGetAllDates() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/all"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(0)))); // Проверяем, что размер массива >= 0
	}

	// Тест для удаления даты
	@Test
	public void testDeleteDate() throws Exception {
		Date customDate = createCustomDate("25.12.2024");
		dateRepository.save(customDate);

		mockMvc.perform(MockMvcRequestBuilders.delete("/del")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"date\":\"25.12.2024\"}"))
				.andExpect(status().isOk())
				.andExpect(content().string("Date deleted successfully"));

		// Проверка, что дата действительно удалена
		Optional<Date> deletedDate = dateRepository.findById(customDate.getId());
		assert(deletedDate.isEmpty());
	}

	// Метод для создания вашего кастомного Date
	private Date createCustomDate(String dateStr) throws ParseException {
		return new Date(dateStr); // Используем ваш конструктор с строкой
	}
}
