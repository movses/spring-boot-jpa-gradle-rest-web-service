package company.service.impl;

import company.model.Company;
import company.service.exceptions.CompanyNotFoundException;
import company.service.exceptions.InvalidUrlException;
import company.service.interfaces.CompanyRepository;
import company.service.interfaces.CompanyService;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.doReturn;


/**
 * Created by movses on 2/22/16.
 */
public class CompanyServiceImplTest {

    private CompanyRepository companyRepository;

    private CompanyService companyService;

    @Before
    public void setUp() {
        companyRepository = Mockito.mock(CompanyRepository.class);
        companyService = new CompanyServiceImpl(companyRepository);
    }

    @Test
    public void getAllTest() {
        companyService.getAll();
        verify(companyRepository, times(1)).findAll();
    }

    @Test(expected=InvalidUrlException.class)
    public void getCompanyDetailsTest_InvalidUrlException() {
        String id = "notANumber";
        companyService.getCompanyDetails(id);
    }

    @Test(expected=CompanyNotFoundException.class)
    public void getCompanyDetailsTest_CompanyNotFoundException() {
        String id = "1";
        doReturn(false).when(companyRepository).exists(Long.parseLong(id));
        companyService.getCompanyDetails(id);
    }

    @Test
    public void getCompanyDetailsTest() {
        String id = "1";
        doReturn(true).when(companyRepository).exists(Long.parseLong(id));
        companyService.getCompanyDetails(id);
        verify(companyRepository, times(1)).findOne(Long.parseLong(id));
    }

    @Test
    public void createCompanyTest(){
        Company company = new Company();
        companyService.createCompany(company);
        verify(companyRepository, times(1)).save(company);
    }

    @Test(expected=InvalidUrlException.class)
    public void updateCompanyTest_InvalidUrlException() {
        String id = "notANumber";
        Company company = new Company();
        companyService.updateCompany(id, company);
    }

    @Test(expected=CompanyNotFoundException.class)
    public void updateCompanyTest_CompanyNotFoundException() {
        String id = "1";
        Company company = new Company();
        doReturn(false).when(companyRepository).exists(Long.parseLong(id));
        companyService.updateCompany(id, company);
    }

    @Test()
    public void updateCompanyTest() {
        String id = "1";
        Company company = new Company();

        Company returnedCompany = new Company();
        doReturn(true).when(companyRepository).exists(Long.parseLong(id));
        doReturn(returnedCompany).when(companyRepository).findOne(Long.parseLong(id));

        companyService.updateCompany(id, company);
        verify(companyRepository, times(1)).save(returnedCompany);
    }

    @Test(expected=InvalidUrlException.class)
    public void addBeneficialOwnerTest_InvalidUrlException() {
        String id = "notANumber";
        List<String> beneficialOwners = new ArrayList<>();
        companyService.addBeneficialOwner(id, beneficialOwners);
    }

    @Test(expected=CompanyNotFoundException.class)
    public void addBeneficialOwnerTest_CompanyNotFoundException() {
        String id = "1";
        List<String> beneficialOwners = new ArrayList<>();
        doReturn(false).when(companyRepository).exists(Long.parseLong(id));
        companyService.addBeneficialOwner(id, beneficialOwners);
    }

    @Test()
    public void addBeneficialOwnerTest() {
        String id = "1";
        List<String> beneficialOwners = new ArrayList<>();
        Company company = new Company();
        doReturn(true).when(companyRepository).exists(Long.parseLong(id));
        doReturn(company).when(companyRepository).findOne(Long.parseLong(id));
        companyService.addBeneficialOwner(id, beneficialOwners);
        verify(companyRepository, times(1)).save(company);

    }
}
