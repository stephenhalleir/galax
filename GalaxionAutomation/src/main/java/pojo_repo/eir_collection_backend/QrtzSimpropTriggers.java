package pojo_repo.eir_collection_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QrtzSimpropTriggers {

private String schedName;
private String triggerName;
private String triggerGroup;
private String strProp1;
private String strProp2;
private String strProp3;
private int intProp1;
private int intProp2;
private int longProp1;
private int longProp2;
private int decProp1;
private int decProp2;
private int boolProp1;

public QrtzSimpropTriggers() {

}

public QrtzSimpropTriggers(ResultSet rs) {
try {
	schedName = rs.getString("SCHED_NAME");
	triggerName = rs.getString("TRIGGER_NAME");
	triggerGroup = rs.getString("TRIGGER_GROUP");
	strProp1 = rs.getString("STR_PROP_1");
	strProp2 = rs.getString("STR_PROP_2");
	strProp3 = rs.getString("STR_PROP_3");
	intProp1 = rs.getInt("INT_PROP_1");
	intProp2 = rs.getInt("INT_PROP_2");
	longProp1 = rs.getInt("LONG_PROP_1");
	longProp2 = rs.getInt("LONG_PROP_2");
	decProp1 = rs.getInt("DEC_PROP_1");
	decProp2 = rs.getInt("DEC_PROP_2");
	boolProp1 = rs.getInt("BOOL_PROP_1");
} catch (SQLException e) {
		e.printStackTrace();
	}
}

public String getSchedName() {
 	return schedName;
}
public void setSchedName(String schedName) {
 	 this.schedName=schedName;
}
public String getTriggerName() {
 	return triggerName;
}
public void setTriggerName(String triggerName) {
 	 this.triggerName=triggerName;
}
public String getTriggerGroup() {
 	return triggerGroup;
}
public void setTriggerGroup(String triggerGroup) {
 	 this.triggerGroup=triggerGroup;
}
public String getStrProp1() {
 	return strProp1;
}
public void setStrProp1(String strProp1) {
 	 this.strProp1=strProp1;
}
public String getStrProp2() {
 	return strProp2;
}
public void setStrProp2(String strProp2) {
 	 this.strProp2=strProp2;
}
public String getStrProp3() {
 	return strProp3;
}
public void setStrProp3(String strProp3) {
 	 this.strProp3=strProp3;
}
public int getIntProp1() {
 	return intProp1;
}
public void setIntProp1(int intProp1) {
 	 this.intProp1=intProp1;
}
public int getIntProp2() {
 	return intProp2;
}
public void setIntProp2(int intProp2) {
 	 this.intProp2=intProp2;
}
public int getLongProp1() {
 	return longProp1;
}
public void setLongProp1(int longProp1) {
 	 this.longProp1=longProp1;
}
public int getLongProp2() {
 	return longProp2;
}
public void setLongProp2(int longProp2) {
 	 this.longProp2=longProp2;
}
public int getDecProp1() {
 	return decProp1;
}
public void setDecProp1(int decProp1) {
 	 this.decProp1=decProp1;
}
public int getDecProp2() {
 	return decProp2;
}
public void setDecProp2(int decProp2) {
 	 this.decProp2=decProp2;
}
public int getBoolProp1() {
 	return boolProp1;
}
public void setBoolProp1(int boolProp1) {
 	 this.boolProp1=boolProp1;
}

}