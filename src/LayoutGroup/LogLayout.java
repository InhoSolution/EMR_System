package LayoutGroup;

public class LogLayout extends TapLayoutGroup 
{
	protected void Init() // 자식 호출 후 부모 호출
	{	
		super.Init();
		panelName = "로그";
	}
}