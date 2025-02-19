package fhir.fhir_demo;

import fhir.fhir_demo.service.impl.FHIRService;
import org.hl7.fhir.r4.model.Bundle;

public class FHIRWorker {

    FHIRService fhirService = new FHIRService();

    public Bundle GetListOfPatients(){

        Bundle patientBundle = fhirService.GetPatientBundle();

        Bundle careTeamBundle = fhirService.GetCareTeamBundle();





        /*
        List<Patient> patientList = new ArrayList<>();

        for (Bundle.BundleEntryComponent patient : patientBundle.getEntry()){
            if (patient.getResource() instanceof Patient){
                patientList.add((Patient) patient.getResource());
            }
        }*/

        return patientBundle;

    }

}
