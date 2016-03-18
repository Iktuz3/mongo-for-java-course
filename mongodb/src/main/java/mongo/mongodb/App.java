package mongo.mongodb;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	final Configuration config = new Configuration();
    	config.setClassForTemplateLoading(App.class,"/");
    	Spark.get(new Route("/") {
			
			@Override
			public Object handle(Request arg0, Response arg1) {
				StringWriter writer = new StringWriter();
				try {
					Template iniTemplate = config.getTemplate("hello.ftl");
					Map<String,Object> map = new HashMap<String, Object>();
					map.put("fruits", Arrays.asList("apple","orange","banana","peach"));
					iniTemplate.process(map, writer);
				} catch (IOException e) {
					halt(500);
					e.printStackTrace();
				} catch (TemplateException e) {
					e.printStackTrace();
				}
				return writer;
			}
		});
    	Spark.post(new Route("/favoriteFruit") {
			
			@Override
			public Object handle(Request arg0, Response arg1) {
				final String fruit = arg0.queryParams("fruit");
				if(fruit==null){
					return "select one fruit";
				}
				return "your favorite fruit is: "+fruit;
			}
		});
    	
    	
    }
}