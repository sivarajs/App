package meru.ui.faces.renderer.expr.operator;

public enum ConditionalOperator {
    And(" and ") {
        @Override
        public boolean isTrue(boolean leftValue,
                              boolean rightValue) {
            return leftValue && rightValue;
        }
    },
    Or(" or ") {
        @Override
        public boolean isTrue(boolean leftValue,
                              boolean rightValue) {
            return leftValue || rightValue;
        }
    };

    private String value;

    private ConditionalOperator(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public abstract boolean isTrue(boolean leftValue,
                                   boolean rightValue);
}