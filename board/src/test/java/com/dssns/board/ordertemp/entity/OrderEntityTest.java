package com.dssns.board.ordertemp.entity;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.dssns.board.BoardApplication;
import com.dssns.common.entity.YesOrNo;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(classes = BoardApplication.class)
@Transactional
class OrderEntityTest {

  @Autowired
  private EntityManager entityManager;

  @Test
  void orderEntityCreationTest() {
    // given
    Order order = Order.builder()
        .userId(1L)
        .orderNumber("ORD-2024-001")
        .orderStatus(OrderStatus.PENDING)
        .totalAmount(new BigDecimal("10000.00"))
        .paymentMethod("CARD")
        .delYn(YesOrNo.N)
        .build();

    // when
    entityManager.persist(order);
    entityManager.flush();
    entityManager.clear();

    Order found = entityManager.find(Order.class, order.getId());

    // then
    assertNotNull(found);
    assertNotNull(found.getId());
    assertTrue(found.getOrderNumber().equals("ORD-2024-001"));
    assertTrue(found.getOrderStatus() == OrderStatus.PENDING);
    assertTrue(found.getTotalAmount().compareTo(new BigDecimal("10000.00")) == 0);
    System.out.println("Order 엔티티가 정상적으로 생성되었습니다. ID: " + found.getId());
  }

  @Test
  void goodsEntityCreationTest() {
    // given
    Goods goods = Goods.builder()
        .goodsName("프리미엄 플랜")
        .goodsType("SUBSCRIPTION")
        .price(new BigDecimal("9900.00"))
        .description("월간 구독 플랜")
        .isActive(true)
        .delYn(YesOrNo.N)
        .build();

    // when
    entityManager.persist(goods);
    entityManager.flush();
    entityManager.clear();

    Goods found = entityManager.find(Goods.class, goods.getId());

    // then
    assertNotNull(found);
    assertNotNull(found.getId());
    assertTrue(found.getGoodsName().equals("프리미엄 플랜"));
    assertTrue(found.getIsActive());
    System.out.println("Goods 엔티티가 정상적으로 생성되었습니다. ID: " + found.getId());
  }

  @Test
  void subscriptionEntityCreationTest() {
    // given
    Subscription subscription = Subscription.builder()
        .userId(1L)
        .planType("PRO")
        .status(SubscriptionStatus.ACTIVE)
        .startDate(LocalDateTime.now())
        .endDate(LocalDateTime.now().plusMonths(1))
        .delYn(YesOrNo.N)
        .build();

    // when
    entityManager.persist(subscription);
    entityManager.flush();
    entityManager.clear();

    Subscription found = entityManager.find(Subscription.class, subscription.getId());

    // then
    assertNotNull(found);
    assertNotNull(found.getId());
    assertTrue(found.getPlanType().equals("PRO"));
    assertTrue(found.getStatus() == SubscriptionStatus.ACTIVE);
    System.out.println("Subscription 엔티티가 정상적으로 생성되었습니다. ID: " + found.getId());
  }

  @Test
  void orderAndOrderGoodsRelationshipTest() {
    // given
    Goods goods = Goods.builder()
        .goodsName("테스트 상품")
        .goodsType("ONE_TIME")
        .price(new BigDecimal("5000.00"))
        .isActive(true)
        .delYn(YesOrNo.N)
        .build();

    entityManager.persist(goods);
    entityManager.flush();

    Order order = Order.builder()
        .userId(1L)
        .orderNumber("ORD-2024-002")
        .orderStatus(OrderStatus.PENDING)
        .totalAmount(new BigDecimal("5000.00"))
        .delYn(YesOrNo.N)
        .build();

    entityManager.persist(order);
    entityManager.flush();

    OrderGoods orderGoods = OrderGoods.builder()
        .order(order)
        .goods(goods)
        .quantity(1)
        .unitPrice(new BigDecimal("5000.00"))
        .totalPrice(new BigDecimal("5000.00"))
        .delYn(YesOrNo.N)
        .build();

    // when
    entityManager.persist(orderGoods);
    entityManager.flush();
    entityManager.clear();

    Order foundOrder = entityManager.find(Order.class, order.getId());

    // then
    assertNotNull(foundOrder);
    assertNotNull(foundOrder.getOrderGoods());
    assertTrue(foundOrder.getOrderGoods().size() == 1);
    System.out.println("Order와 OrderGoods의 연관관계가 정상적으로 설정되었습니다. OrderGoods 개수: " + foundOrder.getOrderGoods().size());
  }

  @Test
  void orderPaymentEntityCreationTest() {
    // given
    Order order = Order.builder()
        .userId(1L)
        .orderNumber("ORD-2024-003")
        .orderStatus(OrderStatus.PENDING)
        .totalAmount(new BigDecimal("10000.00"))
        .delYn(YesOrNo.N)
        .build();

    entityManager.persist(order);
    entityManager.flush();

    OrderPayment orderPayment = OrderPayment.builder()
        .order(order)
        .paymentMethod("CARD")
        .paymentStatus(PaymentStatus.PENDING)
        .amount(new BigDecimal("10000.00"))
        .delYn(YesOrNo.N)
        .build();

    // when
    entityManager.persist(orderPayment);
    entityManager.flush();
    entityManager.clear();

    OrderPayment found = entityManager.find(OrderPayment.class, orderPayment.getId());

    // then
    assertNotNull(found);
    assertNotNull(found.getId());
    assertTrue(found.getPaymentStatus() == PaymentStatus.PENDING);
    assertTrue(found.getAmount().compareTo(new BigDecimal("10000.00")) == 0);
    System.out.println("OrderPayment 엔티티가 정상적으로 생성되었습니다. ID: " + found.getId());
  }
}

