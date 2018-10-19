package api;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

/**
 * @author wangn
 */
public class HdfsApiTest {
    /**
     * 默认HDFS地址
     */
    private static final String DEFAULT_HDFS_PATH = "hdfs://node2:9000/";
    /**
     * FileSystem
     */
    private FileSystem fileSystem;

    /**
     * 获取文件系统对象
     *
     * @throws URISyntaxException   URISyntaxException
     * @throws IOException          IOException
     * @throws InterruptedException InterruptedException
     */
    @Before
    public void getFileSystem() throws URISyntaxException, IOException, InterruptedException {
        //设置winutil
        System.setProperty("hadoop.home.dir", "G:\\winutils\\hadoop-2.8.3");
        //真实项目应该在ClassPath下建立xxx-site.xml文件 构造会自动加载
        Configuration configuration = new Configuration();
        //指定文件系统为HDFS
        configuration.set("fs.defaultFS", DEFAULT_HDFS_PATH);
        //指定副本数量
        configuration.set("dfs.replication", "3");
        fileSystem = FileSystem.get(new URI(DEFAULT_HDFS_PATH), configuration, "ning");
    }

    /**
     * 关闭连接
     *
     * @throws IOException IOException
     */
    @After
    public void closeFileSystem() throws IOException {
        fileSystem.close();
    }

    /**
     * 上传文件
     */
    @Test
    public void testUp() throws IOException {
        //存HDFS的位置
        Path path = new Path(DEFAULT_HDFS_PATH + "word/b.log");
        FSDataOutputStream fsDataOutputStream = fileSystem.create(path);
        //源文件
        FileInputStream fileInputStream = new FileInputStream("C://Users//wangn//Desktop//b.txt");
        IOUtils.copy(fileInputStream, fsDataOutputStream);
    }

    /**
     * 测试上传和下载
     *
     * @throws IOException IOException
     */
    @Test
    public void testUpload() throws IOException {
        fileSystem.copyFromLocalFile(new Path("C://Users//wangn//Desktop//a.txt"), new Path("/test"));
        fileSystem.copyToLocalFile(new Path("/test"), new Path("C://Users//wangn//Desktop//a.txt"));
    }

    /**
     * 下载文件
     *
     * @throws IOException IOException
     */
    @Test
    public void testDown() throws IOException {
        //HDFS中的源文件
        Path path = new Path(DEFAULT_HDFS_PATH + "out/rizhi/part-r-00000");
        FSDataInputStream fsDataInputStream = fileSystem.open(path);
        //输出目录
        FileOutputStream fileOutputStream = new FileOutputStream("C://Users//wangn//Desktop//test.txt");
        IOUtils.copy(fsDataInputStream, fileOutputStream);
    }

    /**
     * 测试创建文件夹
     *
     * @throws IOException IOException
     */
    @Test
    public void testMkdir() throws IOException {
        boolean mkdirs = fileSystem.mkdirs(new Path("/test"));
        System.out.println(mkdirs);
    }

    /**
     * 测试删除
     */
    @Test
    public void testRemove() throws IOException {
        //recursive:递归删除：删文件夹其内容也删除
        boolean delete = fileSystem.delete(new Path("/out/flowcount"), true);
        System.out.println(delete);
    }

    /**
     * 修改名称
     *
     * @throws IOException IOException
     */
    @Test
    public void testRename() throws IOException {
        boolean rename = fileSystem.rename(new Path("/test/1011.rar"), new Path("/test/a.rar"));
        System.out.println(rename);
    }

    /**
     * 列出文件
     * @throws IOException IOException
     */
    @Test
    public void testListFile() throws IOException {
        RemoteIterator<LocatedFileStatus> listFiles = fileSystem.listFiles(new Path("/"), false);
        while (listFiles.hasNext()) {
            LocatedFileStatus fileStatus = listFiles.next();
            System.out.println(fileStatus.getPath().getName() + "-->" + fileStatus);
        }
    }

    /**
     * 遍历文件/文件夹
     */
    @Test
    public void testListFolder() throws IOException {
        FileStatus[] fileStatuses = fileSystem.listStatus(new Path("/"));
        Arrays.stream(fileStatuses).forEach(fileStatus -> System.out.println(fileStatus.getPath().getName()));
    }

}
