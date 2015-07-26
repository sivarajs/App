package meru.persistence;

import java.util.List;

public enum AttributeOperator {
    EQUALS("=",
           "Equals") {
        @Override
        public Object getValue(Object value) {
            return value;
        }

    },
    NOT_EQUALS("<>",
               "Not Equals") {
        @Override
        public Object getValue(Object value) {
            return value;
        }
    },
    IS("is",
       "Is") {
        @Override
        public Object getValue(Object value) {
            return value;
        }
    },
    LIKE("like",
         "Like") {
        @Override
        public Object getValue(Object value) {
            return "%" + value + "%";
        }
    },
    IN("in",
       "In") {
        @SuppressWarnings("rawtypes")
        @Override
        public Object getValue(Object value) {

            StringBuilder strBuilder = new StringBuilder(5);
            strBuilder.append("(");

            if (value instanceof List) {

                List values = (List) value;
                if (!values.isEmpty()) {

                    boolean isString = values.get(0) instanceof String;
                    boolean isFirst = true;
                    for (Object val : values) {

                        if (!isFirst) {
                            strBuilder.append(",");
                        }
                        if (isString) {
                            strBuilder.append("'");
                        }
                        strBuilder.append(val);

                        if (isString) {
                            strBuilder.append("'");
                        }
                        isFirst = false;
                    }
                }
            }
            else {
                strBuilder.append(value);
            }
            strBuilder.append(")");
            return strBuilder.toString();
        }
    },
    GREATER_THAN(">",
                 "Greater Than") {
        @Override
        public Object getValue(Object value) {
            return value;
        }
    },
    LESSER_THAN("<",
                "Lesser Than") {
        @Override
        public Object getValue(Object value) {
            return value;
        }
    },
    GREATER_THAN_OR_EQUALS(">=",
                           "Greater Than Or Equals") {
        @Override
        public Object getValue(Object value) {
            return value;
        }
    },
    LESSER_THAN_OR_EQUALS("<=",
                          "Lesser Than Or Equals") {
        @Override
        public Object getValue(Object value) {
            return value;
        }
    };

    private String mOperator;
    private String mLabel;
    private String mOperatorForQuery;

    private AttributeOperator(String operator,
                              String label) {

        mOperator = operator;
        mLabel = label;
        mOperatorForQuery = " " + operator + " ";
    }

    public final String getOperator() {

        return mOperator;
    }

    public final String getOperatorForQuery() {
        return mOperatorForQuery;
    }

    public final String getLabel() {

        return mLabel;
    }

    public abstract Object getValue(Object value);

    public static AttributeOperator getOperator(String operator) {

        for (AttributeOperator op : values()) {
            if (op.mOperator.equals(operator)) {
                return op;
            }
        }

        throw new RuntimeException("Unknown Operator : " + operator);
    }

    public static AttributeOperator getOperatorByLabel(String label) {

        for (AttributeOperator operator : values()) {
            if (operator.mLabel.equals(label)) {
                return operator;
            }
        }

        throw new RuntimeException("Unknown Operator : " + label);
    }

    public String toString() {

        return mLabel;
    }
}