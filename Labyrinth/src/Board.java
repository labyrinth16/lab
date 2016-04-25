import java.util.LinkedList;


public class Board {
	private Corridor[][] content = new Corridor[7][7];
	private Corridor freeCorridor = null;
	protected void init(){

		// sets the solid fields
		content[0][0] = new Corridor(Type.EDGE, false, null, Orientation.EAST);		
		content[0][2] = new Corridor(Type.CROSS,false,Treasure.SKULL,Orientation.EAST);
		content[0][4] = new Corridor(Type.CROSS,false,Treasure.SWORD,Orientation.EAST);
		content[0][6] = new Corridor(Type.EDGE, false, null, Orientation.SOUTH);
		
		content[2][0] = new Corridor(Type.CROSS,false,Treasure.GOLD,Orientation.NORTH);
		content[2][2] = new Corridor(Type.CROSS,false,Treasure.KEYS,Orientation.NORTH);
		content[2][4] = new Corridor(Type.CROSS,false,Treasure.GEM,Orientation.EAST);
		content[2][6] = new Corridor(Type.CROSS,false,Treasure.HELMET,Orientation.SOUTH);
		
		content[4][0] = new Corridor(Type.CROSS,false,Treasure.BOOK,Orientation.NORTH);
		content[4][2] = new Corridor(Type.CROSS,false,Treasure.CROWN,Orientation.WEST);
		content[4][4] = new Corridor(Type.CROSS,false,Treasure.TREASURE,Orientation.SOUTH);
		content[4][6] = new Corridor(Type.CROSS,false,Treasure.CANDLESTICK,Orientation.SOUTH);
		
		content[6][0] = new Corridor(Type.EDGE, false, null, Orientation.NORTH);
		content[6][2] = new Corridor(Type.CROSS,false,Treasure.MAP,Orientation.WEST);
		content[6][4] = new Corridor(Type.CROSS,false,Treasure.RING,Orientation.WEST);
		content[6][6] = new Corridor(Type.EDGE, false, null, Orientation.WEST);
		
		// initiates and places the rest of the corridors
		LinkedList<Corridor> restCorridors = new LinkedList<Corridor>();
		Treasure[] restTreasuresEdge = {
				Treasure.SKINK,Treasure.MOTH,Treasure.OWL,Treasure.SCARAB,Treasure.SPIDER,Treasure.MOUSE
				};
		Treasure[] restTreasuresCross = {	
				Treasure.BAT,Treasure.DJIN,Treasure.DRAGON,Treasure.GNOME,Treasure.GHOST,Treasure.WIZZARD
				};
		for (Treasure temp:restTreasuresEdge){
			restCorridors.add(new Corridor(Type.EDGE,true,temp,Orientation.getRandom()));
		}
		for (Treasure temp:restTreasuresCross){
			restCorridors.add(new Corridor(Type.CROSS,true,temp,Orientation.getRandom()));
		}
		for (int i = 0; i < 10; i++) {
			restCorridors.add(new Corridor(Type.EDGE,true,null,Orientation.getRandom()));
		}
		for (int i = 0; i < 12; i++) {
			restCorridors.add(new Corridor(Type.STRAIGHT,true,null,Orientation.getRandom()));
		}
		for (int i = 0; i < content.length; i++) {
			for (int j = 0; j < content[0].length; j++) {
				if (content[i][j]==null) {
					int temp = (int) (Math.random() * restCorridors.size());
					content[i][j]=restCorridors.get(temp);
					restCorridors.remove(temp);
				}
			}
		}
		freeCorridor = restCorridors.get(0);
	}
	
	protected void move(int x, int y){
		Corridor temp;
		if (x == 0){
			for (int i = 0; i < content.length; i++) {
				temp = content[i][y];
				content[i][y] = freeCorridor;
				freeCorridor = temp;
			}
		} else if (x == 5){
			for (int i = 0; i < content.length; i++) {
				temp = content[6-i][y];
				content[6-i][y] = freeCorridor;
				freeCorridor = temp;
			}
		} else if (y == 0){
			for (int i = 0; i < content.length; i++) {
				temp = content[x][i];
				content[x][i] = freeCorridor;
				freeCorridor = temp;
			}
		} else {
			for (int i = 0; i < content.length; i++) {
				temp = content[x][6-i];
				content[x][6-i] = freeCorridor;
				freeCorridor = temp;
			}
		}
	}
	
	protected LinkedList<Corridor> connectionList(int x,int y){
		LinkedList<Corridor> list = new LinkedList<Corridor>();
		Corridor position = content[x][y];
		list.add(position);
		if (position.getConnections()[0]==true&&y<7&&content[x][y+1].getConnections()[2]==true&&list.contains(content[x][y+1])!=true) list.addAll(connectionList(x, y+1));
		if (position.getConnections()[1]==true&&x<7&&content[x+1][y].getConnections()[3]==true&&list.contains(content[x+1][y])!=true) list.addAll(connectionList(x+1, y));
		if (position.getConnections()[2]==true&&y>0&&content[x][y-1].getConnections()[0]==true&&list.contains(content[x][y-1])!=true) list.addAll(connectionList(x, y-1));
		if (position.getConnections()[3]==true&&x>0&&content[x-1][y].getConnections()[1]==true&&list.contains(content[x-1][y])!=true) list.addAll(connectionList(x-1, y));
		return list;
	}
	
	protected boolean isConnectedTo(Treasure treasure,int x,int y){
		LinkedList<Corridor> list = connectionList(x, y);
		for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getTreasure()==treasure) return true;
        }
		return false;
	}
	
	private void printBoard(){
		for (int i = 0; i < content.length; i++) {
			for (int j = 0; j < content[0].length; j++) {
					System.out.print(content[i][j].getType() + "\t");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args){
		Board test = new Board();
		test.init();
		test.printBoard();
		System.out.println(test.freeCorridor.getType());
		test.move(5, 1);
		test.printBoard();
		System.out.println(test.freeCorridor.getType());
	}

}
