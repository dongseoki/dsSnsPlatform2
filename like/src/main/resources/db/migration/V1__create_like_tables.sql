
DROP TABLE IF EXISTS tb_like;
CREATE TABLE `tb_like` (
                        like_no BIGINT NOT NULL AUTO_INCREMENT,
                        ref_tbl VARCHAR(255),
                        ref_id BIGINT,
                        created_date DATETIME,
                        last_modified_date DATETIME,
                        created_by BIGINT,
                        last_modified_by BIGINT,
                        del_yn VARCHAR(1) DEFAULT 'N',
                        PRIMARY KEY (like_no)
);
CREATE INDEX idx_ref_id ON tb_like(ref_id);