package edutrack.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import edutrack.model.ReadOnlyAddressBook;
import edutrack.model.person.Person;
import edutrack.model.tag.Tag;

public class SampleDataUtilTest {

    @Test
    public void getSampleAddressBook_tagsProperlyRegistered() {
        ReadOnlyAddressBook sampleAb = SampleDataUtil.getSampleAddressBook();
        Person[] samplePersons = SampleDataUtil.getSamplePersons();

        // Verify that all tags from sample persons are registered in the global tag list
        for (Person person : samplePersons) {
            for (Tag tag : person.getTags()) {
                assertTrue(sampleAb.getTagList().contains(tag),
                        "Tag '" + tag.tagName + "' should be registered in the AddressBook");
            }
        }

        // Verify expected number of unique tags
        // Sample data has: friends, colleagues, neighbours, family, classmates
        assertEquals(5, sampleAb.getTagList().size());
    }
}

