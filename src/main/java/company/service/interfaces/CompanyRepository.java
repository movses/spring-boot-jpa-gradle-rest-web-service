package company.service.interfaces;

import company.model.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



/**
 * Created by movses on 2/19/16.
 */
@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {
}
