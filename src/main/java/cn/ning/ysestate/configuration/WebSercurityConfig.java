package cn.ning.ysestate.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSercurityConfig extends WebSecurityConfigurerAdapter {

    PasswordEncoder passwordEncoder = new PasswordEncoder() {
        @Override
        public String encode(CharSequence charSequence) {
            return charSequence.toString();
        }

        @Override
        public boolean matches(CharSequence charSequence, String s) {
            return s.equals(charSequence.toString());
        }

    };

    @Qualifier("dataSource")
    @Autowired
    private DataSource dataSource;
    @Value("${queries.users-query}")
    private String usersQuery;
    @Value("${queries.roles-query}")
    private String rolesQuery;


    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable();
        http
                .authorizeRequests()
                    .antMatchers("/admin/**", "/admin").hasRole("ADMIN")
                    .antMatchers("/houselist", "/house_detail/**", "/regis", "/houselist/**").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .successForwardUrl("/index")
                    .permitAll()
                .and()
                    .logout().logoutSuccessUrl("/houselist").permitAll()
                    .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(rolesQuery)
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder);

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/bower_components/**","/image/**","/css/**","/icon_fonts_assets","/img/**","/js/**","/apple-touch-icon.png","/favicon.png");
    }


}
