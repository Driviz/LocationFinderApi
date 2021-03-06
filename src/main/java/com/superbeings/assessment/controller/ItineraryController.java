package com.superbeings.assessment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.superbeings.assessment.models.ItineraryItem;
import com.superbeings.assessment.service.ItineraryService;

@RestController
public class ItineraryController {

	@Autowired
	ItineraryService itineraryService;

	@GetMapping("/itinerary")
	public List<ItineraryItem> getItinerary()
			throws Exception {
		List<ItineraryItem> itineraryItemList = itineraryService.getItinerary();
		return itineraryItemList;
	}
}
