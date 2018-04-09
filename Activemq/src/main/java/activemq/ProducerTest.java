package activemq;

import javax.jms.JMSException;

public class ProducerTest {

    /**
     * @param args
     * @throws Exception
     * @throws JMSException
     */
    public static void main(String[] args) throws JMSException, Exception {
        ProducerTool producer = new ProducerTool();
        producer.produceMessage("Hello, world!");
        producer.close();
    }
}      

