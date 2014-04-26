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

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Our simulated radio. By default, a Radio instance simply encapsulates
 * "random" values of intercepted {@code radial}, 3-letter {@code stationID},
 * and whether the plane is {@code overStation} or not.
 * <p>
 * However, by calling {@code new Radio(true)}, you can get a stateful radio
 * that updates its values every 10 seconds. It will automatically notify
 * classes registered with {@link #addListener(RadioListener) addListener}
 * when changes occur.
 * 
 * @author David Do, William Gaul
 */
public class Radio {
	
	private ArrayList<RadioListener> listeners;
	
	protected int radial;
	protected String stationID;
	protected boolean overStation;
	
	/* National */public Radio() {
		this(false);
	}

	public Radio(boolean timed) {
		listeners = new ArrayList<>();
		reset();
		if (timed) {
			new Timer().scheduleAtFixedRate(new TimerTask() {

				@Override
				public void run() {
					generateRandomRadial();
					generateRandomOverStation();
					
					notifyListeners();
				}
			}, 0, 10 * 1000);
		}
	}
	
	public void addListener(RadioListener listener) {
        listeners.add(listener);
    }
	
	public void reset() {
		generateRandomRadial();
		generateRandomStationID();
		generateRandomOverStation();
		
		notifyListeners();
	}
	
	private void notifyListeners() {
		for (RadioListener listener : listeners) {
        	listener.incomingData();
        }
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
	
	private void generateRandomOverStation() {
		this.overStation = (Utils.randomInt(1, 10) == 10);
	}
	
	public int getRadial() {
		return radial;
	}
	
	public String getStationID() {
		return stationID;
	}
	
	public boolean isOverStation() {
		return overStation;
	}
}
