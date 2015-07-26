package meru.ui.faces.renderer.html.component.input.select;

import java.io.IOException;

import meru.el.EL;
import meru.sys.lang.ClassHelper;
import meru.ui.faces.renderer.html.ForEachEntityHtmlView;
import meru.ui.faces.tag.component.input.select.SelectOneEntityMenu;

public class SelectOneEntityMenuHtmlView extends ForEachEntityHtmlView<Object> {

    private SelectOneEntityMenu mSelectOneMenu;
    private long mSelectedId;
    private String mAttrValue;

    public SelectOneEntityMenuHtmlView(SelectOneEntityMenu entityMenu) {
        super(entityMenu.getEntity(),
              entityMenu.getFilter());

        mSelectOneMenu = entityMenu;
    }

    @Override
    protected void preWriteEntities() throws IOException {

        String value = mSelectOneMenu.getValue();
        if (value != null && EL.isBindVariable(value)) {
            value = EL.getBindVariable(value);
            String val = value.substring(0,
                                         value.lastIndexOf('.') + 1) + "id";
            Object id = getVariableValue(val);
            if (id != null) {
                mSelectedId = (Long) id;
            }
        }

        if (mSelectedId == 0) {
            String def = mSelectOneMenu.getDefault();
            if (def != null) {
                mSelectedId = Long.parseLong(def);
            }
        }

        int index = value.lastIndexOf('.') + 1;
        mAttrValue = value.substring(index);

        // writeOption(0,
        // "-- Select --");
        writeOption(0,
                    "");
    }

    @Override
    protected void writeNoEntity() throws IOException {

    }

    @Override
    protected void writeEntity(Object entity) throws IOException {

        long id = (Long) ClassHelper.getFieldValue(entity,
                                                   "id");
        Object dispVal = ClassHelper.getFieldValue(entity,
                                                   mAttrValue);

        writeOption(id,
                    dispVal.toString());
    }

    private void writeOption(long id,
                             String value) {

        mHtmlBuilder.startElement("option");

        if (id != 0L) {
            mHtmlBuilder.addAttribute("value",
                                      id);

        }

        if (id == mSelectedId) {
            mHtmlBuilder.addAttribute("selected",
                                      "selected");
        }

        mHtmlBuilder.addText(value)
                    .endElement();

    }
}
