package meru.ui.faces.renderer.expr;

import meru.ui.faces.renderer.UIView;
import meru.ui.faces.renderer.expr.operator.ConditionalOperator;

public class UIConditionExpression {

    private ConditionExpressionUnit condExpressionUnit;

    public UIConditionExpression(String expression) {

        StringBuilder strBuilder = new StringBuilder();
        char prevChar = 0;
        ConditionalOperator conditionalOperator = null;
        ConditionExpressionUnit currExprUnit = null;

        for (char ch : expression.toCharArray()) {

            switch (ch) {

                case '&':
                    if (prevChar == '&') {
                        conditionalOperator = ConditionalOperator.And;
                    }
                    break;
                case '|':
                    if (prevChar == '|') {
                        conditionalOperator = ConditionalOperator.Or;
                    }
                    break;
                default:
                    if (prevChar == '&' || prevChar == '|') {
                        strBuilder.append(prevChar);
                    }
                    strBuilder.append(ch);
            }

            if (conditionalOperator != null) {

                ConditionExpressionUnit exprUnit = createExpressionUnit(strBuilder,
                                                                        conditionalOperator,
                                                                        currExprUnit);

                currExprUnit = exprUnit;

                strBuilder.delete(0,
                                  strBuilder.length() - 1);
                prevChar = 0;
                conditionalOperator = null;
            }
            else {
                prevChar = ch;
            }
        }

        if (strBuilder.length() != 0) {
            createExpressionUnit(strBuilder,
                                 conditionalOperator,
                                 currExprUnit);

        }

    }

    private ConditionExpressionUnit createExpressionUnit(StringBuilder strBuilder,
                                                         ConditionalOperator conditionalOperator,
                                                         ConditionExpressionUnit currExprUnit) {
        ConditionExpressionUnit exprUnit = new ConditionExpressionUnit(new UIBooleanExpression(strBuilder.toString()
                                                                                                         .trim()),
                                                                       conditionalOperator);

        if (currExprUnit != null) {
            currExprUnit.next = exprUnit;
        }
        else {
            condExpressionUnit = exprUnit;
        }

        return exprUnit;
    }

    public boolean getValue(UIView view) {

        ConditionExpressionUnit exprUnit = condExpressionUnit;
        while (exprUnit != null) {

            if (exprUnit.booleanExpression.getValue(view)) {
                if (exprUnit.next == null || exprUnit.conditionOperator == ConditionalOperator.Or) {
                    return true;
                }
            }
            else {
                if (exprUnit.conditionOperator == ConditionalOperator.And) {
                    return false;
                }
            }

            exprUnit = exprUnit.next;
        }

        return false;
    }

    class ConditionExpressionUnit {
        UIBooleanExpression booleanExpression;
        ConditionalOperator conditionOperator;
        ConditionExpressionUnit next;

        public ConditionExpressionUnit(UIBooleanExpression booleanExpression,
                                       ConditionalOperator conditionOperator) {
            this.booleanExpression = booleanExpression;
            this.conditionOperator = conditionOperator;

        }
    }
}
