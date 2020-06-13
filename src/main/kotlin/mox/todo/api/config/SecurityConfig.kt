package mox.todo.api.config

import org.springframework.boot.autoconfigure.security.servlet.WebSecurityEnablerConfiguration
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService

@Configuration
class SecurityConfig(
    private val userService: FirebaseUserService
) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity?) {
        http!!.csrf().disable()
            .antMatcher("/**")
            .authorizeRequests { it.anyRequest().authenticated() }
            .httpBasic(Customizer.withDefaults())
    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth!!.userDetailsService(userService)
    }

}