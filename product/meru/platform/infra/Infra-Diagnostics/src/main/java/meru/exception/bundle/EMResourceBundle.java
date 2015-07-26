package meru.exception.bundle;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.ListResourceBundle;
import java.util.Map;

import meru.exception.code.EntityErrorCode;
import meru.exception.code.SecurityErrorCode;

public class EMResourceBundle extends ListResourceBundle {

    private Map<String, String> messageMap = new HashMap<String, String>();
    
    private Object[][] messages = new Object[][] {
    
    };

    
    public EMResourceBundle() {
        
        messageMap.put(SecurityErrorCode.PASSWORDS_DO_NOT_MATCH, "Passwords do no match");
        messageMap.put(SecurityErrorCode.INVALID_USER_PASSWORD, "Invalid UserName or Password");
        messageMap.put(SecurityErrorCode.USER_ALREADY_EXISTS, "The user ''{0}'' already exists");
        
        
        messageMap.put(EntityErrorCode.ENTITY_NO_ATTRIBUTE, "The attribute ''{0}'' is not found in the entity ''{1}''");
        
    }
    
	protected Object[][] getContents() {
		return messages;
	}

	
	private final String getPattern(String code) {
	    String value = messageMap.get(code);
	    return (value == null) ? code : value;
	}
	
	public String getMessage(String code) {
	    
	    String message = messageMap.get(code);
	    if (message == null) {
	        return code;
	    }
	    
        return message;
    }
	
	public String getMessage(String code, Object...args) {
	    
	    return MessageFormat.format(getPattern(code), args);
	    
	}
}
