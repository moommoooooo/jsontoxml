package com.example.jsonToXml;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.w3c.dom.Document;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootApplication
public class jsontoXml {

	public static void main(String[] args) throws JsonProcessingException, IOException, ParserConfigurationException, TransformerException {
		  File json = new File("file.json");
		  
		  ObjectMapper objectMapper = new ObjectMapper();
		  JsonNode jsonNode = objectMapper.readTree(json);
//		  JsonNode userNode= jsonNode.get("userdetails");
		
		  DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		  DocumentBuilder builder = factory.newDocumentBuilder();
     
	      try {
	    	    builder = factory.newDocumentBuilder();
	      }catch (ParserConfigurationException e) {
	    	    throw new IOException("Failed to create settings document builder", e);
	      }
	      Document doc = builder.newDocument();
	    	  //System.out.println(userNode);
	    	  System.out.println(jsonNode);
	      
	      Element root = doc.createElement("root");
	      doc.appendChild(root);
//	      Element userdetails=doc.createElement("userdetails");
//	      root.appendChild(userdetails);
	    
	      Element postid =doc.createElement("postid");
	      postid.setTextContent(jsonNode.get("postid").asText());
	      root.appendChild(postid);
	   
	      Element id =doc.createElement("id");
	      id.setTextContent(jsonNode.get("id").asText());
	      root.appendChild(id);
	      
	      Element name =doc.createElement("name");
	      name.setTextContent(jsonNode.get("name").asText());
	      root.appendChild(name);
	      
	      Element email =doc.createElement("email");
	      email.setTextContent(jsonNode.get("email").asText());
	      root.appendChild(email);
	      
	      Element body =doc.createElement("body");
	      body.setTextContent(jsonNode.get("body").asText());
	      root.appendChild(body);
	    
	      TransformerFactory transformerFactory = TransformerFactory.newInstance();
	      Transformer transf = transformerFactory.newTransformer();

	      transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	      transf.setOutputProperty(OutputKeys.INDENT, "yes");
	      transf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

	      DOMSource source = new DOMSource(doc);

	      File myFile = new File("src/main/resources/users.xml");

	      StreamResult console = new StreamResult(System.out);
	      StreamResult file = new StreamResult(myFile);

	      transf.transform(source, console);
	      transf.transform(source, file);
	  
  }

	
}

