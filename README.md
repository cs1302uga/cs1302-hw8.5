# cs1302-hw08 Fun with Components and Containers

![Approved for: Fall 2022](https://img.shields.io/badge/Approved%20for-Fall%202022-darkgreen)

> Words, words, words.
> **-- William Shakespeare, _Hamlet_**

This homework explores creating graphical user interfaces (GUIs) using the JavaFX library. Students
will create an interactive, tabbed GUI that loads images (one per tab) from a specified URL. The program
supports images in BMP, GIF, JPEG, and PNG formats. Students will need to create custom JavaFX components
by extending (inheriting from) existing JavaFX components.

## Course-Specific Learning Outcomes

* **LO2.e:** (Partial) Utilize existing generic methods, interfaces, and classes in a software solution.
* **LO7.a:** (Partial) Design and implement a graphical user interface in a software project.

## References and Prerequisites

* [CSCI 1302 JavaFX Tutorial](https://github.com/cs1302uga/cs1302-tutorials/blob/alsi/javafx/javafx.md)
* [CSCI 1302 JavaFX Custom Component Tutorial](https://github.com/cs1302uga/cs1302-tutorials/blob/alsi/components/components.md)
* [JavaFX Bookmarks](https://github.com/cs1302uga/cs1302-tutorials/blob/master/javafx/javafx-bookmarks.md)
* [JavaFX API Documentation](https://openjfx.io/javadoc/17/)

## Questions

In your notes, clearly answer the following questions. These instructions assume that you are
logged into the Odin server.

**NOTE:** For each step, please provide in your notes the full command that you typed to make the related
action happen along with an explanation of why that command worked. Some commands require multiple options.
It is important to not only recall what you typed but also why you typed each of them. If context is necessary
(e.g., the command depends on your present working directory), then please note that context as well.
You won't need to submit your notes in your final submission. However, if done properly, your exercise notes
will serve as a helpful study guide for the exam.

### Getting Started

1. Use Git to clone the repository for this exercise onto Odin into a subdirectory called `cs1302-hw08`:

   ```
   $ git clone --depth 1 https://github.com/cs1302uga/cs1302-hw08.git
   ```

## Exercise Steps

### Checkpoint 1 Steps

1. Take a few minutes to look over the starter code provided to you. You should notice that it is a completed implementation of the
   code written by the end of the
   [CSCI 1302 JavaFX Custom Component Tutorial](https://github.com/cs1302uga/cs1302-tutorials/blob/alsi/components/components.md),
   assigned as a reading last week. In that tutorial, you created the following containment hierarchy using your custom `ImageLoader`
   component:

   ```
                                                             --|
                         Stage                                 |
                           |                                   |
                         Scene                                 |
          |--              |                                   | Overall
          |               HBox                                 | Containment
   Scene  |                |\                                  | Hierarchy
   Graph  |                | \------------------\              |
          |                |                    |              |
          |           ImageLoader          ImageLoader         |
          |--                                                --|
   ```

1. Compile and run the code using [Maven](https://github.com/cs1302uga/cs1302-tutorials/blob/alsi/maven.md) commands.

   **NOTE:** The starter code includes a `module-info.java` file for the `cs1302.hw08` module. If you
   encounter an "unnamed module" error when using `mvn exec:java`, then use `cs1302.hw08/cs1302.hw08.ImageDriver`
   for the value of the `-Dexec.mainClass` option instead of `cs1302.hw08.ImageDriver`.

1. Create a compile script so you don't have to retype those commands each time.

1. Stage and commit your compile script then tag your commit so that it's easier to checkout
   at a later point in time:

   ```
   $ git tag tutorial
   ```

   [Tagging](https://git-scm.com/book/en/v2/Git-Basics-Tagging) allows us
   to give the commit a more convenient name to a commit than its
   hexademical checksum.

<hr/>

![CP](https://img.shields.io/badge/Just%20Finished%20Checkpoint-1-success?style=for-the-badge)

<hr/>

### Checkpoint 2 Steps

1. Now, read the class-level API documentation for the
   [`TilePane`](https://openjfx.io/javadoc/17/javafx.graphics/javafx/scene/layout/TilePane.html)
   class, then adapt your code to replace the highest `HBox` in the
   containment hierarchy with a `TilePane` object.

   * The `TilePane` object's `prefColumns` should be set to `2` using the appropriate setter
     method.

   * The `TilePane` object should have four `ImageLoader` objects as its children.

   Here is the corresponding containment hierarchy for what is expected:

   ```
                                                             --|
                              Stage                            |
                                |                              |
                              Scene                            |
          |--                   |                              | Overall
          |                 TilePane                           | Containment
   Scene  |                    /|\                             | Hierarchy
   Graph  |          /--------/ | \--------\                   |
          |         /          / \          \                  |
          |      ImageLoader  /   \        ImageLoader         |
          |                  /     \                           |
          |        ImageLoader     ImageLoader                 |
          |--                                                --|
   ```

1. **Compile and run your code without any errors or warnings,
   make sure it passes a `checkstyle` audit,
   then stage and commit your changes.**

1. Now, increase the number of `ImageLoader` objects to `8`. This
   should be easy if you used a loop.

1. **Compile and run your code without any errors or warnings,
   make sure it passes a `checkstyle` audit,
   then stage and commit your changes.** If your GUI goes off the sides of your screen, don't worry, it will
   look fine on a higher-resolution display. Feel free to change the number of `ImageLoader` objects back
   to 4 before submitting.

1. Commit these changes then tag your commit so that it's easier to checkout
   at a later point in time:

   ```
   $ git tag tilepane
   ```

<hr/>

![CP](https://img.shields.io/badge/Just%20Finished%20Checkpoint-2-success?style=for-the-badge)

<hr/>

### Checkpoint 3 Steps

1. Now, read the class-level API documentation for the
   [`TabPane`](https://openjfx.io/javadoc/17/javafx.controls/javafx/scene/control/TabPane.html)
   and [`Tab`](https://openjfx.io/javadoc/17/javafx.controls/javafx/scene/control/Tab.html)
   classes, then adapt your code to replace the `TilePane` in the
   containment hiearchy with a `TabPane` object.

   * The `TabPane` object should have four `Tab` objects, each containing an `ImageLoader` object
     as its child.

   Here is the corresponding containment hierarchy for what is expected:

   ```
                                                             --|
                              Stage                            |
                                |                              |
                              Scene                            |
          |--                   |                              | Overall
          |                  TabPane                           | Containment
   Scene  |                    /|\                             | Hierarchy
   Graph  |          /--------/ | \--------\                   |
          |        Tab          |          Tab                 |
          |        /           / \           \                 |
          |     ImageLoader  Tab Tab         ImageLoader       |
          |                  /     \                           |
          |        ImageLoader     ImageLoader                 |
          |--                                                --|
   ```

1. **Compile and run your code without any errors or warnings,
   make sure it passes a `checkstyle` audit,
   then stage and commit your changes.**

1. Tag your commit so that it's easier to checkout at a later
   point in time:

   ```
   $ git tag tabpane
   ```

1. View the condensed, graphical version of your Git log.
   Since you tagged each relevant commit with a name, you
   can go back in time by checking out those commits more
   easily. For example,

   ```
   $ git checkout tutorial
   ```

   Then, compile and run to see what your exercise looked like
   at that point in time!

<hr/>

![CP](https://img.shields.io/badge/Just%20Finished%20Checkpoint-3-success?style=for-the-badge)

<hr/>

### Submission Steps

**Each student needs to individually submit their own work.**

1. Create a plain text file called `SUBMISSION.md` directly inside the `cs1302-hw08`
   directory with the following information:

   1. Your name and UGA ID number

   Here is an example of the contents of `SUBMISSION.md`.

   ```
   Sally Smith (811-000-999)
   ```

1. Change directories to the parent of `cs1302-hw08` (e.g., `cd ..` from `cs1302-hw08`). If you would like
   to make a backup tar file, the instructions are in the submissions steps for [hw01](https://github.com/cs1302uga/cs1302-hw01).
   We won't repeat those steps here and you can view them as optional.

1. Use the `submit` command to submit this exercise to `csci-1302`:

   ```
   $ submit cs1302-hw08 csci-1302
   ```

   Read the output of the submit command very carefully. If there is an error while submitting, then it will displayed
   in that output. Additionally, if successful, the `submit` command creates a new receipt file in the directory you
   submitted. The receipt file begins with rec and contains a detailed list of all files that were successfully submitted.
   Look through the contents of the rec file and always remember to keep that file in case there is an issue with your submission.

   **Note:** You must be on Odin to submit.

<hr/>

![CP](https://img.shields.io/badge/Just%20Finished-Submission-success?style=for-the-badge)
<hr/>

[![License: CC BY-NC-ND 4.0](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-nd/4.0/) [![License: CC BY-NC 4.0](https://img.shields.io/badge/Instructor%20License-CC%20BY--NC%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc/4.0/)

<small>
Copyright &copy; Michael E. Cotterell, Bradley J. Barnes, and the University of Georgia.
This work is licensed under
a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a> to students and the public and licensed under
a <a rel="license" href="http://creativecommons.org/licenses/by-nc/4.0/">Creative Commons Attribution-NonCommercial 4.0 International License</a> to instructors at institutions of higher education.
The content and opinions expressed on this Web page do not necessarily reflect the views of nor are they endorsed by the University of Georgia or the University System of Georgia.
</small>
