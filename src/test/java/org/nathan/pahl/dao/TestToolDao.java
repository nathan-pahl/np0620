package org.nathan.pahl.dao;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.nathan.pahl.model.Tool;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class TestToolDao {
	
	private ToolDao toolDao;
	
	@Mock
	private ToolRepository toolRepository;
	
	@BeforeEach
	public void beforeEach() {
		toolDao = new ToolDao(toolRepository);
	}
	
	@Test
	public void testFindToolByToolCode_withResult() {
		String toolCode = "JAKR";
		Tool tool = new Tool();
		Optional<Tool> optional = Optional.of(tool);
		when(toolRepository.findById(toolCode)).thenReturn(optional);
		
		Tool result = toolDao.findToolByToolCode(toolCode);
		assertEquals(tool, result);
	}
	
	@Test
	public void testFindToolByToolCode_withNoResult() {
		String toolCode = "JAKR";
		Optional<Tool> optional = Optional.empty();
		when(toolRepository.findById(toolCode)).thenReturn(optional);
		
		Tool result = toolDao.findToolByToolCode(toolCode);
		assertNull(result);
	}

}
