package baseAPI.API.Seguranca.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {
    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return  httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/auth/**", "/api-api-docs/swagger-config","/api-api-docs", "/v3/api-docs/**", "/swagger-ui/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/auth/**", "/api-api-docs/swagger-config","/api-api-docs", "/v3/api-docs/**", "/swagger-ui/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/v1/auth/**", "/api-api-docs/swagger-config","/api-api-docs", "/v3/api-docs/**", "/swagger-ui/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/cliente/**").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/cliente/**").hasRole("USER")
                        .requestMatchers(HttpMethod.PUT, "/cliente/**").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/ordemServico/**").hasAnyRole("USER", "TEC")
                        //user
                        .requestMatchers(HttpMethod.POST, "/ordemServico/NovaOrdemServico").hasRole("USER")
                        .requestMatchers(HttpMethod.PUT, "/ordemServico/AlterarStatusOrdemServico").hasRole("USER")
                        .requestMatchers(HttpMethod.PUT, "/ordemServico/FinalizarOrdemServico").hasRole("USER")
                        .requestMatchers(HttpMethod.PUT, "/ordemServico/ProdutoNaoRetirado").hasRole("USER")
                        //tec
                        .requestMatchers(HttpMethod.PUT, "/ordemServico/AnaliseOrdemServico").hasRole("TEC")
                        .requestMatchers(HttpMethod.PUT, "/ordemServico/FinalizarReparoOrdemServico").hasRole("TEC")
                        .requestMatchers(HttpMethod.GET, "/reparo/**").hasAnyRole("TEC")
                        .requestMatchers(HttpMethod.POST, "/reparo/**").hasAnyRole("TEC")
                        .requestMatchers(HttpMethod.GET, "/fornecedor/**").hasAnyRole("TEC")
                        .anyRequest().hasRole("ADMIN")
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
