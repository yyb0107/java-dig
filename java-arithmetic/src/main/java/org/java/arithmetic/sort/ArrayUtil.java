package org.java.arithmetic.sort;

import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ArrayUtil {
  /**
   * 冒泡排序
   * 
   * 每次循环将数字大的往后靠，数字小的逐渐浮在前面，故名冒泡排序
   * 
   * @param array
   */
  public static void bubbleSort(int array[]) {
    for (int i = 0; i < array.length; i++) {
      for (int j = 0; j < array.length - i - 1; j++) {
        if (array[j] > array[j + 1]) {
          int temp = array[j];
          array[j] = array[j + 1];
          array[j + 1] = temp;
        }
      }
    }
  }

  /**
   * 选择排序
   * 
   * 在未排序状态下选择最左端的值与其他数字进行比较，选择最小，左边逐渐变成有序状态
   * 
   * @param array
   */
  public static void selectSort(int[] array) {
    for (int i = 0; i < array.length; i++) {
      for (int j = i + 1; j < array.length; j++) {
        if (array[i] > array[j]) {
          int temp = array[i];
          array[i] = array[j];
          array[j] = temp;
        }
      }
    }
  }

  /**
   * 插入排序
   * 
   * 创建一个和原数组大小相同的数组，从原数组中的数据依次取出，与目标数组进行比较，维持目标数组的有序状态
   * 
   * @param array
   * @return
   */
  public static int[] insertSort(int[] array) {
    // 结果数据初始值都是0
    int[] result = new int[array.length];
    for (int i = 0; i < array.length; i++) {
      int newValue = array[i];
      int offset = i;
      for (int j = i; j > 0; j--) {
        if (newValue < result[j]) {
          result[j + 1] = result[j];
          offset--;
        }
      }
      result[offset] = newValue;
    }
    return result;
  }

  /**
   * shell 希尔排序 不知道为什么要出现这种排序
   * 
   * 最后一层for循环可以将array[j] > current放置for结构中，可以少一层else语句块
   * 
   * @param array
   */
  public static void shellSort(int[] array) {
    int gap = array.length / 2;
    int j = 0, current = 0;
    for (; gap > 0; gap /= 2) {
      for (int i = gap; i < array.length; i++) {
        current = array[i];
        for (j = i - gap; j >= 0; j -= gap) {
          if (array[j] > current) {
            array[j + gap] = array[j];
          } else {
            break;
          }
        }
        array[j + gap] = current;
      }
    }
  }

  public static int[] mergeSort(int[] array) {
    int[] result = null;
    int len = array.length;
    if (len <= 1) {
      return array;
    }
    int mid = len / 2;
    int[] left = Arrays.copyOfRange(array, 0, mid);
    int[] right = Arrays.copyOfRange(array, mid, len);
    result = merge(mergeSort(left), mergeSort(right));
    return result;
  }

  protected static int[] merge(int[] left, int[] right) {
    int[] result = new int[left.length + right.length];
    int i = 0, j = 0, k = 0;
    for (; i < left.length && j < right.length;) {
      if (left[i] < right[j]) {
        result[k++] = left[i++];
      } else {
        result[k++] = right[j++];
      }
    }
    while (k < result.length) {
      if (i < left.length) {
        result[k++] = left[i++];
      } else {
        result[k++] = right[j++];
      }
    }
    return result;
  }

  public static void quickSort(int[] array, int start, int end) {
    int i = start, index = 0;
    int base = array[i];
    for (int j = i + 1; j < end; j++) {
      if (array[j] < base) {
        index++;
        // swap
        if (j != index + i) {
          int temp = array[j];
          array[j] = array[index + i];
          array[index + i] = temp;
        }
      }
    }
    // swap
    array[i] = array[i + index];
    array[i + index] = base;
    log.debug(
        String.format("start %s end %s index %s\t %s", start, end, index, arrayToString(array)));
    if (index == 0) {
      index++;
    }
    if (end - start > 1) {
      if (index > 0) {
        quickSort(array, start, start + index);
        quickSort(array, start + index, end);
      }
    }
  }

  protected static String arrayToString(int[] data) {
    if (data != null) {
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < data.length; i++) {
        sb.append(data[i] + " ");
      }
      return sb.toString();
    }
    return null;
  }
}
