package top.itning.sentinel.config.init;

import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.datasource.FileRefreshableDataSource;
import com.alibaba.csp.sentinel.datasource.FileWritableDataSource;
import com.alibaba.csp.sentinel.init.InitFunc;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.csp.sentinel.slots.system.SystemRule;
import com.alibaba.csp.sentinel.slots.system.SystemRuleManager;
import com.alibaba.csp.sentinel.transport.util.WritableDataSourceRegistry;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import top.itning.sentinel.config.datasource.FileRefreshableDataSourceWithLog;
import top.itning.sentinel.config.datasource.FileWritableDataSourceWithLog;

import java.net.URLDecoder;
import java.util.List;
import java.util.Objects;

/**
 * <p>初始化数据源
 * <p>应用启动的时候读取JSON文件中的数据并设置规则
 * <p>当控制台有新配置的时候写入文件中
 *
 * @author itning
 * @date 2020/8/22 15:51
 */
public class DataSourceInitFunc implements InitFunc {
    private final Converter<String, List<FlowRule>> flowRuleListParser = source -> JSON.parseObject(source,
            new TypeReference<List<FlowRule>>() {
            });
    private final Converter<String, List<DegradeRule>> degradeRuleListParser = source -> JSON.parseObject(source,
            new TypeReference<List<DegradeRule>>() {
            });
    private final Converter<String, List<SystemRule>> systemRuleListParser = source -> JSON.parseObject(source,
            new TypeReference<List<SystemRule>>() {
            });

    private final Converter<List<FlowRule>, String> flowRuleListEncoder = JSON::toJSONString;

    private final Converter<List<DegradeRule>, String> degradeRuleListEncoder = JSON::toJSONString;

    private final Converter<List<SystemRule>, String> systemRuleEncoder = JSON::toJSONString;

    @Override
    public void init() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        String flowRulePath = URLDecoder.decode(Objects.requireNonNull(classLoader.getResource("FlowRule.json")).getFile(), "UTF-8");
        String degradeRulePath = URLDecoder.decode(Objects.requireNonNull(classLoader.getResource("DegradeRule.json")).getFile(), "UTF-8");
        String systemRulePath = URLDecoder.decode(Objects.requireNonNull(classLoader.getResource("SystemRule.json")).getFile(), "UTF-8");

        fileDataSource(flowRulePath, degradeRulePath, systemRulePath);
        writableDatasource(flowRulePath, degradeRulePath, systemRulePath);
    }

    private void fileDataSource(String flowRulePath, String degradeRulePath, String systemRulePath) throws java.io.FileNotFoundException {
        // Data source for FlowRule
        FileRefreshableDataSource<List<FlowRule>> flowRuleDataSource = new FileRefreshableDataSourceWithLog<>(
                flowRulePath, flowRuleListParser);
        FlowRuleManager.register2Property(flowRuleDataSource.getProperty());

        // Data source for DegradeRule
        FileRefreshableDataSource<List<DegradeRule>> degradeRuleDataSource = new FileRefreshableDataSourceWithLog<>(
                degradeRulePath, degradeRuleListParser);
        DegradeRuleManager.register2Property(degradeRuleDataSource.getProperty());

        // Data source for SystemRule
        FileRefreshableDataSource<List<SystemRule>> systemRuleDataSource = new FileRefreshableDataSourceWithLog<>(
                systemRulePath, systemRuleListParser);
        SystemRuleManager.register2Property(systemRuleDataSource.getProperty());
    }

    private void writableDatasource(String flowRulePath, String degradeRulePath, String systemRulePath) {
        FileWritableDataSource<List<FlowRule>> flowRuleWritableDataSource = new FileWritableDataSourceWithLog<>(flowRulePath, flowRuleListEncoder);
        WritableDataSourceRegistry.registerFlowDataSource(flowRuleWritableDataSource);

        FileWritableDataSource<List<DegradeRule>> degradeRuleWritableDataSource = new FileWritableDataSourceWithLog<>(degradeRulePath, degradeRuleListEncoder);
        WritableDataSourceRegistry.registerDegradeDataSource(degradeRuleWritableDataSource);

        FileWritableDataSource<List<SystemRule>> systemRuleWritableDataSource = new FileWritableDataSourceWithLog<>(systemRulePath, systemRuleEncoder);
        WritableDataSourceRegistry.registerSystemDataSource(systemRuleWritableDataSource);
    }
}
