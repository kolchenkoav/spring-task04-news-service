package com.example.rest.rest.aop;

import com.example.rest.rest.service.CommentService;
import com.example.rest.rest.service.NewsService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerMapping;

import java.util.Map;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class LoggingAspect {
    private final NewsService newsService;
    private final CommentService commentService;

    /*=============================================================================
    *   В сервисе News аннотацией @LoggableNews помечены методы update и deleteById.
    *   Если userId не совпадает с пользователем который создал новость, то
    *   делаем Rollback
    * ============================================================================*/
    @Before("@annotation(com.example.rest.rest.aop.LoggableNews)")
    public void logBeforeNews(JoinPoint joinPoint) {
        // нам нужно только update или delete
        if (joinPoint.getSignature().getName().equals("findById")) {
            return;
        }

        // Получаем HttpServletRequest за пределами контроллера, используя контекст
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();

        // Извлекаем переменные пути запроса:
        var pathVariables = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Long newsId = null;
        try {
            newsId = Long.valueOf(pathVariables.get("id"));
        } catch (Exception e) {
            log.warn("Неправильно задана переменная пути. Должно быть в запросе: /api/news/1  где 1 - ID новости");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return;
        }
        Long userIdMaster = newsService.findById(newsId).getUser().getId();

        // GET-параметры запроса:
        Long userId = null;
        try {
            userId = Long.valueOf(request.getParameter("userId"));
        } catch (Exception e) {
            log.warn("Неправильно задан параметр userId");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return;
        }

        if (!userIdMaster.equals(userId)) {
            log.warn("Пользователь с ID: {} не МОЖЕТ редактировать запись. Новость создал пользователь с ID: {}", userId, userIdMaster);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return;
        }
        log.info("News -> {} is done.", joinPoint.getSignature().getName());
    }

    /*=============================================================================
     *   В сервисе Comment аннотацией @LoggableComments помечены методы update и deleteById.
     *   Если userId не совпадает с пользователем который создал комментарий, то
     *   делаем Rollback
     * ============================================================================*/
    @Before("@annotation(com.example.rest.rest.aop.LoggableComments)")
    public void logBeforeComment(JoinPoint joinPoint) {
        if (joinPoint.getSignature().getName().equals("findById")) {
            return;
        }

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();

        Long commentId = null;
        var pathVariables = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

        try {
            commentId = Long.valueOf(pathVariables.get("id"));
        } catch (Exception e) {
            log.warn("Неправильно задана переменная пути   должно быть в запросе: /api/comment/1  где 1 - ID комментария");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return;
        }

        Long userIdMaster = commentService.findById(commentId).getUser().getId();

        Long userId = null;
        try {
            userId = Long.valueOf(request.getParameter("userId"));
        } catch (NumberFormatException e) {
            log.warn(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return;
        }

        if (!userIdMaster.equals(userId)) {
            log.warn("Пользователь с ID: {} не МОЖЕТ редактировать комментарий. Комментарий создал пользователь с ID: {}", userId, userIdMaster);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return;
        }
        log.info("Comment -> {} is done.", joinPoint.getSignature().getName());
    }
}
