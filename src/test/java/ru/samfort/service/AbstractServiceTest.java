package ru.samfort.service;

import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.util.Throwables.getRootCause;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringJUnitConfig (locations = {
        "classpath:spring/spring_app.xml",
        "classpath:spring/spring_mvc.xml"
})
//@Sql(scripts = {"classpath:db/initDB_hsql.sql","classpath:db/populateDB.sql"}, config = @SqlConfig(encoding = "UTF-8"))
@Transactional
abstract class AbstractServiceTest {


    //  Check root cause in JUnit: https://github.com/junit-team/junit4/pull/778
    <T extends Throwable> void validateRootCause(Runnable runnable, Class<T> exceptionClass) {
        assertThrows(exceptionClass, () -> {
            try {
                runnable.run();
            } catch (Exception e) {
                throw getRootCause(e);
            }
        });
    }
}
