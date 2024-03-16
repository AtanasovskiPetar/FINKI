import java.io.IOException;
import java.util.HashSet;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class LogReducer extends Reducer<Text, Text, Text, Text> {
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        HashSet<String> uniqueIPs = new HashSet<>();

        for (Text value : values) {
            uniqueIPs.add(value.toString());
        }

        context.write(key, new Text(uniqueIPs.toString()));
    }
}
