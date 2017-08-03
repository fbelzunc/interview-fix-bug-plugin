# interview-fix-bug-plugin

# Start here

The following plugin contains a `RuntimeException` which can be reproduced by:

1. Clone the git repository

```
git clone https://github.com/cloudbees/cloudbees-support-inteviews.git
```

2. Run the plugin

```
cd cloudbees-support-inteviews
mvn hpi:run
```

3. Create a Folder. *New Item -> Freestyle project*
4. Create a FreeStyle project. *New Item -> Folder* and add the *Build step* called *Tell me if the following plugins are installed or not in the instance*. 
5. Build the FreeStyle project and you should get the folling Exception``

```
Started by user anonymous
Building in workspace /Users/fbelzunc/cloudbees/plugins/interview-fix-bug-plugin/work/jobs/job-1/workspace
ERROR: Build step failed with exception
java.lang.ClassCastException: com.cloudbees.hudson.plugins.folder.Folder cannot be cast to hudson.model.AbstractProject
	at hudson.plugins.hello_world.HelloWorldBuilder.perform(HelloWorldBuilder.java:66)
	at hudson.tasks.BuildStepMonitor$1.perform(BuildStepMonitor.java:20)
	at hudson.model.AbstractBuild$AbstractBuildExecution.perform(AbstractBuild.java:761)
	at hudson.model.Build$BuildExecution.build(Build.java:203)
	at hudson.model.Build$BuildExecution.doRun(Build.java:160)
	at hudson.model.AbstractBuild$AbstractBuildExecution.run(AbstractBuild.java:536)
	at hudson.model.Run.execute(Run.java:1741)
	at hudson.model.FreeStyleBuild.run(FreeStyleBuild.java:43)
	at hudson.model.ResourceController.execute(ResourceController.java:98)
	at hudson.model.Executor.run(Executor.java:374)
Build step 'Say hello world' marked build as failure
Finished: FAILURE
```


# Objectives

A. Explain why this issue happens and fix the bug. The goal is to list the name of all the items in the instance - including the items inside a folder.
B. Modify the plugin so given a list of plugins separated by comma, i.e `cloudbees-folder, operations-center-client`, the plugin tells you if those plugins exists or not in the instance.

Provide a [PR](https://help.github.com/articles/creating-a-pull-request-from-a-fork/) with *A* fixed and *B* implemented.

# Interesting links

* https://wiki.jenkins.io/display/JENKINS/Plugin+tutorial
* http://updates.jenkins-ci.org/download/plugins/
* http://javadoc.jenkins.io/