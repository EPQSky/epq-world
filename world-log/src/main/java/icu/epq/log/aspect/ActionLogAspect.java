package icu.epq.log.aspect;

import com.google.gson.Gson;
import icu.epq.log.annotation.ActionLog;
import icu.epq.log.record.ActionRecord;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.stereotype.Component;

/**
 * 用户行为日志
 *
 * @author epqsky
 */
@Slf4j
@Aspect
@Component
public class ActionLogAspect {

    private RestHighLevelClient restHighLevelClient;

    public ActionLogAspect(RestHighLevelClient restHighLevelClient) {
        this.restHighLevelClient = restHighLevelClient;
    }

    @Around("@annotation(actionLog)")
    public Object around(ProceedingJoinPoint point, ActionLog actionLog) throws Throwable {

        ActionRecord actionRecord = new ActionRecord();
        actionRecord.setClassName(point.getTarget().getClass().getName());
        actionRecord.setMethodName(point.getSignature().getName());
        actionRecord.setArgsValue(point.getArgs());
        actionRecord.setActionTime(System.currentTimeMillis());

        // 执行方法
        Object result = point.proceed();
        // 行为日志
        log.info("用户行为详情=" + actionRecord);

        IndexRequest request = new IndexRequest("action");
        request.source(new Gson().toJson(actionRecord), XContentType.JSON);

        restHighLevelClient.index(request, RequestOptions.DEFAULT);

        return result;
    }

}
