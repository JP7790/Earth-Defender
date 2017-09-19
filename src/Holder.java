/**
 * Earth Defender: Final Project
 * Authors: Joe Passanante Matt Chieco
 * 
 * This class is apart of the holder pattern in which the three panels don't need to know about each other to communicate. 
 */
public class Holder {
	private int _score = 0, _level = 1, _health = 500, _cash = 0,_ammo = 10,_maxAmmo = 200;
	private boolean _gamePaused = false;
	public Holder() {
		
	}
	public int get_score() {
		return _score;
	}
	public void set_score(int _score) {
		this._score = _score;
	}
	public int get_level() {
		return _level;
	}
	public void set_level(int _level) {
		this._level = _level;
	}
	public int get_health() {
		return _health;
	}
	public void set_health(int _health) {
		this._health = _health;
	}
	public int get_cash() {
		return _cash;
	}
	public void set_cash(int _cash) {
		this._cash = _cash;
	}
	public int get_ammo() {
		return _ammo;
	}
	public void set_ammo(int _ammo) {
		this._ammo = _ammo;
	}
	public int get_maxAmmo() {
		return _maxAmmo;
	}
	public void set_maxAmmo(int _maxAmmo) {
		this._maxAmmo = _maxAmmo;
	}
	public void set_gamePaused(boolean b) {
		_gamePaused  = b;
	}
	public boolean get_gamePaused(){
		return _gamePaused;
	}


}
