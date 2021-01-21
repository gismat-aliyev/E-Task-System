package az.com.etask.repository.impl;

import az.com.etask.model.Company;
import az.com.etask.repository.inter.CompanyRepository;
import az.com.etask.repository.sql.TaskSQL;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class CompanyRepositoryImpl implements CompanyRepository {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public CompanyRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public boolean addCompany(Company company) {
        try{
            MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
            mapSqlParameterSource.addValue("companyName", company.getCompanyName());
            mapSqlParameterSource.addValue("companyPhone", company.getCompanyPhone());
            mapSqlParameterSource.addValue("companyAddress", company.getCompanyAddress());
            mapSqlParameterSource.addValue("companyEmail", company.getCompanyEmail());
            int result = namedParameterJdbcTemplate.update(TaskSQL.CREATE_COMPANY, mapSqlParameterSource);
            return result > 0;
        }catch (Exception ex){
            log.error(""+ex);
            return false;
        }
    }
}
