package com.exam.sweepmine;

/**
 * Implementation of MineSweeper interface.
 * 
 * A mine-field of N x M squares is represented by N lines of exactly M
 * characters each.
 * 
 * The character '*' represents a mine * and the character '.' represents
 * no-mine.
 * 
 * Lines are separated by '\n'
 * 
 *
 * 
 * Example mine-field string (as input to setMineField()): "*...\n..*.\n...."
 * 
 * (a 3 x 4 mine-field of 12 squares, 2 of which are mines)
 * 
 *
 * 
 * getHintField() produces a hint-field of identical dimensions as the
 * mineFiled() where each
 * 
 * square is a * for a mine or the number of adjacent mine-squares if the square
 * does not contain a mine.
 * 
 *
 * 
 * Example hint-field string (as returned by getHintField(): "*211\n12*1\n0111"
 * (for the above input)
 * 
 *
 */
public class MineSweeperImpl implements MineSweeper {
	/**
	 * Add 2 lines and 2 columns buffer to the mine arrays
	 */
	private int line, column;
	private int[][] mine;
	private String hintString;

	/**
	 * Initializes the mine-field
	 * 
	 * @param mineField
	 *            string containing the mines.A mine-field of N x M squares is
	 *            represented by N lines of exactly M characters each. The
	 *            character '*' represents a mine * and the character '.'
	 *            represents no-mine. Lines are separated by '\n' Example
	 *            mine-field string (as input to setMineField()):
	 *            "*...\n..*.\n...."
	 * @throws IllegalArgumentException
	 *             if mineField is not properly formatted
	 */
	public void setMineField(String mineField) throws IllegalArgumentException {
		// Add '\n' to the mineField
		String endMineField = mineField.concat("\n");

		// Get the lines and columns

		column = endMineField.indexOf('\n');
		int len = endMineField.length();
		if (len == 1) {
			IllegalArgumentException e = new IllegalArgumentException(
					"Null input string!");
			throw e;
		}
		if (column == 0) {
			IllegalArgumentException e = new IllegalArgumentException(
					"Null column string!");
			throw e;
		}

		line = 0;
		int index = endMineField.indexOf('\n');
		while (index != -1) {
			line++;
			index = endMineField.indexOf('\n', index + 2);
		}

		mine = new int[line + 2][column + 2];
		index = 0;
		for (int i = 1; i <= line; i++) {
			for (int j = 1; j <= column; j++) {
				if (endMineField.charAt(index) == '*') {
					mine[i][j] = 1;
				} else {
					if (endMineField.charAt(index) != '.') {
						IllegalArgumentException e = new IllegalArgumentException(
								"Illegal char " + endMineField.charAt(index)
										+ " inside the input string!");
						throw e;
					}
				}
				index++;
			}

			if (endMineField.charAt(index) != '\n') {
				IllegalArgumentException e = new IllegalArgumentException(
						"Illegal char " + endMineField.charAt(index)
								+ " at the column end!");
				throw e;
			}
			index++;
		}

	}

	/**
	 * 
	 * Produces a hint-field for the current mine-filed. reduces a hint-field of
	 * identical dimensions as the mineFiled() where each square is a * for a
	 * mine or the number of adjacent mine-squares if the square does not
	 * contain a mine.
	 * 
	 * Example hint-field string (as returned by getHintField():
	 * "*211\n12*1\n0111" (for the above input)
	 *
	 * 
	 * @return a string representation of the hint-field
	 * 
	 * @throws IllegalStateException
	 *             if the mine-field has not been initialized (i.e.
	 *             setMineField() has not been called)
	 */
	public String getHintField() throws IllegalStateException {
		StringBuffer result = new StringBuffer();
		for (int i = 1; i <= line; i++) {
			for (int j = 1; j <= column; j++) {
				if (mine[i][j] != 0) {
					result.append("*");
				} else {
					int round = mine[i - 1][j - 1] + mine[i - 1][j]
							+ mine[i - 1][j + 1] + mine[i][j - 1]
							+ mine[i][j + 1] + mine[i + 1][j - 1]
							+ mine[i + 1][j] + mine[i + 1][j + 1];
					result.append(round);
				}
			}
			if (i != line) {
				result.append('\n');
			}
		}

		hintString = result.toString();
		return hintString;
	}
	
	public static void main(String[] args){
	    MineSweeper ms = new MineSweeperImpl();
//	    ms.setMineField("*");
//	    System.out.println(ms.getHintField());
//	    
	    ms.setMineField("*...\n..*.\n....");
	    System.out.println("[" + ms.getHintField() + "]");
//
	       ms.setMineField("*...");
	        System.out.println("[" + ms.getHintField() + "]");
	        
//	    ms.setMineField("\n");
//	    System.out.println(ms.getHintField());
	    
//	    ms.setMineField("*...\n.*.\n....");
//        System.out.println(ms.getHintField());
	    
//	     ms.setMineField("*...\n..*..\n....");
//      System.out.println(ms.getHintField());
	    
	    ms.setMineField("*...\n");
        System.out.println("[" + ms.getHintField() + "]");
	    
	}

}
