package top.itning.elasticsearch.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author itning
 * @date 2019/6/5 14:01
 */
@Document(indexName = "user", type = "user")
public class User {
    /**
     * 编号
     */
    @Id
    private String id;
    /**
     * 姓名
     */
    @Field(type = FieldType.Text, fielddata = true)
    private String name;
    /**
     * 电话号
     */
    @Field(type = FieldType.Integer)
    private int tel;
    /**
     * 地址
     */
    @Field(type = FieldType.Text, fielddata = true)
    private String address;

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

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", tel=" + tel +
                ", address='" + address + '\'' +
                '}';
    }
}
