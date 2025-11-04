---
layout: page
title: User Guide
---

# EduTrack User Guide
**EduTrack** is a classroom management system specially catered to tutors.

It allows you to manage your students on your desktop with keyboard commands.
<br>

#### Why should you use it?
If you type fast, you can handle and manage all your students across your different classes faster than traditional GUI apps, using EduTrack!
<br>


## Table of Contents

* [Quick start](#quick-start)
* [Features](#features)
  * [Helpful Info](#helpful-info)
  * [Commands](#commands)
      * [Help page: `help`](#help-page-help)
      * [Adding a student: `add`](#adding-a-student-add)
      * [Listing all students : `list`](#listing-all-students-list)
      * [Editing a student : `edit`](#editing-a-student-edit)
      * [Locating by name or group: `find`](#locating-by-name-or-group-find)
      * [Locating by tag: `findtag`](#locating-by-tag-findtag)
      * [Deleting a student : `delete`](#deleting-a-student--delete)
      * [Clearing all students : `clear`](#clearing-all-students--clear)
      * [Exiting the program : `exit`](#exiting-the-program--exit)
      * [Create Group: `group/create`](#create-group-groupcreate)
      * [Delete Group: `group/delete`](#delete-group-groupdelete)
      * [List Groups: `group/list`](#list-groups-grouplist)
      * [Assign Group: `group/assign`](#assign-group-groupassign)
      * [Unassign Group: `group/unassign`](#unassign-group-groupunassign)
      * [Create Tag: `tag/create`](#create-tag-tagcreate)
      * [Delete Tag: `tag/delete`](#delete-tag-tagdelete)
      * [List Tags: `tag/list`](#list-tags-taglist)
      * [Assign Tag: `tag/assign`](#assign-tag-tagassign)
      * [Unassign Tag: `tag/unassign`](#unassign-tag-tagunassign)
      * [Stats: `stats`](#viewing-statistics-stats)
      * [Sorting: `sort`](#sorting-sort)
      * [Create note: `note/create`](#create-notes-notecreate)
      * [Delete note: `note/delete`](#delete-notes-notedelete)
  * [Other functionality](#other-functionality)
    * [Saving the data](#saving-the-data)
    * [Editing the data file](#editing-the-data-file)
    * [Archiving data files `[coming in v2.0]`](#archiving-data-files-coming-in-v20)
* [FAQ](#faq)
* [Known issues](#known-issues)
* [Command summary](#command-summary)
* [Glossary](#glossary)

--------------------------------------------------------------------------------------------------------------------

At any point in time, if you encounter any terms that you do not understand, consider checking out our Glossary section for a brief explanation!

## Quick start

1. Java is a programming language that needs to be installed. If you don't have it, download it from the official Java website.

Ensure you have Java `17` or above installed in your Computer.<br>
   **Mac users:** Ensure you have the precise Java version prescribed [here](https://se-education.org/guides/tutorials/javaInstallationMac.html).

2. Download the latest `.jar` file from [here](https://github.com/AY2526S1-CS2103T-F14a-3/tp/releases).
> .jar is referring to what the filename ends with.

3. Save the file to the folder you want to use to store all files related to EduTrack.

4. Open a command terminal
   >* **Mac** / **Linux** users: search for 'Terminal'
   >* **Windows** users: search for 'Command Prompt'

5. Enter the command `cd <FOLDER>` where you replace FOLDER with the folder name that you saved the jar file to.

6. Enter the command `java -jar edutrack.jar` to run the application. Make sure the filename you downloaded matches exactly!<br>

   Your screen should have a window pop up that looks like this below:

![Ui](images/Ui.png)

> The data you see here is the sample data that comes with the app.

7. To do things in EduTrack, you have to type in certain commands into the main command box, and hit Enter.
   <br><br>Refer to the [Features](#features) below for the details of all commands that you can use.
   <br><br>You may also refer to the [Command Summary](#command-summary) below for a summary table of all possible commands.

<a href="#table-of-contents" style="font-size:14px;font-weight:normal">⬆️ Return to Table of Contents</a>

--------------------------------------------------------------------------------------------------------------------


# Features

> For new users of EduTrack, or if you want to jump straight into using our app, you can skip past the 'Helpful Notes' section, and go straight to the 'Commands' section to see what commands you can use!
>
> The 'Helpful Notes' section is extra information for your use!

## Helpful Info 

This section contains helpful information about EduTrack as a whole.

<div markdown="block" class="alert alert-info">

**:information_source: Notes about how to understand the command formats in the guide:**<br>

* In each command section, the **Format:** line shows you how to type the command in the command box, and how to use its parameters.


* You have to replace the words (parameters) in upper case with the actual values you want.<br>
  > e.g. in `add n/NAME`, `NAME` is to be replaced with the actual name to be used, such as `add n/John Doe`.

* You can choose not to include parameters in square brackets, they are optional.<br>
  > e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* If you see parameters with `...` after them, you can use them multiple times, or not at all.<br>
  > e.g. `[t/TAG]...` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* In EduTrack, prefixes are short labels ending with `/` that appear before values to identify them.<br>
  > e.g. in `add n/NAME p/PHONE_NUMBER`, `n/` is the prefix for the student's name, and `p/` is the prefix for the student's phone number.

* You can type the parameters in any order, as long as you put the correct prefix.<br>
  > e.g. if the command specifies `n/NAME p/PHONE_NUMBER`,
  > you can also type `p/PHONE_NUMBER n/NAME`.

* For commands that does not take any parameters (single-word commands like `list`), you should type only the command word. If not, an error will pop up to tell you to remove the extra parameters.<br>
  > e.g. `list` command is typed as `list` only.

* If you are using a PDF version of this document, be careful when copying and pasting commands directly.
    * This is because you may accidentally copy over hidden invalid characters.
</div>

### Parameter Constraints
The table below summarises the constraints for each parameter used in commands.

| Parameter | Prefix | Max Length | Format /Constraints                                                                                                                                                                                                                                                                                                   |
|-----------|--------|------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| NAME      | n/     | 100        | Alphanumeric characters; may include letters (any language), digits, spaces, apostrophes ('), hyphens (-), dots (.), or slashes (/); must not be blank                                                                                                                                                                |
| EMAIL     | e/     | 100        | Format: `local-part@domain`<br>- Local-part: alphanumeric + `+`, `_`, `.`, `-`; cannot start or end with a special character<br>- Domain: made out of domain labels separated by `.`, each label must start/end with alphanumeric characters, hyphens allowed internally, last label ≥2 chars<br>- Optional field (can be empty) |
| PHONE     | p/     | 20         | Digits, spaces, hyphens (-), and optional '+' prefix; must contain at least 3 digits<br>- Optional field (can be empty)                                                                                                                                                                                               |
| ADDRESS   | a/     | 100        | Any printable characters; must not start with whitespace; must not be blank<br>- Optional field (can be empty)                                                                                                                                                                                                        |
| TAG       | t/     | 50         | Alphanumeric characters only; may include hyphens (-), underscores (_), or slashes (/); no spaces allowed<br>- Case-insensitive<br>- Must be created first using `tag/create`<br/>- Optional field (can be empty)                                                                                                          |
| GROUP     | g/     | 50         | Alphanumeric characters only; may include hyphens (-), underscores (_), or slashes (/); no spaces allowed<br>- Case-insensitive<br>- Must be created first using `group/create`<br/>- Optional field (can be empty)                                                                                                   |
| NOTE      | no/    | 100        | Any printable characters; must not start with whitespace or control character<br>- Optional field (can be empty)                                                                                                                                                                                                      |

#### Notes:

> - #### Colour Coding
>   - In EduTrack, there are some visual elements that are distinguished from one another by their colour:
>     - Groups are displayed in the (duller) colour `teal` (Hex: #10938F).
>     - Tags are displayed in the (brighter) colour `blue-green`(Hex: #00C4A8).
>
>   - These colour distinctions make it easier to identify key details at a glance.
>
> - Other attributes (such as name, phone, email, address, notes) are displayed as plain text lines without colour highlighting.
>   - For these attributes, you will see an explicit label that is displayed on the application.
>
> - #### Trimming of Leading Whitespace
>   - In EduTrack, when you type a parameter with lots of whitespace at the start, all of that whitespace will be trimmed (deleted) when you enter the command

<a href="#table-of-contents" style="font-size:14px;font-weight:normal">⬆️ Return to Table of Contents</a>

--------------------------------------------------------------------------------------------------------------------

## Commands

>Refer to the [Parameters Constraints](#parameter-constraints) or [Command Summary](#command-summary) table for additional information about our commands!

### Help page: `help`

Shows a message explaining how to access the help page (this page).

![help message](images/helpMessage.png)

Format: `help`

#### Notes:
> - You may use the keyboard shortcut `F1` to access the help window too.


<a href="#table-of-contents" style="font-size:14px;font-weight:normal">⬆️ Return to Table of Contents</a>

--------------------------------------------------------------------------------------------------------------------
### Adding a student: `add`

Adds a student to the address book.

Format: `add n/NAME [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [g/GROUP]... [t/TAG]...`

#### Parameters:
* `NAME` : Name of the student. 

    Must start with alphanumeric character; may include letters (any language), digits, spaces, apostrophes ('), hyphens (-), dots (.), and slashes (/); must not be blank. Maximum length of 100 characters.

* `PHONE_NUMBER` : Phone number of the student (Optional field)

    Digits, spaces, hyphens (-), and an optional '+' prefix are allowed. Must contain at least 3 digits, at most 20 characters. Examples: `91234567`, `9123 4567`, `+65 9123 4567`.

* `EMAIL` : Email address of the student (Optional field)
  
    It must be in the format `local-part@domain`. Maximum length of 100 characters.

* `ADDRESS` : Address of the student (Optional field)

    can contain any printable characters. Must not start with whitespace and at most 100 characters.

* `GROUP` : Group(s) the student belongs to (Optional field)

    Only alphanumeric characters, hyphens (-), underscores (_), and slashes (/) are allowed. No spaces allowed. Maximum length 50 characters.

* `TAG` : Tag(s) to be assigned to the student (Optional field)

  Only alphanumeric characters, hyphens (-), underscores (_), and slashes (/) are allowed. No spaces allowed. Maximum length 50 characters.

#### Notes:

> + The first character of NAME must be alphanumeric.
> + All specified groups and tags must already exist in the system.
> + You can add multiple groups by entering `g/GROUP_NAME` multiple times, as seen in the example.
> + Groups and tags must follow their respective naming conventions (see [group/create](#create-group-groupcreate) and [tag/create](#create-tag-tagcreate) for details).
> + Duplicate students are not allowed in the address book. A duplicate is defined as a student with the same name as an existing student (case-insensitive).

#### Example usage:
* `add n/John Doe`
* `add n/Chee Hin g/CS2103T-F14a`
* `add n/Kevin p/91234567 e/kevin@outlook.com a/123 Baker St g/CS2103T-F14a`
* `add n/J.K. Rowling p/98765432 g/CS2100 g/CS2103T t/needs_help t/median`


<a href="#table-of-contents" style="font-size:14px;font-weight:normal">⬆️ Return to Table of Contents</a>

--------------------------------------------------------------------------------------------------------------------

### Listing all students: `list`

Shows a list of all students in the address book.

Format: `list`


<a href="#table-of-contents" style="font-size:14px;font-weight:normal">⬆️ Return to Table of Contents</a>

--------------------------------------------------------------------------------------------------------------------
### Editing a student: `edit`

Edits the information of an existing student in the address book, allowing you to update their details or correct any mistakes.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]... [g/GROUP]... [no/NOTE]`

#### Notes:
> * Edits the student at the specified `INDEX`. The index refers to the index number shown in the displayed student list. The index **must be a positive integer** 1, 2, 3, ...
> * At least one of the optional fields must be provided.
> * Existing values will be updated to the input values.
> * Editing tags using the `edit` command is supported via the `t/` prefix. Providing one or more `t/TAG` values replaces all of the student's current tags with the new tags you specify..
>  * To clear all tags, include only one `t/` with no value (for example: `edit 2 t/`).
>* Editing groups using the `edit` command is supported via the `g/` prefix. Providing one or more `g/GROUP` values replaces all of the student's existing groups with the new groups you specify.
>  * To clear all groups, include only one `g/` with no value (for example: `edit 2 g/`).

<div markdown="block" class="alert alert-warning">

* Note: the `edit` command replaces all of the student's tags or groups when a single `t/` or `g/` is provided. If you want to add or remove a single tag or group without replacing the full set, use `tag/assign` / `tag/unassign` or `group/assign` / `group/unassign` respectively.

</div>

Examples:
* `edit 1 p/91234567 e/johndoe@example.com` - Edits the phone number and email address of the 1st student to be `91234567` and `johndoe@example.com` respectively.
* `edit 3 p/91234567 g/CS2103T g/CS2100 t/Weak no/Needs more help in CS mods` - Edits the 3rd student to be in the CS2103T and CS2100 group with the `Weak` tag and a note.
* `edit 2 n/Betsy Crower t/` - Edits the name of the 2nd student to be `Betsy Crower` and clears all existing tags.
* `edit 2 n/Betsy Crower g/` - Edits the name of the 2nd student to be `Betsy Crower` and clears all existing groups.


<a href="#table-of-contents" style="font-size:14px;font-weight:normal">⬆️ Return to Table of Contents</a>

--------------------------------------------------------------------------------------------------------------------
### Locating by name or group: `find`

Find students by name or by group, allowing you to quickly locate specific students or students belonging to certain groups.
You must choose exactly one of the two forms below.

Format (choose ONE):

By name: `find n/KEYWORD [MORE_KEYWORDS]...`

By group: `find g/GROUP_NAME [MORE_GROUPS]...`

#### Notes:
> * Exactly one of n/ or g/ must be present.
>* Keywords/Group names are separated by spaces.
>* The search is case-insensitive. e.g `hans` will match `Hans`.
>* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`.
>* Name search matches full words in the student’s name. e.g. `Han` will not match `Hans`.
>* Group search matches full words in group names assigned to the student (no partial matches).
>* **Only one** `g/` prefix may be present; to search multiple groups put the group names separated by spaces inside the single `g/` block (for example: `find g/CS1101S CS1231S`).
>* Students matching at least one keyword will be returned (i.e. `OR` search).
>  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`.

#### Examples:
* `find n/John` returns `john` and `John Doe`
* `find n/alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

* `find g/CS2103T` returns all students in group “CS2103T”
* `find g/CS1101S CS1231S` returns all students in groups “CS1101S” or "CS1231S"
  ![result for 'find g/CS1101S CS1231S'](images/findCS1101SCS1231SResult.png)

<a href="#table-of-contents" style="font-size:14px;font-weight:normal">⬆️ Return to Table of Contents</a>

--------------------------------------------------------------------------------------------------------------------

### Locating by tag: `findtag`

Finds students who have the specified tag.

Format: `findtag t/TAG`

#### Notes:
>* The search is case-insensitive. e.g. `t/friends` will match students with tag `Friends`.
>* Only one tag can be searched at a time.
>* Only students with the exact tag will be returned.
>* Tag names may include alphanumeric characters, hyphens (-), underscores (_), and slashes (/).
>* If you try to `findtag` a tag where no students are found, the entire view will show an empty list.

#### Examples:
* `findtag t/friends` returns all students tagged with `friends`
![result for 'find t/friends'](images/findfriendsResult.png)
* `findtag t/needs_help` returns all students tagged with `needs_help`
* `findtag t/upper-quartile` returns all students tagged with `upper-quartile`

<a href="#table-of-contents" style="font-size:14px;font-weight:normal">⬆️ Return to Table of Contents</a>

--------------------------------------------------------------------------------------------------------------------

### Deleting a student : `delete`

Deletes the specified student from the address book, useful for removing students who are no longer in your class.

Format: `delete INDEX`

#### Notes:
>* Deletes the student at the specified `INDEX`.
>* The index refers to the index number shown in the displayed student list.
>* The index **must be a positive integer** 1, 2, 3, ...

#### Examples:
* `list` followed by `delete 2` deletes the 2nd student in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st student in the results of the `find` command.
  <br>


<a href="#table-of-contents" style="font-size:14px;font-weight:normal">⬆️ Return to Table of Contents</a>

--------------------------------------------------------------------------------------------------------------------
### Clearing all students : `clear`

Clears all the data in EduTrack, useful for starting afresh.

Format:
- `clear` (on first use)
- `clear confirm` (after already typing `clear` once and hitting 'Enter')

#### Notes:
> - The clear command clears all data, including people, groups, tags.
> - After you type `clear`, you have to type `clear confirm` to complete the clear.
> - If you try to type `clear confirm` before having typed `clear` at least once, you will be prompted to first initiate the clear.
> - After you type `clear`, if you type anything else other than `clear confirm`, it resets the clearing workflow and you have to start over.

#### Example:
`clear` followed by `clear confirm` clears the list
![result for 'clear'](images/clear.png)
![result for 'clear confirm'](images/clearconfirm.png)

<a href="#table-of-contents" style="font-size:14px;font-weight:normal">⬆️ Return to Table of Contents</a>

--------------------------------------------------------------------------------------------------------------------
### Exiting the program : `exit`

Exits the program.

Format: `exit`

<a href="#table-of-contents" style="font-size:14px;font-weight:normal">⬆️ Return to Table of Contents</a>

--------------------------------------------------------------------------------------------------------------------

### Create Group: `group/create`

Creates a new group with a specified name, can be used to organise students into different tutorial groups or classes.

Format: `group/create g/GROUP_NAME`

#### Notes:
>* `GROUP_NAME` refers to the name you wish to assign to the group.
>* `GROUP_NAME` is case-insensitive and acceptable characters are alphanumeric, hyphens (-), underscores (_), and slashes (/).
>* `GROUP_NAME` has a maximum length of 50 characters.
>* Spaces are not allowed.

<a href="#table-of-contents" style="font-size:14px;font-weight:normal">⬆️ Return to Table of Contents</a>

--------------------------------------------------------------------------------------------------------------------
### Delete Group: `group/delete`

Deletes an existing group and removes all group assignments from associated students, useful for cleaning up unused or past groups.

Format: `group/delete g/GROUP_NAME`

<a href="#table-of-contents" style="font-size:14px;font-weight:normal">⬆️ Return to Table of Contents</a>

--------------------------------------------------------------------------------------------------------------------
### List Groups: `group/list`

Displays all existing groups, allowing you to see all the groups you are managing.

Format: `group/list`

<a href="#table-of-contents" style="font-size:14px;font-weight:normal">⬆️ Return to Table of Contents</a>

--------------------------------------------------------------------------------------------------------------------
### Assign Group: `group/assign`

Assigns a group to one or more existing students, useful for organizing students into their respective tutorial groups or classes.

Format: `group/assign INDEX [MORE_INDEXES...] g/GROUP_NAME`

#### Notes:
> * The indices refer to the index numbers shown in the displayed student list.
> * The indices **must be positive integers** 1, 2, 3, ...
> * Only one group can be assigned in each command.
> * If the specified group does not exist, EduTrack will prompt you to create it first using `group/create.`

#### Examples:
> * `group/assign 1 g/CS2103T` assigns the group `CS2103T` to the 1st student in the list.
> * `group/assign 2 3 5 g/CS2040S` assigns the group `CS2040S` to the 2nd, 3rd, and 5th students in the list.

<a href="#table-of-contents" style="font-size:14px;font-weight:normal">⬆️ Return to Table of Contents</a>

--------------------------------------------------------------------------------------------------------------------
### Unassign Group: `group/unassign`

Removes a group from one or more students, useful for updating group memberships when students change groups or leave the class.

Format: `group/unassign INDEX [MORE_INDEXES]... g/GROUP_NAME`

#### Notes:
> * The indices refer to the index numbers shown in the displayed student list.
> * The indices **must be positive integers** 1, 2, 3, ...
> * Only one group can be unassigned in each command.
> * The specified group must exist in the system.

#### Examples:
> * `group/unassign 1 g/CS2103T` removes the group `CS2103T` from the 1st student in the list.
> * `group/unassign 2 3 g/CS2040S` removes the group `CS2040S` from the 2nd and 3rd students in the list.

<a href="#table-of-contents" style="font-size:14px;font-weight:normal">⬆️ Return to Table of Contents</a>

--------------------------------------------------------------------------------------------------------------------

### Create Tag: `tag/create`

Creates a new tag with a specified name, useful for categorising students based on specific attributes or needs.

Format: `tag/create t/TAG_NAME`

#### Notes:
>* `TAG_NAME` refers to the name you wish to assign to the tag.
>* `TAG_NAME` is case-insensitive and acceptable characters are alphanumeric, hyphens (-), underscores (_), and slashes (/).
>* `TAG_NAME` has a maximum length of 50 characters.
>* Spaces are not allowed.

<div markdown="block" class="alert alert-info">
**Tag & Group name validation (quick reference)**

* Allowed characters: letters, digits, hyphen (-), underscore (_), slash (/).
* No spaces are allowed — use `_` or `-` instead.
* Maximum length: 50 characters.
* Matching is case-insensitive (e.g. `friends` == `Friends`).

Use these rules when creating tags or groups (commands: `tag/create`, `group/create`).
</div>

### Visual appearance of tags and groups

* Tags and groups are displayed as small labels beside a student's name and details.
* Tags are shown in a darker cyan colour and groups are shown in a lighter cyan colour; otherwise they appear in the same label style.

<a href="#table-of-contents" style="font-size:14px;font-weight:normal">⬆️ Return to Table of Contents</a>

--------------------------------------------------------------------------------------------------------------------
### Delete Tag: `tag/delete`

Deletes an existing tag and removes all tag assignments from associated students.

Format: `tag/delete t/TAG_NAME`

<a href="#table-of-contents" style="font-size:14px;font-weight:normal">⬆️ Return to Table of Contents</a>

--------------------------------------------------------------------------------------------------------------------
### List Tags: `tag/list`

Displays all existing tags.

Format: `tag/list`

<a href="#table-of-contents" style="font-size:14px;font-weight:normal">⬆️ Return to Table of Contents</a>

--------------------------------------------------------------------------------------------------------------------
### Assign Tag: `tag/assign`

Assigns an existing tag to a specified student.

If the specified tag does not exist, EduTrack will prompt you to create it first using `tag/create`.

Format: `tag/assign INDEX t/TAG_NAME`

#### Notes:
> * Assigns the tag `TAG_NAME` to the student at the specified `INDEX`.
> * The index refers to the index number shown in the displayed student list.
> * The index **must be a positive integer** 1, 2, 3, ...
> * The tag must already exist in the system.

#### Examples:
> * `tag/assign 1 t/needs_help` assigns the tag `needs_help` to the 1st student in the list.
> * `tag/assign 3 t/weak` assigns the tag `weak` to the 3rd student in the list.

<a href="#table-of-contents" style="font-size:14px;font-weight:normal">⬆️ Return to Table of Contents</a>

--------------------------------------------------------------------------------------------------------------------
### Unassign Tag: `tag/unassign`

Unassigns an existing tag from a specified student.

Format: `tag/unassign INDEX t/TAG_NAME`

#### Notes:
> * Removes the tag `TAG_NAME` from the student at the specified `INDEX`.
> * The index refers to the index number shown in the displayed student list.
> * The index **must be a positive integer** 1, 2, 3, ...
> * The tag must currently be assigned to the student.

#### Examples
> * `tag/unassign 1 t/needs_help` removes the tag `needs_help` from the 1st student in the list.
> * `tag/unassign 3 t/weak` removes the tag `weak` from the 3rd student in the list.

<a href="#table-of-contents" style="font-size:14px;font-weight:normal">⬆️ Return to Table of Contents</a>

--------------------------------------------------------------------------------------------------------------------
### Viewing Statistics: `stats`

Displays comprehensive statistics about your students and groups.

Format: `stats`

#### Notes:
> - The statistics window displays information in two main sections:
>   - **Total Stats** - Overview of all students
>     - Total number of students
>     - Total unique tags in use
>     - Breakdown of each tag and how many students have it
>   - **Group Stats** - Breakdown by tutorial group
>     - For each group, displays:
>       - Group name (e.g., CS2103T, CS2100)
>       - Number of students in the group
>       - Number of unique tags used in the group
>       - Breakdown of each tag and how many students in that group have it
> - Use this command to quickly identify which groups need more attention or to see tag distribution across your students.

#### Example:
`stats` on the sample data that comes with the app shows the following
![result for 'stats'](images/stats.png)

<a href="#table-of-contents" style="font-size:14px;font-weight:normal">⬆️ Return to Table of Contents</a>

--------------------------------------------------------------------------------------------------------------------

### Sorting: `sort`

Sorts all students by their names in alphabetical order.

Format: `sort`

#### Notes:
> * Sorting is case-insensitive
> * The order is ascending
> * Sorting always applies to the full student list rather than the current filtered view.
> * Sorting will reset the view to show all students instead of only the filtered results.

<a href="#table-of-contents" style="font-size:14px;font-weight:normal">⬆️ Return to Table of Contents</a>

--------------------------------------------------------------------------------------------------------------------

### Create notes: `note/create`

Creates a note for a student, allowing you to attach important information or reminders specific to that student.

Format: `note/create INDEX no/NOTE_CONTENT`

#### Notes:
> * Each student can have only one note attached. If a note already exists for the student, it will be overwritten.
> * `NOTE_CONTENT` can contain any characters, including spaces and special characters.

<a href="#table-of-contents" style="font-size:14px;font-weight:normal">⬆️ Return to Table of Contents</a>

--------------------------------------------------------------------------------------------------------------------

### Delete notes: `note/delete`

Deletes the note attached to a student. This command does nothing if the student has no note attached.

Format: `note/delete INDEX`

#### Notes:
> * This command does nothing if the student has no note attached, instead it will simply inform you that there is no note to delete.

<a href="#table-of-contents" style="font-size:14px;font-weight:normal">⬆️ Return to Table of Contents</a>

--------------------------------------------------------------------------------------------------------------------

## Other functionality

### Saving the data

EduTrack data are saved automatically to your computer after any command that changes the data.

There is no need to save manually.

<a href="#table-of-contents" style="font-size:14px;font-weight:normal">⬆️ Return to Table of Contents</a>

--------------------------------------------------------------------------------------------------------------------

### Editing the data file

EduTrack data are saved automatically as a JSON file at this file location:
`[JAR file location]/data/addressbook.json`.


Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning"> :exclamation:  **Caution**  For ADVANCED users:
If your changes to the data file makes its format invalid, EduTrack will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the EduTrack to behave in unexpected ways (e.g., if a value entered is outside the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</div>

<a href="#table-of-contents" style="font-size:14px;font-weight:normal">⬆️ Return to Table of Contents</a>

--------------------------------------------------------------------------------------------------------------------

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

<a href="#table-of-contents" style="font-size:14px;font-weight:normal">⬆️ Return to Table of Contents</a>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous EduTrack home folder.

<a href="#table-of-contents" style="font-size:14px;font-weight:normal">⬆️ Return to Table of Contents</a>

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen.
   * The remedy is to delete the `preferences.json` file created by the application before running the application again.

<a href="#table-of-contents" style="font-size:14px;font-weight:normal">⬆️ Return to Table of Contents</a>

--------------------------------------------------------------------------------------------------------------------

## Command Summary

| Action                  | Description                                 | Format / Example                                                                                                                                                                             |
|-------------------------|---------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**                 | Add a student to the list.                  | `add n/NAME [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [g/GROUP]... [t/TAG]...`   <br><br> Example: `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd g/CS2103T t/good_at_uml` |
| **Clear**               | Removes all stored data.                    | `clear`                                                                                                                                                                                      |
| **Delete**              | Deletes student from EduTrack.              | `delete INDEX`<br><br>Example: `delete 3`                                                                                                                                                    |
| **Edit**                | Edit an existing student.                   | `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]... [g/GROUP]... [no/NOTE]` <br><br> Example: `edit 2 n/James Lee e/jameslee@example.com`                                        |
| **Find**                | Locate students by keywords in their names. | `find n/KEYWORD [MORE_KEYWORDS]...`<br><br>Example: `find n/James Jake`                                                                                                                      |
| **Find by Group**       | Find all students in a specific group.      | `find g/GROUP [MORE_GROUPS]...`<br><br>Example: `find g/CS2103T`                                                                                                                             |
| **Find by Tag**         | Find students with a specific tag.          | `findtag t/TAG`<br><br>Example: `findtag t/friends`                                                                                                                                          |
| **Create Group**        | Make a new group.                           | `group/create g/GROUP`<br><br>Example: `group/create g/CS2103T`                                                                                                                              |
| **Delete Group**        | Remove an existing group.                   | `group/delete g/GROUP`<br><br>Example: `group/delete g/CS2103T`                                                                                                                              |
| **Assign to Group**     | Assign students to a group.                 | `group/assign INDEX [MORE_INDEXES]... g/GROUP_NAME`<br><br>Example: `group/assign 1 2 g/CS2103T`                                                                                             |
| **Unassign from Group** | Remove students from a group.               | `group/unassign INDEX [MORE_INDEXES]... g/GROUP_NAME`<br><br>Example: `group/unassign 1 2 g/CS2103T`                                                                                         |
| **List Group**          | Show all existing groups.                   | `group/list`                                                                                                                                                                                 |
| **Create Tag**          | Make a new tag.                             | `tag/create t/TAG`<br><br>Example: `tag/create t/needs_help`                                                                                                                                 |
| **Delete Tag**          | Remove an existing tag.                     | `tag/delete t/TAG`<br><br>Example: `tag/delete t/needs_help`                                                                                                                                 |
| **Assign Tag**          | Assign a tag to a student.                  | `tag/assign INDEX t/TAG_NAME`<br><br>Example: `tag/assign 1 t/needs_help`                                                                                                                    |
| **Unassign Tag**        | Remove a tag from a student.                | `tag/unassign INDEX t/TAG_NAME`<br><br>Example: `tag/unassign 1 t/needs_help`                                                                                                                |
| **List Tag**            | Show all existing tags.                     | `tag/list`                                                                                                                                                                                   |
| **Stats**               | Show all student statistics.                | `stats`                                                                                                                                                                                      |
| **List**                | Show all students.                          | `list`                                                                                                                                                                                       |
| **Help**                | Display help information.                   | `help`                                                                                                                                                                                       |
| **Sort**                | Sort all students alphabetically.           | `sort`                                                                                                                                                                                       |
| **Create Note**         | Creates a note for specified student.       | `note/create INDEX no/NOTE`<br><br>Example: `note/create 3 no/Missed the past 3 deadlines. Needs more help with CS2100.`                                                                     |
| **Delete Note**         | Deletes note attached to specified student. | `note/delete INDEX`<br><br>Example: `note/delete 3`                                                                                                                                          |

<a href="#table-of-contents" style="font-size:14px;font-weight:normal">⬆️ Return to Table of Contents</a>

--------------------------------------------------------------------------------------------------------------------

## Glossary

* GUI — Graphical User Interface: the visual, mouse-driven parts of the application (windows, buttons, labels). In EduTrack the GUI is implemented using JavaFX (a programming tool) and complements the keyboard command box.
* Alphanumeric - Numbers and letters from the Standard English alphabet 
* Case-insensitive - This means that casing does not matter, meaning that, for example, "John" and "john" are regarded the same.
* Prefix — A short label ending with `/` used to identify command parameters (e.g. `n/`, `t/`, `g/`).
* Filtered view / list — A view of the full student list that only shows students matching a predicate; many commands (e.g. `find`) update the filtered list.
* Predicate — A rule (boolean test) used to decide whether a student should be included in a filtered list (e.g. "name contains keyword").
* Index — A 1-based number shown next to each student in the UI; many commands (e.g. `edit`, `delete`) operate on the student at that index in the currently displayed (filtered) list.
* JSON — JavaScript Object Notation, a text format used for saving EduTrack data to disk.
* Tag — A short label used to categorise a student (no spaces, allowed characters: alphanumeric, `-`, `_`, `/`). Tags are case-insensitive for matching and searches (e.g. `t/Friends` matches `friends`).
* Group — A central entity used to group students (no spaces, allowed characters: alphanumeric, `-`, `_`, `/`). Group names are case-insensitive for matching and searches.
* Hex - A technical way of specifically identifying different colours digitally.



<a href="#table-of-contents" style="font-size:14px;font-weight:normal">⬆️ Return to Table of Contents</a>
