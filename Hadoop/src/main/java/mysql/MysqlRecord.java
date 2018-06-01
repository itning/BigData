package mysql;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.lib.db.DBWritable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MysqlRecord implements Writable, DBWritable {
    private int id;
    private Date date;
    private String pid;
    private int amount;

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeInt(id);
        out.write(date.toString().getBytes());
        Text.writeString(out, this.pid);
        out.write(amount);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.id = in.readInt();
        this.date = Date.valueOf(Text.readString(in));
        this.pid = in.readUTF();
        this.amount = in.readInt();
    }

    @Override
    public void write(PreparedStatement statement) throws SQLException {
        statement.setInt(1, this.id);
        statement.setDate(2, this.date);
        statement.setString(3, this.pid);
        statement.setInt(4, this.amount);
    }

    @Override
    public void readFields(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt(1);
        this.date = resultSet.getDate(2);
        this.pid = resultSet.getString(3);
        this.amount = resultSet.getInt(4);
    }
}
