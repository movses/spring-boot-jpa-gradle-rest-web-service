package company.service.exceptions;

/**
 * Created by movses on 2/19/16.
 */

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CompanyNotFoundException extends RuntimeException {
}