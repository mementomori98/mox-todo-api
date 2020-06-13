package mox.todo.api.config

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.auth.FirebaseAuth
import org.springframework.context.annotation.Bean
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import java.io.ByteArrayInputStream
import java.io.InputStream

@Component
class FirebaseUserService : UserDetailsService {

    init {
        val trash = System.getenv("FIREBASE_CREDENTIALS")
        var serviceAccount: InputStream? = null
        serviceAccount = ByteArrayInputStream(
            System.getenv("FIREBASE_CREDENTIALS").toByteArray()
        )
        val options = FirebaseOptions.Builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .build()
        FirebaseApp.initializeApp(options)
    }

    override fun loadUserByUsername(s: String?): UserDetails {
        try {
            val user = FirebaseAuth.getInstance().getUser(s)

            return User
                .withUsername(s)
                .password(passwordEncoder()!!.encode("1234"))
                .roles("USER")
                .build()
        }
        catch (e: Exception) {
            e.printStackTrace()
            throw UsernameNotFoundException("User with id token $s not found")
        }

    }

    @Bean
    private fun passwordEncoder(): PasswordEncoder? {
        return BCryptPasswordEncoder()
    }

}
