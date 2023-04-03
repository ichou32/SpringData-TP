package ma.enset.service;

import ma.enset.entities.Consultation;
import ma.enset.entities.Medecin;
import ma.enset.entities.Patient;
import ma.enset.entities.RendezVous;

public interface IHospitalService {
    Patient savePatient(Patient patient);
    Patient findPatientById(Long id);
    Medecin saveMedecin(Medecin medecin);
    RendezVous saveRendezVous(RendezVous rendezVous);
    RendezVous findRendezVousById(Long id);
    Consultation saveConsultation(Consultation consultation);
}
