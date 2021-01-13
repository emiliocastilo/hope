package es.plexus.hopes.hopesback.repository;

import es.plexus.hopes.hopesback.controller.model.PharmacyDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static es.plexus.hopes.hopesback.repository.utils.QueryConstants.QUERY_FIND_PHARMACY_BY_DISPENSATION_AND_MEDICINE;

@Repository
public class PharmacyRepositoryCustomImpl implements PharmacyRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<PharmacyDTO> findAll(Pageable pageable) {
        List<PharmacyDTO> list;
        int start = pageable.getPageNumber() * pageable.getPageSize();
        List<PharmacyDTO> pharmacyResultList = entityManager
                .createQuery(QUERY_FIND_PHARMACY_BY_DISPENSATION_AND_MEDICINE, PharmacyDTO.class)
                .getResultList();

        if (pageable.getSort().get().findFirst().isPresent()) {
            orderPharmacyResultList(pharmacyResultList, pageable.getSort());
        }

        if (pharmacyResultList.size() < start) {
            list = Collections.emptyList();
        } else {
            int end = Math.min(start + pageable.getPageSize(), pharmacyResultList.size());
            list = pharmacyResultList.subList(start, end);
        }

        return new PageImpl<>(list, pageable, pharmacyResultList.size());
    }

    private void orderPharmacyResultList(List<PharmacyDTO> pharmacyResultList, Sort sort) {
        Optional<Sort.Order> sortOrder = sort.get().findFirst();
        if (sortOrder.isPresent()) {
            Sort.Order order = sortOrder.get();
            switch (order.getProperty()) {
                case "nhc":
                    pharmacyResultList.sort(obtainComparatorString(order, PharmacyDTO::getNhc));
                    break;
                case "date":
                    pharmacyResultList.sort(obtainComparatorDate(order, PharmacyDTO::getDate));
                    break;
                case "nationalCode":
                    pharmacyResultList.sort(obtainComparatorString(order, PharmacyDTO::getNationalCode));
                    break;
                case "presentation":
                    pharmacyResultList.sort(obtainComparatorString(order, PharmacyDTO::getPresentation));
                    break;
                case "quantity":
                    pharmacyResultList.sort(obtainComparatorString(order, PharmacyDTO::getQuantity));
                    break;
                case "mgDispensed":
                    pharmacyResultList.sort(obtainComparatorBigDecimal(order, PharmacyDTO::getMgDispensed));
                    break;
                case "unitCost":
                    pharmacyResultList.sort(obtainComparatorBigDecimal(order, PharmacyDTO::getUnitCost));
                    break;
                case "totalCost":
                    pharmacyResultList.sort(obtainComparatorBigDecimal(order, PharmacyDTO::getTotalCost));
                    break;
                default:
                    break;
            }
        }
    }

    private static Comparator<PharmacyDTO> obtainComparatorString(Sort.Order order, Function<PharmacyDTO, String> sortBy) {
        return order.isAscending() ?
                Comparator.nullsFirst(Comparator.comparing(sortBy)) : Comparator.nullsLast(Comparator.comparing(sortBy)).reversed();
    }

    private static Comparator<PharmacyDTO> obtainComparatorDate(Sort.Order order, Function<PharmacyDTO, LocalDateTime> sortBy) {
        return order.isAscending() ?
                Comparator.nullsFirst(Comparator.comparing(sortBy)) : Comparator.nullsLast(Comparator.comparing(sortBy).reversed());
    }

    private static Comparator<PharmacyDTO> obtainComparatorBigDecimal(Sort.Order order, Function<PharmacyDTO, BigDecimal> sortBy) {
        return order.isAscending() ?
                Comparator.nullsFirst(Comparator.comparing(sortBy)) : Comparator.nullsLast(Comparator.comparing(sortBy).reversed());
    }
}
