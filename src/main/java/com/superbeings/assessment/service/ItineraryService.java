package com.superbeings.assessment.service;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.superbeings.assessment.models.Itinerary;
import com.superbeings.assessment.models.backend.MapBoxResponseModel;
import com.superbeings.assessment.transformer.MapBoxResponseTransformer;

@Service
public class ItineraryService {

	private static final String FORWARD_GEOCODE_PATH = "mapbox.places/";
	private static final String JSON = ".json?";
	private static final String LIMIT = "limit=";
	private static final String COUNTRY = "&country=";
	private static final String ACCESS_TOKEN = "&access_token=";
	private static final String DEFAULT_QUERY = "history,istanbul";
	private static final String DEFAULT_COUNTRY = "TR";
	private static final Logger log = LoggerFactory.getLogger(ItineraryService.class);

	@Autowired
	private RestTemplate mapboxRestTemplate;

	@Autowired
	private MapBoxResponseTransformer mapBoxResponseTransformer;

	@Value("${mapbox.basepath}")
	private String basepath;

	@Value("${limit.max}")
	private int limit;

	public Itinerary getItinerary(String token) throws Exception {
		URI requestURI = createRequestURI(token);
		Itinerary itinerary = null;
		try {
			MapBoxResponseModel mapBoxResponseModel = mapboxRestTemplate.getForObject(requestURI,
					MapBoxResponseModel.class);
			log.info("Before Transformation: " + mapBoxResponseModel.toString());
			itinerary = mapBoxResponseTransformer.transform(mapBoxResponseModel);
			log.info("After Transformation: " + itinerary.toString());
		} catch (HttpClientErrorException e) {
			log.info("Backend Exception Occurred: " + e.toString());
			throw new ResponseStatusException(e.getStatusCode(), e.getMessage(), e);
		}
		return itinerary;
	}

	private URI createRequestURI(String token) {
		StringBuilder sb = new StringBuilder();
		sb.append(basepath);
		sb.append(FORWARD_GEOCODE_PATH);
		sb.append(DEFAULT_QUERY);
		sb.append(JSON);
		sb.append(LIMIT);
		sb.append(limit);
		sb.append(COUNTRY);
		sb.append(DEFAULT_COUNTRY);
		sb.append(ACCESS_TOKEN);
		sb.append(token);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(sb.toString());
		return builder.build().encode().toUri();
	}
}
