package company.service.interfaces;

import company.model.Company;

import java.util.List;

/**
 * Created by movses on 2/19/16.
 */
public interface CompanyService {
    Company createCompany(Company company);
    Company getCompanyDetails(String name);
    List<Company> getAll();
    Company addBeneficialOwner(String id, List<String> beneficialOwners);
    Company updateCompany(String id, Company company);
}
