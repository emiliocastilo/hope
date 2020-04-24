package es.plexus.hopes.hopesback.repository;

import es.plexus.hopes.hopesback.repository.model.Doctor;
import es.plexus.hopes.hopesback.repository.model.User;
import es.plexus.hopes.hopesback.utils.SearchCriteria;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Arrays;

@AllArgsConstructor
public class DoctorSpecification implements Specification<Doctor> {

	private final String[] fieldsUserFK = {"username", "email"};

	private final SearchCriteria criteria;

	@Override
	public Predicate toPredicate(Root<Doctor> candidateRoot, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		final Join<Doctor, User> join = candidateRoot.join("user");

		boolean anyMatchUserFk = Arrays.asList(fieldsUserFK).contains(criteria.getKey());

		if (anyMatchUserFk) {
			return criteriaBuilder.like(
					criteriaBuilder.lower(join.get(criteria.getKey()).as(String.class)),
					("%" + criteria.getValue() + "%").toLowerCase());
		} else {
			return criteriaBuilder.like(
					criteriaBuilder.lower(candidateRoot.get(criteria.getKey()).as(String.class)),
					("%" + criteria.getValue() + "%").toLowerCase());
		}
	}
}
