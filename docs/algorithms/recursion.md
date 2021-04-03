# Recursion

A recursive function or method must include:

1. **Base case(s)**  
    Ensuring that all recursive branches terminate eventually. There can be multiple base cases.
2. **Recursive call**  
    I.e. a call to the function/method itself
3. **Advancement towards termination**  
    A parameter in some form to advance towards termination

If 1. or 2. are not met, the function/method will be subject to infinite recursion.

## Examples

Recursion is best learned by examples and practice. Some examples of how recursion can be used to solve various problems are presented below.

???+ example "Recursive printing"

    Given the following Doubly Linked List

    <figure>
    <img src="/algorithms/media/recursive-print-doubly-linked-list.svg" width="500" />
    </figure>

    the task is to determine what gets printed when the method below is called with the `head` node as input.

    ```java linenums="1"
    public void recursivePrint(Node current) {
        if (current == null || current.next == null) {
            System.out.println("Recursion is cool!");
            return;
        }
        if (current.next != null && current.data.length() < 5) {
            recursivePrint(current.next.next);
            System.out.println(current.data.length());
            recursivePrint(current.previous);
        }
        else if (current.data.length() % 2 == 1) {
            System.out.println(current.data);   
            recursivePrint(current.next);
        }
        else if (current.data.length() % 2 == 0) {
            recursivePrint(current.next.next);
            System.out.println(current.data.length());
        }
    }
    ```
    Recursion can be nicely presented in a tree format as shown below.
    The execution starts out in the top with where the method is called with `"Mary"` as input. The execution lines that prints are denoted with a green border.

    <figure>
    <img src="/algorithms/media/recursive-print-tree.svg" />
    </figure>

    At line 7, the first recursive call is made to `recursivePrint("Stephanie")`. This can be thought of as a "branch" in the execution tree. The current method which has `"Mary"` as input is now waiting for line 7 to finish execution.

    The `"Stephanie"` node has `data.length=9`, which causes line 12 to print `Stephanie`. The line immediately after, line 13 then makes a recursive call with `Andrew` as input.

    The `Andrew` node has `data.length=6` and creates a new recursive call with `Adrianna` as input on line 16. This creates a new "branch", since there is still code left to be executed that then must wait for line 16 to finish.

    The `Adrianna` node has `.next=null`. The base is reached and `"Recursion is cool!"` is printed. This is the first time we reach the base case, but there is still code left in the call stack, waiting to be executed.

    We must climb up in the tree again and finish "branches" that are not done. The most recently created branch is the one in the `"Andrew"` call. The execution continues at line 17 where is prints the length of the name, i.e. 6.

    The next unfinished branch is at the first call to `"Mary"`. The execution continues at line 8 and prints 4. In line 9 a new recursive call is made, but since it is the last line to execute in the "`Mary`" call, no additional branch is created.

    The call in line 9 has `null` as input since `"Mary"` has `.previous=null`. This reaches the base case and prints `"Recursion is cool!"` and we are done.

    In summary, running the method from the head of the Doubly Linked List `recursivePrint(head)` prints

    1. Stephanie
    2. Recursion in cool!
    3. 6
    4. 4
    5. Recursion is cool! 


??? example "Greatest common divisor"

    The *Greatest Common Divisor* (gcd) is the largest integer that divides both $x$ and $y$. It can be found in multiple ways. One recursive way is by the so-called *Euclidean Division* algorithm, which can be defined like this:

    $$
    x = q \cdot y + r \quad , \quad r=x \ \mathrm{mod} \ y \quad , \quad y > r
    $$

    This is a mathematical way to describe a simple division problem of $x/y$, where $q$ is the quotient and $r$ the remainder. The quotient is the integer result of the $x/y$ division. Thus $y$ must be larger than the remainder $r$ or $q$ would be larger.

    The problem is recursive given that the remainder $r$ from a division $x/y$ goes into the next division to be calculated. The next division becomes $y/r$, and the pattern repeats.

    Consider the interactive Python session below where $\mathrm{gcd}(144, 44)$ is found.

    ```python
    >>> 144 % 44
    12
    >>> 44 % 12
    8
    >>> 12 % 8
    4
    >>> 8 % 4
    0
    # Result: gcd(144, 44) = 4
    ```

    The recursive problem can be stated as
    $$
    gcd(x, y) = gcd(y, r)
    $$

    The Java method below will find the *gcd* recursively by using the *Euclidean Division* algorithm.

    The code comments provide the necessary explanation.

    ```java linenums="1"
    public static int gcd(int x, int y) {

        // 1. Base case: If there is no remainder
        if (y == 0) {
            return x;
        }
        else {
            // 3. Advancement parameter: Calculate the new remainder
            int remainder = x % y;

            // 2. Recursive call: Pass in the new remainder to the next recursive call
            return gcd(y, remainder);
        }
    }
    ```

??? example "Number of increasing subsequences in array"

    Given an array of integers, find the number of subsequences that are continuously increasing. Subsequences need not be contiguous, i.e. gaps between numbers are allowed.

    ```python linenums="1"
    from typing import List


    def increasing_sequences(A: List, idx_neg: int = -2, count: int = 1):
        '''Find the number of increasing subsequences in array A.
        
        Parameters
        -----------
        A:
            The array to count increasing subsequences in. 
        idx_neg:
            The negative index of `A` to be compared to the last element of `A`.
        count:
            The current number of increasing subsequences.
            
        Examples
        --------
            >>> b = increasing_sequences([3, 2, 4])
                3
            >>> increasing_sequences([10, 24, 25, -1, -5, -2, -1])
                7
        '''
        if not A or len(A) == 1:
            # 1. Base case:
            # The empty array and single-element arrays are taken to have 1 sequence
            return count
        
        if idx_neg == -len(A)-1:
            # The current array has been fully traversed
            # 2.+3. Recursive call w. end of array moved one index down
            return increasing_sequences(A[:-1], count=count)
            
        if A[idx_neg] < A[-1]:
            # Increasing subsequence found
            count = count + 1

        # 2.+3. Recursive call w. index moved one to the left in current array
        return increasing_sequences(A, idx_neg-1, count)
    ```

??? example "Towers of Hanoi"

    ```python linenums="1"
    from typing import List


    def tower(n: int, src: List, dst: List, helper: List):
        """Solve the Tower of Hanoi problem recursively.
        
        Parameters
        ----------
        n:
            The number of disks present in the game.
        src:
            The pole that initially has the entire stack of disks.
        dst:
            The pole to move the stack of disks onto.
        helper:
            The remaining of the three poles.
        """
        
        if n == 1:
            print(f"Move disk {n} from {src} to {dst}")
            
        else:
            # Move the entire stack except for the bottom ring from the stack
            # from the 'src' rod to the 'other' rod
            tower(n=n-1, src=src, dst=helper, helper=dst)
            
            # 
            print(f"Move disk {n} from {src} to {dst}")
            
            tower(n=n-1, src=helper, dst=dst, helper=src)


    a, b, c = "A", "B", "C"
    n = 3
    tower(n, a, c, b)
    ```

??? example "Removing consecutive duplicates in a Linked List"

    Below are two methods from a Singly Linked List data structure class. The first one is used as API interface for removing duplicates in the list, it calls a second internal method that recursively removed the duplicates.

    The second method removes consecutive duplicates. If the list is sorted, it will remove all duplicates.

    The code assumes that `head` is an instance variable of the Singly Linked List.

    ```java linenums="1"
    public void removeDuplicates() {
        // Perform a recursive remove and update the head afterwards
        head = recursiveRemove(head);
    }

    private Node<T> recursiveRemove(Node<T> current) {

        // The base case, if the list is empty
        if current == null) {
            return null;
        }

        // The recursive call, with local "head" of the next sublist as new argument
        current.next = recursiveRemove(current.next);

        if (current.next != null && current.data.equals(current.next.data)) {
            // Duplicate detected, remove it by returning the node it points to
            // rather than the node itself. Effectively skipping the node in the
            // rebuilding of the list
            return current.next;
        }
        // Not a duplicate, return the node itself
        return current;
    }
    ```

    The `recursiveRemove` method can be thought of as subsequent calls to a list that reduces for each method call.
    The first call is from the *head* of the global list. If the list is not empty, we create a new call with the next node as the new *head*. This in essence a *local head* of the sublist in question.

    This effectively queues up method calls for all the nodes in the list.

    A great explanation is given in [this](https://learning.edx.org/course/course-v1:GTx+CS1332xI+2T2020/block-v1:GTx+CS1332xI+2T2020+type@sequential+block@07739a92788a464ea3290cca3115de3f/block-v1:GTx+CS1332xI+2T2020+type@vertical+block@bc75c77c34014388abeafdda0c2b7d9a) video from Georgia Tech's Data Structures and Algorithms course (requires access).
