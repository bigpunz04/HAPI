package fhir.fhir_demo.mapper;

import fhir.fhir_demo.dto.PatientDto;
import fhir.fhir_demo.entity.Patient;

public class PatientMapper {

    // map Patient entity to PatientDto

   /* public static PatientDto mapToPatientDto(Patient patient){

        PatientDto patientDto = PatientDto.builder()
                .id(patient.getId())
                .firstName(patient.getFirstName())
                .lastName(patient.getLastName())
                .url(patient.getUrl())
                .createdOn(patient.getCreatedOn())
                .updatedOn(patient.getUpdatedOn())
                .build();

        return patientDto;
    }

    public static Patient mapToPatient(PatientDto patientDto){
        Patient patient = Patient.builder()
                .id(patientDto.getId())
                .firstName(patientDto.getFirstName())
                .lastName(patientDto.getLastName())
                .url(patientDto.getUrl())
                .createdOn(patientDto.getCreatedOn())
                .updatedOn(patientDto.getUpdatedOn())
                .build();
         return patient;
    }*/

}
