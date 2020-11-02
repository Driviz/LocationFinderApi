package com.superbeings.assessment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.superbeings.assessment.models.Itinerary;
import com.superbeings.assessment.service.ItineraryService;

@RestController
public class ItineraryController {

	@Autowired
	ItineraryService itineraryService;

	@GetMapping("/itinerary")
	public ResponseEntity<Itinerary> getItinerary(@RequestHeader(value = "token", required = true) String token)
			throws Exception {
		Itinerary itinerary = itineraryService.getItinerary(token);
		return new ResponseEntity<>(itinerary, HttpStatus.OK);
	}
}
