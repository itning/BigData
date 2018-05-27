package flow;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class FlowCountBean implements Writable {
    /**
     * 手机号
     */
    private long tel;
    /**
     * 上传流量
     */
    private long upload;
    /**
     * 下载流量
     */
    private long download;
    /**
     * 总流量
     */
    private long sum;

    public long getTel() {
        return tel;
    }

    public void setTel(long tel) {
        this.tel = tel;
    }

    public long getUpload() {
        return upload;
    }

    public void setUpload(long upload) {
        this.upload = upload;
    }

    public long getDownload() {
        return download;
    }

    public void setDownload(long download) {
        this.download = download;
    }

    public long getSum() {
        return sum;
    }

    public void setSum(long sum) {
        this.sum = sum;
    }

    public void clear() {
        tel = 0;
        upload = 0;
        download = 0;
        sum = 0;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeLong(tel);
        out.writeLong(upload);
        out.writeLong(download);
        out.writeLong(sum);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        tel = in.readLong();
        upload = in.readLong();
        download = in.readLong();
        sum = in.readLong();
    }

    @Override
    public String toString() {
        return "FlowCountBean{" +
                "tel=" + tel +
                ", upload=" + upload +
                ", download=" + download +
                ", sum=" + sum +
                '}';
    }
}
