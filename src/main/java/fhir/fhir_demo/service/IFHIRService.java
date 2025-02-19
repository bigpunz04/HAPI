package fhir.fhir_demo.service;

import org.hl7.fhir.r4.model.Bundle;

public interface IFHIRService {

    Bundle GetPatientBundle();
    Bundle GetCareTeamBundle();
}
