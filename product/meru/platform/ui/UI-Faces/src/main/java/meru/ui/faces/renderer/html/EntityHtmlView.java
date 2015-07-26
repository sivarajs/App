package meru.ui.faces.renderer.html;

import java.io.IOException;

import meru.el.EL;
import meru.persistence.EntityQuery;

public class EntityHtmlView extends HtmlView {

    private String mEntityName;
    private String mEntityId;

    private String mVariable;

    protected HtmlBuilder htmlBuilder;

    public EntityHtmlView(String entityName,
                          String entityId) {
        mEntityName = entityName;
        mEntityId = entityId;

        mVariable = Character.toLowerCase(entityName.charAt(0))
                + entityName.substring(1);

        htmlBuilder = new HtmlBuilder();
    }

    @Override
    protected void buildHtml() throws IOException {

        Object entityId = null;

        if (EL.isBindVariable(mEntityId)) {
            entityId = getVariableValue(EL.getBindVariable(mEntityId));
            
            if (entityId instanceof String) {
                try {
                    entityId = Long.parseLong((String) entityId);
                } catch (NumberFormatException e) {
                    if (entityId.equals(EntityQuery.AttributeValue.NULL.getValue())) {
                        return;
                    }   
                }
            }

        }
        else {
            entityId = Long.parseLong(mEntityId);
        }
        
        
        if (entityId == null) {
            return;
        }
        
        Object entity = mEntityDataProvider.getEntity(mEntityName,
                                                      entityId);

        setVariable(mVariable,
                    entity);

        // TODO workaround is for entityForm
        if (getParent() != null) {
            getParent().setVariable(mVariable,
                                    entity);
        }
    }
}
