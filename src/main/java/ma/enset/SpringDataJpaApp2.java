package ma.enset;

import ma.enset.entities.*;
import ma.enset.repository.ConsultationRepository;
import ma.enset.repository.MedecinRepository;
import ma.enset.repository.PatientRepository;
import ma.enset.repository.RendezVousRepository;
import ma.enset.service.IHospitalService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class SpringDataJpaApp2{
    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaApp2.class, args);
    }

      @Bean
      CommandLineRunner start(IHospitalService hospitalService, PatientRepository patientRepository, MedecinRepository medecinRepository){
        return  args -> {
            Stream.of("youssef", "mohammed", "hassan")
                    .forEach(name->{
                        Patient patient = new Patient();
                        patient.setName(name);
                        patient.setDateNaissance(new Date());
                        patient.setMalade(false);
                        hospitalService.savePatient(patient);
                    });
            Stream.of("Ayman", "ichou", "ahmed")
                    .forEach(name->{
                        Medecin medecin = new Medecin();
                        medecin.setName(name);
                        medecin.setEmail(name+"@gmail.com");
                        medecin.setSpecialist(Math.random()>0.5? "Cardio":"Dentist");
                        hospitalService.saveMedecin(medecin);
                    });

            Patient patient = hospitalService.findPatientById(1L);
            Patient patient1 = patientRepository.findByName("ichou");
            Medecin medecin = medecinRepository.findByName("mohammed");

            RendezVous rendezVous = new RendezVous();
            rendezVous.setDate(new Date());
            rendezVous.setStatuRDV(StatuRDV.PENDING);
            rendezVous.setPatient(patient);
            rendezVous.setMedecin(medecin);
            hospitalService.saveRendezVous(rendezVous);

            RendezVous rendezVous1= hospitalService.findRendezVousById(1L);
            Consultation consultation = new Consultation();
            consultation.setDateRendezVous(new Date());
            consultation.setRendezVous(rendezVous1);
            consultation.setRapport("it cost a lot of money");
            hospitalService.saveConsultation(consultation);
        };
      }
}
