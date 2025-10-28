package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/** Immutable remark field for a Person. No validation/constraints. */
public class Remark {
    public final String value;

    /**
     * Constructs a {@code Remark}.
     *
     * @param value A valid remark string
     */
    public Remark(String value) {
        requireNonNull(value);
        this.value = value;
    }

    public boolean isEmpty() {
        return value.isEmpty();
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Remark && value.equals(((Remark) other).value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
