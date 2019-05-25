package dao.impl;

import model.Code;

import java.util.ArrayList;
import java.util.List;

public class CodeDao {

    private static final List<Code> CODES = new ArrayList<>();

    public void addCode(Code code) {
        CODES.add(code);
    }

    public boolean checkCode(Code code) {
        return CODES.contains(code);
    }
}
