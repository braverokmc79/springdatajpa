package com.jpa.spring;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//@Component
@Transactional
public class JpaRunner implements ApplicationRunner {

    @PersistenceContext
    EntityManager entityManager;


    public void run2(ApplicationArguments args) throws Exception {
        Account account=new Account();
        account.setUsername("HongGilDong");
        account.setPassword("jpa");

        //entityManager.persist(account);
        Study study =new Study();
        study.setName("Spring Data JPA");

        //보조적인 설정
//        account.getStudies().add(study);
//        //항상 관계의 주인에 넣어줘야 한다.
//        study.setOwner(account);

        //====>
        account.addStudy(study);

        Session session =entityManager.unwrap(Session.class);
        session.save(account);
        session.save(study);

        Account kessun = session.load(Account.class, account.getId());
        System.out.println(" ====================================" );
        System.out.println("1.kessun = " + kessun.getUsername());

        //더티 체킹
        kessun.setUsername("whiteship");
        kessun.setUsername("whiteship");
        System.out.println(" ====================================" );
        System.out.println("2.kessun = " + kessun.getUsername());
    }




     public void run3(ApplicationArguments args) throws  Exception{
         Post post=new Post();
         post.setTitle("Spring Data JPA 언제 보나...");

        Comment comment =new Comment();
        comment.setComment("빨리 보고 싶어요.");
        post.addComment(comment);

        Comment comment1 =new Comment();
        comment1.setComment("곧 보여드릴께요");
        post.addComment(comment1);


//        Session session =entityManager.unwrap(Session.class);
//        session.save(post);

        entityManager.persist(post);

        /** cascade = CascadeType.ALL 설정 되어 있기 때문에 다음 코드를 넣지 않아도
         *  comment data 가 들어간다.
         * * **/
//        entityManager.persist(comment);
//        entityManager.persist(comment1); Post

         entityManager.flush();
         entityManager.close();
         List<PostDTO> resultList = entityManager.createQuery("select new com.jpa.spring.PostDTO( p.id, p.title, c.id, c.comment )  from  Post p  join  p.comments c on p.id=1",
                 PostDTO.class).getResultList();


         for(PostDTO p :resultList){
                System.out.println("resultList   = " + p);
         }
         /**
          출력
          p.getComments() = PostDTO(id=1, title=Spring Data JPA 언제 보나..., commentId=1, comment=빨리 보고 싶어요.)
          p.getComments() = PostDTO(id=1, title=Spring Data JPA 언제 보나..., commentId=2, comment=곧 보여드릴께요)
          * 
          */

         //삭제
//         Post singleResult = entityManager.createQuery("select p from Post p where p.id = 1l ", Post.class).getSingleResult();
//         System.out.println("singleResult = " + singleResult.getTitle());
//         entityManager.remove(singleResult);
//         entityManager.flush();
//         entityManager.clear();
//
//         List<Comment> commentList = entityManager.createQuery("select c from Comment c where c.post.id = 1l ", Comment.class).getResultList();
//         System.out.println("CommentList = " + commentList.size());

     }



    //@Override
    public void run4(ApplicationArguments args) throws  Exception {
        Post post = new Post();
        post.setTitle("Spring Data JPA 언제 보나...");

        Comment comment = new Comment();
        comment.setComment("빨리 보고 싶어요.");
        post.addComment(comment);

        Comment comment1 = new Comment();
        comment1.setComment("곧 보여드릴께요");
        post.addComment(comment1);

        entityManager.persist(post);
        Post resultPost = entityManager.createQuery("select p from Post p join p.comments c  where p.id=1l ", Post.class).getSingleResult();

        resultPost.getComments().forEach(c->{
            System.out.println(" comment  = " +c.getComment());
        });
    }



   // @Override
    public void run(ApplicationArguments args) throws Exception{
        Post post = new Post();
        post.setTitle("Spring Data JPA 언제 보나...");

        Comment comment = new Comment();
        comment.setComment("빨리 보고 싶어요.");
        post.addComment(comment);

        Comment comment1 = new Comment();
        comment1.setComment("곧 보여드릴께요");
        post.addComment(comment1);

        entityManager.persist(post);

        List<Post> resultList = entityManager.createQuery("select p from Post p join p.comments ", Post.class).getResultList();
        System.out.println("=========================================================");
        resultList.forEach(System.out::println);
        System.out.println("=========================================================");
    }






}
