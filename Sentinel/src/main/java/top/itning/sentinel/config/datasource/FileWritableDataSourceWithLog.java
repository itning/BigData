package top.itning.sentinel.config.datasource;

import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.datasource.FileWritableDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>拉模式
 * <p>主要功能是将从控制台的新配置写入到文件中以便下次重启APP时规则还在
 *
 * @author itning
 * @date 2020/8/22 16:31
 */
public class FileWritableDataSourceWithLog<T> extends FileWritableDataSource<T> {
    private static final Logger logger = LoggerFactory.getLogger(FileWritableDataSourceWithLog.class);

    public FileWritableDataSourceWithLog(String filePath, Converter<T, String> configEncoder) {
        super(filePath, configEncoder);
    }

    @Override
    public void write(T value) throws Exception {
        logger.info("write: {}", value);
        super.write(value);
    }
}
