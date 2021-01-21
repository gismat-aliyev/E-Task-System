package az.com.etask.service.impl;

import az.com.etask.model.Company;
import az.com.etask.repository.inter.CompanyRepository;
import az.com.etask.service.inter.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CompanyServiceImpl implements CompanyService {

    public CompanyRepository companyRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public boolean addCompany(Company company) {
        try {
            log.info("Company :{}", company);
            return companyRepository.addCompany(company);
        } catch (Exception ex) {
            log.error("" + ex);
            return false;
        }

    }
}
