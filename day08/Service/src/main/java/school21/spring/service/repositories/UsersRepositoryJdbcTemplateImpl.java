package school21.spring.service.repositories;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import school21.spring.service.models.User;

import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository{
    public JdbcTemplate jdbCtemplate;

    public UsersRepositoryJdbcTemplateImpl(JDBCtemplate jdbCtemplate) {
        this.jdbCtemplate = new JdbcTemplate(jdbCtemplate.dmds);
    }

    static class JDBCtemplate{
        DriverManagerDataSource dmds;
        Properties properties;

        public JDBCtemplate() {
            try {
                properties = new Properties();
                InputStream is = UsersRepositoryJdbcImpl.SetDataSource.class.getClassLoader().getResourceAsStream("db.properties");
                properties.load(is);
                if (is != null) {
                    is.close();
                }
            }
            catch (IOException e){
                e.printStackTrace();
            }
            dmds = new DriverManagerDataSource();
            dmds.setDriverClassName(properties.getProperty("db.driver.name"));
            dmds.setUrl(properties.getProperty("db.url"));
            dmds.setUsername(properties.getProperty("db.user"));
            dmds.setPassword(properties.getProperty("db.password"));
        }
    }

    @Override
    public User findById(Long id) {
        try {
            return this.jdbCtemplate.query("select * from users where id = ?;", new Object[]{id},
                    new BeanPropertyRowMapper<>(User.class)).stream().findAny().orElse(null);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<User> findAll() {
        return this.jdbCtemplate.query("select * from users;", (id, email) -> (new User(id.getLong(1), id.getString(2))));
    }

    @Override
    public void save(User entity) {
        this.jdbCtemplate.update("insert into users values(?, ?);", entity.getIdentifier(), entity.getEmail());
    }

    @Override
    public void update(User entity) {
        this.jdbCtemplate.update("update users set email = ? where id = ?;", entity.getEmail(), entity.getIdentifier());
    }

    @Override
    public void delete(Long id) {
        this.jdbCtemplate.update("delete from users where id = ?;", id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try {
            User tmp = this.jdbCtemplate.query("select * from users where email = ?;",
                    new Object[]{email}, new BeanPropertyRowMapper<>(User.class)).stream().findAny().orElse(null);
            return tmp == null ? Optional.of(tmp) : Optional.empty();
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
