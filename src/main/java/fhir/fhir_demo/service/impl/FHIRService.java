package fhir.fhir_demo.service.impl;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.context.FhirVersionEnum;
import ca.uhn.fhir.context.PerformanceOptionsEnum;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.client.api.ServerValidationModeEnum;
import ca.uhn.fhir.rest.gclient.ICriterion;
import ca.uhn.fhir.rest.gclient.StringClientParam;
import fhir.fhir_demo.FHIRConfig;
import fhir.fhir_demo.entity.PatientCondition;
import fhir.fhir_demo.service.IFHIRService;
import org.hl7.fhir.r4.model.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FHIRService implements IFHIRService {

    String serverEndpoint = FHIRConfig.ServerEndpoint;
    String patientEndpoint = FHIRConfig.PatientString;

    List<Patient> patientList = new ArrayList<>();

    FhirContext fhirContext = FhirContext.forR4();

    IGenericClient client;

    public FHIRService(){
        fhirContext.getRestfulClientFactory().setServerValidationMode(ServerValidationModeEnum.NEVER);
        fhirContext.setPerformanceOptions(PerformanceOptionsEnum.DEFERRED_MODEL_SCANNING);
        client = fhirContext.newRestfulGenericClient(serverEndpoint);
    }

    public List<fhir.fhir_demo.entity.Patient> GetPatientList(){

        Bundle patientBundle = GetPatientBundle();

        List<fhir.fhir_demo.entity.Patient> patientList = new ArrayList<>();

        for (Bundle.BundleEntryComponent entry : patientBundle.getEntry()){
            Resource resource = entry.getResource();

            if(resource instanceof Patient){
                Patient patient = (Patient) resource;

                String lastName = String.valueOf(patient.getName().get(0).getFamily());
                String firstName = String.valueOf(patient.getName().get(0).getGiven().get(0));
                String id = patient.getIdentifier().get(0).getValue();

                Bundle conditionBundle = GetConditionBundleByPatient(id);
                List<PatientCondition> patientConditionList = GetConditionList(conditionBundle);


                fhir.fhir_demo.entity.Patient patientEntity = new fhir.fhir_demo.entity.Patient();
                patientEntity.setId(id);
                patientEntity.setFirstName(firstName);
                patientEntity.setLastName(lastName);
                patientEntity.setConditionList(patientConditionList);
                patientList.add(patientEntity);
            }
        }

        //List<PatientCondition> conditionList = GetConditionList(patientList);

        return patientList;
    }

    public List<PatientCondition> GetConditionList(Bundle conditionBundle){

        List<PatientCondition> conditionList = new ArrayList<>();

        for(Bundle.BundleEntryComponent entry : conditionBundle.getEntry()){
            Resource resource = entry.getResource();
            PatientCondition patientCondition = new PatientCondition();

            if(resource instanceof Condition){
                Condition condition = (Condition) resource;
                patientCondition.setSeverity(condition.getSeverity().getCoding().get(0).getDisplay());
                patientCondition.setClinicalStatus(condition.getClinicalStatus().getCoding().get(0).getCode());
                patientCondition.setCptCode(condition.getCode().getCoding().get(1).getCode());
                patientCondition.setConditionDescription(condition.getCode().getCoding().get(1).getDisplay());

                conditionList.add(patientCondition);
                System.out.println(patientCondition);
            }
        }

        return conditionList;

    }

    private Bundle GetConditionBundleByPatient(String id){

        return   client
                .search()
                .forResource(Condition.class)
                .where(Condition.PATIENT.hasId(id))
                .returnBundle(Bundle.class)
                .execute();

    }

    public Bundle GetPatientBundle(){

        //get bundle of patients from hapi fhir server
        Bundle patientBundle = client
                .search()
                .forResource(Patient.class)
                .count(5)
                .returnBundle(Bundle.class)
                .execute();

        return patientBundle;

    }

    public Bundle GetCareTeamBundle(){
        return  client
                .search()
                .forResource(ExplanationOfBenefit.class)
                .count(10) // Fetch up to 10 CareTeam resources
                .returnBundle(Bundle.class)
                .execute();
    }

}
