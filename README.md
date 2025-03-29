# cs1302-hw8.5 Threaded Reader App

![Approved for: Spring 2025](https://img.shields.io/badge/Approved%20for-Spring%202025-blue)

This homework explores creating responsive graphical user interfaces (GUIs) using the JavaFX library and Java threads. 
The application displays the contents of a text (`.txt`) found at the URL provided by the user. However, the starter code 
hangs (doesn't allow the user to interact with the application) while the data in the text file is being downloaded. 
Students must use Java threads to ensure that the GUI remains responsive while the text is downloading.

## Course-Specific Learning Outcomes

* **LO7.a:** (Partial) Design and implement a graphical user interface in a software project.

## References and Prerequisites

* [CSCI 1302 JavaFX Chapter](https://cs1302uga.github.io/cs1302-book/java/javafx/javafx-index.html)
* [CSCI 1302 Threads Chapter](https://cs1302uga.github.io/cs1302-book/java/threads/threads-index.html)
* [JavaFX API Documentation](https://openjfx.io/javadoc/17/)

## Questions

In your notes, clearly answer the following questions. These instructions assume that you are
logged into the Odin server.

**NOTE:** For each step, please provide in your notes the full command you typed to make the related
action happen along with an explanation of why that command worked. Some commands require multiple options.
It is important to not only recall what you typed but also why you typed each of them. If context is necessary
(e.g., the command depends on your present working directory), then please note that context as well.
You won't need to submit your notes in your final submission. However, if done properly, your exercise notes
will serve as a helpful study guide for the exam.

### Getting Started

1. Use Git to clone the repository for this exercise onto Odin into a subdirectory called `cs1302-hw8.5`:

   ```
   $ git clone --depth 1 https://github.com/cs1302uga/cs1302-hw8.5.git
   ```

## Exercise Steps

### Checkpoint 1 Steps

1. Take a few minutes to look over the starter code provided to you.

1. Compile and run the code using the provided compile script (`compile.sh`) which uses 
   [Maven](https://cs1302uga.github.io/cs1302-book/tools/maven/maven-index.html) commands.

1. The default URL in the application points to a plain-text UTF-8 version of __The Adventures of Sherlock
   Holmes__ by Sir Arthur Conan Doyle. **Before you click load, please note:**
   * We have added an artificial delay to the application, so downloading the text will take longer than usual. 
     The delay will help us see the impact of long-running event handlers. In this case, the long-running
     event handler runs when the button is clicked. While the event handler is executing (to download the text),
     nothing else can occur in the application.
   * When you click load, try to interact with the application. Specifically, see if you can type in the
     URL bar.
   * Go ahead and click "Load". Notice how the application becomes completely unresponsive until the text displays.
   * Wait for the text to display and then close the application.
   
1. Take a few minutes to better understand the starter code that was given to you by drawing the scene graph it
   creates. Then answer the following questions in your notes:
   * What are the names of the JavaFX components that hold the text?
   * What is the name of the method that is called when the button is clicked? In other words, what method serves as 
     the event handler for the button?
   * What part of the event handler method takes the longest to complete?
   
1. Remember, JavaFX applications run on the JavaFX Application Thread. However, when we have a long-running event
   handler, we need to move that event handler to a separate thread so the JavaFX Application Thread can continue 
   updating the GUI while the long-running handler is executing.
   
1. Modify the code so that a new thread is created and runs the `loadPage` method when the button is clicked. Do not
   include any calls to `Platform.runLater` at this time.
   
1. Compile and run the application with the given compile script and then click the "Load" button. You should see an
   error message similar to the following (you may have to close the window to see the error):
   
   ```
    Caused by: java.lang.IllegalStateException: Not on FX application thread; currentThread = Thread-3
    at com.sun.javafx.tk.Toolkit.checkFxUserThread (Toolkit.java:295)
    at com.sun.javafx.tk.quantum.QuantumToolkit.checkFxUserThread (QuantumToolkit.java:458)
    at javafx.scene.Parent$3.onProposedChange (Parent.java:474)
    at com.sun.javafx.collections.VetoableListDecorator.clear (VetoableListDecorator.java:293)
    at cs1302.reader.ReaderApp.loadPage (ReaderApp.java:91)
    at cs1302.reader.ReaderApp.lambda$init$0 (ReaderApp.java:58)
    at java.lang.Thread.run (Thread.java:833)
   ```
   
   This error tells you that the code on line 91 (may be slightly different in your output) needs to be run
   from the JavaFX Application Thread. Notice that the code on line 91 modifies the `TextFlow` object which also
   modifies the scene graph. 
   
   Any code that modifies the scene graph must run on the JavaFX Application Thread. In this case, we just need to
   move that line back to the JavaFX Application Thread by using 
   [`Platform.runLater()`](https://openjfx.io/javadoc/17/javafx.graphics/javafx/application/Platform.html#runLater(java.lang.Runnable)).
   
1. Go ahead and add the call to `Platform.runLater` to the line that caused the problem and then compile and run again. If
   the same error occurs at a different location, go ahead and add `Platform.runLater` to that line as well.
   
1. Once you are confident that you have rid your program of errors, test your program by making sure you can interact with the
   `TextField` while the data is downloading. If that works, try a different URL for another book.
   
1. Make sure your application works with an invalid URL (displays exception message in the `TextFlow`) and passes `check1302`.
   
1. Congratulations on building an application that uses threads!

<hr/>

![CP](https://img.shields.io/badge/Just%20Finished%20Checkpoint-1-success?style=for-the-badge)

<hr/>

### Submission Steps

**Each student needs to individually submit their own work.**

1. Create a plain text file called `SUBMISSION.md` directly inside the `cs1302-hw8.5`
   directory with the following information:

   1. Your name and UGA ID number

   Here is an example of the contents of `SUBMISSION.md`.

   ```
   Sally Smith (811-000-999)
   ```

1. Change directories to the parent of `cs1302-hw8.5` (e.g., `cd ..` from `cs1302-hw8.5`). If you would like
   to make a backup `.tar` file, the instructions are in the submission steps for [hw01](https://github.com/cs1302uga/cs1302-hw01).
   We won't repeat those steps here and you can view them as optional.

1. Use the `submit` command to submit this exercise to `csci-1302`:

   ```
   $ submit cs1302-hw8.5 csci-1302
   ```

   Read the output of the submit command very carefully. If there is an error while submitting, then it will displayed
   in that output. Additionally, if successful, the `submit` command creates a new receipt file in the directory you
   submitted. The receipt file begins with "rec" and contains a detailed list of all files that were successfully submitted.
   Look through the contents of the receipt file and always remember to keep that file in case there is an issue with your submission.

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
