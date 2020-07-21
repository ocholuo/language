
# Tree(樹): Intro(簡介)


[toc]

[1](https://www.geeksforgeeks.org/binary-tree-data-structure/)

---

若熟悉`Linked List`(連結串列)將會更容易理解樹：
- `Linked list`是一維的線性結構(不是往前、就是往後)
- 樹(與Graph)則推廣成多維的結構。

linkedlist

![f1](https://i.imgur.com/mLBAp4m.png)

- A、B、C、D稱為node(節點)，用以代表資料(data)、狀態(state)。
- 連結各個node之間的連結(link)稱為edge，可能是單方向，或者雙向。



## 隨處可見的Tree(樹)
Tree(樹)是用以描述具有`階層結構(hierarchical structure)`的問題的首選，
- 階層結構意味著明確的先後次序，
- 例如，若要印出ABC三個字母的所有排列組合(permutation)


而樹的最根本特徵就是：
- 在樹的結構裡，`只有一個root(樹根)`，`並且不存在cycle`。
- 此特徵將衍生出另外兩項等價的性質：
  - 在樹中若要從root尋找特定node，一定只存在一條路徑(path)。
  - 每個node只會有一個parent。



## 樹的元素


針對node / vertex：

![f9](https://i.imgur.com/EVshcEh.png)

- `degree`(分歧度)：
  - 一個node擁有的subtree(子樹)的個數。
  - A的degree為3，F的degree為2，N的degree為0。
- `root`(樹根)：
  - 樹中最上層的node，也是唯一一個其parent為NULL的node。
  - A即為root。
- `external node/leaf`：
  - 沒有child/subtree的node。
  - G、H、J、K、L、M、N皆為leaf node。
- `internal node`：
  - 至少有一個child的node。
  - A、B、C、D、E、F、I皆為internal node。


- `parent <--> child`：
  - 以pointer說明，被指向者(pointed)為child，指向者(point to)為parent。
- `siblings：擁有相同parent的node們，互相稱兄道弟。`
  - B、C、D共同的parent為A，B、C、D即為彼此的sibling。
- `descendant`(子嗣)：
  - 站在A，所有能夠以「parent指向child」的方式找到的node，皆稱為A的descendant，因此整棵樹除了A以外皆為A的descendant。
  - 在F，能夠以「parent指向child」找到的node有L、M，則稱L、M為F的descendant。
- `ancestor`(祖先)：
  - 圖四中，站在K，所有能夠以「尋找parent」的方式找到的node，皆稱為K的ancestor，因此，E、B、A皆為K的ancestor。
- `path`(路徑)：
  - 由descendant與ancestor關係連結成的`edge`，例如A-B-E-K、A-C-F-N。
- `level`： root-2-3-4
  - 定義root的level為1，其餘node的level為其parent的level加一。
- `height of node`：
  - 某一node與其最長path上之descendant leaf node之間的edge數。
  - 例如，F的height為1，D的height為2，leaf node的height為0。
- `height of tree`：樹的height即為root的height。
  - 樹的height為A的height，等於3。
- `depth`：
  - 某一node與root之間的edge數。
  - 例如，F的depth為2，L的depth為3。

在樹中的traversal(尋訪)之時間複雜度(time complexity)會與height(樹高)有關。

---

## 定義

以下列出兩種互相等價的Tree(樹)的定義：

A. Tree(樹)是由一個或多個節點所組成的有限集合，並且滿足：
- 存在且只有一個稱為root(樹根)的節點；
- 其餘的節點可以分割成任意正整數個(包含零個)互斥(disjoint)的集合：T1、...、Tn，其中每一個集合也都滿足樹的定義，這些集合又稱為這棵樹的subtree(子樹)。
- B. Tree(樹)是由一個或多個nodes/vertices以及edge所組成，而且沒有cycle的集合(set)。

Forest(樹林)
- 由n≥0棵彼此互斥(disjoint)的Tree(樹)所形成的集合(Set)，即稱為Forest(樹林)。
- Forest(樹林)由多個Tree(樹)所組成，可以用來表示互斥集合(disjoint set)。



---

## 程式碼
以程式碼實作一棵樹，常用的手法為：先以`class TreeNode`(或是struct)定義出每顆node能夠指向多少subtree、攜帶哪些資料形態，再以另一個class Tree表示整棵樹，並以root作為樹的存取點：


---

## 集合關係

Tree(樹)位居承先啟後的重要戰略位置，資料結構之集合關係圖：

![f11](https://i.imgur.com/xqAyRXN.png)


本篇介紹的Tree(樹)並沒有限制child/ subtree的個數
- 理論上可以有多到超過記憶體空間的child node。
  - ![f1-1](https://i.imgur.com/wt3t5d0.png)
- 然而在實務上，較常使用每個node至多只有兩個child的樹，為`Binary Tree(二元樹)`。
  - ![f2](https://i.imgur.com/ngahlhP.png)
  - 樹上的每一個node之degree皆為2
  - 並稱兩個child pointer為left child和right child。
- 從Binary Tree再增加「鍵值(Key)大小規則」，即`Binary Search Tree(BST，二元搜尋樹)`。
- 以BST為基礎，在每個node上添加顏色(紅與黑)用以平衡樹的height，以減短搜尋時間，這種樹稱為`Red Black Tree(RBT，紅黑樹)`。
- 常見的平衡樹(balanced tree)還有：AVL tree、2-3-4 tree、Splay tree等等，請參考：Wikipedia：Self-balancing binary search tree
- 另一個方向，若打破「不能存在cycle」的限制，則從Tree推廣至圖(Graph)。


---

# Binary Tree: Intro(簡介)


## 程式碼


```java
public class BinarySearchTree {

    // 根节点
    public static TreeNode root;

    public BinarySearchTree() {
        this.root = null;
    }
}

```

---

---

## 學習Binary Tree

- Binary Search Tree(BST)：
  - 在某些資料經常要增加、刪除的應用中，BST常用來做搜尋，
  - 例如許多程式語言的Library中的map和set。
- Binary Space Partition：
  - 應用於幾乎所有的3D電玩遊戲以決定哪些物件需要rendered。
- Binary Tries：
  - 應用於大多數high-bandwidth router(高頻寬路由器)以儲存router-tables。
- Heaps：
  - 用以實現高效率的priority queues(優先權佇列)，許多作業系統用來安排工作程序。請參考：Priority Queue：Binary Heap。
- Huffman Coding Tree：
  - 例如.jpeg、.mp3等壓縮技術皆使用Huffman編碼。(在一顆20MB的硬碟要價新台幣一萬元的時代，壓縮技術就是救世主。)



---

# Binary Tree | Set 1 (Introduction)

## Why Trees?

1. to store information that naturally forms a hierarchy. For example, the file system on a computer:
2. Trees (with some ordering e.g., BST) provide moderate access/search (quicker than Linked List and slower than arrays).
3. Trees provide moderate insertion/deletion (quicker than Arrays and slower than Unordered Linked Lists).
4. Like Linked Lists and unlike Arrays, Trees don’t have an upper limit on number of nodes as nodes are linked using pointers.


Main applications of trees include:
1. Manipulate hierarchical data.
2. Make information easy to search (see tree traversal).
3. Manipulate sorted lists of data.
4. As a workflow for compositing digital images for visual effects.
5. Router algorithms
6. Form of a multi-stage decision-making (see business chess).


## First Simple Tree
Binary Tree: A tree whose elements have at most 2 children is called a binary tree. Since each element in a binary tree can have only 2 children, we typically name them the left and right child.

Summary: Tree is a hierarchical data structure. Main uses of trees include maintaining hierarchical data, providing moderate access and insert/delete operations. Binary trees are special cases of tree where every node has at most two children.

```java
/* Class containing left and right child of current node and key value*/

class Node
{
	int key;
	Node left, right;

	public Node(int item)
	{
		key = item;
		left = right = null;
	}
}


class BinaryTree
{
	// Root of Binary Tree
	Node root;

	// Constructors
	BinaryTree(int key)
	{
		root = new Node(key);
	}

	BinaryTree()
	{
		root = null;
	}


  // create a simple tree with 4 nodes
	public static void main(String[] args) {

		BinaryTree tree = new BinaryTree();

		/*create root*/
		tree.root = new Node(1);

		/* following is the tree after above statement
			1
			/ \
		null null	 */

		tree.root.left = new Node(2);
		tree.root.right = new Node(3);

		/* 2 and 3 become left and right children of 1
			1
			/ \
			2	 3
		/ \ / \
		null null null null */


		tree.root.left.left = new Node(4);
		/* 4 becomes left child of 2
					1
				/	 \
			2		 3
			/ \	 / \
			4 null null null
		/ \
		null null
		*/
	}
}
```

---


# Binary Tree | Set 2 (Properties)

1) The maximum number of nodes at level ‘l’: 2^l.
   - level is number of nodes on path from root to the node (including root and node).
   - Level of root is 0, number of nodes = 2^0 = 1


2) Maximum number of nodes in a binary tree of height ‘h’: 2^h – 1.
   - height of a tree is maximum number of nodes on root to leaf path.
   - Height of a tree with single node is considered as 1.
   - In some books, height of the root is considered as 0. In this convention, the above formula becomes 2h+1 – 1


3) In a Binary Tree with N nodes, minimum possible height or minimum number of levels: Log2(N+1)
   - This can be directly derived from point 2 above.
   - If we consider the height of a leaf node is considered as 0, then above formula for minimum possible height becomes   ? Log2(N+1) ? – 1


4) A Binary Tree with L leaves has at least: Log2L ? + 1   levels
A Binary tree has maximum number of leaves (and minimum number of levels) when all levels are fully filled. Let all leaves be at level l, then below is true for number of leaves L.

   L   <=  2l-1  [From Point 1]
   l =   ? Log2L ? + 1
   where l is the minimum number of levels.


5) In Binary tree where every node has 0 or 2 children, number of leaf nodes is always one more than nodes with two children.

---

# Binary Tree | Set 3 (Types of Binary Tree)

## Full & Complete Binary Tree
有兩類Binary Tree十分常見，分別為Full Binary Tree以及Complete Binary Tree。

1. `Perfect Binary Tree`：
   - 所有internal node都有兩個subtree(child pointer)；
   - 所有leaf node具有相同的level(或相同的height)。
   - 由以上性質能夠推論出：
     - 若leaf node之level為n，整棵樹共有 2^n − 1個node。
   - 並且，每個node與其child有以下關係：
     - 第i個node的left child之index為 2i；
     - 第i個node的right child之index為 2i+1；
     - 除了root之parent為NULL之外，第i個node的parent之index為 ⌊i/2⌋ 。
```
               18
           /       \  
         15         30  
        /  \        /  \
      40    50    100   40


               18
           /       \  
         15         30  
```

2. `Full Binary Tree`：
   - In a Full Binary Tree, number of leaf nodes is the number of internal nodes plus 1:
     - Number of leaf nodes = Number of internal nodes + 1
```
               18
           /       \  
         15         30  
        /  \        /  \
      40    50    100   40

             18
           /    \   
         15     20    
        /  \       
      40    50   
    /   \
   30   50

               18
            /     \  
          40       30  
                   /  \
                 100   40
```

3. `Complete Binary Tree`:
   - 若一棵樹的node按照Full Binary Tree的次序排列(由上至下，由左至右)，則稱此樹為Complete Binary Tree。
   - 圖四的樹共有10個node，且這十個node正好填滿Full Binary Tree的前十個位置，則此樹為Complete Binary Tree。
   - if all the levels are completely filled except possibly the last level and the last level has all keys as left as possible
```
               18
           /       \  
         15         30  
        /  \        /  \
      40    50    100   40


               18
           /       \  
         15         30  
        /  \        /  \
      40    50    100   40
     /  \   /
    8   7  9
```

圖四：這是一棵Complete Binary Tree。
![f4](https://i.imgur.com/K8LQjx1.png)

圖五：這不是一棵Complete Binary Tree。
![f5](https://i.imgur.com/ed7613u.png)


4. Balanced Binary Tree
A binary tree is balanced if the height of the tree is O(Log n) where n is the number of nodes. For Example, the AVL tree maintains O(Log n) height by making sure that the difference between the heights of the left and right subtrees is almost 1. Red-Black trees maintain O(Log n) height by making sure that the number of Black nodes on every root to leaf paths is the same and there are no adjacent red nodes. Balanced Binary Search trees are performance-wise good as they provide O(log n) time for search, insert and delete.

5. `degenerate / pathological tree`
   - A Tree where every internal node has one child. Such trees are performance-wise same as linked list.
```
      10
      /
    20
     \
     30
      \
      40     
```


---

# Handshaking Lemma and Interesting Tree Properties ??

---


# Enumeration of Binary Trees ???

A Binary Tree is `labeled` if every node is assigned a label
a Binary Tree is `unlabeled` if nodes are not assigned any label.

```
Below two are considered same unlabeled trees
    o                 o
  /   \             /   \
 o     o           o     o

Below two are considered different labeled trees
    A                C
  /   \             /  \
 B     C           A    B
```

How many different Unlabeled Binary Trees can be there with n nodes?

```
For n  = 1, there is only one tree
   o

For n  = 2, there are two possible trees
   o      o
  /        \  
 o          o

For n  = 3, there are five possible trees
    o      o           o         o      o
   /        \         /  \      /         \
  o          o       o    o     o          o
 /            \                  \        /
o              o                  o      o
```


The idea is to `consider all possible pair of counts for nodes in left and right subtrees` and multiply the counts for a particular pair. Finally add results of all pairs.

```
For example, let T(n) be count for n nodes.
T(0) = 1  [There is only 1 empty tree]
T(1) = 1
T(2) = 2

T(3) =  T(0)*T(2) + T(1)*T(1) + T(2)*T(0) = 1*2 + 1*1 + 2*1 = 5

T(4) =  T(0)*T(3) + T(1)*T(2) + T(2)*T(1) + T(3)*T(0)
     =  1*5 + 1*2 + 2*1 + 5*1
     =  14
```

The above pattern basically represents n’th Catalan Numbers. First few catalan numbers are 1 1 2 5 14 42 132 429 1430 4862,…
T(n)=\sum_{i=1}^{n}T(i-1)T(n-i)=\sum_{i=0}^{n-1}T(i)T(n-i-1)=C_n
Here,
T(i-1) represents number of nodes on the left-sub-tree
T(n−i-1) represents number of nodes on the right-sub-tree

n’th Catalan Number can also be evaluated using direct formula.



   T(n) = (2n)! / (n+1)!n!
Number of Binary Search Trees (BST) with n nodes is also same as number of unlabeled trees. The reason for this is simple, in BST also we can make any key as root, If root is i’th key in sorted order, then i-1 keys can go on one side and (n-i) keys can go on other side.

How many labeled Binary Trees can be there with n nodes?
To count labeled trees, we can use above count for unlabeled trees. The idea is simple, every unlabeled tree with n nodes can create n! different labeled trees by assigning different permutations of labels to all nodes.

Therefore,

Number of Labeled Tees = (Number of unlabeled trees) * n!
                       = [(2n)! / (n+1)!n!]  × n!
For example for n = 3, there are 5 * 3! = 5*6 = 30 different labeled trees


---

# Insertion in a Binary Tree

![binary-tree-insertion](https://i.imgur.com/xjtBYaX.png)







Deletion in a Binary Tree
BFS vs DFS for Binary Tree
Binary Tree (Array implementation)
AVL with duplicate keys
Applications of tree data structure
Applications of Minimum Spanning Tree Problem
Continuous Tree
Foldable Binary Trees
Expression Tree
Evaluation of Expression Tree
Symmetric Tree (Mirror Image of itself)
















.
