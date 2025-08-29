package com.vit.fullstackapi.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class ApiController {

    static final String FULL_NAME = "ananya_saxena"; // your full name
    static final String DOB = "29082005";            // ddmmyyyy
    static final String EMAIL = "ananya@vit.edu";    // your email
    static final String ROLL = "22BCE3905";          // your roll no

    @PostMapping("/bfhl")
    public Map<String, Object> processData(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new LinkedHashMap<>();
        try {
            List<String> data = (List<String>) request.get("data");

            List<String> even = new ArrayList<>();
            List<String> odd = new ArrayList<>();
            List<String> alphabets = new ArrayList<>();
            List<String> special = new ArrayList<>();
            int sum = 0;

            for (String item : data) {
                if (item.matches("\\d+")) {
                    int num = Integer.parseInt(item);
                    if (num % 2 == 0) even.add(item);
                    else odd.add(item);
                    sum += num;
                } else if (item.matches("[a-zA-Z]+")) {
                    alphabets.add(item.toUpperCase());
                } else {
                    special.add(item);
                }
            }

            // reverse + alternating caps
            StringBuilder concat = new StringBuilder();
            String allChars = String.join("", alphabets);
            for (int i = allChars.length() - 1, j = 0; i >= 0; i--, j++) {
                char c = allChars.charAt(i);
                concat.append((j % 2 == 0) ? Character.toUpperCase(c) : Character.toLowerCase(c));
            }

            response.put("is_success", true);
            response.put("user_id", FULL_NAME + "_" + DOB);
            response.put("email", EMAIL);
            response.put("roll_number", ROLL);
            response.put("odd_numbers", odd);
            response.put("even_numbers", even);
            response.put("alphabets", alphabets);
            response.put("special_characters", special);
            response.put("sum", String.valueOf(sum));
            response.put("concat_string", concat.toString());

        } catch (Exception e) {
            response.put("is_success", false);
            response.put("message", "Invalid input format");
        }
        return response;
    }
}
