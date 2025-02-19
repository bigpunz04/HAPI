package fhir.fhir_demo;

import fhir.fhir_demo.entity.Patient;
import fhir.fhir_demo.service.impl.FHIRService;
import org.hl7.fhir.r4.model.Bundle;
import org.junit.jupiter.api.Test;

import java.util.List;

public class FHIRServiceTests {

    @Test
    void testService(){
        FHIRService fhirService = new FHIRService();
        List<Patient> patientList = fhirService.GetPatientList();
    }
}
