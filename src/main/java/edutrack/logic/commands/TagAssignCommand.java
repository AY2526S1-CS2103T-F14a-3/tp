package edutrack.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edutrack.commons.core.index.Index;
import edutrack.commons.util.ToStringBuilder;
import edutrack.logic.Messages;
import edutrack.logic.commands.exceptions.CommandException;
import edutrack.model.Model;
import edutrack.model.person.Person;
import edutrack.model.tag.Tag;

/**
 * Assigns a tag to a person identified by index.
 */
public class TagAssignCommand extends Command {
    public static final String COMMAND_WORD = "tag/assign";
    public static final String MESSAGE_SUCCESS = "Assigned tag to %1$s: %2$s";
    public static final String MESSAGE_TAG_NOT_FOUND = "This tag does not exist in the address book";
    public static final String MESSAGE_DUPLICATE_TAG = "This person already has this tag";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Assigns a tag to a person.\n"
            + "Parameters: INDEX (must be a positive integer) t/TAG\n"
            + "Example: " + COMMAND_WORD + " 1 t/Physics";

    private final Index index;
    private final Tag tag;

    /**
     * Creates a TagAssignCommand to assign the specified {@code Tag} to a person at {@code Index}
     */
    public TagAssignCommand(Index index, Tag tag) {
        requireNonNull(index);
        requireNonNull(tag);
        this.index = index;
        this.tag = tag;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasTag(tag)) {
            throw new CommandException(MESSAGE_TAG_NOT_FOUND);
        }

        // Get the canonical tag from the model to ensure correct casing
        Tag canonicalTag = model.getTag(tag);

        List<Person> lastShownList = model.getFilteredPersonList();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());

        if (personToEdit.getTags().contains(canonicalTag)) {
            throw new CommandException(MESSAGE_DUPLICATE_TAG);
        }

        // Add canonical tag to person
        Set<Tag> updatedTags = new HashSet<>(personToEdit.getTags());
        updatedTags.add(canonicalTag);

        Person editedPerson = new Person(
                personToEdit.getName(),
                personToEdit.getPhone(),
                personToEdit.getEmail(),
                personToEdit.getAddress(),
                updatedTags,
                personToEdit.getGroups(),
                personToEdit.getNote());

        model.setPerson(personToEdit, editedPerson);

        return new CommandResult(String.format(MESSAGE_SUCCESS, editedPerson.getName(), canonicalTag));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof TagAssignCommand)) {
            return false;
        }

        TagAssignCommand otherCommand = (TagAssignCommand) other;
        return index.equals(otherCommand.index) && tag.equals(otherCommand.tag);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("tag", tag)
                .toString();
    }
}

