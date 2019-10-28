package backend;

public class DictNamesDTO {
    @MsqlFieldName(name = "id")
    private String id;

    @MsqlFieldName(name = "name")
    private String name;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
