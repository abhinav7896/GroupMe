package com.csci5308.groupme.course.survey.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Paths;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import com.csci5308.groupme.course.survey.constants.Criteria;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.qos.logback.classic.Logger;
import constants.FilePathConstants;

@SpringBootTest
public class PrettyResponseTest {

	private static final Logger logger = (Logger) LoggerFactory.getLogger(PrettyResponseTest.class);
	private static Map<?, ?> questionParamsMap;
	private static PrettyResponse prettyResponse;
	private static String questionId;

	@BeforeAll
	public static void init() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			Map<?, ?> allSurveyResponses = mapper
					.readValue(Paths.get(FilePathConstants.TEST_RESPONSES_JSON_FILE).toFile(), Map.class);
			Map<?, ?> candidateResponses = (Map<?, ?>) allSurveyResponses.get("c1");
			questionId = (String) candidateResponses.entrySet().iterator().next().getKey();
			questionParamsMap = (Map<?, ?>) candidateResponses.get(questionId);
			prettyResponse = new PrettyResponse(questionParamsMap);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Test
	public void getTitleTest() throws Exception {
		String title = (String) questionParamsMap.get("title");
		logger.info("Title {}", prettyResponse.getTitle());
		assertEquals(title, prettyResponse.getTitle());
	}

	@Test
	public void getAnswerTest() throws Exception {
		String answer = (String) questionParamsMap.get("answer");
		logger.info("Answer {}", prettyResponse.getAnswer());
		assertEquals(answer, prettyResponse.getAnswer());
	}

	@Test
	public void getCriterionTest() throws Exception {
		String criterion = Criteria.DESCRIPTION_SIMILARITY;
		logger.info("Criterion {}", prettyResponse.getCriterion());
		assertEquals(criterion, prettyResponse.getCriterion());
	}

	@Test
	public void getWeightTest() throws Exception {
		String weight = (String) questionParamsMap.get("weight");
		logger.info("Weight {}", prettyResponse.getWeight());
		assertEquals(weight, prettyResponse.getWeight());
	}

	@Test
	public void getTypeTest() throws Exception {
		String type = (String) questionParamsMap.get("type");
		logger.info("Type {}", prettyResponse.getType());
		assertEquals(type, prettyResponse.getType());
	}

	@Test
	public void getQuestionTest() throws Exception {
		String question = (String) questionParamsMap.get("question");
		logger.info("Question {}", prettyResponse.getTitle());
		assertEquals(question, prettyResponse.getTitle());
	}

	@Test
	public void getQuestionIdTest() throws Exception {
		prettyResponse.setQuestionId(questionId);
		logger.info("QuestionId {}", prettyResponse.getQuestionId());
		assertEquals(questionId, prettyResponse.getQuestionId());
	}

}
