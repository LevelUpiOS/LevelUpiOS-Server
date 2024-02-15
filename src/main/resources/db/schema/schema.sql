CREATE TABLE category
(
    category_id BIGINT       NOT NULL AUTO_INCREMENT,
    name        VARCHAR(30)  NOT NULL,
    description VARCHAR(255) NOT NULL DEFAULT '',
    created_at  DATETIME     NOT NULL DEFAULT NOW(),
    updated_at  DATETIME     NOT NULL DEFAULT NOW(),
    deleted_at  DATETIME              DEFAULT NULL,

    PRIMARY KEY (category_id)
);

CREATE TABLE exam
(
    exam_id     BIGINT      NOT NULL AUTO_INCREMENT,
    category_id BIGINT      NOT NULL,
    name        VARCHAR(30) NOT NULL,
    created_at  DATETIME    NOT NULL DEFAULT NOW(),
    updated_at  DATETIME    NOT NULL DEFAULT NOW(),
    deleted_at  DATETIME             DEFAULT NULL,

    PRIMARY KEY (exam_id),
    FOREIGN KEY (category_id) REFERENCES category (category_id)
);

CREATE TABLE solution
(
    solution_id BIGINT       NOT NULL AUTO_INCREMENT,
    explanation VARCHAR(255) NOT NULL DEFAULT '',
    created_at  DATETIME     NOT NULL DEFAULT NOW(),
    updated_at  DATETIME     NOT NULL DEFAULT NOW(),
    deleted_at  DATETIME              DEFAULT NULL,

    PRIMARY KEY (solution_id)
);

CREATE TABLE ox_solution
(
    solution_id BIGINT  NOT NULL,
    answer      TINYINT NOT NULL,

    PRIMARY KEY (solution_id),
    FOREIGN KEY (solution_id) REFERENCES solution (solution_id)
);

CREATE TABLE question
(
    question_id BIGINT       NOT NULL AUTO_INCREMENT,
    exam_id     BIGINT       NOT NULL,
    solution_id BIGINT       NOT NULL,
    paragraph   VARCHAR(255) NOT NULL DEFAULT '',
    created_at  DATETIME     NOT NULL DEFAULT NOW(),
    updated_at  DATETIME     NOT NULL DEFAULT NOW(),
    deleted_at  DATETIME              DEFAULT NULL,

    PRIMARY KEY (question_id),
    FOREIGN KEY (exam_id) REFERENCES exam (exam_id),
    FOREIGN KEY (solution_id) REFERENCES solution (solution_id)
);

CREATE TABLE users
(
    user_id    BIGINT     NOT NULL AUTO_INCREMENT,
    created_at DATETIME   NOT NULL DEFAULT NOW(),
    updated_at DATETIME   NOT NULL DEFAULT NOW(),
    deleted_at DATETIME            DEFAULT NULL,

    PRIMARY KEY (user_id)
);

CREATE TABLE submission
(
    submission_id BIGINT   NOT NULL AUTO_INCREMENT,
    user_id       BIGINT   NOT NULL,
    exam_id       BIGINT   NOT NULL,
    created_at    DATETIME NOT NULL DEFAULT NOW(),
    updated_at    DATETIME NOT NULL DEFAULT NOW(),
    deleted_at    DATETIME          DEFAULT NULL,

    PRIMARY KEY (submission_id),
    FOREIGN KEY (user_id) REFERENCES users (user_id),
    FOREIGN KEY (exam_id) REFERENCES exam (exam_id)
);

CREATE TABLE answer
(
    answer_id     BIGINT   NOT NULL AUTO_INCREMENT,
    submission_id BIGINT   NOT NULL,
    question_id   BIGINT   NOT NULL,
    created_at    DATETIME NOT NULL DEFAULT NOW(),
    updated_at    DATETIME NOT NULL DEFAULT NOW(),
    deleted_at    DATETIME          DEFAULT NULL,

    PRIMARY KEY (answer_id),
    FOREIGN KEY (submission_id) REFERENCES submission (submission_id),
    FOREIGN KEY (question_id) REFERENCES question (question_id)
);

CREATE TABLE ox_answer
(
    answer_id BIGINT  NOT NULL,
    guess     TINYINT NOT NULL,

    PRIMARY KEY (answer_id),
    FOREIGN KEY (answer_id) REFERENCES answer (answer_id)
);

CREATE TABLE bookmark
(
    bookmark_id BIGINT   NOT NULL AUTO_INCREMENT,
    user_id     BIGINT   NOT NULL,
    question_id BIGINT   NOT NULL,
    created_at  DATETIME NOT NULL DEFAULT NOW(),
    updated_at  DATETIME NOT NULL DEFAULT NOW(),

    PRIMARY KEY (bookmark_id),
    FOREIGN KEY (user_id) REFERENCES users (user_id),
    FOREIGN KEY (question_id) REFERENCES question (question_id)
);