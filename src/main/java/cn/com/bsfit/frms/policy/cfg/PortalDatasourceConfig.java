package cn.com.bsfit.frms.policy.cfg;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"cn.com.bsfit.frms.policy.mapper.portal"}, sqlSessionFactoryRef = "portalSqlSessionFactory")
public class PortalDatasourceConfig {

    @Value("${portal.jdbc.type}")
    private String portalJdbcType;

    @Bean
    @ConfigurationProperties(prefix = "portal.jdbc")
    public DataSource portalDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "portalSqlSessionFactory")
    public SqlSessionFactory portalSqlSessionFactory(@Qualifier("portalDataSource") DataSource portalDataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(portalDataSource);
        bean.setConfigLocation(new ClassPathResource("PortalMybatisConfig_oracle.xml"));
        bean.setConfigLocation(new ClassPathResource("PortalMybatisConfig_" + portalJdbcType + ".xml"));
        return bean.getObject();
    }
}
