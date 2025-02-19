package fhir.fhir_demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PatientCondition {

    private String clinicalStatus;
    private Patient patient;
    private String conditionDescription;
    private String severity;
    private String cptCode;

}
