package main.java.company.service.impl;

/**
 * Created by movses on 2/19/16.
 */


import main.java.company.model.Company;
import main.java.company.service.exceptions.CompanyNotFoundException;
import main.java.company.service.interfaces.CompanyRepository;
import main.java.company.service.interfaces.CompanyService;
import main.java.company.service.exceptions.InvalidUrlException;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.inject.Inject;


@Service
public class CompanyServiceImpl  implements CompanyService {

    private final CompanyRepository repository;

    @Inject
    public CompanyServiceImpl(final CompanyRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<Company> getAll() {
        List<Company> companies = new ArrayList<Company>();
        Iterable<Company> iterable = repository.findAll();
        Iterator iter = iterable.iterator();
        while (iter.hasNext()) {
            companies.add((Company) iter.next());
        }
        return companies;
    }

    @Override
    public Company get(String stringId) {
        Long id;
        try {
            id = Long.parseLong(stringId);
        } catch(NumberFormatException e) {
            throw new InvalidUrlException();
        }
        if (repository.exists(id)) {
            return repository.findOne(id);
        } else {
            throw new CompanyNotFoundException();
        }
    }

    /*
    * Handles POST request
    * */
    @Override
    @Transactional
    public Company save(Company company) {
        return repository.save(company);
    }

    /*
    * Handles PUT request
    * */
    @Override
    @Transactional
    public Company update(String stringId, Company company) {
        Long id;
        try {
            id = Long.parseLong(stringId);
        } catch(NumberFormatException e) {
            throw new InvalidUrlException();
        }

        if (repository.exists(id)) {
            Company cmp = repository.findOne(id);
            cmp.setName(company.getName());
            cmp.setAddress(company.getAddress());
            cmp.setCity(company.getCity());
            cmp.setCountry(company.getCountry());
            cmp.setEmail(company.getEmail());
            cmp.setPhone(company.getPhone());
            cmp.setBeneficialOwners(company.getBeneficialOwners());
            return repository.save(cmp);
        } else {
            throw new CompanyNotFoundException();
        }
    }

    /*
     * Handles PATCH request
      * */
    @Override
    @Transactional
    public Company update(String stringId, List<String> beneficialOwners) {
        Long id;
        try {
            id = Long.parseLong(stringId);
        } catch(NumberFormatException e) {
            throw new InvalidUrlException();
        }

        if (repository.exists(id)) {
            Company company = repository.findOne(id);
            List<String> combinedBeneficialOwners = new ArrayList<String>(company.getBeneficialOwners());
            combinedBeneficialOwners.addAll(beneficialOwners);
            company.setBeneficialOwners(combinedBeneficialOwners);
            return repository.save(company);
        } else {
            throw new CompanyNotFoundException();
        }
    }
}
