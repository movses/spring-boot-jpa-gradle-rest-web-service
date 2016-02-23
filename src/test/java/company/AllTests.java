package company;

/**
 * Created by movses on 2/22/16.
 */

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runners.Suite;
import company.controller.CompanyControllerTest;
import company.service.impl.CompanyServiceImplTest;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        CompanyControllerTest.class,
        CompanyServiceImplTest.class
})
public class AllTests {
        public static void main(String[] args) {
            Result result = JUnitCore.runClasses(AllTests.class);
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }
            System.out.println(result.wasSuccessful());
        }
}
