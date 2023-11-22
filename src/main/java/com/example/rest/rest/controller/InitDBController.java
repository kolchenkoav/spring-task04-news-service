package com.example.rest.rest.controller;

import com.example.rest.rest.model.Comment;
import com.example.rest.rest.model.Group;
import com.example.rest.rest.model.News;
import com.example.rest.rest.model.User;
import com.example.rest.rest.service.CommentService;
import com.example.rest.rest.service.GroupService;
import com.example.rest.rest.service.NewsService;
import com.example.rest.rest.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*===========================================================
    Заполнение БД данными для проверки работы API
============================================================= */
@Slf4j
@RestController
@RequestMapping("/api/init-db")
@RequiredArgsConstructor
public class InitDBController {
    private final UserService userService;
    private final GroupService groupService;
    private final NewsService newsService;
    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<Void> initDb() {
        if (false) {   // TODO реализовать через переменную в application.yml
            dropDb();
        }
        setUsers();
        setGroups();
        setNews();
        setComments();
        log.info("База данных заполнена тестовыми данными");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    private void dropDb() {
        // TODO можно реализовать очистку данных
    }

    private void setComments() {
        Comment comment = new Comment();
        comment.setComment("Ого. Страшно то как)");
        comment.setNews(newsService.findById(4L));
        comment.setUser(userService.findById(2L));
        commentService.save(comment);
        comment = new Comment();
        comment.setComment("Ничего страшного");
        comment.setNews(newsService.findById(4L));
        comment.setUser(userService.findById(3L));
        commentService.save(comment);
        comment = new Comment();
        comment.setComment("Бывает...");
        comment.setNews(newsService.findById(4L));
        comment.setUser(userService.findById(1L));
        commentService.save(comment);
        comment = new Comment();
        comment.setComment("Много букв вы написали");
        comment.setNews(newsService.findById(4L));
        comment.setUser(userService.findById(4L));
        commentService.save(comment);
        comment = new Comment();
        comment.setComment("Может быть и согласятся");
        comment.setNews(newsService.findById(1L));
        comment.setUser(userService.findById(5L));
        commentService.save(comment);
        comment = new Comment();
        comment.setComment("Молодец. Так держать");
        comment.setNews(newsService.findById(6L));
        comment.setUser(userService.findById(4L));
        commentService.save(comment);
        comment = new Comment();
        comment.setComment("Я и не сомневался");
        comment.setNews(newsService.findById(6L));
        comment.setUser(userService.findById(3L));
        commentService.save(comment);
    }

    private void setNews() {
        News news = new News();
        news.setMessage("Азербайджан предложил Армении провести мирные переговоры на госгранице");
        news.setGroup(groupService.findById(2L));
        news.setUser(userService.findById(1L));
        newsService.save(news);
        news = new News();
        news.setMessage("В Ленинградском зоопарке выбрали имя для детеныша белоруких гиббонов");
        news.setGroup(groupService.findById(4L));
        news.setUser(userService.findById(5L));
        newsService.save(news);
        news = new News();
        news.setMessage("Путин подчеркнул принцип равноправия в отношениях с Таджикистаном");
        news.setGroup(groupService.findById(2L));
        news.setUser(userService.findById(5L));
        newsService.save(news);
        news = new News();
        news.setMessage("В Сочи прогнозируют смерчи");
        news.setGroup(groupService.findById(5L));
        news.setUser(userService.findById(1L));
        newsService.save(news);
        news = new News();
        news.setMessage("Гололедица и ветер: москвичам рассказали о погоде на вторник");
        news.setGroup(groupService.findById(5L));
        news.setUser(userService.findById(1L));
        newsService.save(news);
        news = new News();
        news.setMessage("Колесников выиграл стометровку и стал чемпионом России на короткой воде");
        news.setGroup(groupService.findById(1L));
        news.setUser(userService.findById(2L));
        newsService.save(news);
    }

    private void setGroups() {
        Group group = new Group();
        group.setName("Спорт");
        groupService.save(group);
        group = new Group();
        group.setName("Политика");
        groupService.save(group);
        group = new Group();
        group.setName("Культура");
        groupService.save(group);
        group = new Group();
        group.setName("Горячие новости");
        groupService.save(group);
        group = new Group();
        group.setName("Погода");
        groupService.save(group);
    }

    private void setUsers() {
        User user = new User();
        user.setUserName("Иван Иванов");
        userService.save(user);
        user = new User();
        user.setUserName("Петр Петров");
        userService.save(user);
        user = new User();
        user.setUserName("Василий Васильев");
        userService.save(user);
        user = new User();
        user.setUserName("Сидор Сидоров");
        userService.save(user);
        user = new User();
        user.setUserName("Мария Арутюнова");
        userService.save(user);
    }
}
