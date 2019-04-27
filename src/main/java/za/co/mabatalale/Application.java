package za.co.mabatalale;

import za.co.mabatalale.jobs.JobManager;
import za.co.mabatalale.rest.SparkInit;



/**
 * Created by robson on 2017/02/25.
 */
public class Application {

    public static void main(String[] args) {
       new Thread(new JobManager()).start();
       new SparkInit();
        //ForemanReportBuilder foremanReportBuilder = new ForemanReportBuilder();
        //foremanReportBuilder.runReportBuilder();
        //System.out.println("done");
        //StartEndShiftService startEndShiftService = new StartEndShiftService(new EndOfShiftRepository(),new ShiftStartRepository(),new CurrentSessionsRepository());
        //System.out.println(startEndShiftService.getSessionKey(1,1));
        /*try {
            PulsarClient client = PulsarClient.builder()
                    .serviceUrl("pulsar://localhost:6650")
                    .build();
            Producer<byte[]> producer = client.newProducer()
                    .topic("my-topic")
                    .create();

            // You can then send messages to the broker and topic you specified:
            producer.send("My message".getBytes());
            Producer<String> stringProducer = client.newProducer(Schema.STRING)
                    .topic("my-topic")
                    .create();
            stringProducer.send("My message @ 2020");
            producer.close();
            client.close();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }*/
    }

}
