package gr.aueb.cf.schoolapp.dto;

public class CityUpdateDTO extends Base{
    private String name;

    public CityUpdateDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
