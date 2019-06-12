package com.example.demo.study.jdk8.suanfa;

import java.io.IOException;
import java.nio.channels.ServerSocketChannel;
import java.util.*;

/**
 * @Describe
 * https://www.cnblogs.com/bjh1117/p/8335628.html
 * @Auth duranfu
 * @Date 2018/8/8
 */
public class Sort {
	public static void main(String[] args) {
//		test();
//		moveZeroes(arr);
//		moveZeroes(arr,5);
//		sushuDemo();
		int[] arr = {4,2,3,1,6,5,7};
		int []temp = new int[arr.length];
//		bubbleSort1(arr);
//		selectSort1(arr);
//		sort1(arr,0,arr.length - 1,temp);
//		quickSort1(arr,0,arr.length-1);
//		insertionSort(arr);

//		int[] arr = {1,3,5,7,9,10,11};
//		int i = binarySearch2(arr, 7);
//		System.out.println("=======" + i + "=====");
		for (int a: arr) {
			System.out.println(a);
		}

//		int[] arr = {1, 1, 2, 3, 5, 8, 13};
//		System.out.println(bofei(6));

//		test5();
	}



	public static void test() {
		String str = "1,2,3";
		String[] arr = str.split(",");
		System.out.println(arr[0]);
	}


	/**
	 * 不开辟空间，将数组中的0移到最后面，其他非0元素顺序不变
	 * @param nums
	 * @param numsSize
	 * {0,1,0,3,12};
	 */
	public static void moveZeroes(int[] nums, int numsSize) {
		int[] arr = {0,1,0,3,12};
		int i = 0, j = 0;
		while (i < numsSize) {
			if (nums[i] != 0) {
				if (i != j) {
					nums[j] = nums[i];
					nums[i] = 0;
				}
				j++;
			}
			i++;
		}
	}

	/**
	 * 简单排序：冒泡排序,选择排序，插入排序时间复杂度都是O(n*n)
	 *
	 * 1、冒泡排序
	 * 对相邻的元素进行两辆比较，顺序相反则交换，每一趟将最大的元素浮到最顶端
	 * N个元素需要排N-1轮，第i轮需要比较N-i次，N个元素需要比较n(n-1)/2次
	 *
	 * @param arr
	 */
	public static void bubbleSort(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = 0; j < arr.length - 1 - i; j++) {
				if (arr[j] > arr[j + 1]) {
					swap(arr, j, j + 1);
				}
			}
		}
	}

	/**
	 * 2、选择排序
	 *从第一个元素开始，分别与后面的元素向比较，找到最小的元素与第一个元素交换位置
	 *  选择排序是对冒泡排序的改进，他的比较次数和冒泡排序相同，但交换次数要小于冒泡排序，当数据量较大时，效率会有很大提升
	 * @param arr
	 */
	public static void selectSort(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			int min = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] < arr[min]) {
					min = j;
				}
			}
			swap(arr, min, i);
		}
	}

	/**
	 * 3、插入排序
	 * 插入排序的思想是每一步将一个待排序的记录，插入到前面已经排好的有序序列中去，直到插完所有元素为止
	 * 插入排序在最好的情况下，需要比较n-1次，无需交换元素，时间复杂度为n,最坏情况下为n次方
	 * 插入排序是简单排序中最快的排序算法
	 * @param arr
	 */
	static void insertionSort(int[] arr) {
		int j;
		for (int i = 1; i < arr.length; i++) {
			int  temp = arr[i];
			for (j = i; j > 0 && temp < arr[j - 1] ; j--) {
				arr[j] = arr[j - 1];
			}
			arr[j] = temp;
		}
	}

	/********************4、希尔排序start*****************************/
	/**
	 * https://www.cnblogs.com/chengxiao/p/6104371.html
	 *希尔排序也是一种插入排序，是简单插入排序改进之后的一个更高效的版本，也称为缩小增量排序。
	 * 把记录按下标的一定增量分组，对每组使用直接插入排序算法排序，随着增量逐渐减少，每组包含
	 * 的关键词越来越多，当增量减至1时，整个文件恰被分成一组，算法便终止
	 *
	 *希尔排序对增量序列的选择非常重要，直接影响到希尔排序的性能，最坏时间复杂度为O(n的平方)
	 */
	public static void shellSort(int[] arr) {
		//增量gap，并逐步缩小增量
		for (int gap = arr.length/2; gap > 0 ; gap/=2) {
			for (int i = gap; i < arr.length; i++) {
				int j = i;
				while (j-gap >= 0 && arr[j] < arr[j - gap]) {
					swap(arr, j, j-gap);
					j -= gap;
				}
			}
		}
	}
	/********************4、希尔排序end*****************************/


	/********************5、快排start*****************************/

	/**
	 *
	 * https://blog.csdn.net/it_zjyang/article/details/53406764#commentBox
	 * @param arr
	 * @param start
	 * @param end
	 * @return
	 */
	public static void quickSort(int[] arr, int start, int end) {
		if (start > end) {
			return;
		} else {
			int divide = divide(arr, start, end);
			quickSort(arr, start, divide - 1);
			quickSort(arr, divide + 1, end);
		}
	}


	public static int divide(int[] arr, int start, int end) {
		//每次都以最右边作为基准值,且有头指针和尾指针
		int base = arr[end];
		//start一旦等于end,就说明左右两个指针合并到了同一个位置，可以结束此轮循环
		while (start < end) {
			while (start < end && arr[start] <= base) {
				//从左边开始遍历，如果比基准值小，就继续往右走
				start ++;
			}
			//上面的while循环结束时说明当前的arr[start]的值比基准值大，应当与基准值换位置
			if (start < end) {
				swap(arr, start, end);
				//交换后，此时的那个被调换过位置的的值也到了正确的位置(基准值右边),因此右边的指针也得减1
				end --;
			}
			while (start < end && arr[end] >= base) {
				//从右边开始遍历，如果比基准值大就继续往左走
				end --;
			}
			//上面的while循环结束时说明当前的a[end]的值比基准值小，应该与基准值交换
			if (start < end) {
				swap(arr, start, end);
				start ++;
			}
		}
		return end;
	}

/********************快排end*****************************/



/********************6、归并排序start*****************************/
	/****
	 * 图解：https://www.cnblogs.com/chengxiao/p/6194356.html
	 *最坏时间复杂度O(N log N),所使用的比较次数几乎是最优的
	 */
	private static void sort(int[] arr, int left, int right,int[] temp) {
		if(left < right) {
			int mid = (left + right)/2;
			sort(arr, left, mid, temp);
			sort(arr, mid+1, right, temp);
			merge(arr,left,mid,right,temp);
		}
	}

	public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
		int i = left;//左序列指针
		int j = mid + 1;//右序列指针
		int t = 0;//临时数组指针
		while (i <= mid && j <= right) {
			if (arr[i] <= arr[j]) {
				temp[t++] = arr[i ++];
			} else {
				temp[t++] = arr[j++];
			}
		}

		while (i <= mid) {//将左边剩余元素填充进temp
			temp[t++] = arr[i++];
		}

		while (j <= right) {//将右边剩余元素填充进temp
			temp[t++] = arr[j++];
		}

		t = 0;

		while (left <= right) {
			arr[left++] = temp[t++];
		}
	}

	public static void test5() {
		int[] a = {1,3,5,7,9};
		int[] b = {2,4,6,8,10,19};
		int[] temp = new int[20];
		merge2(a, b, temp);
		for (int i:
		     temp) {
			System.out.println(i);
		}
	}

	//合并两个有序数组为一个有序数组
	public static void merge2(int[] a, int[] b, int[] temp) {
		int i = 0;
		int j = 0;
		int t = 0;
		while (i < a.length && j < b.length) {
			if (a[i] <= b[j]) {
				temp[t++] = a[i++];
			} else {
				temp[t++] = b[j++];
			}
		}
		while (i < a.length) {
			temp[t++] = a[i++];
		}
		while (j<b.length) {
			temp[t++] = b[j++];
		}
	}

/********************归并排序end*****************************/



/********************7、外部排序start*****************************/
	/**https://www.cnblogs.com/codeMedita/p/7425291.html
	 * 有时，待排序的文件很大,计算机内存不能容纳整个文件，这时对文件就不能使用内部排序了；
	 * 内部排序：待排序的内容在内存中就可以完成
	 * 外部排序：待排序的内容不能在内存中一下子完成，需要做内外存的内容交换，
	 *  外部排序常采用的排序方法也是归并排序。这种归并排序由两个阶段组成
	 *1、采用适当的内部排序方法对输入文件的每个片段进行排序，将排好序的片段写到外部存储器中
	 * (通常由一个可用的磁盘作为临时缓冲区)这样临时缓冲区中的每个归并段的内容是有序的
	 * 2、利用归并算法，归并第一阶段生成的归并段，直到只剩下一个归并段为止
	 */
/********************外部排序end*****************************/
	/**
	 *  二分查找
	 */
	public static int binarySearch1(int[] arr, int key) {
		int low = 0;
		int high = arr.length -1;
		int middle = 0;
		if (key > arr[high] || key < arr[low] || low > high) {
			return -1;
		}

		while (low <= high) {
			middle = (low + high)/2;
			if (key < arr[middle]) {
				high = middle - 1;
			} else if (key > arr[middle]) {
				low = middle + 1;
			} else {
				return middle;
			}
		}
		return -1;
	}
	/**
	 * 二分查找递归
	 * @param arr 有序数组
	 * @param key 查找的关键字
	 * @param low 最小角标
	 * @param high 最大角标
	 * @return
	 */

	public static int  binarySearch(int[] arr, int key, int low, int high) {
		if (key > arr[high] || key < arr[low] || low > high) {
			return -1;
		}
		int middle = (low + high) /2;
		if (key < arr[middle]) {
			return binarySearch(arr, key, low, middle -1);
		} else if (key > arr[middle]) {
			return binarySearch(arr, key, middle + 1, high);
		} else {
			return middle;
		}
	}
	/**
	 * list去重
	 * @param list
	 */
	public static void removeDuplicate1(List<Integer> list) {
		LinkedHashSet set = new LinkedHashSet<>();
		set.addAll(list);
		list.clear();
		list.addAll(set);
	}

	public static void removeDuplicate(List<Integer> list) {
		ArrayList<Integer> result = new ArrayList<>();
		list.forEach(e -> {
			if (!result.contains(e)) {
				result.add(e);
			}
		});
	}

	/**
	 * map去重
	 * @param map
	 */
	public static void removeDuplicateMap(HashMap<String, String> map) {
		HashSet<String> set = new HashSet<>();
		for (Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator(); iterator.hasNext();) {
			Map.Entry<String, String> entry = iterator.next();
			if (set.contains(entry.getValue())) {
				iterator.remove();
				continue;
			} else {
				set.add(entry.getValue());
			}
		}

	}

	/************************找素数的方法 start ***********************/
	public static void sushuDemo() {
		int count = 0;
		boolean isSushu;
		int j;
		for (int i = 2; i < 1000; i++) {
			isSushu = true;
			for (j = 2; j <= Math.sqrt(i); j++) {
				if (i % j == 0) {
					isSushu = false;
					break;
				}
			}
			if (isSushu) {
				count++;
				System.out.println("=======" + i + "=====");
			}
		}
		System.out.println(count);

	}
	/************************找素数的方法 end ***********************/


	/************************合并两个有序数组成一个有序数组 start ***********************/
	//https://www.cnblogs.com/clarke157/p/6910425.html
	public static int[] mergeList(int[] a, int[] b) {
		int[] c = new int[a.length + b.length];
		int i = 0, j = 0, k = 0;//分别是 a ,b ,c 的下标
		while (i < a.length && j < b.length) {
			if (a[i] <= b[j]) {
				c[k++] = a[i++];
			} else {
				c[k++] = b[j++];
			}
		}
		while (i < a.length) {
			c[k++] = a[i++];
		}
		while (i < b.length) {
			c[k++] = b[j++];
		}
		return c;
	}
	/************************合并两个有序数组成一个有序数组 end ***********************/

	/************************波非那切数列 start ***********************/
	//int[] arr = {1, 1, 2, 3, 5, 8, 13};
	public static int  bofei(int n) {
		if (n == 0 || n == 1) {
			return 1;
		} else {
			return bofei(n-1) + bofei(n-2);
		}
	}
	/************************波非那切数列 end ***********************/







	/**
	 * 交换位置
	 * @param arr
	 * @param a
	 * @param b
	 * @return
	 */
	public static void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}

	public static void swap1(int[] arr, int a, int b) {
		arr[a] = arr[a] + arr[b];
		arr[b] = arr[a] - arr[b];
		arr[a] = arr[a] - arr[b];
	}

}
