public class Corridor {
	private Type type;
	private Orientation orientation = Orientation.NORTH;
	private Treasure treasure = null;
	private boolean moveable;
	private boolean[] isConnected = { true, true, true, true };

	protected Corridor(Type type, boolean moveable, Treasure treasure,
			Orientation orientation) {
		this.type = type;
		this.moveable = moveable;
		this.treasure = treasure;
		this.orientation = orientation;
		calculateConnection();
	}

	protected Type getType() {
		return type;
	}

	protected Orientation getOrientation() {
		return orientation;
	}

	protected int getDirection() {
		return orientation.direction();
	}

	protected boolean isMoveable() {
		return moveable;
	}

	protected void rotateClockwise() {
		orientation.rotateClockwise();
		calculateConnection();
	}

	protected void rotateCounterClockwise() {
		orientation.rotateCounterClockwise();
		calculateConnection();
	}

	protected Treasure getTreasure() {
		return treasure;
	}

	protected void calculateConnection() {
		switch (type) {
		case EDGE:
			switch (orientation) {
			case NORTH:
				isConnected[2] = isConnected[3] = false;
				break;
			case EAST:
				isConnected[3] = isConnected[0] = false;
				break;
			case SOUTH:
				isConnected[0] = isConnected[1] = false;
				break;
			case WEST:
				isConnected[1] = isConnected[2] = false;
				break;
			}
			break;

		case STRAIGHT:
			switch (orientation) {
			case NORTH:
				isConnected[1] = isConnected[3] = false;
				break;
			case EAST:
				isConnected[2] = isConnected[0] = false;
				break;
			case SOUTH:
				isConnected[3] = isConnected[1] = false;
				break;
			case WEST:
				isConnected[0] = isConnected[2] = false;
				break;
			}
			break;

		case CROSS:
			switch (orientation) {
			case NORTH:
				isConnected[3] = false;
				break;
			case EAST:
				isConnected[0] = false;
				break;
			case SOUTH:
				isConnected[1] = false;
				break;
			case WEST:
				isConnected[2] = false;
				break;
			}
			break;
		}

	}
	protected boolean[] getConnections(){
		return isConnected;
	}

}
