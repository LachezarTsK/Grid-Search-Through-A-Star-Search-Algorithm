# Grid-Search-Through-A-Star-Search-Algorithm
Searching for a path from start to goal on a grid through the application of A-Star Search Algorithm

Input Format:
- The first line contains an integer 'n', the size of the grid.
- Each of the next 'n' lines contains a string with length 'n'.
  - Each string consists of the chars '.' or 'X' (case insensitive) or both of them.
  - Char 'X' denotes an obstacle. Movement over such a cell is not possible.
  - Char '.' denotes a path. Movement is possible only over such a cell .
- The last line contains four space-separated integers denoting:
startX startY goalX goalY

Additional information:

Each straight line on the grid has a weight of 1.
Thus, for example, a step from (0, 1) to (0, 2), or from (0, 1) to (0, 5), or from (3, 1) to (3, 6) counts as an edge with weight of one.

Constraints:

1<=n<=100
0<=startX startY goalX goalY<n

Output Format:

If there is a path from start to goal, returns the shortest route in the form of nodes' coordinates for each step of the route.
If there is no path from start to goal, returns "No path is found!".

Example One.
Input:
10
.X..XX...X
..........
.XXXXX...X
.X...X....
.X...X..X.
.X...XX...
.X...X..XX
.X...X.X..
.XXX......
...X.X..XX
9 1 9 6
Output:
(9, 1) (9, 0) (1, 0) (1, 6) (4, 6) (4, 7) (6, 7) (6, 6) (9, 6)

Example Two.
Input:
10
.X..XX...X
..........
.XXXXX...X
.X...X....
.X...X..X.
.X...XX...
.X...X..XX
.XXXXX.X..
.XXX......
...X.X..XX
9 1 4 3
Output:
No path found!  (The goal is surrounded from all sides with obstacles.)

Example Three.
Input:
10
.X..XX....
..........
.XXXXX....
.X...X....
.X...X..X.
.X...XX...
.X...X..X.
.XXXXX.X..
.XXX......
..........
9 0 0 9
Output:
(9, 0) (9, 9) (0, 9)
