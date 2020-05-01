import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.RunningJob;
import org.apache.hadoop.mapred.TextOutputFormat;


public class Skyline {

    public static void main(String[] args) throws Exception{

        Configuration conf = new Configuration(); // Hadoop configuration
        conf.set("mapred.textoutputformat.separator", ",");
        String bounds=String.valueOf(48.24*Math.PI/180)+","+String.valueOf(70.52*Math.PI/180)+","+String.valueOf(30*Math.PI/180)+","+String.valueOf(60*Math.PI/180);
        System.out.println("Creating the Job . . .");


        // Creating the job
        JobConf job = new JobConf(conf, Skyline.class);
        job.setJarByClass(Skyline.class);
        job.setJobName("SkylineQueriesJob");

        FileInputFormat.setInputPaths(job, new Path("./input")); // Declaring input path of job
        FileOutputFormat.setOutputPath(job, new Path("./output")); // Declaring output path of job

        job.set("splits",args[2]); // Declaring the number of partitions as a job argument
        job.set("partitioningTechnique",args[3]); // Declaring the partitioning technique as a job argument
        job.set("dimensions",args[4]); // Declaring number of dimensions as a job argument
        job.set("bounds",bounds); // Declaring the bounds of angle partitioning technique as a job argument
        job.setOutputKeyClass(IntWritable.class); // Declaring the keys of the job as an integer
        job.setOutputValueClass(Text.class); // Declaring the format of the input values of data points as Text
        job.setOutputFormat(TextOutputFormat.class); // Declaring the format of the output file of the job as Text
        job.setMapperClass(SkylineMapper.class); // Declaring the mapper class of the job
        job.setCombinerClass(SkylineCombiner.class); // Declaring the combiner class of the job
        job.setReducerClass(SkylineReducer.class); // Declaring the reducer class of the job

        // If the /output/ path exist the delete it to upload a new one.

        //Running the MapReduce Job!
        System.out.println("About to start the Job!");
        long StartJob = System.currentTimeMillis();
        RunningJob runningJob = JobClient.runJob(job);
        long EndJob = System.currentTimeMillis();
        System.out.println("Job time : "+(EndJob-StartJob)/1000.0);
        System.out.println("job.isSuccessfull: " + runningJob.isComplete());

    }

}