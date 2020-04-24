package es.plexus.hopes.hopesback.utils;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SearchCriteria {
	@NonNull
	private String key;
	@NonNull
	private Object value;
	private boolean orPredicate;
}

