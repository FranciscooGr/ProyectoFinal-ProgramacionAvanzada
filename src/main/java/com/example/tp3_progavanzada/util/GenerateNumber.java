package com.example.tp3_progavanzada.util;

import java.security.SecureRandom;
import com.example.tp3_progavanzada.interfaces.AccountNumberGenerator;
import org.springframework.stereotype.Component;

@Component
public class GenerateNumber implements AccountNumberGenerator {
    private static final SecureRandom random = new SecureRandom();

    @Override
    public String generateAccountNumber() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<10; i++){
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

}
