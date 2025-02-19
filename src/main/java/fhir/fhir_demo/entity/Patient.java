package fhir.fhir_demo.entity;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Patient{

    private String id;
    private String firstName;
    private String lastName;
    private String url;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

    private List<PatientCondition> conditionList;
}
