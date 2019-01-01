package cn.bingo.dig.java.generics;

import org.junit.Test;
import cn.bingo.dig.java.entity.User;
import cn.bingo.dig.java.entity.UserDetail;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GenericTypeTest {
  @Test
  public void test1() {
    GenericType<User> genericType = new GenericType<User>();
    User user = new User();
    log.debug("user {}", user);
    genericType.setResult(user);
    User result = genericType.getResult();
    log.debug("result {}", result);
  }

  @Test
  public void test2() {
    GenericType<? super User> genericType = new GenericType<User>();
    UserDetail user = new UserDetail();
    log.debug("user {}", user);
    genericType.setResult(user);
    Object result = genericType.getResult();
    log.debug("result {}", result);
  }

  @Test
  public void test3() {
    GenericType<? extends User> genericType = new GenericType<UserDetail>();
    UserDetail user = new UserDetail();
    log.debug("user {}", user);
//    genericType.setResult(obj);
    User result = genericType.getResult();
    log.debug("result {}", result);
  }
  
  
}
