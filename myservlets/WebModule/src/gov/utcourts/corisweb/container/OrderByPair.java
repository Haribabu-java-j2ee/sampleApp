package gov.utcourts.corisweb.container;

import gov.utcourts.courtscommon.dataaccess.base.enumeration.DirectionType;

public class OrderByPair {
	private int columnPosition;
	private DirectionType directionType;
	
	public OrderByPair(int columnPosition, String type) {
		this.columnPosition = columnPosition;
		this.directionType = DirectionType.valueOf(type.toUpperCase());
	}
	
	public int getColumnPosition() {
		return columnPosition;
	}
	
	public void setColumnPosition(int columnPosition) {
		this.columnPosition = columnPosition;
	}
	
	public String getDirectionTypeAsString() {
		return (directionType == DirectionType.ASC) ? "asc" : "desc";
	}
	
	public DirectionType getDirectionType() {
		return directionType;
	}
	
	public void setDirectionType(DirectionType directionType) {
		this.directionType = directionType;
	}
}
