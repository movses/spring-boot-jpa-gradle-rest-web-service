package company.config;

/**
 * Created by movses on 2/21/16.
 */
import com.jolbox.bonecp.BoneCPDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class BoneCPConfig {

    @Value("${bonecp.url}")
    private String jdbcUrl;

    @Value("${bonecp.username}")
    private String jdbcUsername;

    @Value("${bonecp.password}")
    private String jdbcPassword;

    @Value("${bonecp.driverClass}")
    private String driverClass;

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        BoneCPDataSource dataSource = new BoneCPDataSource();
        dataSource.setDriverClass(driverClass);
        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setUsername(jdbcUsername);
        dataSource.setPassword(jdbcPassword);
        return dataSource;
    }

}
