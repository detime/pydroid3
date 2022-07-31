package detime001.premium;

import android.app.*;
import android.os.*;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;

public class MainActivity implements IXposedHookLoadPackage {
    String myActivityPath = "ru.iiec.pydroid";
    String myPackageName = "ru.iiec.pydroid3";

	@Override
	public void handleLoadPackage(XC_LoadPackage.LoadPackageParam p1) throws Throwable
	{
		if(!p1.packageName.equals(myPackageName)){
			return;
		}
		XposedBridge.log("Loaded app: "+p1.packageName);
		
		XposedHelpers.findAndHookMethod(myActivityPath+".MainActivity", p1.classLoader, "D", new XC_MethodHook(){
			
			@Override
			public void afterHookedMethod(XC_MethodHook.MethodHookParam param){
				param.setResult(true);
				XposedBridge.log("Hooked");
			}
			
		});
			
		
	}

	
	
}
