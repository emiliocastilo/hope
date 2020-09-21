package es.plexus.hopes.hopesback.configuration.security;

import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class PasswordManagementConfiguration {

    private final static int PASSWORD_GENERATION_LENGTH = 15;

    @Bean
    public PasswordGenerator getPasswordGenerator() {
        return new PasswordGenerator();
    }

    public List<CharacterRule> getPasswordRules() {
        final CharacterRule lowerCaseRule = new CharacterRule(EnglishCharacterData.LowerCase);
        lowerCaseRule.setNumberOfCharacters(2);

        final CharacterRule upperCaseRule = new CharacterRule(EnglishCharacterData.UpperCase);
        upperCaseRule.setNumberOfCharacters(2);

        final CharacterRule digitRule = new CharacterRule(EnglishCharacterData.Digit);
        digitRule.setNumberOfCharacters(2);

        final CharacterData specialChars = new SpecialCharacters();
        final CharacterRule specialCharsRule = new CharacterRule(specialChars);
        specialCharsRule.setNumberOfCharacters(2);

        return Arrays.asList(lowerCaseRule, upperCaseRule, digitRule, specialCharsRule);
    }

    public int getDefaultPasswordGenerationLength() {
        return PASSWORD_GENERATION_LENGTH;
    }

    private static class SpecialCharacters implements CharacterData {

        private final static String SPECIAL_CHARS = "!$&_+.,;:";
        private final static String SPECIAL_CHARS_ERROR_CODE = "NO_VALID_SPECIAL_CHAR";

        @Override
        public String getErrorCode() {
            return SPECIAL_CHARS_ERROR_CODE;
        }

        @Override
        public String getCharacters() {
            return SPECIAL_CHARS;
        }

    }

}
