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
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"cn.com.bsfit.frms.policy.mapper.rams"}, sqlSessionFactoryRef = "ramsSqlSessionFactory")
public class RamsDatasourceConfig {

    @Value("${rams.jdbc.type}")
    private String ramsJdbcType;

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "rams.jdbc")
    public DataSource ramsDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "ramsSqlSessionFactory")
    public SqlSessionFactory ramsSqlSessionFactory(@Qualifier("ramsDataSource") DataSource ramsDataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(ramsDataSource);
        bean.setConfigLocation(new ClassPathResource("RamsMybatisConfig_" + ramsJdbcType + ".xml"));
        return bean.getObject();
    }
}
