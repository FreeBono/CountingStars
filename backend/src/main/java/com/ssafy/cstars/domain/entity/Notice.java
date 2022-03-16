// package com.ssafy.cstars.domain.entity;

// import com.fasterxml.jackson.annotation.JsonBackReference;
// import lombok.*;

// import javax.persistence.Entity;
// import javax.persistence.FetchType;
// import javax.persistence.JoinColumn;
// import javax.persistence.ManyToOne;

// /**
//  * 공지사항 모델 정의.
//  */
// @Entity
// @Getter
// @NoArgsConstructor(access = AccessLevel.PROTECTED)
// @AllArgsConstructor(access = AccessLevel.PRIVATE)
// @Builder
// public class Notice extends BaseEntity {
//     String title; //  게시글 제목
//     String content; // 게시글 내용

//     // 외래키
//     @JsonBackReference
//     @ManyToOne(fetch = FetchType.LAZY)
//     @JoinColumn(name="admin_id")
//     Admin admin;

<<<<<<< HEAD
//     public void modify(String title, String description) {
//         this.title = title;
//         this.content = description;
//     }
// }
=======
    public void modify(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
>>>>>>> c3bd5782aff9db060de09726a88d1c68373f3f55
