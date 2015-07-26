package app.entity;

public interface Hierarchical extends Nameable {

    String getQualifiedName();

    Object getParentId();

    String getType();

    String getKind();

    String getAction();
}