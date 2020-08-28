package com.csci5308.groupme.course.survey.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import com.csci5308.groupme.TestsConfig;
import com.csci5308.groupme.course.survey.service.GroupFormationService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.csci5308.groupme.SystemConfig;
import com.csci5308.groupme.course.survey.constants.Algorithms;
import com.csci5308.groupme.course.survey.model.Candidate;
import com.csci5308.groupme.course.survey.model.Group;
import com.csci5308.groupme.course.survey.strategy.GroupingStrategy;

import errors.EditCodes;

public class GroupFormationServiceImplTest {

	private static GroupFormationService groupFormationService;

	@BeforeAll
	public static void init() {
		groupFormationService = TestsConfig.instance().getGroupFormationService();
	}

	@Test
	public void configureGroupingStrategyTest() {
		String algorithm = Algorithms.GREEDY_GROUPING_WITH_PAIR_SCORES;
		groupFormationService.configureGroupingStrategy(algorithm);
		GroupingStrategy groupingStrategy = groupFormationService.getGroupingStrategy();
		assertNotNull(groupingStrategy);
	}

	@Test
	public void formGroupsTest() {
		List<Candidate> candidates = new ArrayList<Candidate>();
		Integer groupSize = 2;
		for (int i = 0; i < 5; i++) {
			Candidate candidate = new Candidate();
			candidate.setUserName("testUser" + i);
			candidates.add(candidate);
		}
		groupFormationService.configureGroupingStrategy(Algorithms.MOCK_STRATEGY);
		List<Group> groups = groupFormationService.formGroups(candidates, groupSize);
		assertTrue(groups.size() >= candidates.size() / groupSize);
	}

	@Test
	public void validateGroupSizeZeroTest() {
		List<Candidate> candidates = new ArrayList<Candidate>();
		Integer groupSize = 0;
		for (int i = 0; i < 5; i++) {
			Candidate candidate = new Candidate();
			candidate.setUserName("testUser" + i);
			candidates.add(candidate);
		}
		int status = groupFormationService.validate(candidates, groupSize);
		assertEquals(EditCodes.GROUP_SIZE_IS_ZERO, status);
	}

	@Test
	public void validateGroupSizeGreaterThanTotalStudentsTest() {
		List<Candidate> candidates = new ArrayList<Candidate>();
		for (int i = 0; i < 5; i++) {
			Candidate candidate = new Candidate();
			candidate.setUserName("testUser" + i);
			candidates.add(candidate);
		}
		Integer groupSize = candidates.size() + 1;
		int status = groupFormationService.validate(candidates, groupSize);
		assertEquals(EditCodes.GROUP_SIZE_GREATER_THAN_STRENGTH, status);
	}

}
