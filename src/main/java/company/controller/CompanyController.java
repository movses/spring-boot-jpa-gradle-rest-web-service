package main.java.company.controller;

/**
 * Created by movses on 2/18/16.
 */

import java.util.List;

import main.java.company.model.Company;
import main.java.company.service.interfaces.CompanyService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import javax.inject.Inject;


@RestController
public class CompanyController {

    private final CompanyService companyService;

    @Inject
    public CompanyController(final CompanyService companyService) {
        this.companyService = companyService;
    }

    @RequestMapping(value = "/company", method = RequestMethod.GET)
    public List<Company> getCompanyList() {
        return companyService.getAll();
    }

    @RequestMapping(value = "/company/{id}", method = RequestMethod.GET)
    public Company getCompanyDetails(@PathVariable String id) {
        return companyService.get(id);
    }

    @RequestMapping(value = "/company", method = RequestMethod.POST)
    public Company createCompany(@RequestBody @Valid final Company company) {
        return companyService.save(company);
    }

    @RequestMapping(value = "/company/{id}", method = RequestMethod.PUT)
    public Company updateCompany(@PathVariable String id, @RequestBody @Valid final Company company) {
        return companyService.update(id, company);
    }

    @RequestMapping(value = "/company/{id}", method = RequestMethod.PATCH)
    public Company updateCompanyDetails(@PathVariable String id,
                                        @RequestBody List<String> beneficialOwners) {

        return companyService.update(id, beneficialOwners);
    }
}
