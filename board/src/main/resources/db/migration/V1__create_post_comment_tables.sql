DROP TABLE IF EXISTS post;
DROP TABLE IF EXISTS comment;
CREATE TABLE post (
                      post_no BIGINT NOT NULL AUTO_INCREMENT,
                      title VARCHAR(255),
                      content TEXT,
                      type VARCHAR(255),
                      created_date DATETIME,
                      last_modified_date DATETIME,
                      created_by BIGINT,
                      last_modified_by BIGINT,
                      del_yn VARCHAR(1) DEFAULT 'N',
                      PRIMARY KEY (post_no)
);

CREATE TABLE comment (
                         comment_no BIGINT AUTO_INCREMENT PRIMARY KEY,
                         post_no BIGINT NOT NULL,
                         content VARCHAR(255),
                         created_date DATETIME,
                         last_modified_date DATETIME,
                         created_by BIGINT,
                         last_modified_by BIGINT,
                         del_yn VARCHAR(1) DEFAULT 'N'
);

CREATE INDEX idx_post_no ON comment(post_no);