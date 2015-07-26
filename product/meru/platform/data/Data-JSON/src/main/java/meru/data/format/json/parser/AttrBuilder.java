package meru.data.format.json.parser;

import meru.data.format.json.JsonConstants;
import meru.sys.lang.ClassHelper;

public class AttrBuilder {

    private StringBuilder mNameBuilder;
    private StringBuilder mValueBuilder;
    private StringBuilder mCurrBuilder;

    private Object mObject;

    public AttrBuilder(Object object) {

        mNameBuilder = new StringBuilder(10);
        mValueBuilder = new StringBuilder(10);
        mCurrBuilder = mNameBuilder;

        mObject = object;
    }

    public void setObject(Object object) {

        mObject = object;
    }

    public void build(char ch) {

        if (ch == JsonConstants.NAME_VALUE_SEPARATOR && isItemEnding()) {
            mCurrBuilder = mValueBuilder;
        }
        else {

            mCurrBuilder.append(ch);
        }

    }

    private boolean isItemEnding() {

        int length = mCurrBuilder.length();
        if (length > 0) {

            char lastChar = mCurrBuilder.charAt(mCurrBuilder.length() - 1);
            return (lastChar == '\'' || lastChar == '"');
        }

        return false;
    }

    public Class<?> getParameterizedClass() {

        verifyFieldSet();
        return ClassHelper.getParameterizedClass(mObject,
                                                 removeQuotes(mNameBuilder));

    }

    public Object createFieldInstance() {

        verifyFieldSet();

        if (mValueBuilder.toString()
                         .trim()
                         .length() != 0) {
            throw new IllegalStateException("Value [" + mValueBuilder + "] for the attribute '" + mNameBuilder + "' must be null to build an object  ");
        }

        mObject = ClassHelper.newFieldInstance(mObject,
                                               removeQuotes(mNameBuilder));

        reset();
        return mObject;

    }

    public void setFieldValue() {

        if (mNameBuilder.length() == 0) {
            return;
        }

        char lastChar = mValueBuilder.charAt(mValueBuilder.length() - 1);
        if (!(lastChar == '\'' || lastChar == '"')) {
            build(',');
            return;
        }

        String value = removeQuotes(mValueBuilder);

        if (!"^NULL^".equals(value)) {

            ClassHelper.setFieldValue(mObject,
                                      removeQuotes(mNameBuilder),
                                      removeQuotes(mValueBuilder));
        }
        reset();

    }

    private void verifyFieldSet() {

        if (mNameBuilder.toString()
                        .trim()
                        .length() == 0) {
            throw new IllegalArgumentException();
        }
    }

    private static String removeQuotes(StringBuilder strBuilder) {

        String value = strBuilder.toString()
                                 .trim();

        char ch = value.charAt(0);

        if (ch == '"' || ch == '\'') {

            ch = value.charAt(value.length() - 1);
            if (ch == '"' || ch == '\'') {
                return value.substring(1,
                                       value.length() - 1);
            }

            return value.substring(1);
        }

        return value;
    }

    private void reset() {

        mNameBuilder.delete(0,
                            mNameBuilder.length());
        mValueBuilder.delete(0,
                             mValueBuilder.length());
        mCurrBuilder = mNameBuilder;
    }

}
