package com.enuri.gm.jca;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;


@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@ActiveProfiles("local")
@Transactional
@SpringBootTest
@TestPropertySource(properties = { "spring.config.location=classpath:bootstrap.yml,classpath:application.yml"})
public class GroupMatchingJcaApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
    private WebApplicationContext ctx;

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	/*@Test
	public void 목록요청() throws Exception {
	    HttpServletResponse response = mockMvc.perform(get("/admin/test"))
	            .andExpect(status().isOk())
	            .andExpect(content().contentType(MediaType.parseMediaType("application/json;charset=UTF-8")))
	            .andReturn()
	            .getResponse();

	    log.info(((MockHttpServletResponse) response).getContentAsString());
	}*/

	/*@BeforeEach
	public void setup() {
	    this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
	            .addFilters(new CharacterEncodingFilter("UTF-8", true))  // 한글깨짐 방지 필터 추가
	            //.alwaysDo(print())
	            .build();
	}*/

	@Test
    public void getTest() throws Exception {
        this.mockMvc.perform(get("/admin/test"))
                .andExpect(status().isOk())
                .andExpect(content().string("테스트1"));	//결과 문구 확인
    }

	@Test
    public void isHomeViewIndex() throws Exception {
        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));  // 호출한 view의 이름이 index인지 확인 (확장자는 생략)
    }

	@Test
    public void getSelectBoxCate() throws Exception {
        this.mockMvc.perform(get("/dcrawling/common/selectBoxCate").param("cateCode", ""))
        		.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("02,영상_디카,03,디지털")));  // 글자 포함되어 있나
    }

	@Test
    public void getCategory() throws Exception {
		this.mockMvc.perform(get("/dcrawling/common/cate"))
				//.andDo(print()) //너무 많아서 주석처리
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		        .andExpect(status().isOk())
				.andExpect(jsonPath("$").exists());	// 결과값 존재
	}

	@Test
	void redisConnectionTest() {
		final String key = "a";
		final String data = "1";

		final var valueOperations = redisTemplate.opsForValue();
		valueOperations.set(key, data);

		final String result = valueOperations.get(key);
		Assertions.assertEquals("1", result);
		valueOperations.set(key, "", 1, TimeUnit.MILLISECONDS);	//삭제를 위해
	}
}
