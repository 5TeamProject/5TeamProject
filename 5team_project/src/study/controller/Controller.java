package study.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import study.commnad.CommandAction;

public class Controller extends HttpServlet {
   
   private Map<String, Object> commandMap = new HashMap<>();

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      process(request, response);
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      process(request, response);
   }

   public void init(ServletConfig config) throws ServletException {
      String props = config.getInitParameter("propertyConfig");
      Properties pr = new Properties();
      FileInputStream f = null;
      
      try {
         f=new FileInputStream(props);
         pr.load(f);
         
      } catch (Exception e) {
         e.printStackTrace();
      } finally{
         if(f!=null) try{f.close();} catch(Exception e){e.printStackTrace();}
      }
      Iterator<?> keyIter = pr.keySet().iterator();
      System.out.println("keyIter:"+keyIter);
      
      while(keyIter.hasNext()){
         String command=(String)keyIter.next();
         String className = pr.getProperty(command);
         try {
            Class<?> commandClass = Class.forName(className);
            Object commandInstance = commandClass.newInstance();
            
            commandMap.put(command, commandInstance);
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
   }

   protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String view = null;
      CommandAction ca = null;
      System.out.println("commandMap : "+commandMap);
      try {
         String command = request.getRequestURI(); 
         if(command.indexOf(request.getContextPath())==0){
            command = command.substring(request.getContextPath().length()+1);
         }
         
         ca=(CommandAction)commandMap.get(command);
         view = ca.process(request, response);
      } catch (Throwable e) {
         e.printStackTrace();
      }
      request.setAttribute("CONTENT", view);
      RequestDispatcher dispatcher = request.getRequestDispatcher("/template/template.jsp");
      dispatcher.forward(request, response);
   }
   
}