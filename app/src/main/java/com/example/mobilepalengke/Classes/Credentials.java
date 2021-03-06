package com.example.mobilepalengke.Classes;

public class Credentials {
    public static boolean isValidEmailAddress(String emailAddress) {
        boolean result = false;
        emailAddress = emailAddress.trim();

        if (!emailAddress.endsWith("@")) {
            String[] splitter = emailAddress.split("@");
            if (splitter.length == 2) {
                if (splitter[0].length() >= 6 && splitter[1].length() > 0) {
                    if (!splitter[0].startsWith(".") && !splitter[0].endsWith(".") &&
                            !splitter[0].startsWith("_") && !splitter[0].endsWith("_") &&
                            !splitter[0].contains("._") && !splitter[0].contains("_.")) {
                        if (splitter[0].matches("[A-Za-z0-9._]*")) {
                            if (!splitter[1].endsWith(".")) {
                                String[] splitter2 = splitter[1].split("\\.");
                                if (splitter2[0].length() >= 3 && splitter2[0].matches("[A-Za-z]*")) {
                                    for (int i = 1; i < splitter2.length; i++) {
                                        if (splitter2[i].matches("[A-Za-z]*")) {
                                            if (i == 1) {
                                                if (splitter2[1].length() >= 3) result = true;
                                                else break;
                                            } else {
                                                if (splitter2[i].length() < 2) {
                                                    result = false;
                                                    break;
                                                }
                                            }
                                        } else {
                                            result = false;
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return result;
    }
}
