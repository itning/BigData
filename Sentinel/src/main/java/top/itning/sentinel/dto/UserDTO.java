package top.itning.sentinel.dto;

/**
 * @author itning
 * @date 2020/8/22 15:22
 */
public class UserDTO {
    private String name;

    public UserDTO() {
    }

    public UserDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
