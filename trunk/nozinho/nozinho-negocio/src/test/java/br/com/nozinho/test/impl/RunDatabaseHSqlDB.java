/**
 * 
 */
package br.com.nozinho.test.impl;

import java.util.Arrays;

import org.hsqldb.util.DatabaseManagerSwing;

/**
 * @author RodrigoAndrade
 *
 */
public abstract class RunDatabaseHSqlDB {

	public void runDatabaseManager() {
		DatabaseManagerSwing.main((String[]) Arrays.asList("-driver", "org.hsqldb.jdbcDriver","-url","jdbc:hsqldb:mem:.","-user","sa").toArray());
	}

}
