/* SudokuSolver
 * Andrew Jackson
*/

public class SudokuSolver {

	private int[] board = new int[9*9];
	
	public SudokuSolver(int[] board) {
		this.board = board;
	}
	
	// solve the puzzle
	public void solve() {
		int[] coord;
		coord = findEmpty(board);
		printPuzzle(board);
		if (recurse(board, 0, coord) == false) {
			System.out.println("No valid board found");
		} else {
			System.out.println("Solved:");
			printPuzzle(board);
		}	
	}
	
	// recursively add values to board until it there are no more empty coords
	// or the board is unsolvable
	public boolean recurse(int[] board, int value, int[] coords) {
		int row = coords[0];
		int col = coords[1];
		
		do {
			// immediately increment value when it goes through loop
			value++;
			// check if inserted value is valid
			// if not then keep incrementing until valid
			while (checkValid(board, value, row, col) == false) {
				value++;		
			}

			// if the value exceeds 9 then the option is not valid and therefore return false
			// set the current board value to 0
			if (value > 9) {
				board[col + row*9] = 0;
				return false;
			} 
			
			// set the board value to the valid value
			board[col + row*9] = value;	
			// find the coords of an empty board space
			coords = findEmpty(board);

			// if there is no more available coords then return true
			if (coords[0] == -1) {
				return true;
			}
		} while (recurse(board, 0, coords) == false);
		return true;
	}
	
	// find an empty spot on the board and return the coords
	public int[] findEmpty(int[] board) {
		
		int[] coord = new int[2];
		coord[0] = -1;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if ((board[j + i*9]) == 0) {
					coord[0] = i;
					coord[1] = j;
					return coord;
				}
			}
		}
		
		return coord;
	}

	// check if the the value inserted at the coords is valid
	public boolean checkValid(int[] board, int value, int row, int col) {
		
		// check if valid for row
		for (int j = 0; j < 9; j++) {
			if ((board[j + row*9]) == value) return false;
		}

		// check if valid for col
		for (int i = 0; i < 9; i++) {
			if ((board[col + i*9]) == value) return false;
		}
		
		// check if valid for block
		int startRow = (row/3) * 3;
		int startCol = (col/3) * 3;

		for (int i = startRow; i < startRow + 3; i++) {
			for (int j = startCol; j < startCol + 3; j++) {
				if ((board[j + i*9]) == value) return false;
			}
		}
		
		// all valid so return true
		return true;
	}
	
	// print the board
	public void printPuzzle(int[] board) {
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.printf(" %s ", board[j + i*9]);
			}
			System.out.println();
		}
		System.out.println();
		
	}
	
	public static void main(String[] args) {
		int[] test_board =  {3,4,0,0,9,0,0,8,1,
							 9,6,0,0,0,0,0,5,4,
							 0,0,8,0,0,0,9,0,0,
							 0,0,0,8,0,9,0,0,0,
							 5,0,0,0,3,0,0,0,2,
							 0,0,0,7,0,2,0,0,0,
							 0,0,6,0,0,0,1,0,0,
							 8,5,0,0,0,0,0,3,6,
							 7,3,0,0,4,0,0,9,8};
	
		SudokuSolver sudoku = new SudokuSolver(test_board);
		sudoku.solve();
	}
	
}
