package top.itning.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

/**
 * @author itning
 * @date 2019/4/25 16:23
 */
public class UdpServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        DatagramSocket socket = new DatagramSocket();

        Receive receive = new Receive(socket);
        receive.start();

        int i = 0;
        while (true) {
            byte[] b = (i++ + "s").getBytes(StandardCharsets.UTF_8);
            DatagramPacket packet = new DatagramPacket(b, b.length, InetAddress.getLocalHost(), 10000);
            packet.setData(b);
            socket.send(packet);
            Thread.sleep(1000);
            System.out.println("send: Data Length " + packet.getLength());
        }
    }

    private static class Receive extends Thread {
        private DatagramSocket datagramSocket;

        Receive(DatagramSocket datagramSocket) {
            super();
            this.datagramSocket = datagramSocket;
        }

        @Override
        public void run() {
            super.run();
            try {
                byte[] buf = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                while (true) {
                    datagramSocket.receive(packet);
                    System.out.println("receive: " + new String(packet.getData(), 0, packet.getLength(), StandardCharsets.UTF_8));
                }
            } catch (IOException ignored) {
            }
        }
    }
}
