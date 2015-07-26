package meru.ui.faces.renderer.expr;

import meru.ui.faces.renderer.UIView;

public class UIVariableExpression extends UIExpression {

    protected String mVariable;
    protected String mFieldExpression;

    public UIVariableExpression(String fieldExpression) {

        fieldExpression = fieldExpression.trim();
        int index = fieldExpression.indexOf(".");
        if (index == -1) {

            mVariable = fieldExpression;
            // throw new RuntimeException("Invalid Expression : " +
            // fieldExpression);
        }
        else {
            mVariable = fieldExpression.substring(0,
                                                  index);
            mFieldExpression = fieldExpression.substring(index + 1);
        }
    }

    public Object getValue(UIView view) {

        return view.getVariableValue(mVariable,
                                     mFieldExpression);
    }

}
