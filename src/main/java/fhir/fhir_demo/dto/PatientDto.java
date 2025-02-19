package fhir.fhir_demo.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String url;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

}
