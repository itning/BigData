package top.itning.easyrule.rule1.rule;

import lombok.extern.slf4j.Slf4j;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import top.itning.easyrule.rule1.UserDTO;

/**
 * 添加地址规则
 *
 * @author itning
 * @date 2020/11/7 9:55
 */
@Slf4j
@Rule(name = "AddAddressRule", description = "添加地址规则", priority = 3)
public class AddAddressRule {
    @Condition
    public boolean when(@Fact("user") UserDTO user) {
        log.info("when {}", user);
        return null == user.getAddress();
    }

    @Action(order = 1)
    public void then(@Fact("user") UserDTO user) throws Exception {
        log.info("then");
        user.setAddress("address");
    }

    @Action(order = 2)
    public void doFinally() throws Exception {
        log.info("doFinally");
    }

}
