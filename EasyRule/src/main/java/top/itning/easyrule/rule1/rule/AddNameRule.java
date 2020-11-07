package top.itning.easyrule.rule1.rule;

import lombok.extern.slf4j.Slf4j;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import top.itning.easyrule.rule1.UserDTO;

import java.util.UUID;

/**
 * 添加名字规则
 *
 * @author itning
 * @date 2020/11/7 9:55
 */
@Slf4j
@Rule(name = "AddNameRule", description = "添加名字规则", priority = 2)
public class AddNameRule {
    @Condition
    public boolean when(@Fact("user") UserDTO user) throws Exception {
        log.info("when {}", user);
        return null == user.getName();
    }

    @Action(order = 1)
    public void then(@Fact("user") UserDTO user) throws Exception {
        log.info("then");
        user.setName(UUID.randomUUID().toString());
    }

    @Action(order = 2)
    public void doFinally() throws Exception {
        log.info("doFinally");
    }

}
