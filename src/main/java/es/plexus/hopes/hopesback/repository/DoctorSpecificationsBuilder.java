package es.plexus.hopes.hopesback.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;

import es.plexus.hopes.hopesback.repository.model.Doctor;
import es.plexus.hopes.hopesback.utils.SearchCriteria;

public class DoctorSpecificationsBuilder {

	private final List<SearchCriteria> params;
	 
    public DoctorSpecificationsBuilder() {
        params = new ArrayList<SearchCriteria>();
    }
 
    public DoctorSpecificationsBuilder with(String key, Object value) {
        params.add(new SearchCriteria(key, value));
        return this;
    }
 
    public Specification<Doctor> build() {
        if (params.size() == 0) {
            return null;
        }
 
        List<Specification> specs = params.stream()
        		.map(DoctorSpecification::new)
        		.collect(Collectors.toList());
         
        Specification<Doctor> result = specs.get(0);
 
        for (int i = 1; i < params.size(); i++) {
            result = params.get(i)
            	.isOrPredicate()
                	? Specification.where(result)
                		.or(specs.get(i))
                	: Specification.where(result)
                  		.and(specs.get(i));
        }       
        return result;
    }
	
}
