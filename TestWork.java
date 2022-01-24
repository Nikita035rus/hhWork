import java.util.Arrays;

public class TestWork {

    public static int[] sort(int[] array, int p, int r) {
        int q;
        if (p < r && p != (p + r) / 2) {
            q = (p + r) / 2;
        } else throw new IllegalArgumentException("NotCorrectException");
        int[] arr1 = createArr(array, p, q);
        int[] arr2 = createArr(array, q + 1, r);
        return mergeSort(
                sortTemp(arr1, 0, arr1.length - 1),
                sortTemp(arr2, 0, arr2.length - 1)
        );
    }

    private static int[] createArr(int[] arr, int a, int b) {
        int[] tempArray = new int[b - a + 1];
        if (a == b) return tempArray;
        for (int i = 0; i < tempArray.length; i++) {
            tempArray[i] = arr[a++];
        }
        return tempArray;
    }

    private static int[] sortTemp(int[] array, int firstIndex, int lastIndex) {
        int leftPosition = firstIndex;
        int rightPosition = lastIndex;
        int pivot = array[(leftPosition + rightPosition) / 2];

        do {
            while (array[leftPosition] < pivot) {
                leftPosition++;
            }
            while (array[rightPosition] > pivot) {
                rightPosition--;
            }
            if (leftPosition <= rightPosition) {
                if (leftPosition < rightPosition) {
                    array[leftPosition] += array[rightPosition];
                    array[rightPosition] = array[leftPosition] - array[rightPosition];
                    array[leftPosition] = array[leftPosition] - array[rightPosition];
                }
                leftPosition++;
                rightPosition--;
            }

        } while (leftPosition <= rightPosition);

        if (leftPosition < lastIndex) {
            sortTemp(array, leftPosition, lastIndex);
        }
        if (firstIndex < rightPosition) {
            sortTemp(array, firstIndex, rightPosition);
        }
        return array;
    }

    private static int[] mergeSort(int[] arr1, int[] arr2) {
        int[] arrayMerge = new int[arr1.length + arr2.length];
        int leftPosition = 0;
        int rightPosition = 0;
        int temp = 0;

        while (leftPosition < arr1.length && rightPosition < arr2.length) {
            if (arr1[leftPosition] < arr2[rightPosition]) {
                arrayMerge[temp] = arr1[leftPosition];
                leftPosition++;
            } else {
                arrayMerge[temp] = arr2[rightPosition];
                rightPosition++;
            }
            temp++;
        }
        if (leftPosition < arr1.length) {
            System.arraycopy(arr1, leftPosition, arrayMerge, temp, (arr1.length - leftPosition));
        }
        if (rightPosition < arr2.length) {
            System.arraycopy(arr2, rightPosition, arrayMerge, temp, (arr2.length - rightPosition));
        }
        return arrayMerge;
    }


    public static void main(String[] args) {
        int[] array = {5, 2, 4, 6, 1, 3, 2, 6};

        System.out.println(Arrays.toString(sort(array, 1, array.length - 1)));
    }
}
