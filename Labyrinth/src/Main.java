import java.util.LinkedList;


public class Main {


	public static void main(String[] args){
		int[][] test = new int[5][5];
//		for (int i = 0; i < 5; i++) {
////			System.out.println((int)(Math.random() * 6));
//			System.out.println((int) 0.00001);
//			System.out.println((int) 5.00001);
//		}
//		int[] test2 = {1,1,1,1,1};
//		test2[0] = test2[1] = test2[3]=3;
//		for (int i = 0; i < test2.length; i++) {
//			System.out.println(test2[i]);
//		}

		Corridor cod = new Corridor(Type.CROSS,false,Treasure.SKULL,Orientation.EAST);
		LinkedList<Corridor> te = new LinkedList<Corridor>();
		te.add(cod);
        for (int i = 0; i < te.size(); i++) {
            if (te.get(i).getTreasure()==Treasure.SKULL)System.out.println("dasd");;
        }
	}

}
