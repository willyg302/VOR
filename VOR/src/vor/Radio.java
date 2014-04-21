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

import java.util.Timer;
import java.util.TimerTask;

/**
 * Our simulated radio.
 * 
 * @author David Do
 */
public class Radio {
	
	protected int radial;
	protected String stationID;
	
	/* National */public Radio() {
		this(false);
	}

	public Radio(boolean timed) {
		reset();
		// TODO?
		// It'd be nice, but we'd need the Display to listen to this class for changes.
		/*
		if (timed) {
			new Timer().scheduleAtFixedRate(new TimerTask() {

				@Override
				public void run() {
					generateRandomRadial();
					generateRandomStationID();
				}
			}, 0, 10 * 1000);
		}*/
	}
	
	public void reset() {
		generateRandomRadial();
		generateRandomStationID();
	}
	
	private void generateRandomRadial() {
		this.radial = Utils.randomInt(0, 359);
	}
	
	private void generateRandomStationID() {
		// @William modified to use char magic
		this.stationID = new StringBuilder()
				.append((char) Utils.randomInt((int) 'A', (int) 'Z'))
				.append((char) Utils.randomInt((int) 'A', (int) 'Z'))
				.append((char) Utils.randomInt((int) 'A', (int) 'Z'))
				.toString();
	}
	
	public int getRadial() {
		return radial;
	}
	
	public String getStationID() {
		return stationID;
	}
	
	public boolean overStation() {
		return (Utils.randomInt(1, 10) == 10);
	}
}
