package com.order.engine;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class CaleculateOrderAmount {
	
	private static Map<String, Integer> unitPrice = new HashMap<>();
	private static Map<String, Integer> promotionMultiply = new HashMap<>();
	private static Map<String, Integer> promotionAdd = new HashMap<>();
	
	static {
		unitPrice.put("A", 50);
		unitPrice.put("B", 30);
		unitPrice.put("C", 20);
		unitPrice.put("D", 15);
		promotionMultiply.put("A*3", 130);
		promotionMultiply.put("B*2", 45);
		promotionAdd.put("C+D", 30);
	}

	public static void main(String[] args) {
		Map<String, Integer> inputMap = new HashMap<>(); 
		Scanner sc = new Scanner(System.in);
		int temp =0;
		System.out.println("enter how many A product wants");
		temp = sc.nextInt();
		if(temp >0)
			inputMap.put("A", temp);
		System.out.println("enter how many B product wants");
		temp = sc.nextInt();
		if(temp >0)
			inputMap.put("B", temp);
		System.out.println("enter how many C product wants");
		temp = sc.nextInt();
		if(temp >0)
			inputMap.put("C", temp);
		System.out.println("enter how many D product wants");
		temp = sc.nextInt();
		if(temp >0)
			inputMap.put("D", temp);

		int total = caleculateTotal(inputMap);
		System.out.println(total);
	}

	public static int caleculateTotal(Map<String, Integer> inputMap) {
		Iterator<String> promotionKeys;
		Iterator<String> inputKeys = inputMap.keySet().iterator();
		String inputString;
		String promotionString;
		int total = 0;
		while(inputKeys.hasNext()) {
			inputString = inputKeys.next();
			promotionKeys = promotionMultiply.keySet().iterator();
			while(promotionKeys.hasNext()) {
				promotionString = promotionKeys.next();
				if(promotionString.contains(inputString)) {
					if(promotionString.contains("*")) {
						String[] promoKeySplit = promotionString.split("\\*");
						int count = Integer.valueOf(promoKeySplit[1]);
						int inputCount = inputMap.get(inputString);
						int remaining = 0;
						if(inputCount >=count) {
							int multi = inputCount/count;
							remaining = inputCount%count;
							total = total + (multi * promotionMultiply.get(promotionString));
						} else {
							remaining = inputCount;
						}
						total = total + (remaining * unitPrice.get(promoKeySplit[0]));
					}
					inputKeys.remove();break;
				}
			}
		}
		if(!inputMap.isEmpty()) {
			Iterator<String> promoAdd = promotionAdd.keySet().iterator();
			Iterator<String> inputAdd;
			while(promoAdd.hasNext()) {
				String promoKey = promoAdd.next();
				String[] promo = promoKey.split("\\+");
				boolean promoNotFound = false;
				boolean tempBlooean = false;
				for(String p : promo) {
					tempBlooean = false;
					inputAdd = inputMap.keySet().iterator();
					while(inputAdd.hasNext()) {
						if(p.equals(inputAdd.next())) {
							tempBlooean = true;break;
						}
					}
					if(!tempBlooean) {
						promoNotFound = true;break;
					}
				}
				if(!promoNotFound) {
					for(String p : promo) {
						inputAdd = inputMap.keySet().iterator();
						while(inputAdd.hasNext()) {
							if(p.equals(inputAdd.next())) {
								inputAdd.remove();
							}
						}
					}
					total = total + promotionAdd.get(promoKey);
				}
			}
		}
		if(!inputMap.isEmpty()) {
			Iterator<String> inputAdd = inputMap.keySet().iterator();
			while(inputAdd.hasNext()) {
				total = total + unitPrice.get(inputAdd.next());
			}
		}
		return total;
	}

}
