package company.service.impl;

/**
 * Created by movses on 2/19/16.
 */


import company.model.Company;
import company.service.exceptions.CompanyNotFoundException;
import company.service.interfaces.CompanyRepository;
import company.service.interfaces.CompanyService;
import company.service.exceptions.InvalidUrlException;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

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
        Iterable<Company> iterable = repository.findAll();
        if (iterable != null) {
            return Lists.newArrayList(iterable);
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public Company getCompanyDetails(String stringId) {
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
    public Company createCompany(Company company) {
        return repository.save(company);
    }

    /*
    * Handles PUT request
    * */
    @Override
    @Transactional
    public Company updateCompany(String stringId, Company company) {
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
    public Company addBeneficialOwner(String stringId, List<String> beneficialOwners) {
        Long id;
        try {
            id = Long.parseLong(stringId);
        } catch(NumberFormatException e) {
            throw new InvalidUrlException();
        }

        if (repository.exists(id)) {
            Company company = repository.findOne(id);
            List<String> combinedBeneficialOwners = new ArrayList<>(company.getBeneficialOwners());
            combinedBeneficialOwners.addAll(beneficialOwners);
            company.setBeneficialOwners(combinedBeneficialOwners);
            return repository.save(company);
        } else {
            throw new CompanyNotFoundException();
        }
    }
}
