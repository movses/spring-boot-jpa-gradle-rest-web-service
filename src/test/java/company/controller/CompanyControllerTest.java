package company.controller;

import company.controller.CompanyController;
import company.model.Company;
import company.service.interfaces.CompanyService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Matchers;
import org.mockito.Mockito;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by movses on 2/22/16.
 */
public class CompanyControllerTest {

    private CompanyService companyService;

    private CompanyController companyController;

    @Before
    public void setUp() {
        companyService = Mockito.mock(CompanyService.class);
        companyController = new CompanyController(companyService);
    }

    @Test
    public void getTest_all() {
        List<Company> companies = new ArrayList<>();
        doReturn(companies).when(companyService).getAll();
        companyController.get();
        verify(companyService, times(1)).getAll();
    }

    @Test
    public void getTest() {
        Company company = new Company();
        String id = "1";
        doReturn(company).when(companyService).getCompanyDetails(id);
        companyController.get(id);
        verify(companyService, times(1)).getCompanyDetails(id);
    }

    @Test
    public void postTest() {
        Company company = new Company();
        doReturn(company).when(companyService).createCompany(company);
        companyController.post(company);
        verify(companyService, times(1)).createCompany(company);
    }

    @Test
    public void putTest() {
        Company company = new Company();
        String id = "1";
        doReturn(company).when(companyService).updateCompany(id, company);
        companyController.put(id, company);
        verify(companyService, times(1)).updateCompany(id, company);
    }

    @Test
    public void patchTest() {
        Company company = new Company();
        String id = "1";
        List<String> beneficialOwners = new ArrayList<>();
        doReturn(company).when(companyService).addBeneficialOwner(id, beneficialOwners);
        companyController.patch(id, beneficialOwners);
        verify(companyService, times(1)).addBeneficialOwner(id, beneficialOwners);
    }

}
