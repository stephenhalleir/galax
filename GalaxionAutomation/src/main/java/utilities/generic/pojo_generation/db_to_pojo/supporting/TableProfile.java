package utilities.generic.pojo_generation.db_to_pojo.supporting;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class TableProfile {

	private String tableName;
	private ArrayList<TableField> fields;

	/**
	 * Populate the table profile based on the table name and metadata
	 * @param tableName
	 * @param metadata
	 */
	public TableProfile(String tableName, ResultSetMetaData metadata) {
		this.tableName = tableName;
		fields = new ArrayList<TableField>();
		try {
			for (int i = 1; i < metadata.getColumnCount(); i++) {
				fields.add(new TableField(metadata.getColumnName(i), metadata.getColumnType(i)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public ArrayList<TableField> getFields() {
		return fields;
	}

	public void setFields(ArrayList<TableField> fields) {
		this.fields = fields;
	}

	public String getClassName() {
		return PojoStringUtility.getClassName(tableName);
	}
}
