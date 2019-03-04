# Intro

The goal of this homework is to install the basic tooling that is used throughout the semester and to learn its basic functionality. The tooling portion of the homework can be completed on a personal machine, laptop or otherwise, or an open lab computer. Many of the steps require research to complete. This lack of specificity is intentional and students are encouraged to use [Stackoverflow](https://stackoverflow.com/) and other resources as appropriate.

Please note that although Eclipse is the assumed standard IDE for the course, it is not mandated. The tooling works in all major IDEs or via command-line interface if so desired. Students are welcome to use a preferred IDE with the caveat that Eclipse is the assumed default so TA and Instructor support with tooling will vary in other IDEs.

The second goal of this homework is to become familiar with the [Class Policies](https://byu.instructure.com/courses/3358/pages/class-policies) and other logistics for the course. This portion of the homework should not take much time.

# Part I: Eclipse, Maven, and Git (20 points)

The goal of this part of the homework is to learn how to create a Maven project in Eclipse, add Git support that project, and create an initial commit.

1. Install the [Java Eclipse IDE](https://www.eclipse.org/downloads/) using the [Eclipse Installer](https://www.eclipse.org/downloads/packages/installer). If you are using an Apple platform, [Homebrew Cask](http://caskroom.io/) is able to install it directly; the package name is `eclipse-java`.

2. Create a Maven Project in Eclipse. Choose an [Archetype](https://maven.apache.org/archetypes/) that is appropriate for a Hello World Java program. Most Eclipse Java downloads include the [M2Eclipse](https://www.eclipse.org/m2e/) Maven tooling, but if it is missing, then it can be added easily via the update site: <http://download.eclipse.org/technology/m2e/releases>. If the [Archetype](https://maven.apache.org/archetypes/) did not include it, then add the classical "Hello World!" console output to a main-method. Run the program to be sure it works as expected.

3. Create a local git repository either through the Eclipse interface or via command line and commit all the project files with an [appropriately formatted commit message](https://gist.github.com/robertpainsi/b632364184e70900af4ab688decf6f53). This commit is crucial for the git patch you will create in Part IV. Most Eclipse Java downloads include the [EGit](https://www.eclipse.org/egit/) Git tooling, but if it is missing, then it can be added easily via the update site: <http://download.eclipse.org/egit/updates>.

# Part II: SonarLint, SLF4J, and Javadoc (20 points)

The goal if this part is to learn to use the SonarLint static code analyzer, or linter, and the SLF4 logger.

1. Install [SonarLint](https://www.sonarlint.org/) from the [Eclipse Marketplace](https://marketplace.eclipse.org/content/sonarlint). Run it on the project created in Part I and fix any reported concerns except the one related to the logger. That will be addressed in the next step.

2. Edit the Maven pom.xml file to add support for [SLF4J](https://www.slf4j.org/) (Simple Logging Facade for Java) and [Logback](https://logback.qos.ch/) for the binding library SLF4J. Once these are added to the Maven dependencies, create an appropriate configuration file for the logger that connects it to STDOUT. A careful [Google search](http://bfy.tw/LdDI) will find several helpful resources.

3. Modify the main function in the Part I program to use the logger, and add additional logging output at different log-levels. Using the configuration, experiment with the different log-levels available and how the console output is changed by the configuration.

4. Create [Javadoc](https://www.oracle.com/technetwork/java/javase/documentation/index-137868.html#examples) comments for the class and main-method. In the main-method documentation, describe the different available log levels and how the configuration affects what is output to the console. Use the Javadoc view in Eclipse to preview the formatted comments.

# Part III: Course Policies (42 Points)

The goal of this part is to become familiar with the logistics of the course itself. All answers should be found in the [Syllabus](https://byu.instructure.com/courses/3358/assignments/syllabus), in the [Class Policies](https://byu.instructure.com/courses/3358/pages/class-policies), [Department Learning Outcomes](https://byu.instructure.com/courses/3358/pages/department-learning-outcomes), [TA Schedule and Help Queue](https://byu.instructure.com/courses/3358/pages/ta-schedule-and-help-queue), and [Study Habits and Teaching Philosophy](https://byu.instructure.com/courses/3358/pages/study-habits-and-teaching-philosophy), or through links on those pages.

Create a new text document in the Part I project. Record answers to the below questions in that text file.

1. (4 points) What is the purpose of CS 329 and why is it important?
2. (3 points) Name three things that interest you that you should be able to do at the end of this course?
3. (5 points) A 10 point homework is due Friday, January 5, 2018. Assuming all questions are answered correctly, then how many points is it scored for each of the below submission dates?
    * Wednesday, January 3
    * Saturday, January 6
    * Sunday, January 7
    * Monday, January 8
    * Friday, January 12
4. (3 point) A 10 point lab is also due Friday, January 5, 2018. Assuming it is done correctly, how many points is it scored for each of the below dates?
    * Tuesday, January 2
    * Thursday, January 4
    * Sunday, January 7
5. (2 points) What is the role of the TAs and what is the minimum time between visits?
6. (2 points) How do TAs decide the order of students to help? Where are the TAs located?
7. (1 point) How do you appeal a grading concern or other issue in the course?
8. (4 points) What are the different types of cheating identified in the syllabus?
9. (2 points) What is the correct way to do group learning activities?
10. (2 points) What are two study habits on the syllabus that you want to improve this semester?
11. (2 points) How do you grow intellect?
12. (1 point) What is University policy on reporting sexual misconduct? Where is it reported?
13. (2 points) If you are a victim of sexual misconduct or know of sexual misconduct, who can you contact for help?
14. (2 points) What is the university policy for an incomplete grade?
15. (3 points) What is the date of the add/drop deadline for this semester? What about the withdraw deadline? The discontinuance deadline?
16. (4 points) What scares you the most about this course?

# Part IV: Git Patch (20 Points)

The goal of this section is to learn to create and use patches in Git.

1. Add any new files that have been created since Part I and commit all the new files and modified files to the repository with an appropriate commit comment.

2. Use Git to create a single patch relative to the Part I project. The patch would include all the changes made to the project in Parts II and III. There are several ways to create the patch. If for some reason there is more than one commit from Part I to Part IV, git will create several patches. As a single patch is required, it may be necessary to create a new branch, merge in changes on the new branch, but do it in a way so that git squashes all the commits into a single change set and does not commit the changes.

3. Test the patch, and if it is good, then submit it for this homework. The TAs will grade everything from the patch.
