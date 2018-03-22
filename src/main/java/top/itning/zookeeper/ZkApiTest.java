package top.itning.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Zookeeper JAVA API
 *
 * @author wangn
 */
public class ZkApiTest {
    /**
     * 逗号分隔主机：端口对，每个对应一个ZooKeeper 主机名/IP 与配置文件相同
     */
    private static final String CONNECT_STRING = "192.168.117.128:2181,192.168.117.129:2181,192.168.117.130:2181";
    /**
     * 会话超时（毫秒）
     */
    private static final int SESSION_TIMEOUT = 2000;
    /**
     * ZooKeeper对象
     */
    private ZooKeeper zooKeeper;

    /**
     * 连接Zookeeper
     *
     * @throws IOException IOException
     */
    @Before
    public void connectZK() throws IOException {
        System.out.println("start connect...");
        zooKeeper = new ZooKeeper(CONNECT_STRING, SESSION_TIMEOUT, System.out::println);
    }

    /**
     * 连接结束关闭
     *
     * @throws InterruptedException InterruptedException
     */
    @After
    public void connectClose() throws InterruptedException {
        System.out.println("close connect...");
        zooKeeper.close();
    }

    /**
     * 测试是否连接成功
     */
    @Test
    public void isConnected() {
        assertFalse("连接失败:结果不为false", zooKeeper.getState().isConnected());
    }

    /**
     * 测试获取子节点
     */
    @Test
    public void getChildren() throws KeeperException, InterruptedException {
        List<String> children = zooKeeper.getChildren("/", false);
        assertEquals(1, children.size());
    }

    /**
     * 获取根节点数据
     */
    @Test
    public void getRootData() throws KeeperException, InterruptedException {
        byte[] data = zooKeeper.getData("/", false, new Stat());
        System.out.println(new String(data));
    }

    /**
     * 创建新节点
     * 短暂（ephemeral）（断开连接自己删除）
     * 持久（persistent）（断开连接不删除）(PERSISTENT_SEQUENTIAL（持久序列/test0000000019 ）)
     */
    @Test
    public void createNode() throws KeeperException, InterruptedException {
        String s = zooKeeper.create("/clientTest", new byte[1], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(s);
    }

    /**
     * 删除节点
     * 版本 -1 为全部删除
     */
    @Test
    public void deleteNode() throws KeeperException, InterruptedException {
        zooKeeper.delete("/clientTest", -1);
        assertEquals(null, zooKeeper.exists("/clientTest", false));
    }


}
