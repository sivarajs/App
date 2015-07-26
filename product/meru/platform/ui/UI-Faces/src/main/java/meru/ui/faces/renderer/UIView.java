package meru.ui.faces.renderer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import meru.ui.faces.EntityDataProvider;
import meru.ui.faces.var.ViewVariable;

public abstract class UIView {

    private UIView mParent;
    private Map<String, ViewVariable> mVariableMap;

    public UIView() {
        mVariableMap = new HashMap<String, ViewVariable>(1);
    }

    public void setParent(UIView parent) {
        mParent = parent;
    }

    public UIView getParent() {
        return mParent;
    }

    public Object getVariableValue(String variableName,
                                   String fieldName) {

        ViewVariable variable = mVariableMap.get(variableName);
        if (variable == null) {
            if (mParent == null) {
                return null;
            }
            else {
                return mParent.getVariableValue(variableName,
                                                fieldName);
            }

            // throw new RuntimeException("Unknown variable : "+variableName);
        }
        
        if (fieldName == null) {
            return variable;
        }

        return variable.getFieldValue(fieldName);
    }

    public String getVariableStringValue(String variable) {
        return (String) getVariableValue(variable);
    }

    public Object getVariableValue(String variable) {
        int index = variable.indexOf('.');
        ViewVariable viewVariable = null;
        if (index == -1) {
            viewVariable = mVariableMap.get(variable);
            if (viewVariable == null) {
                if (mParent == null) {
                    return null;
                }
                else {
                    return mParent.getVariableValue(variable);
                }
            }
            return viewVariable.getValue();
        }

        return getVariableValue(variable.substring(0,
                                                   index),
                                variable.substring(index + 1));

    }

    public void setVariable(String name,
                            Object value) {

        ViewVariable variable = mVariableMap.get(name);

        if (variable == null) {
            variable = new ViewVariable(name);
            variable.setValue(value);
        }
        else {
            variable.setValue(value);
        }

        mVariableMap.put(name,
                         variable);
    }

    public void setVariable(String name,
                            ViewVariable variable) {
        mVariableMap.put(name,
                         variable);

    }

    public boolean containsVariable(String name) {
        return mVariableMap.get(name) != null;
    }

    public abstract void build(ViewBuilder viewBuilder,
                               EntityDataProvider dataProvider) throws IOException;
}
