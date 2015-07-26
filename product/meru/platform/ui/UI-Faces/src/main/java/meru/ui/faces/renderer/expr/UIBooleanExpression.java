package meru.ui.faces.renderer.expr;

import meru.el.EL;
import meru.ui.faces.renderer.UIView;
import meru.ui.faces.renderer.expr.operator.ComparisonOperator;

public class UIBooleanExpression {

    private UIExpression mLeftExpression;
    private UIExpression mRightExpression;
    private ComparisonOperator mOperator;

    public UIBooleanExpression(String expression) {

        for (ComparisonOperator operator : ComparisonOperator.values()) {

            if (expression.indexOf(operator.getValue()) != -1) {

                String[] expressions = expression.split(operator.getValue());

                if (expressions.length == 2) {
                    mOperator = operator;
                    
                    String expr = expressions[0].trim();
                    
                    if (EL.isBindVariable(expr)) {
                    
                      mLeftExpression = new UIVariableExpression(EL.getBindVariable(expr));
                    }
                    else {
                        mLeftExpression = new UIConstantExpression(expr);
                    }

                    expr = expressions[1].trim();
                    if (EL.isBindVariable(expr)) {
                        mRightExpression = new UIVariableExpression(expr);
                    }
                    else {
                        mRightExpression = new UIConstantExpression(expr);
                    }
                }
            }
        }

        if (mOperator == null) {
            throw new IllegalArgumentException("Invalid expression : "
                            + expression);
        }
    }

    public boolean getValue(UIView view) {

        Object leftValue = mLeftExpression.getValue(view);
        Object rightValue = mRightExpression.getValue(view);

        return mOperator.isTrue(leftValue,
                                rightValue);
    }
}
