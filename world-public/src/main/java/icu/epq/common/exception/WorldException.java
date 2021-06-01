package icu.epq.common.exception;

import icu.epq.common.api.R;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 业务逻辑错误抛出
 *
 * @author EPQ
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WorldException extends RuntimeException {

    private R<?> resp;

    public WorldException(R<?> resp) {
        this.resp = resp;
    }

}
