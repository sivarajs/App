package meru.ui.faces.tag.component.table;

import java.util.ArrayList;
import java.util.List;

import meru.ui.faces.tag.Identifiable;
import meru.ui.faces.tag.UITag;
import meru.ui.faces.tag.component.UIComponent;
import meru.xml.XMLNodeHelper;

public class DataTable extends UIComponent implements Identifiable {

    public static final String NAME = "dataTable";

    private List<Column> columns;

    public DataTable() {
        super(NAME);
    }

    public DataTable(String name) {
        super(name);
        setCSSClass(NAME);
    }

    public String getTitle() {
        return getAttribute("title");
    }

    public String getLifeCycle() {
        return getAttribute("lifeCycle");
    }

    public String getContentStyleClass() {
        return getAttribute("contentStyleClass");
    }

    public String getContentStyle() {
        return getAttribute("contentStyle");
    }

    public boolean isRemoveable() {
        return XMLNodeHelper.isTrue(uiElement,
                                    "removeable");
    }

    public boolean isNumberable() {
        return XMLNodeHelper.isTrue(uiElement,
                                    "numberable");
    }

    public String getRowSelection() {
        return getAttribute("rowSelection");
    }
    
    public boolean isMultipleRowSelection() {
        String rowSelection = getAttribute("rowSelection");
        return "multiple".equalsIgnoreCase(rowSelection);
    }
    
    public List<Column> getColumns() {

        if (columns == null) {

            columns = new ArrayList<Column>(2);

            for (UITag uiComponent : childComponents) {
                if (uiComponent instanceof Columns) {
                    for (UITag colComp : ((Columns) uiComponent).getChildTags())
                        if (colComp instanceof Column) {
                            columns.add((Column) colComp);
                        }
                }
            }
        }
        return columns;
    }
}
