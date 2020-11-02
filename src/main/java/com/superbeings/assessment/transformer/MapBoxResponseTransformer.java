package com.superbeings.assessment.transformer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.superbeings.assessment.models.Itinerary;
import com.superbeings.assessment.models.ItineraryItem;
import com.superbeings.assessment.models.backend.Context;
import com.superbeings.assessment.models.backend.Features;
import com.superbeings.assessment.models.backend.MapBoxResponseModel;
import com.superbeings.assessment.models.backend.Properties;

@Component
public class MapBoxResponseTransformer {

	public Itinerary transform(MapBoxResponseModel mapBoxResp) {
		Itinerary itinerary = new Itinerary();
		List<ItineraryItem> itineraryItemsList = getTransformedItemList(mapBoxResp);
		
		itinerary.setItinerary(itineraryItemsList);
		return itinerary;
	}

	private List<ItineraryItem> getTransformedItemList(MapBoxResponseModel mapBoxResp) {
		if(mapBoxResp==null)
			return null;
		
		List<Features> featuresList = mapBoxResp.getFeatures();
		if(featuresList==null || featuresList.isEmpty())
			return null;
		
		List<ItineraryItem> itineraryItemsList = new ArrayList<>();
		for (Features feature : mapBoxResp.getFeatures()) {
			ItineraryItem item = new ItineraryItem();
			item.setName(feature.getText());
			
			Properties props = feature.getProperties();
			if(props!=null && props.getCategory()!=null)
				item.setCategories(props.getCategory().split(","));
			
			List<Context> context = feature.getContext();
			if(context!=null && !context.isEmpty() && context.get(0)!=null)
				item.setRegion(feature.getContext().get(0).getText());
			
			itineraryItemsList.add(item);
		}
		
		return itineraryItemsList;
	}
}
