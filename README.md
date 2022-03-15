## 数据结构

**数据结构的存储方式只有两种：数组（顺序存储）和链表（链式存储）**。

**数组**由于是紧凑连续存储，可以随机访问，通过索引快速找到对应元素，而且相对节约存储空间。但正因为连续存储，内存空间必须一次性分配够，所以说数组如果要扩容，需要重新分配一块更大的空间，再把数据全部复制过去，时间复杂度 O(N)；而且你如果想在数组中间进行插入和删除，每次必须搬移后面的所有数据以保持连续，时间复杂度 O(N)。

**链表**因为元素不连续，而是靠指针指向下一个元素的位置，所以不存在数组的扩容问题；如果知道某一元素的前驱和后驱，操作指针即可删除该元素或者插入新元素，时间复杂度 O(1)。但是正因为存储空间不连续，无法根据一个索引算出对应元素的地址，所以不能随机访问；而且由于每个元素必须存储指向前后元素位置的指针，会消耗相对更多的储存空间。

数据结构的使命：**数据结构种类很多，但它们存在的目的都是在不同的应用场景，尽可能高效地增删查改**。基本操作就是增删查改，遍历方式无非**迭代**和**递归**。

数组遍历框架，典型的线性迭代结构：

```java
void traverse(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
        // 迭代访问 arr[i]
    }
}
```

链表遍历框架，兼具迭代和递归结构：

```java
/* 基本的单链表节点 */
class ListNode {
    int val;
    ListNode next;
}

// 迭代方式
void traverse(ListNode head) {
    for (ListNode p = head; p != null; p = p.next) {
        // 迭代访问 p.val
    }
}

// 递归方式
void traverse(ListNode head) {
    // 递归访问 head.val
    traverse(head.next);
}
```



### 刷题指南

先刷二叉树，因为二叉树是最容易培养框架思维的，而且大部分算法技巧，本质上都是树的遍历问题。

二叉树都是基于递归框架的，核心的思路是**明确当前节点需要做的事情是什么，站在当前节点的视角，需要做哪些操作**，然后套二叉树前序，中序，后续递归框架就行了

几乎所有二叉树的题目都是一套框架就可以出来：

```java
void traverse(TreeNode root) {
    // todo 前序遍历
    traverse(root.left)
    // todo 中序遍历
    traverse(root.right)
    // todo 后序遍历
}
```



## 基础类题目

1. 排序算法

   - **冒泡**

   ```java
   /**
    * 冒泡排序：比较相邻的元素。如果第一个比第二个大，就交换他们两个。
    * 对每一对相邻元素做相同工作，从开始第一对到结尾的最后一对。在这一点，最后的元素应该会是最大的数。
    * 针对所有的元素重复以上的步骤，除了最后一个。
    * 持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
    */
   public static void bubbleSort(int[] numbers) {
       // 外层循环最后一次是两两比较，少循环一次，所以遍历次数是length - 1
       int size = numbers.length - 1;
       for(int i = 0 ; i < size; i ++) {
           // 最大的已在之前冒出，所以内循环只要遍历length - 1 - i
           for(int j = 0 ;j < size - i ; j++) {
               if(numbers[j] > numbers[j+1]) {
                   // 交换两数位置
                   int temp = numbers[j];
                   numbers[j] = numbers[j+1];
                   numbers[j+1] = temp;
               }
           }
       }
   }
   ```

   - **快排**

   ```java
   /**
    * 先找到一个中间数将数组切割为两部分，左部分比该数小，右部分比该数大，然后对两个部分再进行递归。
    * 查找出中轴（默认是最低位low）的在numbers数组排序后所在位置
    * @param low 0
    * @param high length -1
    */
   public void quickSort(int[] numbers,int low,int high) {
       if (low < high) {
           int i = low, j = high, x = numbers[low]; // 数组的第一个作为中轴
           while (i < j) {
               while(i < j && numbers[j] >= x){
                   // 从右向左找第一个小于x的数
                   j--;
               }
               //比中轴小的记录移到低端
               if(i < j){
                   numbers[i++] = numbers[j];
               }
               while(i < j && numbers[i] < x){
                   // 从左向右找第一个大于等于x的数
                   i++;
               }
               //比中轴大的记录移到高端
               if(i < j){
                   numbers[j--] = numbers[i];
               }
           }
           
           numbers[i] = x; //中轴记录到尾, 此时i为中轴的位置
           quickSort(numbers, low, i - 1); // 递归调用
           quickSort(numbers, i + 1, high);
       }
   }
   ```

2. 二叉树遍历（非递归方式）

   ```java
   //      1
   //     / \
   //    2   3
   //   /     \
   //  4       5
   //   \
   //    6
   //   / \
   //  7   8
   
   //1 2 4 6 7 8 3 5
   public static void prePrint(Node root) {
       if(root == null) {
           return;
       }
       Stack<Node> stack = new Stack<Node>();
       Node node = root;
       while (node != null || !stack.isEmpty()) {
           if(node != null) {
               System.out.print(node.val + " ");
               // 节点入栈是为了找右孩子
               stack.push(node);
               node = node.left;
           }else {
               // 找到右孩子，该节点失去作用出栈
               node = stack.pop().right;
           }
       }
   }
   
   //4 7 6 8 2 1 3 5
   public static void inPrint(Node root) {
       if(root == null) {
           return;
       }
       Stack<Node> stack = new Stack<Node>();
       Node node = root;
       while (node != null || !stack.isEmpty()) {
           if(node != null) {
               stack.push(node);
               node = node.left;
           }else {
               node = stack.pop();
               System.out.print(node.val + " ");
               node = node.right;
           }
       }
   }
   
   //7 8 6 4 2 5 3 1
   public static void postPrint(Node root) {
       if(root == null) {
           return;
       }
       Stack<Node> stack = new Stack<Node>();
       Node node = root;
       // 作用是记录上一次访问打印的节点
       Node pre = null;
       while (node != null || !stack.isEmpty()) {
           if(node != null) {
               stack.push(node);
               node = node.left;
           }else {
               // 已经访问到最左的节点了，这里是peek，因为需要首先去访问右节点
               node = stack.peek();
               // 如果当前节点的右孩子为空，或等于上一次访问打印的节点，则当前节点也可以出栈打印了
               if (node.right == null || node.right == pre) {
                   // 没有右孩子了，可以直接出栈打印了
                   node = stack.pop();
                   System.out.print(node.val + " ");
                   pre = node;
                   node = null;
               }else {
                   node = node.right;
               }
   
           }
       }
   }
   ```



## 解题套路框架

> 递归条件：
>
> 1. 大问题 --> 拆成两个子问题
> 2. 子问题求解方式和大问题一样
> 3. 存在最小子问题



### 窗口滑动

[最小覆盖子串](https://leetcode-cn.com/problems/minimum-window-substring/)

窗口滑动就是通过左右指针维护一个窗口，不断滑动，然后更新答案，<font color="red">主要解决子串匹配类问题</font>。

```java
// 两个窗口
// 1. 记录窗口中的符合条件的字符
Map<Character, Integer> window = new HashMap<>();
// 2. 目标需要的字符以及需要的对应的数量
Map<Character, Integer> need = new HashMap<>();
// 窗口指针
int left = 0, right = 0;

while (right < s.size()) {
    // c 是将移入窗口的字符
    char c = s[right];
  	// 右窗口移动
    right++;
    // 进行窗口内数据的一系列更新
    window.add(s[right]);
    ...
		
		// 判断左侧窗口是否要收缩
    while (window needs shrink) {
        // d 是将移出窗口的字符
        char d = s[left];
        // 左窗口移动
        left++;
        // 进行窗口内数据的一系列更新
        ...
    }
}
```

窗口滑动的思路：

1. 在字符串`S`中使用双指针中的左右指针技巧，初始化`left = right = 0`，把索引左闭右开区间`[left, right)`称为一个「窗口」
2. **不断增加`right`指针扩大窗口`[left, right)`，直到窗口中的字符串符合要求**。<font color="red">当移动`right`扩大窗口，即加入字符时，考虑应该更新哪些数据</font> -- 相当于在寻找一个「可行解」
3. 此时，停止增加`right`，转而**不断增加`left`指针缩小窗口`[left, right)`，直到窗口中的字符串不再符合要求**。同理，<font color="red">每次增加`left`，即移出字符时，考虑应该更新哪些数据</font> -- 在优化这个「可行解」
4. 考虑要的结果应该在扩大窗口时还是缩小窗口时进行更新



### 回溯算法（DFS）

[字符串的全排列](https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof/)

回溯算法就是个多叉树的遍历问题，关键就是在前序遍历和后序遍历的位置做一些操作，算法框架如下：

```python
result = []
def backtrack(路径, 选择列表):
  	# 到达决策树底层
    if 满足结束条件:
        result.add(路径)
        return

    for 选择 in 选择列表:
        做选择
        backtrack(路径, 选择列表)
        撤销选择
```

**写 `backtrack` 函数时，需要维护走过的「路径」和当前可以做的「选择列表」，当触发「结束条件」时，将「路径」记入结果集**。

解决一个回溯问题，实际上就是一个决策树的遍历过程，只需要思考 3 个问题：

1. 路径：也就是已经做出的选择。
2. 选择列表：也就是当前可以做的选择。
3. 结束条件：也就是到达决策树底层，无法再做选择的条件。

**其核心就是 for 循环里面的递归，在递归调用之前「做选择」，在递归调用之后「撤销选择」**。

回溯时间复杂度都不可能低于 `O(N!)`，因为穷举整棵决策树是无法避免的。**这也是回溯算法的一个特点，不像动态规划存在重叠子问题可以优化，回溯算法就是纯暴力穷举，复杂度一般都很高**。



### BFS

[二叉树的深度](https://leetcode-cn.com/problems/er-cha-shu-de-shen-du-lcof/)

BFS 的核心思想不难理解，就是把一些问题抽象成图，从一个点开始，向四周开始扩散。一般来说，写 BFS 算法都是用`队列`这种数据结构，每次将一个节点周围的所有节点加入队列。

```java
// 计算从起点 start 到终点 target 的最近距离
int BFS(Node start, Node target) {
  // 1. 核心数据结构，创建队列
  Queue<Node> queue;
  // 避免走回头路
  Set<Node> visited;

  // 2. 初始化放入起点
  queue.offer(start);
  visited.add(start);
  // 记录扩散的步数
  int step = 0;

  // 3. 循环判断队列不为空
  while (!queue.isEmpty()) {
    /* 将当前队列中的所有节点向四周扩散 */
    for (int i = 0; i < queue.size(); i++) {
      Node cur = queue.poll();
      /* 划重点：这里判断是否到达终点 */
      // 4. 取出之前队列里的数据进行处理判断
      if (cur == target) {
        return step;
      }
      /* 将 cur 的相邻节点加入队列 */
      if (!visited.contains(cur)) {
        // 5. 放入下一层的节点
        queue.offer(cur.next);
        visited.add(cur);
      }

    }
    step++;
  }

  return step;
}
```

BFS 相对 DFS 的最主要的区别是：**BFS 找到的路径一定是最短的，但代价就是空间复杂度比 DFS 大很多**。

**1、为什么 BFS 可以找到最短距离，DFS 不行吗**？

首先，BFS 的逻辑，`depth` 每增加一次，队列中的所有节点都向前迈一步，这保证了第一次到达终点的时候，走的步数是最少的。

DFS 也可以找最短路径，但是时间复杂度相对高很多。DFS 实际上是靠递归的堆栈记录走过的路径，要找到最短路径，得把二叉树中所有树杈都探索完才能对比出最短的路径有多长。而 BFS 借助队列做到一次一步「齐头并进」，是可以在不遍历完整棵树的条件下找到最短距离的。

**2、既然 BFS 那么好，为啥 DFS 还要存在**？

BFS 可以找到最短距离，但是空间复杂度高，而 DFS 的空间复杂度较低。

假设二叉树是满二叉树，节点数为 `N`，对于 DFS 算法来说，空间复杂度无非就是递归堆栈，最坏情况下顶多就是树的高度，也就是 `O(logN)`。

但是 BFS 算法，队列中每次都会储存着二叉树一层的节点，这样的话最坏情况下空间复杂度应该是树的最底层节点的数量，也就是 `N/2`，空间复杂度是 `O(N)`。

由此观之，BFS 还是有代价的，一般来说在找最短路径的时候使用 BFS，其他时候还是 DFS 使用得多一些（主要是递归代码好写）。



### 动态规划(一般求最值）

递归树（或者说图），是从上向下延伸，都是从一个规模较大的原问题比如说 `f(20)`，向下逐渐分解规模，直到 `f(1)` 和 `f(2)` 这两个 base case，然后逐层返回答案，这就叫「自顶向下」。

反过来，直接从最底下最简单，问题规模最小的 `f(1)` 和 `f(2)` 开始往上推，直到推到想要的答案 `f(20)`，这就是动态规划的思路，这也是为什么动态规划一般都脱离了递归，而是由循环迭代完成计算。

「状态转移方程」的重要性，它是解决问题的核心。而且很容易发现，其实状态转移方程直接代表着暴力解法。

**千万不要看不起暴力解，动态规划问题最困难的就是写出这个暴力解，即状态转移方程**。只要写出暴力解，优化方法无非是用备忘录或者 DP table，再无奥妙可言。

**动态规划问题的一般形式就是求最值**，动态规划三要素：

1. 重叠子问题

   动态规划的穷举有点特别，因为这类问题**存在「重叠子问题」**，如果暴力穷举的话效率会极其低下，所以需要「备忘录」或者「DP table」来优化穷举过程，避免不必要的计算。

2. 最优子结构

   动态规划问题一定会**具备「最优子结构」**，才能通过子问题的最值得到原问题的最值。

3. 状态转移方程

   虽然动态规划的核心思想就是穷举求最值，但是问题可以千变万化，穷举所有可行解其实并不是一件容易的事，只有列出**正确的「状态转移方程」**，才能正确地穷举。

状态转移方程思维框架：

**明确 base case -> 明确「状态」-> 明确「选择」 -> 定义 dp 数组/函数的含义**

```python
# 初始化 base case
dp[0][0][...] = base

# 穷举状态
# 状态就是在状态转移过程中变化的量
# dp数组中，状态就是索引；递归函数中，状态就是参数
for 状态1 in 状态1的所有取值：
    for 状态2 in 状态2的所有取值：
        for ...
    				# 进行状态转移
            dp[状态1][状态2][...] = 求最值(选择1，选择2...)
```

进一步优化，可以考虑降低空间复杂度，即所谓的「**状态压缩**」，如果发现每次状态转移只需要 DP table 中的一部分，那么可以尝试用状态压缩来缩小 DP table 的大小，只记录必要的数据。

1. 背包问题

   [0-1背包](https://www.bilibili.com/video/BV15B4y1P7X7/)

   [正则表达式匹配](https://leetcode-cn.com/problems/zheng-ze-biao-da-shi-pi-pei-lcof/)

2. 子序列（不连续的序列）

   - 一维的 dp 数组

     $dp_{[i]} = 最值(dp_{[i]}, dp_{[j]} + ...)$

     在子数组`array[0..i]`中，以`array[i]`结尾的目标子序列（[最长递增子序列](https://leetcode-cn.com/problems/longest-increasing-subsequence/)）的长度是`dp[i]`

   - 二维的 dp 数组

     - 涉及两个字符串/数组时（[最长公共子序列](https://leetcode-cn.com/problems/longest-common-subsequence/)），dp 数组的含义如下：

       在子数组`arr1[0..i]`和子数组`arr2[0..j]`中，要求的公共子序列长度为`dp[i][j]`

     - 只涉及一个字符串/数组时（[最长回文子序列](https://leetcode-cn.com/problems/longest-palindromic-subsequence/)），dp 数组的含义如下：

       在子数组`array[i..j]`中，要求的最长子序列的长度为`dp[i][j]`。



### 贪心算法

贪心算法可以认为是动态规划算法的一个特例，相比动态规划，使用贪心算法需要满足更多的条件（贪心选择性质），但是效率比动态规划要高。

贪心选择性质简单说就是：每一步都做出一个局部最优的选择，最终的结果就是全局最优。

- 区间类问题：**区间问题肯定是按照区间的起点或者终点进行排序**（至于到底如何排序，这个就要因题而异），然后每次选择区间最早开始或最早结束的情况，最终产生最优结果
  - [无重叠区间](https://leetcode-cn.com/problems/non-overlapping-intervals/)
  - [视频拼接](https://leetcode-cn.com/problems/video-stitching)
- 跳跃游戏：跳跃游戏就相当于一个将起点排序的区间问题
  - [跳跃游戏 II](https://leetcode-cn.com/problems/jump-game-ii)



### 分治法

[为运算表达式设计优先级](https://leetcode-cn.com/problems/different-ways-to-add-parentheses/)

[数组中的逆序对](https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof/)

将原问题分解成小规模的子问题，然后根据子问题的结果构造出原问题的答案。**分治算法也需要满足一些条件，原问题结果可以通过合并子问题结果来计算**。

```java
void sort(int[] nums, int lo, int hi) {
    int mid = (lo + hi) / 2;
    /****** 分 ******/
    // 对数组的两部分分别排序
    sort(nums, lo, mid);
    sort(nums, mid + 1, hi);
  
    /****** 治 ******/
    // 合并两个排好序的子数组
    merge(nums, lo, mid, hi);
}

```

**先「分」后「治」，先按照运算符将原问题拆解成多个子问题，然后通过子问题的结果来合成原问题的结果**。



## 数据结构类题目

### LinkedList

- 面试题06-从尾到头打印链表

  先进后出，用栈或递归

- 面试题22-链表中倒数第k个结点

  获取链表元素，考虑使用指针

- 面试题24-反转链表

  双指针，递归

- 面试题25-合并两个排序的链表

  指针+伪节点，递归

- 面试题35-复杂链表的复制

  深拷贝（借助Hash表，拼接拆分思想）

- 面试题52-两个链表的第一个公共节点

  双指针（类似快慢指针，两个走过长度终有相等的时候）

- 面试题18-删除链表的节点

  双指针(记录前一节点和当前节点，准备删)



### Stack & Queue

- 面试题09-用两个栈实现队列

  双栈，插入栈和删除栈

- 面试题30-包含min函数的栈

  双栈，辅助栈存最小值

- 面试题31-栈的压入、弹出序列

  模拟栈的压入弹出

- 面试题58-1-翻转单词顺序

  双指针

- 面试题59-1-滑动窗口的最大值

  双循环，内层循环是窗口，需要考虑未形成窗口的情况



### Heap

- 面试题40-最小的K个数

  法一：借助快排思想，k位之前的数都arr[k]小，k位之后的数都arr[k]大

  法二：堆思想



### Hash Table

- 面试题50-第一个只出现一次的字符

  采用HashMap，统计每个字符出现次数

  map遍历：

  1. map.entrySet()
  2. map.keySet() / map.values() // 此方法通过键找值遍历，实际上它相当慢且无效率。因为从键取值是耗时的操作
  3. map.entrySet().iterator();



### Tree

- 面试题07-重建二叉树

  1. 法一：递归

     前序遍历性质： 节点按照 `[ 根节点, [左子树的前序遍历结果], [右子树的前序遍历结果] ]` 排序。
     中序遍历性质： 节点按照 `[ [左子树的中序遍历结果], 根节点, [右子树的中序遍历结果] ]` 排序。

  2. 法二：迭代

- 面试题26-树的子结构(结构相同，值相同)

  递归：先匹配到根节点，再匹配子节点

- 面试题27-二叉树的镜像

  递归、迭代（用栈把下次要处理的节点存起来，每次从栈里取节点进行处理）

- 面试题32-1 -从上往下打印二叉树

  递归、迭代（顺序处理，所以用队列把下次要处理的节点存起来，每次从队列里取节点进行处理）

- 面试题32-2 -从上往下打印二叉树 2

- 面试题32-3 -从上往下打印二叉树 3

  考虑双端队列

- 面试题33-二叉搜索树的后序遍历序列

  递归（数组选子数组，考虑用指针）

- 面试题34-二叉树中和为某一值的路径

  递归

- 面试题36-二叉搜索树与双向链表

  中序遍历，递归

- 面试题55-1-二叉树的深度

  层序遍历，递归

- 面试题55-2-平衡二叉树

- 面试题28-对称的二叉树

  层遍历 + 双端队列;递归

- 面试题37-序列化二叉树

  DFS,BFS

- 面试题54-二叉搜索树的第k大节点

  中序遍历倒序



### 图

- 面试题12-矩阵中的路径(DFS)

- 面试题13-机器人的运动范围(BFS)





## 具体算法类题目

### 斐波那契数列

- 面试题10-1-斐波拉契数列

- 面试题10-2-青蛙跳台阶问题



### 搜索算法

- 面试题04-二维数组中的查找

- 面试题11-旋转数组的最小数字

  二分查找

- 面试题56-1-数组中数字出现的次数

  位运算

- 面试题56-2-数组中数字出现的次数

  位运算



### 全排列

- 面试题38-字符串的排列

  回溯算法



### 动态规划

- 面试题42-连续子数组的最大和
- 面试题19-正则表达式匹配

很多动态规划问题就是在遍历一棵树，如果对树的遍历操作烂熟于心，起码知道怎么把思路转化成代码，也知道如何提取别人解法的核心思路。



### 回溯

- 面试题12-矩阵中的路径(BFS)
- 面试题13-机器人的运动范围(DFS)
  前文回溯算法详解干脆直接说了，回溯算法就是个 N 叉树的前后序遍历问题，没有例外



### 排序

- 面试题51-数组中的逆序对(归并排序)
- 面试题40-最小的K个数(堆排序)



### 位运算

- 面试题15-二进制中1的个数

- 面试题16-数值的整数次方



### 其他算法

- 面试题05-替换空格

- 面试题21-调整数组顺序使奇数位于偶数前面

- 面试题39-数组中出现次数超过一半的数字

- 面试题43- 1～n整数中1出现的次数

- 面试题45-把数组排成最小的数

- 面试题49-丑数

- 面试题57-2-和为S的连续正数序列(滑动窗口思想)

- 面试题57-和为S的两个数字(双指针思想)

- 面试题58-2-左旋转字符串(矩阵翻转)

- 面试题62-圆圈中最后剩下的数(约瑟夫环)

  此题难点在于分析出，n与n-1有关系，更不用说推导出状态转移方程，如果能分析出此步，可以发现是个动态问题

- 面试题66-构建乘积数组




## 其他算法题目

1. 检测数组中重复元素

   ```java
   public boolean checkDuplicateUsingAdd(String[] input) {
        Set tempSet = new HashSet();
        for (String str : input) {
          if (!tempSet.add(str)) {
              // 需要重复元素，可返回str
            return true;
          }
        }
        return false;
   }
   ```

2. 反转字符串

   ```java
   // 1.利用StringBuilder
   String reverseStr = new StringBuffer(str).reverse().toString();
   
   // 2.递归
   public static String reverseRecursively(String str) {
   
     //base case to handle one char string and empty string
     if (str.length() < 2) {
       return str;
     }
   
     return reverseRecursively(str.substring(1)) + str.charAt(0);
   
   }
   
   // 3.数字反转
   private static int reverse(int number){
     int reverse = 0;
   
     while(number != 0){
       reverse = reverse*10 + number%10; 
       number = number/10;
     }
   
     return reverse;
   }
   ```

3. 怎么检查一个字符串只包含数字？ — 利用正则表达式

4. 怎么打印出一个字符串的所有排列？ — 回溯

   ```java
   /**
    * 穷举所有可能情况，典型回溯算法
    */
   public String[] permutation(String s) {
       StringBuilder tmp = new StringBuilder();
       Set<Integer> index = new HashSet<>();
       permutation(s, tmp, index);
       return result.toArray(new String[result.size()]);
   }
   
   private void permutation(String s, StringBuilder tmp, Set<Integer> index) {
       // 1. 到达决策树底层，满足条件退出条件
       if (tmp.length() == s.length()) {
           result.add(String.valueOf(tmp));
           return;
       }
       for (int i = 0; i < s.length(); i++) {
           if (index.contains(i)) {
               continue;
           }
           // 2. 做选择
           index.add(i);
           tmp.append(s.charAt(i));
           // 3. 递归
           permutation(s, tmp, index);
           // 4. 撤销选择回退
           tmp.deleteCharAt(tmp.length() - 1);
           index.remove(i);
       }
   }
   ```

5. 怎样才能打印出数组中的重复元素？

   ```java
   public static void findDupicateInArray(int[] a) {
       for (int j = 0; j < a.length; j++) {
         	int count = 0;
           for (int k = j + 1; k < a.length; k++) {
               if (a[j] == a[k]) {
                   count++;
               }
           }
           if (count == 1) System.out.println("重复元素 : " + a[j]);
           count = 0;
       }
   }
   ```

6. 在没有使用临时变量的情况如何交换两个整数变量的值？

   ```java
   int a=10; // a = 1010
   int b=12; // b = 1100
   // 异或运算能够使数据中的某些位翻转，其他位不变。这就意味着任意一个数与任意一个给定的值连续异或两次，值不变。即：a^b^b=a
   a=a^b; // 得到翻转值
   b=a^b; // 相当于a^b^b，得到之前a的值
   a=a^b; // 此时b为之前a，相当于a^b^a，得到之前b的值
   System.out.println(a + "--" + b);
   ```



