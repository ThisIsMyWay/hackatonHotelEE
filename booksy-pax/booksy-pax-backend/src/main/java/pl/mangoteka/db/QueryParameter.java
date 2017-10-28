package pl.mangoteka.db;

public class QueryParameter {

    private String name;
    private Object value;

    private QueryParameter(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "[ " + name + " -> " + value + " ]";
    }


}
