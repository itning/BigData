package top.itning.easyrule.rule1.rule;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.core.BasicRule;
import top.itning.easyrule.rule1.UserDTO;

import java.util.UUID;

/**
 * 添加电话规则
 *
 * @author itning
 * @date 2020/11/7 10:39
 */
public class AddTelRule extends BasicRule {

    public AddTelRule() {
        this.priority = 4;
    }

    @Override
    public boolean evaluate(Facts facts) {
        Object user = facts.get("user");
        if (user instanceof UserDTO) {
            return ((UserDTO) user).getTel() == null;
        }
        return false;
    }

    @Override
    public void execute(Facts facts) throws Exception {
        UserDTO user = facts.get("user");
        user.setTel(UUID.randomUUID().toString().replace("-", ""));
    }
}
