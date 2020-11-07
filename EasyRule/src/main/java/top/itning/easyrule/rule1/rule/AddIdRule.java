package top.itning.easyrule.rule1.rule;

import lombok.extern.slf4j.Slf4j;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import top.itning.easyrule.rule1.UserDTO;

/**
 * 添加ID规则
 *
 * @author itning
 * @date 2020/11/7 9:55
 */
@Slf4j
@Rule(name = "AddIdRule", description = "添加ID规则", priority = 1)
public class AddIdRule {
    @Condition
    public boolean when(@Fact("user") UserDTO user) {
        log.info("when {}", user);
        return null == user.getId();
    }

    @Action(order = 1)
    public void then(@Fact("user") UserDTO user) throws Exception {
        log.info("then");
        user.setId(1);
    }

    @Action(order = 2)
    public void doFinally() throws Exception {
        log.info("doFinally");
    }

}
