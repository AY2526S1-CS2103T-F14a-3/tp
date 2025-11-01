package edutrack.logic.commands;

import static edutrack.logic.commands.CommandTestUtil.assertCommandFailure;
import static edutrack.logic.commands.CommandTestUtil.assertCommandSuccess;
import static edutrack.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static edutrack.testutil.TypicalPersons.getTypicalAddressBook;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import edutrack.commons.core.index.Index;
import edutrack.logic.Messages;
import edutrack.model.Model;
import edutrack.model.ModelManager;
import edutrack.model.UserPrefs;
import edutrack.model.person.Person;
import edutrack.model.tag.Tag;

/**
 * Contains integration tests (interaction with the Model) for {@code TagAssignCommand}.
 */
public class TagAssignCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validTagAndIndex_success() throws Exception {
        Model freshModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Tag tag = new Tag("UniquePhysicsTag123");
        if (!freshModel.hasTag(tag)) {
            freshModel.addTag(tag);
        }

        Person personToEdit = freshModel.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        String expectedMessage = String.format(TagAssignCommand.MESSAGE_SUCCESS,
                personToEdit.getName(), tag);

        // Create expected model and manually assign the tag
        Model expectedModel = new ModelManager(freshModel.getAddressBook(), new UserPrefs());
        Person editedPerson = new Person(
                personToEdit.getName(),
                personToEdit.getPhone(),
                personToEdit.getEmail(),
                personToEdit.getAddress(),
                personToEdit.getTags(),
                personToEdit.getGroups(),
                personToEdit.getNote());

        java.util.Set<Tag> updatedTags = new java.util.HashSet<>(editedPerson.getTags());
        updatedTags.add(tag);
        Person expectedPerson = new Person(
                editedPerson.getName(),
                editedPerson.getPhone(),
                editedPerson.getEmail(),
                editedPerson.getAddress(),
                updatedTags,
                editedPerson.getGroups(),
                editedPerson.getNote());
        expectedModel.setPerson(personToEdit, expectedPerson);

        TagAssignCommand assignCommand = new TagAssignCommand(INDEX_FIRST_PERSON, tag);
        assertCommandSuccess(assignCommand, freshModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_tagNotFound_throwsCommandException() {
        Tag nonExistentTag = new Tag("NonExistent");
        TagAssignCommand assignCommand = new TagAssignCommand(INDEX_FIRST_PERSON, nonExistentTag);

        assertCommandFailure(assignCommand, model, TagAssignCommand.MESSAGE_TAG_NOT_FOUND);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Tag tag = new Tag("UniquePhysicsTag456");
        if (!model.hasTag(tag)) {
            model.addTag(tag);
        }

        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        TagAssignCommand assignCommand = new TagAssignCommand(outOfBoundIndex, tag);

        assertCommandFailure(assignCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_duplicateTag_throwsCommandException() {
        Tag tag = new Tag("UniquePhysicsTag789");
        if (!model.hasTag(tag)) {
            model.addTag(tag);
        }

        // Assign tag first time
        TagAssignCommand firstAssign = new TagAssignCommand(INDEX_FIRST_PERSON, tag);

        try {
            firstAssign.execute(model);
        } catch (Exception e) {
            // Ignore if person already has tag from test data
        }

        // Try to assign same tag again
        TagAssignCommand secondAssign = new TagAssignCommand(INDEX_FIRST_PERSON, tag);
        assertCommandFailure(secondAssign, model, TagAssignCommand.MESSAGE_DUPLICATE_TAG);
    }

    @Test
    public void execute_caseInsensitiveTagAssignment_usesCanonicalTagName() {
        Model freshModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        // Create tag with specific casing and add to model
        Tag canonicalTag = new Tag("example");
        freshModel.addTag(canonicalTag);

        // Create tag with different casing for assignment
        Tag userInputTag = new Tag("exAmple");

        // Execute command with different casing
        TagAssignCommand assignCommand = new TagAssignCommand(INDEX_FIRST_PERSON, userInputTag);
        try {
            assignCommand.execute(freshModel);
        } catch (Exception e) {
            // Should not throw exception for valid command
        }

        // Verify that the person has the canonical tag, not the user input tag
        Person editedPerson = freshModel.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        assertTrue(editedPerson.getTags().contains(canonicalTag),
                "Person should have the canonical tag 'example'");

        // Verify the exact tag object is the canonical one
        boolean hasCanonicalTag = editedPerson.getTags().stream()
                .anyMatch(t -> t.tagName.equals("example"));
        assertTrue(hasCanonicalTag, "Tag name should be 'example', not 'exAmple'");
    }

    @Test
    public void equals() {
        Tag physics = new Tag("Physics");
        Tag chemistry = new Tag("Chemistry");
        TagAssignCommand assignPhysicsToFirst = new TagAssignCommand(INDEX_FIRST_PERSON, physics);
        TagAssignCommand assignChemistryToFirst = new TagAssignCommand(INDEX_FIRST_PERSON, chemistry);

        // same object -> returns true
        assertTrue(assignPhysicsToFirst.equals(assignPhysicsToFirst));

        // same values -> returns true
        TagAssignCommand assignPhysicsToFirstCopy = new TagAssignCommand(INDEX_FIRST_PERSON, physics);
        assertTrue(assignPhysicsToFirst.equals(assignPhysicsToFirstCopy));

        // different types -> returns false
        assertFalse(assignPhysicsToFirst.equals(1));

        // null -> returns false
        assertFalse(assignPhysicsToFirst.equals(null));

        // different tag -> returns false
        assertFalse(assignPhysicsToFirst.equals(assignChemistryToFirst));

        // different index -> returns false
        Index secondIndex = Index.fromOneBased(2);
        TagAssignCommand assignPhysicsToSecond = new TagAssignCommand(secondIndex, physics);
        assertFalse(assignPhysicsToFirst.equals(assignPhysicsToSecond));
    }
}

