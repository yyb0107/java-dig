package org.java.arithmetic.sort;

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
}
