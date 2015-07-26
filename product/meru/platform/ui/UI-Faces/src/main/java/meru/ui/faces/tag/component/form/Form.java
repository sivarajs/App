package meru.ui.faces.tag.component.form;

import meru.ui.faces.tag.Identifiable;
import meru.ui.faces.tag.component.UIComponent;
import meru.ui.faces.tag.component.input.InputFile;
import meru.xml.XMLNodeHelper;

import org.w3c.dom.NodeList;

public class Form extends UIComponent implements Identifiable {
    public static final String NAME = "form";

    private String encoding;

    public Form() {
        super(NAME);
    }

    public Form(String componentName) {
        super(componentName);
    }

    public String getName() {
        return getAttribute("name");
    }

    public String getAction() {
        return getAttribute("action");
    }

    public String getMethod() {
        return getAttribute("method");
    }

    public String getTarget() {
        return getAttribute("target");
    }

    public String getEncoding() {
        return encoding;
    }

    public String getOnsubmit() {
        return getAttribute("onsubmit");
    }

    public String getSuccessStatus() {
        return getAttribute("successStatus");
    }
    
    public String getSuccessMsg() {
        return getAttribute("successMsg");
    }

    public String getSubmitButtonLabel() {
        String label = getAttribute("submitButtonLabel");
        if (label == null) {
            return "Save";
        }

        return label;
    }

    public String getSubmitButtonStyle() {
        return getAttribute("submitButtonStyle");
    }

    public boolean isSubmittable() {
        return XMLNodeHelper.isAbsentOrTrue(uiElement,
                                            "submittable");
    }

    @Override
    protected boolean load() {

        NodeList nodeList = uiElement.getElementsByTagNameNS(UIComponent.NAMESPACE,
                                                             "inputFile");
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (InputFile.NAME.equals(nodeList.item(i)
                                              .getLocalName())) {
                encoding = "multipart/form-data";
            }
        }

        return true;
    }
}
