package cn.bingo.dig.java.io.serialize;

import java.io.Serializable;
import org.junit.Test;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ObjectCopyTest {

  @Test
  public void testObjectCopy() {
    ClassB classB = new ClassB("ClassB", 1);
    ClassA classA = new ClassA("ClassA", 2, classB);
    log.debug("classA = {}", classA);
    ClassA classAa = ObjectCopy.copy(classA);
    log.debug("classAa = {}", classAa);
  }

  @Test
  public void testObjectClone() {
    ClassB classB = new ClassB("ClassB", 1);
    ClassA classA = new ClassA("ClassA", 2, classB);
    log.debug("classA = {}", classA);
    ClassA classAa = classA.clone();
    log.debug("classAa = {}", classAa);

  }
}


class ClassA implements Serializable, Cloneable {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  public String name;
  public int id;
  public ClassB b;

  public ClassA() {

  }

  public ClassA(String name, int id, ClassB b) {
    this.name = name;
    this.id = id;
    this.b = b;
  }

  public String toString() {
    return new String(String.format("Address:%s [nameHashCode=%s,name=%s,id=%s,classB=%s]",
        super.toString(), name.hashCode(), name, id, b));
  }

  public ClassA clone() {
    try {
      ClassA result = (ClassA) super.clone();
      result.b = result.b.clone();
      return result;
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
    }
    return null;
  }
}


class ClassB implements Serializable, Cloneable {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  public String name;
  public int id;

  public ClassB() {

  }

  public ClassB(String name, int id) {
    this.name = name;
    this.id = id;
  }

  public String toString() {
    return String.format("Address:%s [nameHashCode=%s,name=%s,id=%s]", super.toString(),
        name.hashCode(), name, id);
  }

  public ClassB clone() {
    try {
      return (ClassB) super.clone();
    } catch (CloneNotSupportedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }
}
