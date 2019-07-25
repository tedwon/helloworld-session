/*
 * JBoss, Home of Professional Open Source
 * Copyright 2015, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.as.quickstarts.helloworld;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * A simple servlet taking advantage of features added in 3.0.
 * </p>
 * <p>
 * <p>
 * The servlet is registered and mapped to /HelloServlet using the {@linkplain WebServlet
 *
 * @author Pete Muir
 * @HttpServlet}. The {@link HelloService} is injected by CDI. </p>
 */
@SuppressWarnings("serial")
@WebServlet("/HelloWorld")
public class HelloWorldServlet extends HttpServlet {

   static String PAGE_HEADER = "<html><head><title>helloworld</title></head><body>";

   static String PAGE_FOOTER = "</body></html>";

   @Inject
   HelloService helloService;

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      resp.setContentType("text/html");
//      resp.setHeader("Content-Disposition:\r", "myvalue");
//      resp.setHeader("Content-Disposition:", "attachment; filename=E2809818EB858420EAB3B5EAB084ECA095EBB3B420EC9CB5EBB3B5ED95A920ED95B5EC8BACEC9DB8EC9EAC20EC9EA5ED9599EC839D20EC84A0EBB09C20EAB3B5EAB3A0.hwp");
      resp.setHeader("Content-Disposition", "attachment; filename=%E2%80%9818%EB%85%84%20%EA%B3%B5%EA%B0%84%EC%A0%95%EB%B3%B4%20%EC%9C%B5%EB%B3%B5%ED%95%A9%20%ED%95%B5%EC%8B%AC%EC%9D%B8%EC%9E%AC%20%EC%9E%A5%ED%95%99%EC%83%9D%20%EC%84%A0%EB%B0%9C%20%EA%B3%B5%EA%B3%A0.hwp");
      PrintWriter writer = resp.getWriter();
      writer.println(PAGE_HEADER);
      writer.println("<h1>" + helloService.createHelloMessage("World") + "</h1>");
      writer.println(PAGE_FOOTER);
      writer.close();
   }

}
