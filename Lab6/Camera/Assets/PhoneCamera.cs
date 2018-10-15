using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using System;
using System.IO;
using UnityEngine.SceneManagement;

public class PhoneCamera : MonoBehaviour {

	private bool camAvaliable;
	private WebCamTexture backCam;
	private Texture defaulBackground;
	private bool flag;

	public RawImage background;
	public AspectRatioFitter fit;

	public Button but;

	private void Start()
	{
		flag = false;
		defaulBackground = background.texture;
		WebCamDevice[] devices = WebCamTexture.devices;
		
		if(devices.Length == 0){
			Debug.Log("No camera detected");
			camAvaliable = false;
			return;
		}
		
		for(int i = 0; i < devices.Length; i++)
		{
			if(!devices[i].isFrontFacing)
			{
				backCam = new WebCamTexture(devices[i].name, Screen.width,Screen.height);
			}
		}
		
		if (backCam == null)
		{
			Debug.Log("Unable to find back camera");
			return;
		}
		
		backCam.Play();
		background.texture = backCam;
		
		camAvaliable = true;
		
	}
	
	
	private void Update()
	{
		if (!camAvaliable) return;
		
		float ratio = (float) backCam.width / (float) backCam.height;
		fit.aspectRatio = ratio;
		
		float scaleY = backCam.videoVerticallyMirrored ? -1f : 1f;
		background.rectTransform.localScale = new Vector3(1f, scaleY, 1f);
		
		int orient = -backCam.videoRotationAngle;
		background.rectTransform.localEulerAngles = new Vector3(0, 0, orient);
	
	}


	IEnumerator UploadPNG()
	{
		
		yield return new WaitForEndOfFrame();
		try
		{			
			int width = Screen.width;
			int height = Screen.height;
			Texture2D tex = new Texture2D(width, height, TextureFormat.RGB24, false);

			tex.ReadPixels(new Rect(0, 0, width, height), 0, 0);
			tex.Apply();

			if (flag)
			{
				but.GetComponent<Image>().color = new Color(255f,255f,255f, 0);
			}
			else
			{
				but.GetComponent<Image>().sprite = Sprite.Create((Texture2D)tex, new Rect(0,0, tex.width,tex.height), Vector2.zero);
				but.GetComponent<Image>().color = new Color(255f,255f,255f, 255);
			}

			flag =!flag;
		}
		catch(Exception e)
		{
			Debug.Log("error "+ e.ToString());
		}
	}


	public void TakePhoto() 
	{ 
		StartCoroutine(UploadPNG());
	}
}