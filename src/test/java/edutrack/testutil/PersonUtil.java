package edutrack.testutil;

import static edutrack.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static edutrack.logic.parser.CliSyntax.PREFIX_EMAIL;
import static edutrack.logic.parser.CliSyntax.PREFIX_GROUP;
import static edutrack.logic.parser.CliSyntax.PREFIX_NAME;
import static edutrack.logic.parser.CliSyntax.PREFIX_NOTE;
import static edutrack.logic.parser.CliSyntax.PREFIX_PHONE;
import static edutrack.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import edutrack.logic.commands.AddCommand;
import edutrack.logic.commands.EditCommand.EditPersonDescriptor;
import edutrack.model.group.Group;
import edutrack.model.person.Person;
import edutrack.model.tag.Tag;

/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Person person) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(person);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Person person) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + person.getName().fullName + " ");
        sb.append(PREFIX_PHONE + person.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + person.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + person.getAddress().value + " ");
        person.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));

        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG).append(" ");
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }

        if (descriptor.getGroups().isPresent()) {
            Set<Group> groups = descriptor.getGroups().get();
            if (groups.isEmpty()) {
                sb.append(PREFIX_GROUP).append(" ");
            } else {
                groups.forEach(s -> sb.append(PREFIX_GROUP).append(s.groupName).append(" "));
            }
        }
        descriptor.getNote().ifPresent(note -> sb.append(PREFIX_NOTE).append(note.value).append(" "));
        return sb.toString();
    }
}
