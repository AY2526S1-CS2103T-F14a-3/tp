package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tag(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new Tag(invalidTagName));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));

        // valid names
        assertTrue(Tag.isValidTagName("weak"));
        assertTrue(Tag.isValidTagName("absent"));
        assertTrue(Tag.isValidTagName("class/CS1011"));
        assertTrue(Tag.isValidTagName("CS1011-1"));
        assertTrue(Tag.isValidTagName("needs_help"));

        // invalid names
        assertFalse(Tag.isValidTagName("")); // empty
        assertFalse(Tag.isValidTagName("has space"));
        assertFalse(Tag.isValidTagName("!invalid"));
    }

    @Test
    public void equals_caseInsensitive() {
        Tag lower = new Tag("weak");
        Tag upper = new Tag("WeAk");
        assertTrue(lower.equals(upper));
        assertTrue(upper.equals(lower));
    }

    @Test
    public void hashCode_consistentWithEquals() {
        Tag t1 = new Tag("CLASS/CS1011");
        Tag t2 = new Tag("class/cs1011");
        assertEquals(t1.hashCode(), t2.hashCode());
    }

}
