import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class LogMapper extends Mapper<LongWritable, Text, Text, Text> {
    private final Pattern logPattern = Pattern.compile("\\[([^\\s:/]+\\S*)\\S*\\] \"(?:[A-Z]+)\\s+([^\"]*)\"");

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        Matcher matcher = logPattern.matcher(line);

        if (matcher.find()) {
            String month = matcher.group(1);
            String file = matcher.group(2).trim();
            String ip = line.split("\\s")[0];

            context.write(new Text(month + "\t" + file), new Text(ip));
        }
    }
}
