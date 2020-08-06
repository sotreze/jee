package br.com.casadocodigo.loja.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.casadocodigo.loja.models.SystemRole;;

//@FacesConverter("systemRoleConverter")
//public class SystemRoleConverter implements Converter {
//	
//    @Override
//    public Object getAsObject(FacesContext context, UIComponent component, String id) {
//
//        if (id == null || id.trim().isEmpty()) 
//            return null;
//    	System.out.println("Convertendo para Objeto: " +id);
//    	SystemRole systemRole = new SystemRole();
//    	systemRole.setId(Integer.valueOf(id));
//
//        return systemRole;
//    }
//
//    @Override
//    public String getAsString(FacesContext context, UIComponent component, Object systemRoleObject) {
//        if (systemRoleObject == null)
//            return null;
//        System.out.println("Convertendo para String: " +systemRoleObject);
//        SystemRole systemRole = (SystemRole) systemRoleObject;
//        return systemRole.getId().toString();
//    }
//
//}
