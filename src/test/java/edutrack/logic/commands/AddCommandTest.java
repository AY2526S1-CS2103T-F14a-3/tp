package edutrack.logic.commands;

import static edutrack.testutil.Assert.assertThrows;
import static edutrack.testutil.TypicalPersons.ALICE;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import edutrack.commons.core.GuiSettings;
import edutrack.logic.Messages;
import edutrack.logic.commands.exceptions.CommandException;
import edutrack.model.AddressBook;
import edutrack.model.Model;
import edutrack.model.ReadOnlyAddressBook;
import edutrack.model.ReadOnlyUserPrefs;
import edutrack.model.group.Group;
import edutrack.model.person.Person;
import edutrack.model.tag.Tag;
import edutrack.testutil.PersonBuilder;
import javafx.collections.ObservableList;

public class AddCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Person validPerson = new PersonBuilder().build();

        CommandResult commandResult = new AddCommand(validPerson).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(validPerson)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validPerson), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person validPerson = new PersonBuilder().build();
        AddCommand addCommand = new AddCommand(validPerson);
        ModelStub modelStub = new ModelStubWithPerson(validPerson);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PERSON, () -> addCommand.execute(modelStub));
    }

    @Test
    public void execute_personWithNonExistentGroup_throwsCommandException() {
        Person personWithGroup = new PersonBuilder().withGroup("CS2103T").build();
        AddCommand addCommand = new AddCommand(personWithGroup);
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();

        assertThrows(CommandException.class,
                "Groups do not exist: CS2103T. Please create them first using group/create.", (
                ) -> addCommand.execute(modelStub));
    }

    @Test
    public void execute_personWithExistingGroup_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Group group = new Group("CS2103T");
        modelStub.groupsAdded.add(group);

        Person personWithGroup = new PersonBuilder().withGroup("CS2103T").build();
        CommandResult commandResult = new AddCommand(personWithGroup).execute(modelStub);

        assertEquals(1, modelStub.personsAdded.size());
        // Verify person was added with central group reference
        Person addedPerson = modelStub.personsAdded.get(0);
        assertTrue(addedPerson.getGroups().contains(group));
    }

    @Test
    public void execute_personWithNonExistentTag_throwsCommandException() {
        Person personWithTag = new PersonBuilder().withTags("friends").build();
        AddCommand addCommand = new AddCommand(personWithTag);
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();

        assertThrows(CommandException.class,
                "Tags do not exist: friends. Please create them first using tag/create.", (
                ) -> addCommand.execute(modelStub));
    }

    @Test
    public void execute_personWithExistingTag_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Tag tag = new Tag("friends");
        modelStub.tagsAdded.add(tag);

        Person personWithTag = new PersonBuilder().withTags("friends").build();
        CommandResult commandResult = new AddCommand(personWithTag).execute(modelStub);

        assertEquals(1, modelStub.personsAdded.size());
        // Verify person was added with the tag
        Person addedPerson = modelStub.personsAdded.get(0);
        assertTrue(addedPerson.getTags().stream().anyMatch(t -> t.tagName.equals("friends")));
    }

    @Test
    public void equals() {
        Person alice = new PersonBuilder().withName("Alice").build();
        Person bob = new PersonBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    @Test
    public void toStringMethod() {
        AddCommand addCommand = new AddCommand(ALICE);
        String expected = AddCommand.class.getCanonicalName() + "{toAdd=" + ALICE + "}";
        assertEquals(expected, addCommand.toString());
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasGroup(Group group) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addGroup(Group group) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteGroup(Group group) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Group getGroup(Group group) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTag(Tag tag) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Tag> getFilteredTagList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Group> getFilteredGroupList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredGroupList(Predicate<Group> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTag(Tag tag) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTag(Tag tag) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Tag getTag(Tag tag) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        public void updateFilteredTagList(Predicate<Tag> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPendingClearConfirmation(boolean isPending) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isPendingClearConfirmation() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Person person;

        ModelStubWithPerson(Person person) {
            requireNonNull(person);
            this.person = person;
        }

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return this.person.isSamePerson(person);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Person> personsAdded = new ArrayList<>();
        final ArrayList<Group> groupsAdded = new ArrayList<>();
        final ArrayList<Tag> tagsAdded = new ArrayList<>();

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::isSamePerson);
        }

        @Override
        public void addPerson(Person person) {
            requireNonNull(person);
            personsAdded.add(person);
        }

        @Override
        public boolean hasGroup(Group group) {
            requireNonNull(group);
            return groupsAdded.stream().anyMatch(group::equals);
        }

        @Override
        public Group getGroup(Group group) {
            requireNonNull(group);
            return groupsAdded.stream()
                    .filter(group::equals)
                    .findFirst()
                    .orElseThrow(() -> new AssertionError("Group should exist"));
        }

        @Override
        public boolean hasTag(Tag tag) {
            requireNonNull(tag);
            return tagsAdded.stream().anyMatch(tag::equals);
        }

        @Override
        public Tag getTag(Tag tag) {
            requireNonNull(tag);
            return tagsAdded.stream()
                    .filter(tag::equals)
                    .findFirst()
                    .orElseThrow(() -> new AssertionError("Tag should exist"));
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
