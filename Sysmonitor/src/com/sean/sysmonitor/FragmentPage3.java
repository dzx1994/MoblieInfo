package com.sean.sysmonitor;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class FragmentPage3 extends Fragment{
    private static final String TAG="FragmentPage3";

	private LinearLayout mAbout;
	private LinearLayout mMessage;
	private LinearLayout mText;
	private Button exitButton;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {	
		return inflater.inflate(R.layout.fragment_3, null);		
	}
	
	public void onActivityCreated(Bundle savedInstanceState) {  
        super.onActivityCreated(savedInstanceState);
        View view1 = (View)getActivity().findViewById(R.id.info_set);
        mText = (LinearLayout) view1.findViewById(R.id.system);
        mText.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				new AlertDialog.Builder(getActivity())
				.setTitle("使用说明")
				.setMessage("本简易APP可用于监测手机各部件工作状，以及查询手机内部配置、网络状况等情况")
				.setPositiveButton("确定", null)
				.show();
			}
		});
        mMessage = (LinearLayout) view1.findViewById(R.id.message);
		mMessage.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+"18679691164"));
	        	startActivity(intent);
			}
		});
		mAbout = (LinearLayout) view1.findViewById(R.id.about);
		mAbout.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				new AlertDialog.Builder(getActivity())
				.setTitle("关于")
				.setMessage("欢迎访问：https://github.com/dzx1994/MoblieInfo.git")
				.setPositiveButton("确定", null)
				.show();
			}
		});
		exitButton = (Button)getActivity().findViewById(R.id.exitButton);
		exitButton.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				getActivity().finish();
	            System.exit(0);
			}
		});
	}
}