package akhil.virtualXposed;


import android.util.Log;
import android.widget.TextView;


import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;


public class virtualXposedSample implements IXposedHookLoadPackage {

    public void handleLoadPackage(final LoadPackageParam lpparam) throws Throwable {
        if (!lpparam.packageName.equals("codepath.apps.demointroandroid"))
            return;
        Log.i("VirtualXposed sample", lpparam.packageName);
        XposedBridge.log("VirtualXposed sample: " + lpparam.packageName);
        findAndHookMethod(TextView.class, "setText", CharSequence.class, TextView.BufferType.class, boolean.class, int.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                Log.i("VirtualXposed sample", "in before hooked method");
                XposedBridge.log("VirtualXposed sample: in before hooked method");
                if (param.args[0] != null) {
                    String stringArgs = param.args[0].toString();
                    Log.i("VirtualXposed sample", "param " + stringArgs);
                    XposedBridge.log("VirtualXposed sample: " + "param " + stringArgs);
                    param.args[0] = "Boink";
                }
            }
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                Log.i("VirtualXposed sample", "in after hooked method");
                XposedBridge.log("VirtualXposed sample: in after hooked method");
            }
        });
    }
}