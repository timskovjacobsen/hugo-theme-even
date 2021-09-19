#include <stdio.h>

// Define a new function pointer type for a generic integer function
typedef int (*int_func)(int);

/**
 * Function to apply the passed in function to all elements of an int array
 */
void apply_func(int *items, int n, int_func func) {
  // Apply the function to each item in the array
  for (int i = 0; i < n; i++) {
    items[i] = func(items[i]);
  }
}

/**
 * Function to double the input value
 */
int square(int x) { return x + x; }

/**
 * Function to triple the input value
 */
int triple(int x) { return x * 3; }

/**
 * Helper function to print an integer array in a pretty format
 */
void print_int_array(int *arr, int n) {
  printf("{");
  for (int i = 0; i < n; i++) {
    if (i < n - 1) {
      printf("%d, ", arr[i]);
    } else {
      printf("%d", arr[i]);
    }
  }
  printf("}\n");
}

int main(void) {

  // Define a test array and its size
  int arr[] = {10, 20, 30, 40, 50, 60, 70, 80};
  size_t n = sizeof(arr) / sizeof(*arr);

  // Apply the function to all array elements
  apply_func(arr, n, square);

  // Print the modified array
  print_int_array(arr, n);

  // Another function
  apply_func(arr, n, triple);
  print_int_array(arr, n);
}
