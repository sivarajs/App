package meru.data.format.json.parser;

import java.util.List;
import java.util.Stack;

public class ObjectBuilder {

  private Stack<Object> mObjectStack;
  private AttrBuilder mAttrBuilder;
  private Object mObject;


  public ObjectBuilder(Object object,
                       AttrBuilder attrBuilder) {

    mObject = object;
    mObjectStack = new Stack<Object>();
    mAttrBuilder = attrBuilder;

  }


  public void startBuild() {

    Object prev = null;

    if (mObjectStack.isEmpty()) {

      mObjectStack.push(mObject);
    }
    else {
      prev = mObjectStack.peek();
      Object object = null;
      if (prev instanceof JsonArray) {
        JsonArray array = (JsonArray) prev;
        object = array.newInstance();
        mAttrBuilder.setObject(object);
      }
      else {
        object = mAttrBuilder.createFieldInstance();
        
      }
      
      mObjectStack.push(object);
    }

  }


  public void endBuild() {

    mAttrBuilder.setFieldValue();
    
    mObjectStack.pop();
    
    if (mObjectStack.size() > 0) {
      Object object = mObjectStack.peek();
    
      if (!(object instanceof List)) {
        mAttrBuilder.setObject(object);
      }
    }
  }


  @SuppressWarnings("unchecked")
  public void startList() {

    Class<?> claz = mAttrBuilder.getParameterizedClass();
    List<Object> list = (List<Object>) mAttrBuilder.createFieldInstance();

    JsonArray array = new JsonArray(claz, list);
    mObjectStack.push(array);
  }


  public void endList() {

    mAttrBuilder.setFieldValue();
    
   mObjectStack.pop();
    
    mAttrBuilder.setObject(mObjectStack.peek());

  }


  static class JsonArray {

    Class<?> mClass;
    List<Object> mItemList;


    public JsonArray(Class<?> claz, List<Object> itemList) {

      mClass = claz;
      mItemList = itemList;

    }


    Object newInstance() {

      try {


        Object object = mClass.newInstance();
        mItemList.add(object);

        return object;
        
        
      } catch (RuntimeException e) {
        throw e;
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
  }
}
