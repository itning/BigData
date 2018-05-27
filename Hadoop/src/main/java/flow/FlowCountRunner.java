package flow;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class FlowCountRunner {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration);

        job.setJarByClass(FlowCountRunner.class);

        job.setMapperClass(FlowCountMapper.class);
        job.setReducerClass(FlowCountReducer.class);

        job.setMapOutputKeyClass(LongWritable.class);
        job.setMapOutputValueClass(FlowCountBean.class);

        job.setOutputKeyClass(LongWritable.class);
        job.setOutputValueClass(FlowCountBean.class);

        job.setInputFormatClass(TextInputFormat.class);

        FileInputFormat.setInputPaths(job, "hdfs://192.168.1.11:9000/in");
        FileOutputFormat.setOutputPath(job, new Path("hdfs://192.168.1.11:9000/out/flowcount"));

        job.waitForCompletion(true);
    }
}
