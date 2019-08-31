package hello.security;

import hello.role.Role;
import hello.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomInMemoryAuthenticationProvider customInMemoryAuthenticationProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userAuthService;

    @Bean
    public DaoAuthenticationProvider customDaoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userAuthService);
        return provider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                //user controller
                .antMatchers("/user").hasAnyAuthority(roleUser(), roleAdmin())

                //product controller
                .antMatchers("/product/list").hasAnyAuthority(roleAdmin(), roleUser())
                .antMatchers("/product/show/*").hasAnyAuthority(roleAdmin(), roleUser())
                .antMatchers("/product/create/*").hasAnyAuthority(roleAdmin(), roleUser())
                .antMatchers("/product/edit/*").hasAnyAuthority(roleAdmin())
                .antMatchers("/product/save/*").hasAnyAuthority(roleAdmin())

                //.antMatchers("/product/*").hasAnyAuthority("ROLE_ADMIN")
                //.antMatchers("*").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()
                .permitAll();
    }

    private String roleUser() {
        return Role.ROLE_USER.toString();
    }

    private String roleAdmin() {
        return Role.ROLE_ADMIN.toString();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        //auth.authenticationProvider(daoAuthenticationProvider());
        //auth.authenticationProvider(customDaoAuthenticationProvider());
        auth.authenticationProvider(customInMemoryAuthenticationProvider);
        /*auth.inMemoryAuthentication()
                .withUser("user2")
                .password("password2")
                .roles(Role.REGULAR.toString());*/
    }
}