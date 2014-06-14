package edu.kit.iti.structuredtext.runtime;

import java.util.*;

import edu.kit.iti.structuredtext.ast.Top;


public class VisitorRegistry 
{
	private Map<Class<? extends Top>, Execution<? extends Top>> 
				registry = new HashMap<>();
	
	public void add(Execution<? extends Top> exec) {
		registry.put(exec.handlesThis(), exec);
	}
		
	@SuppressWarnings("unchecked")
	public <T extends Top> Execution<T> get(Class<T> clazz) { 
		return (Execution<T>) registry.get(clazz); 
	}		
}
