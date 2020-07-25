
# solution:

[1](https://github.com/qiyuangong/leetcode)
[2](https://github.com/TheAlgorithms/Python/blob/master/DIRECTORY.md)
[3](https://github.com/csujedihy/lc-all-solutions/blob/master/001.two-sum/question.md)
[4](https://www.jianshu.com/p/a6aa07fc9f05)

https://leetcode.com/problemset/all/?difficulty=Easy
https://leetcode.com/problemset/top-100-liked-questions/?difficulty=Easy

## Problems & Solutions

| #   | Level | Title | Solution | Basic idea (One line) |
|-----| ----- | ----- | -------- | --------------------- |
| 001 | Easy  | [Two Sum](https://leetcode.com/problems/two-sum/) | 阅过放入dic，在看结果是否已在dic仲
| 007 | Easy  | [Reverse Integer][https://leetcode.com/problems/reverse-integer/] ｜ str[::-1]*-1
| 020 | Easy  | [Valid Parentheses](https://leetcode.com/problems/valid-parentheses/) | 扣除开闭和符号:1.成组的replace 2.组成dic，放入新list，成pair就扣除
| 104 | Easy  | [Maximum Depth of Binary Tree] 算🌲层数 | if 最底层 root==null, 算一层。然后看左边，然后看右边。return 1+左边右边。
| 226 | Easy  | [Invert Binary Tree] 🌲 把左右颠倒 ｜ if 最底层 root==null, 返回null，要不然左边等于右边。然后左边套算式迭代，右边套算式迭代。
| 617 | Easy  | [Merge Two Binary Trees] 加法合并两个🌲 ｜ t1 往 t2 套，如果t2为null返回t1，否则就加t1.data。然后左边套算式迭代，右边套算式迭代。
| 653 | Easy  | [Two Sum IV - Input is a BST] 找🌲里有没有数可以加成key ｜ 看key是否等于root，不等于，下层为null返回false，下层有数字则减去root继续往下算。每层迭代。
| 938 | Easy  | [Range Sum of BST] 计算🌲里在key [L,R] 范围内的和 ｜ 1.计算每个数字大小，在内部就加。 2.看数字是否在范围，然后看左边右边。左边套算式迭代，右边套算式迭代，+root大小。
