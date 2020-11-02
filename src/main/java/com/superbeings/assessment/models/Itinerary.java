package com.superbeings.assessment.models;

import java.util.List;


public class Itinerary {
	private List<ItineraryItem> itinerary;

	public List<ItineraryItem> getItinerary() {
		return itinerary;
	}

	public void setItinerary(List<ItineraryItem> itinerary) {
		this.itinerary = itinerary;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itinerary == null) ? 0 : itinerary.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Itinerary other = (Itinerary) obj;
		if (itinerary == null) {
			if (other.itinerary != null)
				return false;
		} else if (!itinerary.equals(other.itinerary))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Itinerary [itinerary=" + itinerary + "]";
	}
	
}
