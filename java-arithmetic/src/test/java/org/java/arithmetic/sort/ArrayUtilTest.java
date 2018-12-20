package org.java.arithmetic.sort;

import java.util.Random;
import java.util.stream.IntStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ArrayUtilTest {
  int data[];

  @Before
  public void before() {
    Random random = new Random();
    data = IntStream.range(0, 20).mapToObj(l -> random.nextInt(100))
        .mapToInt(l -> Integer.valueOf(l)).toArray();
    printArray();
  }

  @After
  public void after() {
    printArray();
    System.out.println();
  }

  @Test
  public void testBubbleSort() {
    ArrayUtil.bubbleSort(data);
  }
  
  @Test
  public void testSelectSort(){
    ArrayUtil.bubbleSort(data);
  }
  
  @Test
  public void testInsertSort(){
   data = ArrayUtil.insertSort(data);
  }

  protected void printArray() {
    if (data != null) {
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < data.length; i++) {
        sb.append(data[i] + " ");
      }
      log.debug(sb.toString());
    }
  }
}
