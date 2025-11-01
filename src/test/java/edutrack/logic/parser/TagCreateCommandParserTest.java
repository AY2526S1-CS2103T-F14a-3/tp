package edutrack.logic.parser;

import static edutrack.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static edutrack.logic.parser.CommandParserTestUtil.assertParseFailure;
import static edutrack.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import edutrack.logic.commands.TagCreateCommand;
import edutrack.model.tag.Tag;

public class TagCreateCommandParserTest {

    private TagCreateCommandParser parser = new TagCreateCommandParser();

    @Test
    public void parse_validArgs_returnsTagCreateCommand() {
        Tag expectedTag = new Tag("Physics");
        assertParseSuccess(parser, " t/Physics", new TagCreateCommand(expectedTag));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // no tag prefix
        assertParseFailure(parser, "Physics",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagCreateCommand.MESSAGE_USAGE));

        // empty tag name
        assertParseFailure(parser, " t/", Tag.MESSAGE_CONSTRAINTS);

        // invalid tag name with spaces
        assertParseFailure(parser, " t/has space", Tag.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_missingTagPrefix_throwsParseException() {
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagCreateCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_preamblePresent_throwsParseException() {
        assertParseFailure(parser, "some preamble t/Physics",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagCreateCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_multipleTags_throwsParseException() {
        // multiple tags should not be allowed
        assertParseFailure(parser, " t/Physics t/Chemistry",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagCreateCommand.MESSAGE_USAGE));
    }
}

