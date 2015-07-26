package meru.ui.faces.renderer.html;

import java.io.IOException;
import java.util.List;

import meru.el.EL;
import meru.ui.faces.renderer.expr.ViewExpressionParser;

public abstract class ForEachEntityHtmlView<T> extends HtmlView {

    protected String mEntityName;
    protected String mFilter;

    private String mVariable;

    public ForEachEntityHtmlView(String entityName,
                                 String filter) {
        mEntityName = entityName;

        mFilter = filter;

        mVariable = Character.toLowerCase(entityName.charAt(0)) + entityName.substring(1);
    }

    protected void buildHtmlView() throws IOException {
        super.buildHtml();
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void buildHtml() throws IOException {

        String filter = mFilter;
        if (filter != null) {
            
            if (EL.containsBindVariable(filter)) {
           
                // try {
                filter = ViewExpressionParser.parseText(filter,
                                                        this,
                                                        true);
                // } catch (AppException e) {
                // e.printStackTrace();
                // }
            }

        }
        
        preFetchEntities();
        

        List<Object> resources = mEntityDataProvider.getEntities(mEntityName,
                                                                 filter);

        preWriteEntities();
        if (resources == null || resources.isEmpty()) {
            writeNoEntity();
        }
        else {
            for (Object resource : resources) {
                setVariable(mVariable,
                            resource);
                writeEntity((T) resource);
            }
        }

        postWriteEntities();
    }

    protected abstract void writeNoEntity() throws IOException;

    protected abstract void writeEntity(T entity) throws IOException;

    protected void preFetchEntities() throws IOException {

    }

    protected void preWriteEntities() throws IOException {

    }

    protected void postWriteEntities() throws IOException {

    }
}
