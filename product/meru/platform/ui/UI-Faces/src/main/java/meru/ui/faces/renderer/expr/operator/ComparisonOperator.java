package meru.ui.faces.renderer.expr.operator;

public enum ComparisonOperator {
    Equals("==") {
        @Override
        public boolean isTrue(Object leftValue,
                              Object rightValue) {
            if (leftValue != null) {
                return leftValue.toString().equals(rightValue);
            }
            return leftValue == rightValue;
        }
    },
    NotEquals("!=") {
        @Override
        public boolean isTrue(Object leftValue,
                              Object rightValue) {
            if (leftValue != null) {
                return !leftValue.toString().equals(rightValue);
            }
            
            return leftValue != rightValue;
        }
    },
    LesserThan("<") {
        @Override
        public boolean isTrue(Object leftValue,
                              Object rightValue) {
            
            if (leftValue != null && rightValue != null) {
                return Double.parseDouble(leftValue.toString()) < Double.parseDouble(rightValue.toString());
            }
            
            return false;
        }
    },
    LesserThanOrEquals("<=") {
        @Override
        public boolean isTrue(Object leftValue,
                              Object rightValue) {
            if (leftValue != null && rightValue != null) {
                return Double.parseDouble(leftValue.toString()) <= Double.parseDouble(rightValue.toString());
            }
            
            return false;
        }
    },
    GreaterThan(">") {
        @Override
        public boolean isTrue(Object leftValue,
                              Object rightValue) {
            if (leftValue != null && rightValue != null) {
                return Double.parseDouble(leftValue.toString()) > Double.parseDouble(rightValue.toString());
            }
            
            return false;

        }
    },
    GreaterThanOrEquals("<=") {
        @Override
        public boolean isTrue(Object leftValue,
                              Object rightValue) {
            if (leftValue != null && rightValue != null) {
                return Double.parseDouble(leftValue.toString()) <= Double.parseDouble(rightValue.toString());
            }
            
            return false;

        }
    };

    private String value;

    private ComparisonOperator(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public abstract boolean isTrue(Object leftValue,
                                   Object rightValue);
}