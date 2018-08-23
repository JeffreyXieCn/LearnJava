/**
 * 
 */
package com.exam.sweepmine.test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.exam.sweepmine.MineSweeperImpl;

/**
 * JUnit test for PalyMineSweeper functions
 * 
 * @author WUHAITAO
 *
 */
public class PlayMineSweeperTest {
	private static MineSweeperImpl playMineSweeper;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		playMineSweeper = new MineSweeperImpl(); 
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * Test the failed scenarios.
	 * 
	 * @param None
	 * 
	 */
	@Test
	public void testSetFail() {
		String[] inputFail = { "",
							   "*...1",
							   "*....\n",
							   "*..\n*.",
							   "\n*..",
							   "*.\n*...\n****"
								};
		
		String[] outputFail = { "Null input string!",
				                "Illegal char 1 inside the input string!", 
								"Illegal char " + '\n' + " inside the input string!",
								"Illegal char " + '\n' + " inside the input string!",
								"Null column string!",
								"Illegal char . at the column end!",
								};		
		
		for(int i = 0; i < inputFail.length ; i++){
			try{
				playMineSweeper.setMineField(inputFail[i]);
			}catch(IllegalArgumentException e){
				assertThat(e.getMessage(), is(outputFail[i]));
			}
		}		
		
	}
	
	/**
	 * Test the successful scenarios.
	 * 
	 * @param None
	 * 
	 */	
	@Test
	public void testSetSuccess() {
		String[] inputSuccess = { "*", 
								  "*..", 
								  "*\n.\n*\n.\n.\n.",
								  "*.....\n....*.\n*..*..\n..**.."
					};

		String[] outputSuccess = { "*",
								   "*10", 
								   "*\n2\n*\n1\n0\n0", 
								   "*10111\n2212*1\n*23*31\n12**20"
					};		

		for(int i = 0; i < inputSuccess.length ; i++){
			try{
				playMineSweeper.setMineField(inputSuccess[i]);
				String result = playMineSweeper.getHintField();
				assertThat(result, is(outputSuccess[i]));
			}catch(IllegalArgumentException e){
				fail("Test Case " + inputSuccess[i] + " throw IllegalArgumentException: " + e.getMessage());
			}
		}		

	}
	
}
