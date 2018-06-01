package join;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class JoinRunner {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        System.setProperty("hadoop.home.dir", "G:\\winutils\\hadoop-2.8.3");
        Configuration configuration = new Configuration();

        Job job = Job.getInstance(configuration);

        job.setJarByClass(JoinRunner.class);

        job.setMapperClass(JoinMapper.class);
        job.setReducerClass(JoinReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(JoinBean.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(JoinOutBean.class);

        job.setInputFormatClass(TextInputFormat.class);

        /*FileInputFormat.setInputPaths(job, "hdfs://192.168.1.11:9000/in");
        FileOutputFormat.setOutputPath(job, new Path("hdfs://192.168.1.11:9000/out/flowcount"));*/

        FileInputFormat.setInputPaths(job, new Path("C:\\Users\\wangn\\Desktop\\a"));
        FileOutputFormat.setOutputPath(job, new Path("C:\\Users\\wangn\\Desktop\\b"));

        job.waitForCompletion(true);
    }
}
