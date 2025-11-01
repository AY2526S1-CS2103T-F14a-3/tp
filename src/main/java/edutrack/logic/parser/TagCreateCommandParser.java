package edutrack.logic.parser;

import static edutrack.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static edutrack.logic.parser.CliSyntax.PREFIX_TAG;

import edutrack.logic.commands.TagCreateCommand;
import edutrack.logic.parser.exceptions.ParseException;
import edutrack.model.tag.Tag;

/**
 * Parses input arguments and creates a new TagCreateCommand object
 */
public class TagCreateCommandParser implements Parser<TagCreateCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the TagCreateCommand
     * and returns a TagCreateCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public TagCreateCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TAG);

        if (!argMultimap.getValue(PREFIX_TAG).isPresent() || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    TagCreateCommand.MESSAGE_USAGE));
        }

        if (argMultimap.getAllValues(PREFIX_TAG).size() > 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    TagCreateCommand.MESSAGE_USAGE));
        }

        Tag tag = ParserUtil.parseTag(argMultimap.getValue(PREFIX_TAG).get());
        return new TagCreateCommand(tag);
    }
}

