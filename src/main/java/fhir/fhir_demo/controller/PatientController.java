package fhir.fhir_demo.controller;

import fhir.fhir_demo.entity.Patient;
import fhir.fhir_demo.service.IFHIRService;
import fhir.fhir_demo.service.impl.FHIRService;
import lombok.ToString;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PatientController {

    private FHIRService fhirService;

    public PatientController(FHIRService fhirService) {
        this.fhirService = fhirService;
    }

    //create handler method, GET Request and return model and view
@GetMapping("/admin/patients")
    public String patients(Model model){
        List<Patient> patientList = fhirService.GetPatientList();
        model.addAttribute("patientList", patientList);
        return "/admin/patientlist";
    }
}
