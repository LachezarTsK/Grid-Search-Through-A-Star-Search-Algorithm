# Grid-Search-Through-A-Star-Search-Algorithm
Searching for a path from start to goal on a grid through the application of A-Star Search Algorithm

Input Format:
- The first line contains an integer 'n', the size of a square grid.
- Each of the next 'n' lines contains a string with length 'n'.
  - Each string consists of the chars '.' or 'X' (case insensitive) or both of them.
  - Char 'X' denotes an obstacle. Movement over such a cell is not possible.
  - Char '.' denotes a path. Movement is possible only over such a cell .
- The last line contains four space-separated integers denoting:
startX startY goalX goalY

Additional Information:<br/>
In the calculation of distance from start, each straight line on the grid has a weight of 1, regardless of its actual length.
Thus, for example, a step from (0, 1) to (0, 2), or from (0, 1) to (0, 5), or from (1, 3) to (6, 3), counts each as an edge with weight of one.
<br/>Thus the algorithm searches not only for the shortest path but also for the path with least turns. However, while 
the algorithm guarantees to return the shortest path, returning the path with least turns is not always guaranteed. There are 
some cases when the obstacles are configured in a way that is beyond the capacity of the algorithm in its present form to track the path with least turns.<br/>

Constraints:<br/>
1<=n<=100<br/>
0<=startX startY goalX goalY<n<br/>

Output Format:<br/>
If there is a path from start to goal, returns the shortest route in the form of nodes' coordinates for each step of the route.
If there is no path from start to goal, returns "No path is found!".

Example One.<br/>
Input:<br/>
10<br/>
.X..XX...X<br/>
..........<br/>
.XXXXX...X<br/>
.X...X....<br/>
.X...X..X.<br/>
.X...XX...<br/>
.X...X..XX<br/>
.X...X.X..<br/>
.XXX......<br/>
...X.X..XX<br/>
9 1 9 6<br/>
Output:<br/>
(9, 1) (9, 0) (1, 0) (1, 6) (4, 6) (4, 7) (6, 7) (6, 6) (9, 6)<br/>

Example Two.<br/>
Input:<br/>
10<br/>
.X..XX...X<br/>
..........<br/>
.XXXXX...X<br/>
.X...X....<br/>
.X...X..X.<br/>
.X...XX...<br/>
.X...X..XX<br/>
.XXXXX.X..<br/>
.XXX......<br/>
...X.X..XX<br/>
9 1 4 3<br/>
Output:<br/>
No path is found! (The goal is surrounded from all sides with obstacles.)<br/>

Example Three.<br/>
Input:<br/>
10<br/>
.X..XX....<br/>
..........<br/>
.XXXXX....<br/>
.X...X....<br/>
.X...X..X.<br/>
.X...XX...<br/>
.X...X..X.<br/>
.XXXXX.X..<br/>
.XXX......<br/>
..........<br/>
9 0 0 9<br/>
Output:<br/>
(9, 0) (9, 9) (0, 9)<br/>
