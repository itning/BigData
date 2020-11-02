package mysql;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.db.DBConfiguration;
import org.apache.hadoop.mapreduce.lib.db.DBInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;


public class MysqlRunner {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        System.setProperty("hadoop.home.dir", "G:\\winutils\\hadoop-2.8.3");
        Configuration configuration = new Configuration();

        Job job = Job.getInstance(configuration);

        job.setJar("G:\\ProjectData\\IdeaProjects\\BigData\\Hadoop\\target\\Hadoop-0.0.1-SNAPSHOT.jar");

        //job.setJarByClass(MysqlRunner.class);

        job.setInputFormatClass(DBInputFormat.class);

        DBConfiguration.configureDB(configuration, "com.mysql.cj.jdbc.Driver",
                "jdbc:mysql://localhost:3306/bigdata?createDatabaseIfNotExist=true&useUnicode=true&characterEncoding=utf-8&useSSL=false&autoReconnect=true&failOverReadOnly=false&connectTimeout=0&serverTimezone=UTC", "root", "kingston");
        String[] fields = {"id", "date", "pid", "amount"};
        DBInputFormat.setInput(job, MysqlRecord.class, "t_order", null, null, fields);

        FileOutputFormat.setOutputPath(job, new Path("C:\\Users\\itning\\Desktop\\b"));

        job.waitForCompletion(true);
    }
}
