package main.java.company.service.interfaces;

import main.java.company.model.Company;

import java.util.List;

/**
 * Created by movses on 2/19/16.
 */
public interface CompanyService {
    Company save(Company company);
    Company get(String name);
    List<Company> getAll();
    Company update(String id, List<String> beneficialOwners);
    Company update(String id, Company company);
}
