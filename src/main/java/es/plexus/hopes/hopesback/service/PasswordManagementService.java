package es.plexus.hopes.hopesback.service;

import es.plexus.hopes.hopesback.configuration.security.PasswordManagementConfiguration;
import org.passay.PasswordGenerator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordManagementService {

    private final PasswordManagementConfiguration passwordGenerationConfiguration;
    private final PasswordEncoder passwordEncoder;

    public PasswordManagementService(final PasswordManagementConfiguration passwordGenerationConfiguration,
                                     final PasswordEncoder passwordEncoder) {
        this.passwordGenerationConfiguration = passwordGenerationConfiguration;
        this.passwordEncoder = passwordEncoder;
    }

    public String generatePassword() {
        final PasswordGenerator passwordGenerator = passwordGenerationConfiguration.getPasswordGenerator();
        return passwordGenerator.generatePassword(
                passwordGenerationConfiguration.getDefaultPasswordGenerationLength(),
                passwordGenerationConfiguration.getPasswordRules());
    }

    public String generateEncodedPassword() {
        return passwordEncoder.encode(generatePassword());
    }

    public String encode(final CharSequence charSequence) {
        return passwordEncoder.encode(charSequence);
    }

    public boolean matches(final CharSequence charSequence, final String value) {
        return passwordEncoder.matches(charSequence, value);
    }

}
