package top.itning.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;

/**
 * @author itning
 * @date 2019/4/25 16:30
 */
public class UdpClient {
    public static void main(String[] args) throws IOException {
        //接收10000端口的UDP数据
        DatagramSocket socket = new DatagramSocket(10000);
        final byte[] buf = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        while (true) {
            socket.receive(packet);
            byte[] data = packet.getData();
            String stringBuilder = "udp://" +
                    packet.getAddress().getHostAddress() +
                    ":" +
                    packet.getPort() +
                    " " +
                    new String(data, 0, packet.getLength(), StandardCharsets.UTF_8) +
                    " and length " +
                    packet.getLength();
            System.out.println(stringBuilder);

            //回传给服务器
            final byte[] back = ("Client Get Data Length " + packet.getLength()).getBytes();
            DatagramPacket backPack = new DatagramPacket(back, back.length, packet.getAddress(), packet.getPort());
            backPack.setData(back);
            socket.send(backPack);
        }
    }
}
