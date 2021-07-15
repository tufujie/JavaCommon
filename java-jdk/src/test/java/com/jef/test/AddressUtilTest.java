package com.jef.test;

import com.jef.util.AddressUtil;

import org.junit.Test;

import java.util.List;


public class AddressUtilTest {

	@Test
	public void testGetCities() {
        AddressUtil addressUtil =  AddressUtil.getInstance();
		List<String> list = addressUtil.getCities("中国", "广东");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
}
