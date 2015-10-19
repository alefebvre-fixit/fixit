package controllers.test;

import javax.inject.Named;

@Named
public class InjectionTesterImpl implements InjectionTester {

	@Override
	public String testInjection() {
		// TODO Auto-generated method stub
		return "Hello World";
	}

}
