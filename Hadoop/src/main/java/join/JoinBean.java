package join;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class JoinBean implements Writable {
    private long oid;
    private String pid;
    private String odate;
    private String pname;
    private String opid;
    private long oamount;
    private String category_id;
    private long price;

    public long getOid() {
        return oid;
    }

    public void setOid(long oid) {
        this.oid = oid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getOdate() {
        return odate;
    }

    public void setOdate(String odate) {
        this.odate = odate;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getOpid() {
        return opid;
    }

    public void setOpid(String opid) {
        this.opid = opid;
    }

    public long getOamount() {
        return oamount;
    }

    public void setOamount(long oamount) {
        this.oamount = oamount;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeLong(oid);
        out.writeUTF(pid);
        out.writeUTF(odate);
        out.writeUTF(pname);
        out.writeUTF(opid);
        out.writeLong(oamount);
        out.writeUTF(category_id);
        out.writeLong(price);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        oid = in.readLong();
        pid = in.readUTF();
        odate = in.readUTF();
        pname = in.readUTF();
        opid = in.readUTF();
        oamount = in.readLong();
        category_id = in.readUTF();
        price = in.readLong();
    }

    @Override
    public String toString() {
        return "JoinBean{" +
                "oid=" + oid +
                ", pid=" + pid +
                ", odate='" + odate + '\'' +
                ", pname='" + pname + '\'' +
                ", opid='" + opid + '\'' +
                ", oamount=" + oamount +
                ", category_id='" + category_id + '\'' +
                ", price=" + price +
                '}';
    }
}
