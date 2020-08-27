import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Class that solves maze problems with backtracking.
 * @author Koffman and Wolfgang
 **/
public class Maze implements GridColors {

  /** The maze */
  private TwoDimGrid maze;

  public Maze(TwoDimGrid m) {
    maze = m;
  }

  /** Wrapper method. */
  public boolean findMazePath() {
    ArrayList<PairInt> shortestPath = findMazePathMin(0, 0);
    if (shortestPath == null){
      return false;
    }
    shortestPath.stream().forEach(pairInt -> maze.recolor(pairInt.getX(),pairInt.getY(),GridColors.PATH));
    return true;
  }

    /**
     * Attempts to find a path through point (x, y).
     * @pre Possible path cells are in BACKGROUND color;
     *      barrier cells are in ABNORMAL color.
     * @post If a path is found, all cells on it are set to the
     *       PATH color; all cells that were visited but are
     *       not on the path are in the TEMPORARY color.
     * @param x The x-coordinate of current point
     * @param y The y-coordinate of current point
     * @return If a path through (x, y) is found, true;
     *         otherwise, false
     */
    public boolean findMazePath(int x, int y) {
        // COMPLETE HERE FOR PROBLEM 1
        //if  cell is outside grid returning false
      if(x<0 || x>=maze.getNCols() || y < 0 || y>=maze.getNRows()) {
        return false;
      }
      if(maze.getColor(x,y)!=GridColors.NON_BACKGROUND) {
        return false;
      }

      if(x==maze.getNCols()-1 && y == maze.getNRows()-1) {
        maze.recolor(x,y,GridColors.PATH);
        return true;
      }

      maze.recolor(x,y,GridColors.TEMPORARY);
      
      if(findMazePath(x+1,y) ||findMazePath(x,y+1) || findMazePath(x-1,y)|| findMazePath(x,y-1)) {
       maze.recolor(x,y, GridColors.PATH);
       return true;
     } else{
      maze.recolor(x,y,GridColors.NON_BACKGROUND);
      return false;
    }
  }

    // ADD METHOD FOR PROBLEM 2 HERE
  public ArrayList<ArrayList<PairInt>> findAllMazePaths(int x, int y) {
    ArrayList < ArrayList < PairInt > > result = new ArrayList < >();
    Stack < PairInt > trace = new Stack < >();
    findMazePathStackBased(0 ,0 , result , trace );
    return result;
  }

  public void findMazePathStackBased(int x, int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> trace) {
    trace.push(new PairInt(x,y));
    if(x<0 || x>=maze.getNCols() || y < 0 || y>=maze.getNRows()) {
      trace.pop();
      return ;
    }

    if(maze.getColor(x,y)!=GridColors.NON_BACKGROUND) {
      trace.pop();
      return ;
    }

    if(x==maze.getNCols()-1 && y == maze.getNRows()-1){
      ArrayList<PairInt> res = new ArrayList<PairInt>();
      System.out.println(trace);
      res.addAll(trace);
      result.add(res);
    }
    maze.recolor(x,y,GridColors.TEMPORARY);

    findMazePathStackBased(x+1,y,result,trace);
    findMazePathStackBased(x,y+1,result,trace);
    findMazePathStackBased(x-1,y,result,trace);
    findMazePathStackBased(x,y-1,result,trace);
    maze.recolor(x,y,GridColors.NON_BACKGROUND);
    trace.pop();
  }

    // ADD METHOD FOR PROBLEM 3 HERE
  public ArrayList<PairInt> findMazePathMin(int x, int y) {
    ArrayList<ArrayList<PairInt>> result =  this.findAllMazePaths(x,y);
    if(result.size() == 0){
      System.out.println("No path found- reset maze and try again");
      return null;
   }
   Integer minSize = Integer.MAX_VALUE;
   int minIndex = -1;
   for (int i = 0; i< result.size(); i++) {
     ArrayList<PairInt> res = result.get(i);
     if(minSize > res.size()) {
       minSize = res.size();
       minIndex = i;
     }
   }

   System.out.println("\n Shortest path: " + result.get(minIndex));
   return result.get(minIndex);
 }


 /*<exercise chapter="5" section="6" type="programming" number="2">*/
 public void resetTemp() {
  maze.recolor(TEMPORARY, BACKGROUND);
}
/*</exercise>*/

/*<exercise chapter="5" section="6" type="programming" number="3">*/
public void restore() {
  resetTemp();
  maze.recolor(PATH, BACKGROUND);
  maze.recolor(NON_BACKGROUND, BACKGROUND);
}
/*</exercise>*/
}
/*</listing>*/
