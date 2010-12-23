package mr.common.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import mr.common.i18n.spring.MessageUtils;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.comparators.ComparatorChain;
import org.springframework.util.StringUtils;


/**
 * Clase útiles para trabajar con colecciones.
 * @author Mariano Ruiz
 */
public class CollectionUtils {


    /**
     * Recorre toda la lista y obtiene por cada objeto el elemento obtenido de <code>propertyExpresion</code>, y concatena
     * en un <code>String</code> el resultado de cada uno separados por <code>separator</code>,
     * y encerrado cada valor por <code>enclosedSymbol</code>.
     * @param list List: lista de objetos
     * @param propertyExpresion String: expresión java bean de la propiedad con el texto o clave i18n
     * @param separator String: literal que separará cada elemento en la cadena,
     * ej. una coma y espacio: <code>', '</code>
     * @param enclosedSymbol String: literal que contendrá cada elemento en la cadena,
     * ej. comillas simples: <code>''</code>
     * @return String
	 * @throws RuntimeException si el nombre del método es erroneo o no accesible
     */
    @SuppressWarnings("rawtypes")
	public static String objectListToString(List list, String propertyExpresion, String separator, String enclosedSymbol) throws RuntimeException {
    	return objectListToString(list, propertyExpresion, separator, enclosedSymbol, null);
    }


    /**
     * Recorre toda la lista y obtiene por cada objeto el elemento obtenido de <code>propertyExpresion</code>, y concatena
     * en un <code>String</code> el resultado (internacionalizado o no) de cada uno separados por <code>separator</code>,
     * y encerrado cada valor por <code>enclosedSymbol</code>.<br/>
     * Si <code>prefix</code> es distinto de nulo se usa como prefijo del string de <code>propertyExpresion</code> para
     * armar la clave i18n y obtener el texto internacionalizado.
     * @param list List: lista de objetos
     * @param propertyExpresion String: expresión java bean de la propiedad con el texto o clave i18n
     * @param separator String: literal que separará cada elemento en la cadena,
     * ej. una coma y espacio: <code>', '</code>
     * @param enclosedSymbol String: literal que contendrá cada elemento en la cadena,
     * ej. comillas simples: <code>''</code>
     * @param prefix String: prefijo de clave i18n
     * @return String
	 * @throws RuntimeException si el nombre del método es erroneo o no accesible
     */
    @SuppressWarnings("rawtypes")
	public static String objectListToString(List list, String propertyExpresion, String separator, String enclosedSymbol, String prefix) throws RuntimeException {
    	if(list==null || list.size()==0) {
    		return "";
    	}
    	String text = "";
    	try {
	    	for(Object obj : list) {
	    		if(!text.equals("")) {
	    			text += enclosedSymbol + separator + enclosedSymbol;
	    		} else {
	    			text += enclosedSymbol;
	    		}
	    		if(prefix==null) {
	    			text += PropertyUtils.getProperty(obj, propertyExpresion).toString();
	    		} else {
	    			text += MessageUtils.getMessageSource().getMessage(prefix + PropertyUtils.getProperty(obj, propertyExpresion).toString(),
	    					                                           null, MessageUtils.getLocale());
	    		}
	    	}
			if(!text.equals("")) {
				text += enclosedSymbol;
			}
    	} catch(Exception e) {
    		throw new RuntimeException(e);
    	}
    	return text;
    }

    /**
     * Recorre toda la lista y obtiene por cada objeto el elemento obtenido de <code>propertyExpresion</code>, y concatena
     * en un <code>String</code> el resultado de cada uno separados por coma.
     * @param list List: lista de objetos
     * @param propertyExpresion String: expresión java bean de la propiedad con el texto o clave i18n
     * @return String
	 * @throws RuntimeException si el nombre del método es erroneo o no accesible
     */
    @SuppressWarnings({ "rawtypes" })
	public static String objectListToString(List list, String propertyExpresion) throws RuntimeException {
    	return objectListToString(list, propertyExpresion, ", ", "");
    }

    /**
     * Recorre toda la lista y obtiene por cada objeto el elemento obtenido de <code>propertyExpresion</code>,
     * le concatena el <code>prefix</code> y se obtiene el valor internacionalizado y se concatena
     * en un <code>String</code> el resultado de cada uno separados por coma.<br/>
     * Si <code>prefix</code> es distinto de nulo se usa como prefijo del string de <code>propertyExpresion</code> para
     * armar la clave i18n y obtener el texto internacionalizado.
     * @param list List: lista de objetos
     * @param propertyExpresion String: expresión java bean de la propiedad con el texto o clave i18n
     * @param prefix String: prefijo de clave i18n
     * @return String
	 * @throws RuntimeException si el nombre del método es erroneo o no accesible
     */
    @SuppressWarnings({ "rawtypes" })
	public static String objectListToString(List list, String propertyExpresion, String prefix) throws RuntimeException {
    	return objectListToString(list, propertyExpresion, ", ", "", prefix);
    }

    /**
     * Transforma una cadena de texto con palabras separadas por <a
     * href="../util/regex/Pattern.html#sum">regular expression</a>
     * a una lista de <code>String</code>.
     * @param stringList String: cadena que contiene las palabras a separar
     * @param regex String: Expresión regular (o literal)
     * @return lista de String, vacía si <code>stringList</code> es no
     * contiene texto
     */
    public static List<String> stringToObjectList(String stringList, String regex) {
		if(StringUtils.hasText(stringList)) {
			return Arrays.asList(stringList.split(regex));
		}
		return new ArrayList<String>(0);
    }

    /**
     * Transforma una cadena de texto con palabras separadas por comas <code>,</code>
     * a una lista de <code>String</code>.
     * @param stringList String: cadena que contiene las palabras a separar
     * @return lista de String, vacía si <code>stringList</code> es no
     * contiene texto
     */
    public static List<String> stringToObjectList(String stringList) {
    	return stringToObjectList(stringList, ",");
    }

    /**
     * Ordena la lista utilizando el nombre del método pasado en el constructor para obtener el valor
     * que será utilizado en la comparación.
     * El valor de retorno del mismo debe implementar {@link java.lang.Comparable#compareTo(Object)},
     * ya que se usa el <code>compareTo(Object)</code> para obtener el resultado.
 	 * @see java.util.Comparator
 	 * @see mr.common.collection.ValueMethodComparator
     * @param list List: lista a ser ordenada
     * @param propertyExpresion String: nombre del método que se utiliza para la ordenación.
	 * @param ascending boolean: <code>true</code> si es comparación ascendente
     * @return List: lista ordenada
	 * @throws RuntimeException si el nombre del método es erroneo o no accesible
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List sortListByMethod(List list, String propertyExpresion, boolean ascending) {
		if(list.isEmpty()) {
			return list;
		}
		Comparator comparator = new ValueMethodComparator(list.get(0).getClass(), propertyExpresion, ascending);
		Collections.sort(list, comparator);
		return list;
	}

    /**
     * Ordena la lista utilizando el nombre del método pasado en el constructor para obtener el valor
     * que será utilizado en la comparación.
     * El valor de retorno del mismo debe implementar {@link java.lang.Comparable#compareTo(Object)},
     * ya que se usa el <code>compareTo(Object)</code> para obtener el resultado.
 	 * @see java.util.Comparator
 	 * @see mr.common.collection.ValueMethodComparator
     * @param list List: lista a ser ordenada
     * @param propertyExpresion String: nombre del método que se utiliza para la ordenación.
     * @return List: lista ordenada
	 * @throws RuntimeException si el nombre del método es erroneo o no accesible
     */
	@SuppressWarnings("rawtypes")
	public static List sorListtByMethod(List list, String propertyExpresion) {
		return sortListByMethod(list, propertyExpresion, true);
	}

    /**
     * Ordena la lista utilizando los nombres de los métodos pasados en el constructor para
     * obtener los valores que serán utilizados en la comparación.
     * El valor de retorno del mismo debe implementar {@link java.lang.Comparable#compareTo(Object)},
     * ya que se usa el <code>compareTo(Object)</code> para obtener el resultado.
 	 * @see java.util.Comparator
 	 * @see mr.common.collection.ValueMethodComparator
     * @param list List: lista a ser ordenada
     * @param methodName String []: nombre del método que se utiliza para la ordenación.
	 * @param ascending boolean []: <code>true</code> si es comparación ascendente en cada methodName
     * @return List: lista ordenada
	 * @throws RuntimeException si el nombre del método es erroneo o no accesible
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List sortListByMethods(List list, String [] methodsName, boolean [] ascending) {
		if(list.isEmpty()) {
			return list;
		}
		if(methodsName.length == 0 || (methodsName.length != ascending.length)) {
			throw new IllegalArgumentException();
		}
		ComparatorChain comparators = new ComparatorChain();
		Comparator comparator;
		for(int i=0; i<methodsName.length; i++) {
			comparator = new ValueMethodComparator(list.get(0).getClass(), methodsName[i], ascending[i]);
			comparators.addComparator(comparator);
		}
		Collections.sort(list, comparators);
		return list;
	}
}
