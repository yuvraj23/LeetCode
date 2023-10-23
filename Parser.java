import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Parser {
    public static void main(String []args){
        String rawData=" <--table Account Limit starts-->\n" +
                "MaxAutoScaleGroup\t|MaxLaunchConfig\t|AutoscaleGroups\t|LaunchConfigs\n" +
                "500\t|200\t|4\t|2\n" +
                "<--table Account Limit ends-->\n" +
                "<--table Group Configs starts-->\n" +
                "CreatedTime\t|AZ\t|ARN\t|Name\t|LaunchConfig\t|CoolDown\t|HealthCheckPeriod\t|HealthCheckType\t|LoadBalancers\t|PlacementGroup\t|VPC\t|Instances\n" +
                "Fri Aug 26 12:57:40 IST 2022\t|us-east-1a, us-east-1b, us-east-1c\t|arn:aws:autoscaling:us-east-1:063251190422:autoScalingGroup:9a6a004b-1af7-439d-97fe-024f77f17af4:autoScalingGroupName/awseb-e-mm97ndhgma-stack-AWSEBAutoScalingGroup-O1O87P84JQQM\t|awseb-e-mm97ndhgma-stack-AWSEBAutoScalingGroup-O1O87P84JQQM\t|-\t|360\t|0\t|EC2\t|-\t|-\t|-\t|i-054f16667afea9991\n" +
                "<--table Group Configs ends-->";

        System.out.println(rawData.indexOf("<--table Account Limit starts-->"));
        System.out.println(rawData.indexOf("<--table Account Limit ends-->"));
        parseString(rawData,"Group Config");




    }

    public static void parseString(String rawData,String childNodeName){
        String startName="<--table "+childNodeName+" starts-->";
        String endName="<--table "+childNodeName+" ends-->";
        int sl=startName.length();
        int el=endName.length();
        int startInd=rawData.indexOf(startName);
        int endInd=rawData.indexOf(endName);

        System.out.println(rawData.substring(startInd+sl,endInd));
        String []parseStr=rawData.substring(startInd+sl,endInd).trim().split("\n");
        Map<String,String> map=new HashMap<>();
        String []headers=parseStr[0].split("\\t\\|");
        String []colVal=parseStr[1].split("\\t\\|");
        for(int i=0;i<headers.length;i++){
            map.put(headers[i],colVal[i]);
        }

        System.out.println(map);

    }
}
