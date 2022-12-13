package utilities.generic.pojo_generation.db_to_pojo.supporting;

public class TableField {
	private String fieldName;
	private int fieldType;
	
	public TableField() {
		
	}
	
	public TableField(String fieldName,int fieldType) {
		this.fieldName=fieldName;
		this.fieldType=fieldType;
	}
	
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public int getFieldType() {
		return fieldType;
	}
	public void setFieldType(int fieldType) {
		this.fieldType = fieldType;
	}
	
	public String getTypeString() {
		switch(fieldType) {
		case 1:
			return "String";
		case 12:
			return "String";
		case 2004:
			return "Blob";
		case -5:
			return "int";
		case -7:
			return "int";
		case 7:
			return "int";
		case 4:
			return "int";
		case 5:
			return "int";
		case -6:
			return "int";
		case 93:
			return "Date";
		case 91:
			return "Date";
		case 92:
			return "Date";
		case 3:
			return "int";
		case -1:
			return "String";
		}

		System.err.println("Error: No field string mapped for type " + fieldType + " - " + fieldName);
		return null;
	}
}
