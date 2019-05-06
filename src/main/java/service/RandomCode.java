package service;

import java.util.Random;

public class RandomCode {

    public static int generateCode() {
        int code = 0;
        while (code < 1000) {
            code = new Random().nextInt(10000);
        }
        return code;
    }
}
