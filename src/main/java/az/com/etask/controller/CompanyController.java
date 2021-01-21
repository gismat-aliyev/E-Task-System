package az.com.etask.controller;

import az.com.etask.model.Company;
import az.com.etask.service.inter.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/addCompany")
    public ResponseEntity<?> addCompany(@RequestParam("companyName") String companyName,
                                        @RequestParam("companyAddress") String address,
                                        @RequestParam("companyPhone") String phone,
                                        @RequestParam("companyEmail") String email) {
        boolean result = companyService.addCompany(new Company(null, companyName, phone, address, email));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
