/**
 * VHF Omnidirectional Range simulator in Java
 * Copyright (C) 2014  William Gaul, David Do, Landon Soriano
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package vor;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Run this class to test everything with JUnit. You can do so in two ways:
 *   1. Right click AllTests.java in the Project Explorer and select Run As > 1 JUnit Test
 *   2. Alt-Shift-X, T
 * 
 * When you create a new test class, add it to the SuiteClasses decorator below.
 * 
 * @author William
 */
@RunWith(Suite.class)
@SuiteClasses({ ResourcesTest.class, UtilsTest.class })
public class AllTests {
	//
}
