package com.example.informaciskalab2.service.Impl;

import com.example.informaciskalab2.service.OTPService;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

@Service
public class OTPServiceImpl implements OTPService {

    private static final int OTP_LENGTH = 6;
    private static final long EXPIRY_DURATION = 5 * 60 * 1000;

    private final SecureRandom secureRandom = new SecureRandom();
    private final Map<String, OTPDetails> otpData = new HashMap<>();

    @Override
    public String generateOTP(String email) {
        String otp = String.format("%06d", secureRandom.nextInt(999999));
        otpData.put(email, new OTPDetails(otp, System.currentTimeMillis() + EXPIRY_DURATION));
        return otp;
    }

    @Override
    public boolean validateOTP(String email, String otp) {
        OTPDetails details = otpData.get(email);
        if (details == null || !details.getOtp().equals(otp)) {
            return false;
        }
        if (System.currentTimeMillis() > details.getExpiryTime()) {
            otpData.remove(email);
            return false;
        }
        otpData.remove(email);
        return true;
    }

    @Data
    private static class OTPDetails {
        private final String otp;
        private final long expiryTime;

        public OTPDetails(String otp, long expiryTime) {
            this.otp = otp;
            this.expiryTime = expiryTime;
        }
    }
}
