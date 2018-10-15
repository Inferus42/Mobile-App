using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Buton : MonoBehaviour {

	public PhoneCamera ph;

	public void Photo(){
		ph.TakePhoto ();
	}
}
