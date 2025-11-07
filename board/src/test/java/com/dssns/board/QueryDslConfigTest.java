package com.dssns.board;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.dssns.board.entity.Post;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(classes = BoardApplication.class)
@Transactional
class QueryDslConfigTest {

    @Autowired
    private EntityManager entityManager;

    @Test
    void qPostClassExistsTest() {
        // given & when
        // QPost 클래스가 컴파일 시 생성되었는지 확인
        try {
            Class<?> qPostClass = Class.forName("com.dssns.board.entity.QPost");

            // then
            assertNotNull(qPostClass);
            assertTrue(qPostClass.getName().equals("com.dssns.board.entity.QPost"));
            System.out.println("QPost 클래스가 정상적으로 생성되었습니다: " + qPostClass.getName());
        } catch (ClassNotFoundException e) {
            throw new AssertionError("QPost 클래스가 생성되지 않았습니다. QueryDSL annotation processor가 제대로 동작하지 않았을 수 있습니다.", e);
        }
    }

    @Test
    void queryDslBasicQueryTest() {
        // given
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        com.dssns.board.entity.QPost qPost = com.dssns.board.entity.QPost.post;

        // when
        // 간단한 select 쿼리 실행 (실제 데이터가 없어도 쿼리 생성은 가능)
        Long count = queryFactory
                .select(qPost.count())
                .from(qPost)
                .fetchOne();

        // then
        assertNotNull(count);
        assertTrue(count >= 0);
        System.out.println("QueryDSL 쿼리가 정상적으로 실행되었습니다. Post 개수: " + count);
    }

    @Test
    void queryDslSelectQueryTest() {
        // given
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        com.dssns.board.entity.QPost qPost = com.dssns.board.entity.QPost.post;

        // when
        // QPost의 필드에 접근하여 쿼리 생성 테스트
        var result = queryFactory
                .selectFrom(qPost)
                .limit(10)
                .fetch();

        // then
        assertNotNull(result);
        System.out.println("QueryDSL select 쿼리가 정상적으로 실행되었습니다. 조회된 Post 개수: " + result.size());
    }
}
