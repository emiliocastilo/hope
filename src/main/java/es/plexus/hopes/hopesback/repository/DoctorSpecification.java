package es.plexus.hopes.hopesback.repository;

import java.util.Arrays;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import es.plexus.hopes.hopesback.repository.model.Doctor;
import es.plexus.hopes.hopesback.repository.model.User;
import es.plexus.hopes.hopesback.utils.SearchCriteria;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DoctorSpecification implements Specification<Doctor>{

	private final String[] fieldsUserFK = {"username", "email"};    

	private SearchCriteria criteria;
	 
    @Override
    public Predicate toPredicate
      (Root<Doctor> candidateRoot, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
  
    	Join<Doctor, User> join = candidateRoot.join("user");
    	
    	boolean anyMatchUserFk = Arrays.stream(fieldsUserFK).anyMatch(criteria.getKey()::equals);
    	
    	if(anyMatchUserFk) {
	    	return criteriaBuilder.like(
	    			criteriaBuilder.lower(
	    					join.get(
	    							criteria.getKey()).as(String.class)
	    					),
	    			("%"+criteria.getValue()+"%").toLowerCase());
	    } else {
    	
	    	return criteriaBuilder.like(
    			criteriaBuilder.lower(
    					candidateRoot.get(
    							criteria.getKey()).as(String.class)
    					),
    			("%"+criteria.getValue()+"%").toLowerCase());
	    }
    }
    
}
