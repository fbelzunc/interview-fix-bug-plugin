package hudson.plugins.hello_world;

import hudson.Launcher;
import hudson.Extension;
import hudson.model.AbstractProject;
import hudson.model.BuildListener;
import hudson.model.AbstractBuild;
import hudson.model.Descriptor;
import hudson.model.Item;
import hudson.tasks.Builder;
import hudson.tasks.BuildStepDescriptor;
import jenkins.model.Jenkins;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.DataBoundConstructor;

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
        for (Item item : Jenkins.getInstance().getAllItems()) {
            AbstractProject abstractProject = (AbstractProject) item;
            listener.getLogger().println(abstractProject.getFullName());
        }
        // Exercise 2 - given pluginNames separate by comma, provide as an output in the build
        // if the introduced plugins are installed or not in the instance: Jenkins.getInstance()....¡

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
