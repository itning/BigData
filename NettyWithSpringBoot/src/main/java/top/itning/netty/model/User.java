package top.itning.netty.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author itning
 * @since 2021/12/9 11:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private Long id;
    private String name;
}
