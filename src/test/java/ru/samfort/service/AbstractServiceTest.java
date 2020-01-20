package ru.samfort.service;

import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig (locations = {
        "classpath:spring/spring_app.xml",
        "classpath:spring/spring_mvc.xml"
})
//@Sql(scripts = {"classpath:db/initDB_hsql.sql","classpath:db/populateDB.sql"}, config = @SqlConfig(encoding = "UTF-8"))
abstract class AbstractServiceTest {

}
