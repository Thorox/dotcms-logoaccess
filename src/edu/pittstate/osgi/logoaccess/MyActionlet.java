package edu.pittstate.osgi.logoaccess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.dotmarketing.util.UtilMethods;
import com.dotmarketing.util.Mailer;
import com.dotmarketing.portlets.workflows.actionlet.WorkFlowActionlet;
import com.dotmarketing.portlets.workflows.model.WorkflowActionClassParameter;
import com.dotmarketing.portlets.workflows.model.WorkflowActionFailureException;
import com.dotmarketing.portlets.workflows.model.WorkflowActionletParameter;
import com.dotmarketing.portlets.workflows.model.WorkflowProcessor;
import com.dotmarketing.portlets.contentlet.model.Contentlet;
import com.dotmarketing.portlets.contentlet.business.ContentletAPI;
import com.dotmarketing.util.Logger;

public class MyActionlet extends WorkFlowActionlet {

    private static final long serialVersionUID = 1L;

    @Override
    public List<WorkflowActionletParameter> getParameters () {
        return null;
    }

    @Override
    public String getName () {
        return "Logo Access";
    }

    @Override
    public String getHowTo () {
        return "For Use By CMS Administrators Only";
    }

    @Override
    public void executeAction ( WorkflowProcessor processor, Map<String, WorkflowActionClassParameter> params ) throws WorkflowActionFailureException {
        boolean clean = false;
        boolean live  = false;
        Logger.error(this, "Entering logoaction", null);
	try {
		Contentlet old = processor.getContentlet();
		Mailer m = new Mailer();
		m.setFromEmail("cmsadmin@pittstate.edu");
		m.setToEmail(old.getStringProperty("yourEmail"));
		m.setSubject("PSU Logo Access Link");
		String mailmsg = "Click http://www.pittstate.edu/office/marketing/logos.dot?id=" + old.getStringProperty("identifier") + " to access PSU Logos";
		m.setTextBody(mailmsg);
	 	if (!m.sendMessage()) {
			Logger.error(this, "Could not send message", null);
		}
		Logger.error(this, "Exiting logoaction", null);
	} catch (Exception e) {
		throw new WorkflowActionFailureException(e.getMessage());
	}
}
}
 
