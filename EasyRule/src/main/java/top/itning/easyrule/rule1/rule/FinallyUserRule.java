package top.itning.easyrule.rule1.rule;

import lombok.extern.slf4j.Slf4j;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import top.itning.easyrule.rule1.UserDTO;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 最终规则
 *
 * @author itning
 * @date 2020/11/7 10:19
 */
@Slf4j
@Rule(name = "FinallyUserRule", description = "最终规则", priority = Integer.MAX_VALUE)
public class FinallyUserRule {
    private static final AtomicInteger TIME = new AtomicInteger();

    @Condition
    public boolean when(@Fact("user") UserDTO user) {
        if (TIME.get() > 0) {
            return false;
        }
        TIME.incrementAndGet();
        return true;
    }

    @Action(order = 1)
    public void then(@Fact("user") UserDTO user) throws Exception {
        log.info("then {}", user);
    }

    @Action(order = 2)
    public void doFinally() throws Exception {
        log.info("doFinally");
    }
}
