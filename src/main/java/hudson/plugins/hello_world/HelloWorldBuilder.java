package hudson.plugins.hello_world;

import hudson.Launcher;
import hudson.Extension;
import hudson.Plugin;
import hudson.PluginWrapper;
import hudson.model.*;
import hudson.tasks.Builder;
import hudson.tasks.BuildStepDescriptor;
import jenkins.model.AbstractTopLevelItem;
import jenkins.model.Jenkins;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.DataBoundConstructor;
import com.cloudbees.hudson.plugins.folder.AbstractFolder;
import net.sf.json.JSONObject;


public class HelloWorldBuilder extends Builder {

    private final String pluginNames;

    @DataBoundConstructor
    public HelloWorldBuilder(String pluginNames) {
        this.pluginNames = pluginNames;
    }

    public String getPluginNames() {
        return pluginNames;
    }
    @Override
    public boolean perform(AbstractBuild build, Launcher launcher, BuildListener listener) {
        // Exercise 1 - List the fullName of all the elements in the instance
        // Explain as well why this Runtime exception happens
		/*
            The problem is that not all the items are not instances of AbstractProject, so we have to test all 
            the classes that implement the interface Item, checking for each if it's instanceof before the casting have done.
		*/
        listener.getLogger().println("---------------------------");
        listener.getLogger().println("Elements of the system:");
        listener.getLogger().println("---------------------------");
		for (Item item : Jenkins.getInstance().getAllItems()) { //(AbstractProject.class)
			if(item instanceof AbstractProject){
				AbstractProject abstractProject = (AbstractProject) item; 
				listener.getLogger().println(abstractProject.getFullName());
            }else if(item instanceof AbstractItem){
                AbstractItem abstractItem = (AbstractItem) item;
                listener.getLogger().println(abstractItem.getFullName());
            }else if(item instanceof AbstractTopLevelItem){
                AbstractTopLevelItem abstractTopLevelItem = (AbstractTopLevelItem) item;
                listener.getLogger().println(abstractTopLevelItem.getFullName());
            }else if(item instanceof FreeStyleProject){
                FreeStyleProject freeStyleProject = (FreeStyleProject) item;
                listener.getLogger().println(freeStyleProject.getFullName());
            }else if(item instanceof Job){
                Job job = (Job) item;
                listener.getLogger().println(job .getFullName());
            }else if(item instanceof ViewJob){
                ViewJob viewJob = (ViewJob) item;
                listener.getLogger().println(viewJob.getFullName());
            }else if(item instanceof Project){
                Project project = (Project) item;
                listener.getLogger().println(project.getFullName());
            }

        }


        listener.getLogger().println("---------------------------");
        listener.getLogger().println("Plugins that are active or not");
        listener.getLogger().println("Search string: "+this.pluginNames );
        listener.getLogger().println("---------------------------");
        // Exercise 2 - given pluginNames separate by comma, provide as an output in the build
        // if the introduced plugins are installed or not in the instance: Jenkins.getInstance()....¡
        String[] plugins=this.pluginNames.split(",");
		for(String plugin:plugins)
        {
            PluginWrapper pg=Jenkins.getInstance().getPluginManager().getPlugin(plugin.trim());
            if(null!=pg && pg.isActive()){
                listener.getLogger().println("The plugin "+plugin.trim()+" is loaded. Long name: "+pg.getLongName());
            }else{
                listener.getLogger().println("The plugin "+plugin.trim()+" is not loaded");
            }
        }
        return true;
    }

    @Override
    public Descriptor<Builder> getDescriptor() {
        return (DescriptorImpl)super.getDescriptor();
    }

    @Extension
    public static final class DescriptorImpl extends BuildStepDescriptor<Builder> {

        public DescriptorImpl() {
            load();
        }

        @Override
        public String getDisplayName() {
            return "Tell me if the following plugins are installed or not in the instance";
        }

        @Override
        public boolean isApplicable(Class type) {
            return true;
        }

        @Override
        public boolean configure(StaplerRequest staplerRequest, JSONObject json) throws FormException {
            save();
            return true;
        }

    }
}
