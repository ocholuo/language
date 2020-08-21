



// 206. Reverse Linked List
// Reverse a singly linked list.
// Example:
// Input: 1->2->3->4->5->NULL
// Output: 5->4->3->2->1->NULL

// Follow up:
// A linked list can be reversed either iteratively or recursively. Could you implement both?



// Approach #1 (Iterative) [Accepted]

// Assume that we have linked list 1 → 2 → 3 → Ø, we would like to change it to Ø ← 1 ← 2 ← 3.
// While you are traversing the list, change the current node's next pointer to point to its previous element. Since a node does not have reference to its previous node, you must store its previous element beforehand. You also need another pointer to store the next node before changing the reference. Do not forget to return the new head reference at the end!


public ListNode reverseList(ListNode head) {

    ListNode prev = null;
    ListNode curr





    ListNode prev = null;
    ListNode curr = head;
    while (curr != null) {
        ListNode nextTemp = curr.next;
        curr.next = prev;
        prev = curr;
        curr = nextTemp;
    }
    return prev;
}

head:
Input: 1->2->NULL

prev:  null 

curr:  head(1) null 

nextTemp:   (2)


Output: 5->4->3->2->1->NULL


// Complexity analysis
// Time complexity : O(n)
// Assume that n is the list's length
// Space complexity : O(1)
