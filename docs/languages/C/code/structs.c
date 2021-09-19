#include <stdio.h>

// Define a structure called Rectangle with fields height and width
struct Rectangle {
  float height;
  float width;
};

// We can create an alias for the Rectangle via a typedef
typedef struct Rectangle rect;

int main(void) {
  // A Rectangle can be instantiated directly via the `struct` keyword
  struct Rectangle r1 = {8, 10};

  // Or we can instantiate via the alias we created
  rect r2 = {5, 7};

  // A more explicit constructor with field names given
  rect r3 = {.height = 3, .width = 9};

  // We can access the fields by dot notation
  printf("%f\n", r1.height);
  printf("%f\n", r2.height);
  printf("%f\n", r3.height);

  // Initialize an array of rectangles
  rect rectangle_arr[] = {{.height = 8, .width = 10},
                          {.height = 5, .width = 7},
                          {.height = 3, .width = 9}};

  // Print the rectangles
  for (int i = 0; i < 3; i++) {
    float h = rectangle_arr[i].height;
    float w = rectangle_arr[i].width;
    printf("Rectangle(%.2f, %.2f)\n", h, w);
  }
}