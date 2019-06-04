package model;

import java.util.Objects;

public class Code {

    private String value;
    private long userId;
    private long orderId;

    public Code(String value, long userId, long orderId) {
        this.value = value;
        this.userId = userId;
        this.orderId = orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Code code = (Code) o;
        return userId == code.userId &&
                orderId == code.orderId &&
                Objects.equals(value, code.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, userId, orderId);
    }
}
