package meru.ui.faces.renderer.expr;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import meru.el.EL;
import meru.sys.lang.ClassHelper;
import meru.ui.faces.renderer.UIView;

public class ViewExpressionParser implements EL.ExpressionParser {

    private StringBuilder mStrBuilder;
    private UIView mUIView;
    private boolean mEscape;

    public ViewExpressionParser(StringBuilder strBuilder,
                                UIView uiView) {
        mStrBuilder = strBuilder;
        mUIView = uiView;
    }

    public ViewExpressionParser(StringBuilder strBuilder,
                                UIView uiView,
                                boolean escape) {
        mStrBuilder = strBuilder;
        mUIView = uiView;
        mEscape = escape;
    }

    public static String parseText(String text,
                                   UIView uiView) {
        return parseText(text,
                         uiView,
                         false);
    }

    public static String parseText(String text,
                                   UIView uiView,
                                   boolean escape) {

        StringBuilder strBuilder = new StringBuilder(text.length());
        ViewExpressionParser parser = new ViewExpressionParser(strBuilder,
                                                               uiView,
                                                               escape);
        EL.parseText(text,
                     parser);
        return strBuilder.toString();
    }

    @Override
    public void readVariable(String variable) {

        Object value = mUIView.getVariableValue(variable);
        if (value == null) {
//            throw new AppException("Unknown variable : "+variable);
//            if (variable.startsWith("session.user.")) {
//                //throw new LoginRequiredException();
//
//            }
//            else {
                value = "";
//            }
        }

        String val = ClassHelper.toString(value);
        if (mEscape) {
            try {
                mStrBuilder.append(URLEncoder.encode(val,
                                                     "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                // Ignore will never happen
            }
        }
        else {
            mStrBuilder.append(val);
        }

    }

    @Override
    public void readText(String text) {
        mStrBuilder.append(text);
    }
}
