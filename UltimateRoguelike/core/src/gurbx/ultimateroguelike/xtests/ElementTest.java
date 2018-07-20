package gurbx.ultimateroguelike.xtests;

import gurbx.ultimateroguelike.elements.Element;
import gurbx.ultimateroguelike.elements.tier1.FireElement;

public class ElementTest {
	
	public static void main(String[] args) {
		Element element = new FireElement();
		
		System.out.println(element.getType());
		
//		element = element.mergedArcane().element;
		
		System.out.println(element.mergedDark());
	}

}
