package ecom.mlslsenarathna.aspect;


import ecom.mlslsenarathna.model.entity.AuditLogEntity;
import ecom.mlslsenarathna.repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
@RequiredArgsConstructor
public class AuditAspect {

    private final AuditLogRepository auditLogRepository;

    @AfterThrowing(pointcut = "@annotation(ecom.mlslsenarathna.annotation.AuditFailure)", throwing = "ex")
    public void logBookingFailure(JoinPoint joinPoint, Exception ex) {

        Object[] args = joinPoint.getArgs();
        String userId = (args.length > 1) ? args[1].toString() : "UNKNOWN";

        AuditLogEntity log = new AuditLogEntity();
        log.setUserId(userId);
        log.setReason(ex.getMessage());
        log.setTimestamp(LocalDateTime.now());
        log.setMethodName(joinPoint.getSignature().getName());
        auditLogRepository.save(log);

    }
}