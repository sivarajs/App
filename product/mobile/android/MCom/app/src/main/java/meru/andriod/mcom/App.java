package meru.andriod.mcom;

import org.json.simple.JSONObject;

public class App {

    public static final String SELECTED_PRODUCT_CATEGORY = "selectedProductCategory";

    public static final String getProductNameWithQuantity(JSONObject productLineItem) {

        JSONObject product = (JSONObject) productLineItem.get("product");
        JSONObject unitOfMessure = (JSONObject) productLineItem.get("unitOfMeasure");
        return product.get("name") + " " + productLineItem.get("quantity") + " "
                + unitOfMessure.get("value");
    }

    
    
}
