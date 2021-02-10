package es.plexus.hopes.hopesback.repository;

import es.plexus.hopes.hopesback.repository.model.Medicine;
import es.plexus.hopes.hopesback.repository.utils.QueryConstants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {
	@Query("select m from Medicine m "
			+ " WHERE LOWER(m.actIngredients) like CONCAT('%',LOWER(:search),'%') "
			+ " OR LOWER(m.codeAct) like CONCAT('%',LOWER(:search),'%') "
			+ " OR LOWER(m.acronym) like CONCAT('%',LOWER(:search),'%') "
			+ " OR LOWER(m.presentation) like CONCAT('%',LOWER(:search),'%') ")
	Page<Medicine> findMedicinesBySearch(@Param("search")String search, Pageable pageable);

    @Query("select m from Medicine m "
            + "  WHERE translate(LOWER(m.treatmentType),'áéíóúÁÉÍÓÚäëïöüÄËÏÖÜ','aeiouAEIOUaeiouAEIOU') = translate(LOWER(:treatmentType),'áéíóúÁÉÍÓÚäëïöüÄËÏÖÜ','aeiouAEIOUaeiouAEIOU') "
            + " AND (LOWER(m.actIngredients) like CONCAT('%',LOWER(:search),'%') "
            + " OR LOWER(m.codeAct) like CONCAT('%',LOWER(:search),'%') "
            + " OR LOWER(m.acronym) like CONCAT('%',LOWER(:search),'%') "
            + " OR LOWER(m.presentation) like CONCAT('%',LOWER(:search),'%')) ")
    Page<Medicine> findMedicinesBySearchAndTreatmentType(@Param("search") String search, @Param("treatmentType") String treatmentType, Pageable pageable);

	Optional<Medicine> findByNationalCode(String nationalCode);

	@Query("select m from Medicine m")
	List<Medicine> findAll();
}
