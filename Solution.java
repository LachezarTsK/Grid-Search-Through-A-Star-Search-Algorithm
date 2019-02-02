import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Solution {

	private static String[][] grid;
	private static int[][] distanceFromStart;
	private static int[][] estimatedDistanceToGoal;
	private static Node start;
	private static Node goal;

	/**
	 * Applying AStarSearch algorithm to find the shortest path from start to goal.
	 * Applied Approximation Heuristics - Manhattan Distance.
	 */
	private static boolean findPath_aStarSearchRoute(int startX, int startY, int goalX, int goalY,
			Map<Node, Node> parentMap) {

		distanceFromStart = new int[grid.length][grid.length];
		estimatedDistanceToGoal = new int[grid.length][grid.length];
		for (int i = 0; i < grid.length; i++) {
			Arrays.fill(distanceFromStart[i], Integer.MAX_VALUE);
			Arrays.fill(estimatedDistanceToGoal[i], Integer.MAX_VALUE);
		}
		distanceFromStart[startX][startY] = 0;
		boolean[][] visited = new boolean[grid.length][grid.length];

		PriorityQueue<Node> queue = new PriorityQueue<Node>();
		Node current = new Node(startX, startY, 0, Integer.MAX_VALUE);
		queue.add(current);
		start = current;

		while (!queue.isEmpty()) {
			current = queue.remove();

			if (visited[current.getX()][current.getY()] == false) {
				if (current.getX() == goalX && current.getY() == goalY) {
					goal = current;
					return true;
				}
				visited[current.getX()][current.getY()] = true;
				/**
				 * Methods movesLeft, movesRight, movesDown, movesUp:
				 * 
				 * Check for possible moves in the respective directions and adds them to the
				 * queue.
				 * 
				 * Calculates the new values for distance from start, estimated distance to goal
				 * and modifies data accordingly in the corresponding arrays.
				 *
				 * Adds the traveled paths to parentMap.
				 */
				movesRight(goalX, goalY, current, queue, visited, parentMap);
				movesLeft(goalX, goalY, current, queue, visited, parentMap);
				movesUp(goalX, goalY, current, queue, visited, parentMap);
				movesDown(goalX, goalY, current, queue, visited, parentMap);
			}
		}
		return false;
	}

	private static void movesRight(int goalX, int goalY, Node current, PriorityQueue<Node> queue, boolean[][] visited,
			Map<Node, Node> parentMap) {

		for (int i = current.getY() + 1; i < grid.length; i++) {
			if (grid[current.getX()][i].equalsIgnoreCase("X")) {
				break;
			}
			if (visited[current.getX()][i] == false) {

				int currentFromStart = distanceFromStart[current.getX()][i];
				int newFromStart = 1 + distanceFromStart[current.getX()][current.getY()];
				int estimateToGoal = Math.abs(current.getX() - goalX) + Math.abs(i - goalY);
				Node newQueueObject = new Node(current.getX(), i, newFromStart, estimateToGoal);
				queue.add(newQueueObject);
				if (currentFromStart > newFromStart) {
					parentMap.put(newQueueObject, current);
					distanceFromStart[current.getX()][i] = newFromStart;
				}
			}
		}
	}

	private static void movesLeft(int goalX, int goalY, Node current, PriorityQueue<Node> queue, boolean[][] visited,
			Map<Node, Node> parentMap) {
		for (int i = current.getY() - 1; i >= 0; i--) {
			if (grid[current.getX()][i].equalsIgnoreCase("X")) {
				break;
			}
			if (visited[current.getX()][i] == false) {

				int currentFromStart = distanceFromStart[current.getX()][i];
				int newFromStart = 1 + distanceFromStart[current.getX()][current.getY()];
				int estimateToGoal = Math.abs(current.getX() - goalX) + Math.abs(i - goalY);
				Node newQueueObject = new Node(current.getX(), i, newFromStart, estimateToGoal);
				queue.add(newQueueObject);
				if (currentFromStart > newFromStart) {
					parentMap.put(newQueueObject, current);
					distanceFromStart[current.getX()][i] = newFromStart;
				}
			}
		}
	}

	private static void movesDown(int goalX, int goalY, Node current, PriorityQueue<Node> queue, boolean[][] visited,
			Map<Node, Node> parentMap) {
		for (int i = current.getX() - 1; i >= 0; i--) {
			if (grid[i][current.getY()].equalsIgnoreCase("X")) {
				break;
			}
			if (visited[i][current.getY()] == false) {

				int currentFromStart = distanceFromStart[i][current.getY()];
				int newFromStart = 1 + distanceFromStart[current.getX()][current.getY()];
				int estimateToGoal = Math.abs(i - goalX) + Math.abs(current.getY() - goalY);
				Node newQueueObject = new Node(i, current.getY(), newFromStart, estimateToGoal);
				queue.add(newQueueObject);
				if (currentFromStart > newFromStart) {
					parentMap.put(newQueueObject, current);
					distanceFromStart[i][current.getY()] = newFromStart;
				}
			}
		}
	}

	private static void movesUp(int goalX, int goalY, Node current, PriorityQueue<Node> queue, boolean[][] visited,
			Map<Node, Node> parentMap) {
		for (int i = current.getX() + 1; i < grid.length; i++) {
			if (grid[i][current.getY()].equalsIgnoreCase("X")) {
				break;
			}
			if (visited[i][current.getY()] == false) {

				int currentFromStart = distanceFromStart[i][current.getY()];
				int newFromStart = 1 + distanceFromStart[current.getX()][current.getY()];
				int estimateToGoal = Math.abs(i - goalX) + Math.abs(current.getY() - goalY);
				Node newQueueObject = new Node(i, current.getY(), newFromStart, estimateToGoal);
				queue.add(newQueueObject);
				if (currentFromStart > newFromStart) {
					parentMap.put(newQueueObject, current);
					distanceFromStart[i][current.getY()] = newFromStart;
				}
			}
		}
	}

	private static List<Node> reconstructPath(Map<Node, Node> parentMap) {

		LinkedList<Node> shortestPath = new LinkedList<Node>();
		Node current = goal;

		while (!current.equals(start)) {
			shortestPath.addFirst(current);
			current = parentMap.get(current);
		}
		shortestPath.addFirst(start);
		return shortestPath;
	}

	public static void getSearchResults(int startX, int startY, int goalX, int goalY) {
		/**
		 * Serves as a basis to reconstruct the shortest path from start to goal.
		 */
		Map<Node, Node> parentMap = new HashMap<Node, Node>();

		boolean pathFound = findPath_aStarSearchRoute(startX, startY, goalX, goalY, parentMap);
		if (pathFound == false) {
			System.out.println("No path found!");
			return;
		}

		List<Node> shortestPath = reconstructPath(parentMap);
		Iterator<Node> path = shortestPath.iterator();
		while (path.hasNext()) {
			System.out.print(path.next() + " ");
		}
	}

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		int gridSize = scanner.nextInt();
		grid = new String[gridSize][gridSize];
		for (int i = 0; i < gridSize; i++) {
			grid[i] = scanner.next().split("");
		}
		int startX = scanner.nextInt();
		int startY = scanner.nextInt();
		int goalX = scanner.nextInt();
		int goalY = scanner.nextInt();
		scanner.close();
		getSearchResults(startX, startY, goalX, goalY);
	}
}

class Node implements Comparable<Node> {

	private int x;
	private int y;
	private int distanceFromStart;
	private int estimatedDistance;

	public Node(Integer x, Integer y, Integer distanceFromStart, Integer estimatedDistance) {
		this.x = x;
		this.y = y;
		this.distanceFromStart = distanceFromStart;
		this.estimatedDistance = estimatedDistance;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	@Override
	public int compareTo(Node object) {
		return (this.distanceFromStart + this.estimatedDistance)
				- (object.distanceFromStart + object.estimatedDistance);
	}

	public String toString() {
		return "(" + this.x + ", " + this.y + ")";
	}
}
