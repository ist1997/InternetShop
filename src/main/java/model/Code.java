package model;

import java.util.Objects;

public class Code {

    private String value;
    private long userId;
    private long goodId;

    public Code(String value, long userId, long goodId) {
        this.value = value;
        this.userId = userId;
        this.goodId = goodId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Code code = (Code) o;
        return userId == code.userId &&
                goodId == code.goodId &&
                Objects.equals(value, code.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, userId, goodId);
    }
}
