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
    data = IntStream.range(0, 30).mapToObj(l -> random.nextInt(100))
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
  public void testSelectSort() {
    ArrayUtil.bubbleSort(data);
  }

  @Test
  public void testInsertSort() {
    data = ArrayUtil.insertSort(data);
  }

  @Test
  public void testShellSort() {
    ArrayUtil.shellSort(data);
  }

  @Test
  public void testMerge() {
    data = ArrayUtil.merge(new int[] {2, 6, 1, 1}, new int[] {8, 1, 4});
  }

  @Test
  public void testMergeSort() {
    data = ArrayUtil.mergeSort(data);
  }

  @Test
  public void testQuickSort() {
    ArrayUtil.quickSort(data, 0, data.length);
  }

  @Test
  public void testHeapSort() {
    ArrayUtil.heapSort(data);
  }

  @Test
  public void testCountingSort() {
    ArrayUtil.countingSort(data, 0, 100);
  }

  @Test
  public void testRadixSort() {
    ArrayUtil.radixSort(data, 2);
  }

  @Test
  public void testFindTopN() {
    ArrayUtil.findTopN(data, 8);
  }

  @Test
  public void testFindTopN2() {
    ArrayUtil.findTopN(data, 0, 10);
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
