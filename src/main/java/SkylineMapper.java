import java.io.IOException;
import java.util.Random;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class SkylineMapper extends MapReduceBase implements Mapper<LongWritable, Text, IntWritable, Text> {
    private Text word = new Text();
    private Random rnd = new Random();
    private static int Splits;
    private static String parTech;
    private int Dimensions;
    private String Bounds;

    public void map(LongWritable key, Text value, OutputCollector<IntWritable, Text> collector, Reporter reporter)
            throws IOException {
        String line = value.toString(); //read tuple
        if ( line.charAt(0)>='0' && line.charAt(0)<='9' && !line.isEmpty() && Splits>1){
            // If tuple is not the header, then call the partition function for setting the integer key and pass the tuple as text value
            word.set(line);
            collector.collect(new IntWritable(partition(line)), word);
        }
        else{
            // Pass the header with key 0
            word.set(line);
            collector.collect(new IntWritable(0), word);
        }
    }

    public int partition(String line){
            // In case of random partitioning, choose an int key between [0,partitions-1]
            return rnd.nextInt(Splits);
    }

    // Reading the arguments of the MapReduce Job
    public void configure(JobConf job) {
        Splits = (int)Long.parseLong(job.get("splits"));
        parTech = job.get("partitioningTechnique");
        Dimensions = (int)Long.parseLong(job.get("dimensions"));
        Bounds = job.get("bounds");
    }

}