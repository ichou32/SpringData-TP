package ma.enset.repository;

import ma.enset.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findByNameContains(String name);
    @Query("select p from Patient p where p.name like :x")
    List<Patient> searchPatien(@Param("x") String name);
}
