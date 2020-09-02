# git-course

### Intro to VCS

Git is a tool to store files with the ability to track changes: who's introduced them, which changes were introduced and when. So things may look this way:

1. PersonA creates a file "readme.txt" and write a sentence "Read Me!" in it on Nov 12 2002
2. PersonB may check out that file, change its content to "Do not read me!" the next day
3. PersonA now can see that PersonB made the changes, find him and punish him. After that he can revert the changes back

The history of changes is super important during software development:

1. If someone made a change and you don't understand it - you know whom to ask
2. You can roll back changes e.g. in case of a bug. The bug may have been introduced a year ago - but you still have access to those changes
3. You can collaborate with others on the same list of files by sharing changes and reviewing changes of others

Tools like this are called VCS (version control system) or SCM (source code management), people use these terms interchangeably.

### Overview of VCS tools

There are many VCS tools like Git, SVN, Perforce, etc. Such tools will have:

* **VCS Server**. After you introduced a change to a file you'd like to share it with your colleagues. You could send that change via email or communicate with your colleagues' computers directly, but most of the time it's very inconvenient. Thus you'll need a VCS Server - a common place where everyone will pull/push changes from/to. Examples of such servers: https://github.com, https://bitbucket.org/, etc. Many organizations create such servers internally - mostly because they are afraid to share their information to the outside world.
* **VCS Server Software**. VCS Server has to host a software that would accept changes and allow others to log in and pull the changes. There are many of such apps: Gerrit, GitHub, BitBucket. The companies that create those apps often host the public version of it (like https://github.com), but you can buy the software and run it within your organization.
* **VCS clients**. This is an application that's installed on your computer: it can track changes that you introduce into files and then send those changes to VCS Servers (as well as receive updates from the server). These client apps is what you're going to deal with most of the time. There are 2 types:
   * Command line clients (e.g. `git`, `svn`, `p4`). You'll have to open your terminal and type the commands for those client apps.
   * UI clients (e.g. SourceTree, TurtoiseSVN, Perforce Visual Client). There are many of them and usually IDEs have their own built-in UI to work with VCS.

Knowing command line tools is usually more important because they are closer to what's actually happening under the hood and will give you a better understanding of the VCS of choice.

### Basic Git commands