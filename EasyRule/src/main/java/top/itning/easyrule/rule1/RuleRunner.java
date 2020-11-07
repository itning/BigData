package top.itning.easyrule.rule1;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.api.RulesEngineParameters;
import org.jeasy.rules.core.DefaultRulesEngine;
import top.itning.easyrule.rule1.rule.*;

/**
 * 运行
 *
 * @author itning
 * @date 2020/11/7 9:59
 */
public class RuleRunner {
    public static void main(String[] args) {
        UserDTO userDTO = new UserDTO();

        Facts facts = new Facts();
        facts.put("user", userDTO);

        AddNameRule nameRule = new AddNameRule();
        FinallyUserRule finallyUserRule = new FinallyUserRule();

        RulesEngineParameters parameters = new RulesEngineParameters();
        // 为true时， 从第一条开始，匹配一条就会跳过后面规则匹配，不匹配则一直往下执行
        parameters.skipOnFirstAppliedRule(false);
        // 为true时， 如果执行@Action中发生异常就会跳过后面规则匹配
        parameters.skipOnFirstFailedRule(true);
        // 为true时，从第一条开始，匹配一条才会往下执行，不匹配则跳过后面
        parameters.skipOnFirstNonTriggeredRule(true);
        // 大于指定的优先级则不进行匹配
        //parameters.priorityThreshold(5);


        Rules rules = new Rules(
                nameRule,
                finallyUserRule,
                new AddIdRule(),
                new AddAddressRule(),
                // 不使用代理
                new AddTelRule()
        );

        // 循环直到条件都不满足规则引擎实现
        //RulesEngine rulesEngine = new InferenceRulesEngine();
        RulesEngine rulesEngine = new DefaultRulesEngine(parameters);
        rulesEngine.fire(rules, facts);
    }
}
