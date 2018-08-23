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
public class MineSweeperImplNew implements MineSweeper {
    /**
     * An element with value 1 in this array represents a mine,
     * note that a "virtual border" is added around the actual mine field to make the computation of hint string easier.
     */
    private int[][] mine;

    private boolean mineFieldInitialized = false;

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
    	mineFieldInitialized = false;

    	if(mineField == null || mineField.equals("") || mineField.startsWith("\n")){
    		throw new IllegalArgumentException("The mine field string is not in the right format");
    	}

    	if(!mineField.matches("[\\.\\*\\n]+")){
    		throw new IllegalArgumentException("The mine field string can only contain character ., *, or \\n");
    	}

    	//OK, the mineField string passed the initial check,
    	//let's now do further check and determine the lines and columns of the mine field.
    	if(!mineField.endsWith("\n")){ //we tolerate the case that \n exists at the end of the final line
    		mineField = mineField + "\n";
    	}

    	int mineColumn = mineField.indexOf("\n");
    	int mineRow = mineField.length() - mineField.replace("\n", "").length();
    	mine = new int[mineRow + 2][mineColumn + 2]; //add the virtual border to the actual mine field
    	for(int i = 0; i < mine.length; i++){
    		for(int j = 0; j < mine[i].length; j++){
    			mine[i][j] = 0;
    		}
    	}

    	int curRowBeginAt = 0;
    	int curRowEndAt = mineField.indexOf("\n");
    	String curRowStr;
    	int curRow = 1;

    	while(curRowEndAt != -1){
    		curRowStr = mineField.substring(curRowBeginAt, curRowEndAt);
    		if(curRowStr.length() != mineColumn){
    			throw new IllegalArgumentException("The mine field string is not in the right format");
    		}

    		for(int i = 0; i < curRowStr.length(); i++){
    			if(curRowStr.charAt(i) == '*'){
    				mine[curRow][i+1] = 1;
    			}
    		}
    		curRow++;

    		curRowBeginAt = curRowEndAt + 1;
    		curRowEndAt = mineField.indexOf("\n", curRowBeginAt);
    	}

        mineFieldInitialized = true;
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
    	if(!mineFieldInitialized){
    		throw new IllegalStateException("The mine-field has not been properly initialized.");
    	}

        StringBuffer result = new StringBuffer();
        for (int i = 1; i <= mine.length - 2; i++) {
            for (int j = 1; j <= mine[i].length - 2; j++) {
                if (mine[i][j] == 1) {
                    result.append("*");
                } else {
                    int round = mine[i - 1][j - 1] + mine[i - 1][j] + mine[i - 1][j + 1] //neighbour on the top
                    		  + mine[i][j - 1] + mine[i][j + 1]                          //neighbour in the middle
                    	      + mine[i + 1][j - 1] + mine[i + 1][j] + mine[i + 1][j + 1];//neighbour at the bottom
                    result.append(round);
                }
            }

            result.append('\n');
        }

        return result.substring(0, result.length() - 1); //remove the last \n
    }

	public static void main(String[] args) {
		MineSweeper ms = new MineSweeperImplNew();

		// ms.getHintField();

		// ms.setMineField("");

		// ms.setMineField("\n..*..");

		// ms.setMineField(null);

		// ms.setMineField("abc");

		// ms.setMineField("*...\n.?*.\n....");

		// ms.setMineField("*...\n.*.\n....");
		// System.out.println(ms.getHintField());

		// ms.setMineField("*...\n..*..\n....");
		// System.out.println(ms.getHintField());

		String mineField = "*";
		ms.setMineField(mineField);
		System.out.println(mineField + "==>\n[" + ms.getHintField() + "]\n\n");

		mineField = "*...\n..*.\n....";
		ms.setMineField(mineField);
		System.out.println(mineField + "==>\n[" + ms.getHintField() + "]\n\n");

		mineField = "*...";
		ms.setMineField(mineField);
		System.out.println(mineField + "==>\n[" + ms.getHintField() + "]\n\n");

		mineField = "*...\n";
		ms.setMineField(mineField);
		System.out.println(mineField + "==>\n[" + ms.getHintField() + "]\n\n");

		mineField = "....\n....\n....";
		ms.setMineField(mineField);
		System.out.println(mineField + "==>\n[" + ms.getHintField() + "]\n\n");

		mineField = "****\n****\n****";
		ms.setMineField(mineField);
		System.out.println(mineField + "==>\n[" + ms.getHintField() + "]\n\n");

	}

}
