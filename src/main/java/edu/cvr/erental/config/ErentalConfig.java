package edu.cvr.erental.config;

import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
// import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.lang.NonNull;
 import org.springframework.security.authentication.AuthenticationManager;
 import org.springframework.security.authentication.AuthenticationProvider;
 import org.springframework.security.authentication.ProviderManager;
 import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import edu.cvr.erental.security.AppAuthFailureHandler;
import edu.cvr.erental.security.AppAuthSucessHandler;
import edu.cvr.erental.security.AppUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@EnableJpaRepositories(basePackages = { "edu.cvr.erental.model" })
@EntityScan(basePackages = { "edu.cvr.erental.model" })
public class ErentalConfig implements ApplicationContextAware, WebMvcConfigurer {

    private ApplicationContext applicationContext;

    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    // @Bean
    // public SpringResourceTemplateResolver templateResolver(){
    //     // SpringResourceTemplateResolver automatically integrates with Spring's own
    //     // resource resolution infrastructure, which is highly recommended.
    //     SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
    //     templateResolver.setApplicationContext(this.applicationContext);
    //     templateResolver.setPrefix("templates/");
    //     templateResolver.setSuffix(".html");
    //     // HTML is the default value, added here for the sake of clarity.
    //     templateResolver.setTemplateMode(TemplateMode.HTML);
    //     // Template cache is true by default. Set to false if you want
    //     // templates to be automatically updated when modified.
    //     templateResolver.setCacheable(true);
    //     return templateResolver;
    // }

    // @Bean
    // public SpringTemplateEngine templateEngine(){
    //     // SpringTemplateEngine automatically applies SpringStandardDialect and
    //     // enables Spring's own MessageSource message resolution mechanisms.
    //     SpringTemplateEngine templateEngine = new SpringTemplateEngine();
    //     templateEngine.setTemplateResolver(templateResolver());
    //     // Enabling the SpringEL compiler with Spring 4.2.4 or newer can
    //     // speed up execution in most scenarios, but might be incompatible
    //     // with specific cases when expressions in one template are reused
    //     // across different data types, so this flag is "false" by default
    //     // for safer backwards compatibility.
    //     templateEngine.setEnableSpringELCompiler(true);
    //     return templateEngine;
    // }

    // @Bean
    // public ThymeleafViewResolver viewResolver(){
    //     ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
    //     viewResolver.setTemplateEngine(templateEngine());
    //     return viewResolver;
    // }

    // @Bean
    // public InMemoryUserDetailsManager userDetailsService() {
    //     UserDetails user1 = User.withUsername("user1")
    //             .password(passwordEncoder().encode("user1Pass"))
    //             .roles("USER")
    //             .build();
    //     UserDetails user2 = User.withUsername("user2")
    //             .password(passwordEncoder().encode("user2Pass"))
    //             .roles("USER")
    //             .build();
    //     UserDetails admin = User.withUsername("admin")
    //             .password(passwordEncoder().encode("adminPass"))
    //             .roles("ADMIN")
    //             .build();
    //     return new InMemoryUserDetailsManager(user1, user2, admin);
    // }

     @Bean
     public PasswordEncoder passwordEncoder() {
         return new BCryptPasswordEncoder();
     }

     @Bean
     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
         /*http.csrf(Customizer.withDefaults())
                .authorizeHttpRequests((requests) -> requests
				//.requestMatchers("/login").permitAll()
                .requestMatchers("/resources/**","/login").permitAll()
				.anyRequest().authenticated()
			)*/   
                //.formLogin(form ->form.loginPage("/login").successHandler(new AppAuthSucessHandler()));
            /*http
                .authorizeRequests()
                    .requestMatchers("/resources/**","/login").permitAll()
                    .anyRequest().authenticated()
                    .and()
                        .formLogin(form->form
                            .login("/login")
                            .successHandler(new AppAuthSucessHandler())
                            .permitAll());
                            .and()*/
            http
                .csrf(Customizer.withDefaults())
                    .authorizeHttpRequests((requests)-> requests
                    .requestMatchers("/resources/**","/login","/registeruser").permitAll()
                    .anyRequest().authenticated()
                    )
                    .formLogin(form->form
                    .loginPage("/login")
                    .loginProcessingUrl("/login")
                    .successHandler(new AppAuthSucessHandler())
                    //.failureUrl("/loginforehr.html?error=true")
                    //.failureHandler(new AppAuthFailureHandler())
                    .permitAll()
                    )
                    // .logout(logout->logout
                    // .logoutUrl("/perform_logout")
                    // .deleteCookies("JSESSIONID")
                    // )
                    .sessionManagement((session) -> session
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    );



                    


          //.loginPage("/loginforehr.html")
          //.loginProcessingUrl("/perform_login")
          //.defaultSuccessUrl("/signup.html", true)
          //.failureUrl("/loginforehr.html?error=true"));
          //.failureHandler(new AppAuthFailureHandler())
          //.and()
          //.logout()
          //.logoutUrl("/perform_logout")
          //.deleteCookies("JSESSIONID")
          //.logoutSuccessHandler(logoutSuccessHandler());
         return http.build();
     }
     @Bean
	public AuthenticationManager authenticationManager(AuthenticationProvider authenticationProvider) {
		
		return new ProviderManager(authenticationProvider);
	}
     @SuppressWarnings("deprecation")
    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService appservice)
    {
        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        //provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        provider.setUserDetailsService(appservice);
        return provider;
    }
    @Bean
    public UserDetailsService appUserDetailsService()
    {
        return new AppUserDetailsService();
    }
}