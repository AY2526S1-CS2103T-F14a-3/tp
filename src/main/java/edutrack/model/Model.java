package edutrack.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import edutrack.commons.core.GuiSettings;
import edutrack.model.group.Group;
import edutrack.model.person.Person;
import edutrack.model.tag.Tag;
import javafx.collections.ObservableList;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    Predicate<Group> PREDICATE_SHOW_ALL_GROUPS = unused -> true;
    Predicate<Tag> PREDICATE_SHOW_ALL_TAGS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /** Sorts the filtered person list by name in alphabetical order */
    void sortPersonList();


    // Clear command related functionality

    /**
     * Sets the pending clear confirmation status.
     */
    void setPendingClearConfirmation(boolean isPending);

    /**
     * Returns true if a clear command is pending confirmation.
     */
    boolean isPendingClearConfirmation();
    // Groups API
    boolean hasGroup(Group group);
    void addGroup(Group group);
    /**
     * Deletes the given group.
     * The group must exist in the address book.
     */
    void deleteGroup(Group group);
    Group getGroup(Group group);
    ObservableList<Group> getFilteredGroupList();
    void updateFilteredGroupList(Predicate<Group> predicate);

    // Tags API
    boolean hasTag(Tag tag);
    void addTag(Tag tag);
    void deleteTag(Tag tag);
    Tag getTag(Tag tag);
    ObservableList<Tag> getFilteredTagList();
    void updateFilteredTagList(Predicate<Tag> predicate);
}

