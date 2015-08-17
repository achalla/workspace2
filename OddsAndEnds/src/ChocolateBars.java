
public class ChocolateBars {
	static final int BIG_LENGTH = 5, SMALL_LENGTH = 1;
	
	
	
	public static int makeChocolateTimedOut(int small, int big, int goal) {
		if(small < 0 || big < 0) return -1;//can't be done
		if(goal == 0) return 0;//done, don't do any more

		if(makeChocolate(small,big-1,goal-5)>-1) return makeChocolate(small,big-1,goal-5);
		if(makeChocolate(small-1,big,goal-1)>-1) return makeChocolate(small-1,big,goal-1)+1;
		return -1;
	}
	
	public static int makeChocolate2(int small, int big, int goal) {
		if(small < 0 || big < 0) return -1;//can't be done
		if(goal == 0) return 0;//done, don't do any more

		if(goal-5 >= 0 && big > 0) return makeChocolate(small,big-1,goal-5);
		if(goal-1 >= 0 && small > 0) return makeChocolate(small-1,big,goal-1)+1;
		return -1;
	}
	
	// works, gives StackOverflowError for makeBricks(1000000, 1000, 1000100)
	public static boolean makeBricks2(int small, int big, int goal) {
		if(small < 0 || big < 0) return false;
		if(goal == 0) return true;
		if(goal < 0) return false;
		if(makeBricks(small,big-1,goal-5)) return true;
		if(makeBricks(small-1, big, goal-1)) return true;
		return false;
	}
	
	public static boolean makeBricks(int ones, int fives, int goal) {
		if(goal/BIG_LENGTH > fives) return ones >= (goal-fives*BIG_LENGTH); //exhausted all bigs, need smalls to cover what's left
		else return goal%BIG_LENGTH <= ones; //overshot with bigs, need to check mod with smalls
	}
	
	/*
	 * Not sure what challenge canDo and howDo are from...
	 */
	public static boolean canDo(int ones, int fives, int target){
		if(ones < 0 || fives < 0) return false;
		if(target == 0) return true;
		if(canDo(ones-1,fives,target-1)) return true;
		if(canDo(ones,fives-1,target-5))return true;
		return false;
	}
	
	public static int howDo(int small, int big, int goal){ //returns number of SMALL bars
		if(small < 0 || big < 0) return -1;
		if(goal == 0) return 0;
		if(howDo(small-1,big,goal-1)>-1) return howDo(small-1,big,goal-1)+1;
		if(howDo(small,big-1,goal-5)>-1)return 0;//howDo(ones,fives-1,target-5)+1;
		return -1;
	}

	
	
/*
We want make a package of goal kilos of chocolate. We have small bars (1 kilo each) and big bars (5 kilos each). Return the number of small bars to use, assuming we always use big bars before small bars. Return -1 if it can't be done. 

makeChocolate(4, 1, 9) -> 4
makeChocolate(4, 1, 10) -> -1
makeChocolate(4, 1, 7) -> 2
 */
	
	
	/*
	//Matthew Bender modified my first draft, 8/9/15
	public int makeChocolate(int small, int big, int goal) {
			if(small < 0 || big < 0) return -1;//can't be done
			if(goal == 0) return 0;//done, don't do any more

	                int left = goal - 5 * big;
			if (left >= 0) {
	                    return left > small ? -1 : left;
	                }
	                int ret = goal % 5;
	                if (ret > small) {
	                    return -1;
	                } else {
	                    return ret;
	                }   
		}
	*/

	//Anu...when she got around to it on 8/13/15
	public static int makeChocolate(int small, int big, int goal) {
		if(goal/BIG_LENGTH > big) return (small >= (goal-big*BIG_LENGTH)?(goal-big*BIG_LENGTH):-1); //exhausted all bigs, need smalls to cover what's left
		else return (goal%BIG_LENGTH <= small? goal%BIG_LENGTH:-1); //overshot with bigs, need to check mod with smalls
	}
	
	
	public static void main(String[] args){
//		System.out.println(canDo(0,1,5));
//		System.out.println(canDo(5,0,5));
//		System.out.println(canDo(0,0,5));
//		System.out.println(canDo(2,1,7));
//		System.out.println(canDo(1,1,7));		
//		System.out.println(canDo(1,2,7));		
		
//		System.out.println(howDo(0,1,5));//1
//		System.out.println(howDo(5,0,5));//5
//		System.out.println(howDo(0,0,5));//-1
//		System.out.println(howDo(2,1,7));//3
//		System.out.println(howDo(1,1,7));//-1
//		System.out.println(howDo(1,2,7));//-1		
		
//		System.out.println(makeChocolate(0,1,5));//1
//		System.out.println(makeChocolate(5,0,5));//5
//		System.out.println(makeChocolate(0,0,5));//-1
//		System.out.println(makeChocolate(2,1,7));//2
//		System.out.println(makeChocolate(1,1,7));//-1
//		System.out.println(makeChocolate(1,2,7));//-1
		
//		System.out.println(makeChocolate(4,1,9));
		System.out.println(makeBricks(1000000, 1000, 1000100));
		
	}
}
