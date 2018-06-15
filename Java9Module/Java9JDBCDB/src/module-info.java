/**
 * 
 */
module application.db {
	
	requires ojdbc6;
	requires java.sql;
	requires transitive application.common;
	
	exports application.db to application.ui;
}