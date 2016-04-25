
enum Orientation{
	NORTH(0),
	EAST(90),
	SOUTH(180),
	WEST(270);
	private int orientation;
	Orientation(int orientation){
		this.orientation = orientation;
	}
	protected int direction() {return orientation;}
	protected void rotateClockwise() {switch (orientation) {
	case 90: orientation = 180;break;
	case 180: orientation = 270;break;
	case 270: orientation = 0;break;
	default: orientation = 90;break;
	}}
	protected void rotateCounterClockwise() {switch (orientation) {
	case 90: orientation = 0;break;
	case 180: orientation = 90;break;
	case 270: orientation = 180;break;
	default: orientation = 270;break;
	}}
    public static Orientation getRandom() {
        return values()[(int) (Math.random() * values().length)];
    }
}