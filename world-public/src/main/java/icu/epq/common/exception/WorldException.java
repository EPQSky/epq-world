package icu.epq.common.exception;

import icu.epq.common.api.R;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * 业务逻辑错误抛出
 *
 * @author EPQ
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorldException extends RuntimeException {

    private R<?> resp;

}
