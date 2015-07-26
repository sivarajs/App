package meru.persistence;

import java.lang.reflect.Field;

import javax.persistence.Column;
import javax.persistence.JoinColumn;

import meru.sys.lang.ClassHelper;

public class PersistenceUtil {

    public static boolean isFieldRequired(Class<?> claz, String fieldName) {

        // Field field = null;

        // if (fieldName.indexOf(".") != -1) {
        // String childObjFieldName = fieldName.substring(0,
        // fieldName.indexOf("."));
        // field = getField(claz,
        // childObjFieldName);
        //
        // OneToOne oneToOne = field.getAnnotation(OneToOne.class);
        // if (oneToOne != null) {
        // CascadeType[] cascades = oneToOne.cascade();
        // if (cascades == null || cascades.length == 0) {
        //
        // JoinColumn column = field.getAnnotation(JoinColumn.class);
        // return (column != null) ? column.nullable() : false;
        //
        // }
        // }
        // }

        Field field = ClassHelper.getField(claz,
                                           fieldName);

        Column column = field.getAnnotation(Column.class);

        if (column != null) {
            return !column.nullable();
        }

        JoinColumn joinColumn = field.getAnnotation(JoinColumn.class);

        if (joinColumn != null) {
            return !joinColumn.nullable();
        }

        return true;
    }

}
