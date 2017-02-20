package com.sean.sysmonitor.utils;

import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.Reader;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import android.util.Base64;
import android.util.Log;

public class Utils {
	private static String TAG="Util";

	public static String getMac() {
		String str = "";
		String macSerial = "";
		try {
			Process pp = Runtime.getRuntime().exec("cat /sys/class/net/wlan0/address ");
			InputStreamReader ir = new InputStreamReader(pp.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);

			for (; null != str;) {
				str = input.readLine();
				if (str != null) {
					macSerial = str.trim();// ȥ�ո�
					break;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (macSerial == null || "".equals(macSerial)) {
			try {
				return loadFileAsString("/sys/class/net/eth0/address").toUpperCase().substring(0, 17);
			} catch (Exception e) {
				e.printStackTrace();

			}

		}
		return macSerial;
	}

	public static String loadFileAsString(String fileName) throws Exception {
		FileReader reader = new FileReader(fileName);
		String text = loadReaderAsString(reader);
		reader.close();
		return text;
	}

	public static String loadReaderAsString(Reader reader) throws Exception {
		StringBuilder builder = new StringBuilder();
		char[] buffer = new char[4096];
		int readLength = reader.read(buffer);
		while (readLength >= 0) {
			builder.append(buffer, 0, readLength);
			readLength = reader.read(buffer);
		}
		return builder.toString();
	}
    public static String sign(String str, String str2) {
        try {
            RSAPublicKey rSAPublicKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(str2, 2)));
            Log.i(TAG,"rSAPublicKey value is : "+KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(str2, 2))).toString());
            String[] a = sign(str, 39);
            StringBuilder stringBuilder = new StringBuilder();
            Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            instance.init(1, rSAPublicKey);
            for (String bytes : a) {
                stringBuilder.append(Base64.encodeToString(instance.doFinal(bytes.getBytes("UTF-8")), 2));
                
            }
            Log.i(TAG,"stringBuilder value is :"+stringBuilder.toString());
            return stringBuilder.toString();
//            return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(str2, 2))).toString();
        } catch (Exception e) {
            return "";
        }
    }

    private static String[] sign(String str, int i) {
        int length = ((str.length() + i) - 1) / i;
        String[] strArr = new String[length];
        int i2 = 0;
        while (i2 < length) {
            strArr[i2] = str.substring(i2 * i, (i2 == length + -1 ? str.length() - (i2 * i) : i) + (i2 * i));
            i2++;
        }
        return strArr;
    }
}
