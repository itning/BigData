package top.itning.sentinel.config;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author itning
 * @date 2020/8/22 15:34
 */
@Configuration
public class SentinelConfig {
    private static final Logger logger = LoggerFactory.getLogger(SentinelConfig.class);

    @Bean
    public SentinelResourceAspect sentinelResourceAspect() {
        initFlowRules();
        return new SentinelResourceAspect();
    }

    public void initFlowRules() {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource("test");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // Set limit QPS to 20.
        rule.setCount(5);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
        logger.info("Init Flow Rules Success");
    }
}
