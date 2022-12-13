package utilities.generic.pojo_generation.db_to_pojo.supporting;

import java.util.ArrayList;

public class PojoStringUtility {

	public static String capitalize(String fieldName) {
		return fieldName.substring(0,1).toUpperCase() + fieldName.substring(1).toLowerCase();
	}
	
	/**
	 * Get the Java variable name for the field
	 * @param tableName - e.g. ORDER_TASK_NAME
	 * @return the java class name - e.g. orderTaskName
	 */
	public static String getVariableName(String fieldName) {
		String[] parts = fieldName.split("_");
		String str=parts[0].toLowerCase();
		
		for(int i=1;i<parts.length;i++) {
			parts[i] = capitalize(parts[i]);
			str = str+parts[i];
		}
		
		return str;
	}
	
	/**
	 * Get the Java class name for the table
	 * @param tableName - e.g. PRODUCT_ORDER
	 * @return the java class name - e.g. ProductOrder
	 */
	public static String getClassName(String tableName) {
		
		if(tableName==null) {
			System.err.println("ERROR: TABLE NAME IS NULL");
			return null;
		}
		
		String[] parts = tableName.split("_");
		String str="";
		for(int i=0;i<parts.length;i++) {
			parts[i] = capitalize(parts[i]);
			str = str+parts[i];
		}
		
		return str;
	}
	
	/**
	 * Genetate a GET method string for the field
	 * @param field
	 * @return a GET method string
	 */
	public static String getGetMethod(TableField field) {
		String str = "public " + field.getTypeString() + " get" + getClassName(field.getFieldName()) + "() {" + "\n";
		str = str + " \treturn " + getVariableName(field.getFieldName()) + ";\n}";
		return str;	
	}
	
	
	/**
	 * Genetate a SET method string for the field
	 * @param field
	 * @return a SET method string
	 */
	public static String getSetMethod(TableField field) {
		String str = "public void set" + getClassName(field.getFieldName()) + "(" + field.getTypeString() + " " + getVariableName(field.getFieldName()) + ") {" + "\n";
		str = str + " \t this." + getVariableName(field.getFieldName()) + "=" + getVariableName(field.getFieldName()) + ";\n}";
		return str;	
	}
	
	/**
	 * Get a variable declaration for the field
	 * @param field - e.g. ORDER_EXTERNAL_REF
	 * @return "private String orderExternalRef;"
	 */
	public static String getVariableDeclaration(TableField field) {
		return "private " + field.getTypeString() + " " + getVariableName(field.getFieldName()) + ";";
	}
	
	/**
	 * Generate default constructor for the table
	 * @param tableName
	 * @return
	 */
	public static String getDefaultConstructor(String tableName) {
		String str="public " + getClassName(tableName) + "() {\n";

		str = str + "\n}";
		return str;
	}
	
	/**
	 * Generate a constructor taking a result set as input
	 * @param tableName
	 * @param fields
	 * @return
	 */
	public static String getConstructor(String tableName,ArrayList<TableField> fields) {
		String str="public " + getClassName(tableName) + "(ResultSet rs) {\n";
		str = str + "try {\n";
		
		for(TableField field:fields) {
			String thisField="\t" + getVariableName(field.getFieldName()) + " = rs.get" + capitalize(field.getTypeString()) + "(\"" + field.getFieldName() + "\");";
			str = str + thisField + "\n";
		}
		
		str = str + "} catch (SQLException e) {\r\n" + 
				"		e.printStackTrace();\r\n" + 
				"	}";

		
		str = str + "\n}";
		return str;
	}
	
	public static String getVariableDeclarations(ArrayList<TableField> fields) {
		String s="";
		
		for(TableField f:fields) {
			s = s + getVariableDeclaration(f) + "\n";
		}
		
		return s;
	}
	
	public static String getClassCode(TableProfile profile, String packageName) {
		
		// pckage declaration and imports
		String str= "package " + packageName + ";\r\n\n" + 
				"import java.sql.Date;\r\n" + 
				"import java.sql.ResultSet;\r\n" + 
				"import java.sql.SQLException;\r\n\n";
		
		// class declaration
		str = str + "public class " + getClassName(profile.getTableName()) + " {\n\n";
		
		// variable declarations
		str = str + getVariableDeclarations(profile.getFields()) + "\n";
		
		// default constructor
		str = str + getDefaultConstructor(profile.getTableName()) + "\n\n";
		
		if(profile.getFields().size()>0) {
			// ResultSet constructor
			str = str + getConstructor(profile.getTableName(),profile.getFields()) + "\n\n";
		}
		
		
		// add getter and setter methods
		for(TableField f:profile.getFields()) {
			str = str + PojoStringUtility.getGetMethod(f) + "\n";
			str = str + PojoStringUtility.getSetMethod(f) + "\n";
		}
		
		return str + "\n}";
	}
}
