//package com.example.demo.configuration;
//
//
//import com.example.demo.common.datasSource.DatabaseType;
//import com.example.demo.common.datasSource.DynamicDataSource;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.env.Environment;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//
//import javax.sql.DataSource;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * 多数据源配置
// */
//@Configuration
//public class DataSourceConfig {
//
//    @Autowired
//    private Environment env;
//
//    @Bean(name = "primaryDataSource")
//    @Qualifier("primaryDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.primary")
//    public DataSource primaryDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "secondaryDataSource")
//    @Qualifier("secondaryDataSource")
//    @Primary
//    @ConfigurationProperties(prefix = "spring.datasource.secondary")
//    public DataSource secondaryDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean
//    @Primary
//    public DynamicDataSource dataSource(@Qualifier("primaryDataSource")DataSource primaryDataSource, @Qualifier("secondaryDataSource")DataSource secondaryDataSource) {
//        Map<Object, Object> targetDataSources = new HashMap<>();
//        targetDataSources.put(DatabaseType.mytestdb, primaryDataSource);
//        targetDataSources.put(DatabaseType.mytestdb2, secondaryDataSource);
//        DynamicDataSource dataSource = new DynamicDataSource();
//        dataSource.setTargetDataSources(targetDataSources);// 该方法是AbstractRoutingDataSource的方法
//        dataSource.setDefaultTargetDataSource(primaryDataSource);// 默认的datasource设置为myTestDbDataSource
//        return dataSource;
//    }
//
//    /**
//     * 根据数据源创建SqlSessionFactory
//     * @param ds
//     * @return
//     */
//    @Bean
//    public SqlSessionFactory sqlSessionFactory(DynamicDataSource ds) throws Exception {
//        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
//        fb.setDataSource(ds);// 指定数据源(这个必须有，否则报错)
//        fb.setTypeAliasesPackage(env.getProperty("mybatis.typeAliasesPackage"));
//        fb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapperLocations")));
//        return fb.getObject();
//    }
//
//    /**
//     * 配置事务管理器
//     * @param dataSource
//     * @return
//     */
//    @Bean
//    public DataSourceTransactionManager transactionManager(DynamicDataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }
//
//
////    @Bean(name = "primaryJdbcTemplate")
////    public JdbcTemplate primaryJdbcTemplate(
////            @Qualifier("primaryDataSource") DataSource dataSource) {
////        return new JdbcTemplate(dataSource);
////    }
////
////    @Bean(name = "secondaryJdbcTemplate")
////    public JdbcTemplate secondaryJdbcTemplate(
////            @Qualifier("secondaryDataSource") DataSource dataSource) {
////        return new JdbcTemplate(dataSource);
////    }
//}
