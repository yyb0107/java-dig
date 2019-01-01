package cn.bingo.dig.java.generics;

import org.junit.Test;
import cn.bingo.dig.java.entity.User;
import cn.bingo.dig.java.entity.UserDetail;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GenericTypeTest2 {
  interface GenericGetter<T extends GenericGetter<T>>{
    
    T get();
  };
  
  interface Getter extends GenericGetter<Getter>{
    
  }
  
  class GenericAndReturnTypes{
    public void test(Getter g){
      Getter result = g.get();
      GenericGetter r = g.get();
      
    }
  }
}
