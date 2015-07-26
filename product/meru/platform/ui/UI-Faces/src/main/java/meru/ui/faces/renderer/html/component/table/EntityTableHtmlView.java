package meru.ui.faces.renderer.html.component.table;

import java.io.IOException;

import meru.el.EL;
import meru.sys.lang.ClassHelper;
import meru.ui.faces.renderer.expr.ObjectFieldExpressionParser;
import meru.ui.faces.renderer.expr.ViewExpressionParser;
import meru.ui.faces.renderer.html.ForEachEntityHtmlView;
import meru.ui.faces.tag.component.table.Column;
import meru.ui.faces.tag.component.table.EntityTable;
import meru.xml.XMLNodeHelper;

public class EntityTableHtmlView extends ForEachEntityHtmlView<Object> {

    private static final String NO_RESOURCES_MSG = "No items found";

    private EntityTable mEntityTable;
    private boolean mIsEven;
    private int mIndex;

    public EntityTableHtmlView(EntityTable entityTable) {
        super(entityTable.getEntity(),
              entityTable.getFilter());

        mEntityTable = entityTable;
    }

    @Override
    protected void writeNoEntity() throws IOException {
        mHtmlBuilder.addHtmlText("<tr height='100' valign='middle' class='noItems'><td align='center'><div style='line-height:100px;text-align:center'> -- "
                + NO_RESOURCES_MSG + " -- </div></td></tr></tbody></table>");
    }

    @Override
    protected void writeEntity(Object resource) throws IOException {

        mHtmlBuilder.startElement("tr")
                    .addAttribute("valign",
                                  "middle");

        if (mIsEven) {
            mHtmlBuilder.addAttribute("class",
                                      "even");
        }

        Object pid = ClassHelper.getFieldValue(resource,
                                               "id");
        mHtmlBuilder.addAttribute("eid",
                                  pid);

        if (mEntityTable.isMultipleRowSelection()) {
            mHtmlBuilder.startElement("td")
                        .addAttribute("width",
                                      "20")
                        .addAttribute("class",
                                      "rowSelection")
                        .startElement("input")
                        .addAttribute("class",
                                      "rsCheck")
                        .addAttribute("type",
                                      "checkbox")
                        .endElement();
        }

        for (Column column : mEntityTable.getColumns()) {

            mHtmlBuilder.startElement("td")
                        .addAttribute("style",
                                      column.getCssStyle());

            if (column.isNumberable()) {
                mHtmlBuilder.addAttribute("width",
                                          "20")
                            .startElement("div")
                            .addAttribute("class",
                                          "numberable")
                            .addText(String.valueOf(++mIndex));
            }

            else if (column.isRemoveable()) {
                mHtmlBuilder.addAttribute("width",
                                          "20")
                            .startElement("div")
                            .addAttribute("class",
                                          "removeable");
            }
            else {

                String contentStyle = column.getContentStyle();

                contentStyle += ";width:" + column.getWidth()
                        + "px;text-align:" + column.getAlign();

                mHtmlBuilder.addAttribute("width",
                                          column.getWidth())
                            .startElement("div")
                            .addAttribute("style",
                                          contentStyle)
                            .addClassAttribute(column.getContentClass());

                String value = column.getValue();
                if (value != null) {
                    value = ViewExpressionParser.parseText(value,
                                                           this);

                    String href = column.getHref();
                    if (href != null) {

                        mHtmlBuilder.startElement("a")
                                    .addText(" href=\"",
                                             false);

                        String appRoot = getVariableStringValue("application.root");
                        if (appRoot != null) {
                            href = appRoot + "/" + href;
                        }

                        if (EL.containsBindVariable(href)) {

                            EL.parseText(href,
                                         new ObjectFieldExpressionParser(mHtmlBuilder,
                                                                         resource));
                        }

                        mHtmlBuilder.addText("\"",
                                             false)
                                    .addText(value)
                                    .endElement();
                    }
                    else {

                        String type = column.getType();
                        if (type != null) {

                            switch (type) {
                            case "img":
                                mHtmlBuilder.startElement("img")
                                            .addAttribute("src",
                                                          value)
                                            .addStyleAttribute(column.getContentStyle());
                                break;
                            }

                        }
                        else {

                            if (column.isEditable()) {
                                String width = column.getWidth();
                                if (width == null) {
                                    width = "20";
                                }

                                String onchange = column.getOnInputChange();

                                if (onchange != null) {
                                    onchange += "(this);";
                                }

                                mHtmlBuilder.startElement("input")
                                            .addClassAttribute("editable")
                                            .addAttribute("eid",
                                                          pid)
                                            .addAttribute("onchange",
                                                          onchange)
                                            .addAttribute("value",
                                                          value);

                                String editableEntityId = XMLNodeHelper.getAttribute(column.getUIElement(),
                                                                                     "editableValue");

                                if (editableEntityId != null) {
                                    editableEntityId = ViewExpressionParser.parseText(editableEntityId,
                                                                                      this);
                                    mHtmlBuilder.addAttribute("editableValue",
                                                              editableEntityId);
                                }

                            }
                            else {
                                mHtmlBuilder.addText(value);
                            }
                        }
                    }
                }

            }
            mHtmlBuilder.endElement();
        }

        mHtmlBuilder.endElement();
        mIsEven = !mIsEven;

    }
}
