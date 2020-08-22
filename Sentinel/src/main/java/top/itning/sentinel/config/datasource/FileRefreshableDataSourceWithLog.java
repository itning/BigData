package top.itning.sentinel.config.datasource;

import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.datasource.FileRefreshableDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;

/**
 * <p>拉模式
 * <p>内部实现线程池自动轮询检查文件是否变化
 *
 * @author itning
 * @date 2020/8/22 15:58
 */
public class FileRefreshableDataSourceWithLog<T> extends FileRefreshableDataSource<T> {
    private static final Logger logger = LoggerFactory.getLogger(FileRefreshableDataSourceWithLog.class);

    public FileRefreshableDataSourceWithLog(String fileName, Converter<String, T> configParser) throws FileNotFoundException {
        super(fileName, configParser);
    }

    @Override
    public String readSource() throws Exception {
        String source = super.readSource();
        logger.info("Read Source: {}", source);
        return source;
    }

    @Override
    protected boolean isModified() {
        boolean modified = super.isModified();
        if (modified) {
            logger.info("Is Modified: true");
        }
        return modified;
    }

    @Override
    public void close() throws Exception {
        logger.info("close");
        super.close();
    }
}
