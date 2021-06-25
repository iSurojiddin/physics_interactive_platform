
CREATE TABLE "user" (
    "id" SERIAL NOT NULL PRIMARY KEY,
    "first_name" VARCHAR NOT NULL,
    "last_name" VARCHAR NOT NULL,
    "address" VARCHAR DEFAULT '' NOT NULL,
    "email" VARCHAR DEFAULT '' NOT NULL,
    "password" VARCHAR NOT NULL,
    "role" INTEGER DEFAULT 2 NOT NULL,
    "current_score" INTEGER DEFAULT 0 NOT NULL,
    "test_score" INTEGER DEFAULT 0 NOT NULL,
    "total_score" INTEGER DEFAULT 0 NOT NULL
);

create table "admin" (
    "id" SERIAL NOT NULL PRIMARY KEY,
    "first_name" VARCHAR NOT NULL,
    "last_name" VARCHAR NOT NULL,
    "login" VARCHAR NOT NULL,
    "password" VARCHAR NOT NULL
);

create table "topics" (
    "id" SERIAL NOT NULL PRIMARY KEY,
    "name" VARCHAR NOT NULL,
    "text" VARCHAR NULL,
    "video_link" VARCHAR NOT NULL,
);

create table "questions" (
    "id" SERIAL NOT NULL PRIMARY KEY,
    "topics_id" INTEGER NOT NULL,
        CONSTRAINT fk_topics_id
            FOREIGN KEY(topics_id)
                REFERENCES "topics"(id),
    "question" VARCHAR DEFAULT '' NOT NULL,
    "a_ans" VARCHAR DEFAULT '' NOT NULL,
    "b_ans" VARCHAR DEFAULT '' NOT NULL,
    "c_ans" VARCHAR DEFAULT '' NOT NULL,
    "d_ans" VARCHAR DEFAULT '' NOT NULL,
    "true_ans" VARCHAR DEFAULT '' NOT NULL
);

create table "user_answer" (
    "id" SERIAL NOT NULL PRIMARY KEY,
    "create_at" TIMESTAMP,
    "user_id" INTEGER NOT NULL,
        CONSTRAINT fk_user_id
            FOREIGN KEY(user_id)
                REFERENCES "user"(id),
    "question_id" INTEGER NOT NULL,
        CONSTRAINT fk_question_id
            FOREIGN KEY(question_id)
                REFERENCES "questions"(id),
    "is_right" INTEGER NOT NULL,
    "remaining" INTEGER NOT NULL,
    "t_ans" VARCHAR NOT NULL
);

# --- !Downs
drop table "user_answer";
drop table "questions";
drop table "admin";
drop table "user";

