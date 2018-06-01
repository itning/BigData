package join;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class JoinOutBean implements Writable {
    private long id;
    private String date;
    private String name;
    private String cid;
    private long price;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeLong(id);
        out.writeUTF(date);
        out.writeUTF(name);
        out.writeUTF(cid);
        out.writeLong(price);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        id = in.readLong();
        date = in.readUTF();
        name = in.readUTF();
        cid = in.readUTF();
        price = in.readLong();
    }

    @Override
    public String toString() {
        return "JoinOutBean{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", name='" + name + '\'' +
                ", cid='" + cid + '\'' +
                ", price=" + price +
                '}';
    }
}
