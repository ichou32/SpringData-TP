package ma.enset;

import ma.enset.entities.Patient;
import ma.enset.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class SpringDataJpaTpApplication implements CommandLineRunner {

    @Autowired
    PatientRepository patientRepository;
    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaTpApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        /* Start inset patients into database */
        Patient p1 = new Patient(null,"youssef", new Date(1999,10,12),false, 2);
        Patient p2 = new Patient(null,"mohammed", new Date(2000,12,27),false, 2);
        Patient p3 = new Patient(null,"aya", new Date(1998,8,15),false, 2);
        Patient p4 = new Patient(null,"yassin", new Date(2000,2,8),false, 2);
        patientRepository.save(p1);
        patientRepository.save(p2);
        patientRepository.save(p3);
        patientRepository.save(p4);
        /* End inserting patient into database */

        // get patients from database
        List<Patient> patientList = patientRepository.findAll();

        // display all patients
        patientList.forEach(p->{
            System.out.println(p.toString());
        });

        // consult a patient by it's id
        Patient patient = patientRepository.findById(Long.valueOf(2)).get();
        System.out.println("********************");
        System.out.println("id :"+patient.getId());
        System.out.println("name :"+patient.getName());
        System.out.println("Birth date :"+patient.getDateNaissance());
        System.out.println("score :"+patient.getScore());
        System.out.println("********************");

        // find patient
        List<Patient> searchList = patientRepository.searchPatien("%ss%");
        searchList.forEach(p->{
            System.out.println(p.toString());
        });

        // update a patient
        Patient patientToEdit = patientRepository.findById(Long.valueOf(2)).get();
        patientToEdit.setMalade(false);
        patientToEdit.setName("ichou");
        patientToEdit.setDateNaissance(new Date(2000,12,12));
        patientRepository.save(patientToEdit);

        // Delete a patient

        patientRepository.deleteById(Long.valueOf(1));
    }
}
