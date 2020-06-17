package org.nathan.pahl.dao;

import java.util.Optional;

import org.nathan.pahl.model.Tool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Class used to interface with the {@link ToolRepository}.
 */
@Component
public class ToolDao {
	
	private static final Logger logger = LoggerFactory.getLogger(ToolDao.class);
	
	private ToolRepository toolRepository;
	
	@Autowired
	public ToolDao(ToolRepository toolRepository) {
		this.toolRepository = toolRepository;
	}
	
	/**
	 * Search the {@link ToolRepository} for a {@link Tool} with the given toolCode.
	 * 
	 * @param toolCode the identifier of the {@link Tool}
	 * @return the found {@link Tool} if present, else null
	 */
	public Tool findToolByToolCode(String toolCode) {
		Optional<Tool> optional = this.toolRepository.findById(toolCode);
		if(optional.isEmpty()) {
			logger.warn("Could not find tool data for the provided tool code.");
			return null;
		}
		return optional.get();

	}

}
