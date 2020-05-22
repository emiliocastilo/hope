package es.plexus.hopes.hopesback.repository;

import es.plexus.hopes.hopesback.repository.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {

	@Query("SELECT photo FROM Photo photo JOIN photo.pathology pth " +
			"JOIN photo.patient pac WHERE pth.id = :pthId AND pac.id = :pacId")
	List<Photo> findAllByPatientAndPathology(@Param("pthId") Long pthId, @Param("pacId") Long pacId);

}
